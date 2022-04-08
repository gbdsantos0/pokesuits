package com.dbc.pokesuits.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dbc.pokesuits.dto.pokemon.PokemonEditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.entity.PokemonEntity;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PokemonService {

	private final PokemonRepository pokemonRepository;
	private final UserService userService;
    private final MochilaService mochilaService;
	private final ObjectMapper objectMapper;
	
	public Page<PokemonDTO> listarPokemonsPorUser(Integer pagina, Integer user) throws RegraDeNegocioException {
		log.info("Chamado metodo listar Pokemons;");
		
		Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10, Sort.by("idPokemon").descending());
		
		 TreinadorEntity treinador = userService.getById(user).getTreinador();
		 if(treinador == null )throw new RegraDeNegocioException("O treinador não foi criado");
		 
		 MochilaEntity mochila = treinador.getMochila();
		 
		 if(mochila == null )throw new RegraDeNegocioException("A mochila não foi criada");
		
		List<PokemonDTO> collect = mochila.getPokemons()
				.stream()
				.map(pokemon -> {
					PokemonDTO pokemonDTO = objectMapper.convertValue(pokemon, PokemonDTO.class);
					pokemonDTO.setIdMochila(pokemon.getMochilaPokemon().getIdMochila());
					if(pokemon.getNome() == null)pokemonDTO.setNome("Não Nomeado");
					return pokemonDTO;
				}).collect(Collectors.toList());
		
		return new PageImpl<>(collect, pageable, collect.size());
	}
	
	public PokemonDTO adicionarPokemon(PokemonCreateDTO createDTO) throws RegraDeNegocioException {
		log.info("Chamado metodo adicionar Pokemon;");
		
		PokemonEntity PokemonConvertido = objectMapper.convertValue(createDTO, PokemonEntity.class);
		PokemonConvertido.setMochilaPokemon(mochilaService.getById(createDTO.getIdMochila()));
		
		PokemonEntity pokemonAtualizado = pokemonRepository.save(PokemonConvertido);
		
		log.info("Persistido o Pokemon de ID: "+ PokemonConvertido.getIdPokemon());
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		pokemonDTO.setIdMochila(pokemonAtualizado.getMochilaPokemon().getIdMochila());
		if(pokemonDTO.getNome() == null)pokemonDTO.setNome("Não Nomeado");
		
		return pokemonDTO;
	}

	public PokemonDTO removerPokemon(Integer id) throws RegraDeNegocioException {
		log.info("Chamado metodo remover Pokemon;");
		
		PokemonEntity pokemonRemovido = getById(id);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonRemovido, PokemonDTO.class);
		pokemonDTO.setIdMochila(pokemonRemovido.getMochilaPokemon().getIdMochila());
		if(pokemonDTO.getNome() == null)pokemonDTO.setNome("Não Nomeado");
		
		pokemonRepository.deleteById(id);
		
		log.info("Persistido as mudanças no Pokemon de ID: "+ id);
		
		return pokemonDTO;
	}
	
	public void removerPokemonPorUserLogado(Integer idUser, Integer idPokemon) throws RegraDeNegocioException {
		log.info("Chamado metodo remover Pokemon;");
		
		MochilaEntity mochilaPeloIdUser = mochilaService.getMochilaPeloIdUser(idUser);
		
		PokemonEntity pokemonRemovido = mochilaPeloIdUser.getPokemons()
				.stream()
				.filter(p -> p.getIdPokemon().equals(idPokemon))
				.findFirst()
				.orElseThrow(()->new RegraDeNegocioException("Pokemon com o id passado não existe"));
		Integer idPokemon2 = pokemonRemovido.getIdPokemon();
		
		mochilaPeloIdUser.getPokemons().remove(pokemonRemovido);
		
		pokemonRepository.deleteById(idPokemon2);
		
		log.info("Persistido as mudanças no Pokemon de ID: " + idPokemon);
		
	}
	
	public PokemonDTO editarPokemon(PokemonEditDTO createDTO, Integer idUser, Integer idPokemon) throws RegraDeNegocioException {
		log.info("Chamado metodo editarPokemon;");
		
		PokemonEntity byId = getPokemonsByIdUser(idUser)
				.stream()
				.filter(p -> p.getIdPokemon().equals(idPokemon))
				.findFirst()
				.orElseThrow(()->new RegraDeNegocioException("Pokemon com o id passado não existe"));

		byId.setLevel(createDTO.getLevel()==null? byId.getLevel() : createDTO.getLevel());
		byId.setNome(createDTO.getNome()==null? byId.getNome() : createDTO.getNome());


		if(!(createDTO.getLevel()==null && createDTO.getNome()==null)){
			PokemonEntity pokemonAtualizado = pokemonRepository.save(byId);
			log.info("Persistido as mudanças no Pokemon de ID: "+ byId.getIdPokemon());
			PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
			pokemonDTO.setIdMochila(pokemonAtualizado.getMochilaPokemon().getIdMochila());
			if(pokemonDTO.getNome() == null)pokemonDTO.setNome("Não Nomeado");

			return pokemonDTO;
		}
		return null;
	}
	
	public PokemonEntity getById(Integer idPokemon) throws RegraDeNegocioException{
		log.info("Chamado metodo getById do Pokemon;");
		return pokemonRepository.findById(idPokemon).orElseThrow(()-> new RegraDeNegocioException("O ID do Pokemon Passado não existe"));
	}
	
	public List<PokemonEntity> getPokemonsByIdUser(Integer idUser) throws RegraDeNegocioException{
		log.info("Chamado metodo getPokemonsByIdUser do Pokemon;");
		return new ArrayList<>(mochilaService.getMochilaPeloIdUser(idUser).getPokemons());
	}
}
