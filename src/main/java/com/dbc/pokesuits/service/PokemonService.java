package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.entity.PokemonEntity;
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
	
//	public Page<PokemonDTO> listarPokemons(Integer pagina) {
//		log.info("Chamado metodo listarPokemons;");
//		
//		Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 20, Sort.by("idPokemon"));
//		
//		List<PokemonDTO> collect = pokemonRepository.findAll(pageable)
//				.stream()
//				.map(pokemon -> {
//					PokemonDTO pokemonDTO = objectMapper.convertValue(pokemon, PokemonDTO.class);
//					pokemonDTO.setIdMochila(pokemon.getMochilaPokemon().getIdMochila());
//					if(pokemon.getNome() == null)pokemonDTO.setNome("Não Nomeado");
//					return pokemonDTO;
//				}).collect(Collectors.toList());
//		
//		return new PageImpl<>(collect);
//	}
	
	public Page<PokemonDTO> listarPokemonsPorUser(Integer pagina, Integer user) throws RegraDeNegocioException {
		log.info("Chamado metodo listarPokemons;");
		
		Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10, Sort.by("idPokemon"));
		
		List<PokemonDTO> collect = userService.getById(user).getTreinador().getMochila().getPokemons()
				.stream()
				.map(pokemon -> {
					PokemonDTO pokemonDTO = objectMapper.convertValue(pokemon, PokemonDTO.class);
					pokemonDTO.setIdMochila(pokemon.getMochilaPokemon().getIdMochila());
					if(pokemon.getNome() == null)pokemonDTO.setNome("Não Nomeado");
					return pokemonDTO;
				}).collect(Collectors.toList());
		
		return new PageImpl<>(collect, pageable, collect.size());
	}
	
	public List<PokemonDTO> listarPokemonsPorUserSemPage(Integer user) throws RegraDeNegocioException {
		log.info("Chamado metodo listarPokemons;");
		
		return userService.getById(user).getTreinador().getMochila().getPokemons()
				.stream()
				.map(pokemon -> {
					PokemonDTO pokemonDTO = objectMapper.convertValue(pokemon, PokemonDTO.class);
					pokemonDTO.setIdMochila(pokemon.getMochilaPokemon().getIdMochila());
					if(pokemon.getNome() == null)pokemonDTO.setNome("Não Nomeado");
					return pokemonDTO;
				}).collect(Collectors.toList());
	}
	
	public PokemonDTO adicionarPokemon(PokemonCreateDTO createDTO) throws RegraDeNegocioException {
		log.info("Chamado metodo adicionarPokemon;");
		
		PokemonEntity PokemonConvertido = objectMapper.convertValue(createDTO, PokemonEntity.class);
		PokemonConvertido.setMochilaPokemon(mochilaService.getById(createDTO.getIdMochila()));
		
		PokemonEntity pokemonAtualizado = pokemonRepository.save(PokemonConvertido);
		
		log.info("Persistido o Pokemon de ID: ", PokemonConvertido.getIdPokemon());
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		pokemonDTO.setIdMochila(pokemonAtualizado.getMochilaPokemon().getIdMochila());
		if(pokemonDTO.getNome() == null)pokemonDTO.setNome("Não Nomeado");
		
		return pokemonDTO;
	}

	public PokemonDTO removerPokemon(Integer id) throws RegraDeNegocioException {
		log.info("Chamado metodo removerPokemon;");
		
		PokemonEntity pokemonRemovido = getById(id);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonRemovido, PokemonDTO.class);
		pokemonDTO.setIdMochila(pokemonRemovido.getMochilaPokemon().getIdMochila());
		if(pokemonDTO.getNome() == null)pokemonDTO.setNome("Não Nomeado");
		
		pokemonRepository.deleteById(id);
		
		log.info("Persistido as mudanças no Pokemon de ID: ", id);
		
		return pokemonDTO;
	}

	public PokemonDTO editarPokemon(PokemonCreateDTO createDTO, Integer idUser, Integer idPokemon) throws RegraDeNegocioException {
		log.info("Chamado metodo editarPokemon;");
		
		PokemonEntity byId = getPokemonsByIdUser(idUser)
				.stream()
				.filter(p -> p.getIdPokemon()==idPokemon)
				.findFirst()
				.orElseThrow(()->new RegraDeNegocioException("Pokemon com o id passado não existe"));
		
		byId.setLevel(createDTO.getLevel());
		byId.setNome(createDTO.getNome());
		byId.setPeso(createDTO.getPeso());
		
		PokemonEntity pokemonAtualizado = pokemonRepository.save(byId);
		
		log.info("Persistido as mudanças no Pokemon de ID: ", byId.getIdPokemon());
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		pokemonDTO.setIdMochila(pokemonAtualizado.getMochilaPokemon().getIdMochila());
		if(pokemonDTO.getNome() == null)pokemonDTO.setNome("Não Nomeado");
		
		return pokemonDTO;
	}
	
	public PokemonEntity getById(Integer idPokemon) throws RegraDeNegocioException{
		log.info("Chamado metodo getById do Pokemon;");
		return pokemonRepository.findById(idPokemon).orElseThrow(()-> new RegraDeNegocioException("O ID do Pokemon Passado não existe"));
	}
	
	public List<PokemonEntity> getPokemonsByIdUser(Integer idUser) throws RegraDeNegocioException{
		log.info("Chamado metodo getPokemonsByIdUser do Pokemon;");
//		return mochilaService.getMochilaCompleta(idUser);	
		return null;
	}
}
