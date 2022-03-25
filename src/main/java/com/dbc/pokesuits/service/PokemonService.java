package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.entity.PokemonEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	public List<PokemonDTO> listarPokemons() {
		return pokemonRepository.findAll()
				.stream()
				.map(pokemon -> objectMapper.convertValue(pokemon, PokemonDTO.class))
				.collect(Collectors.toList());
	}

	public PokemonDTO adicionarPokemon(PokemonCreateDTO createDTO) {
		
		PokemonEntity PokemonConvertido = objectMapper.convertValue(createDTO, PokemonEntity.class);
		
		PokemonEntity pokemonAtualizado = pokemonRepository.save(PokemonConvertido);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		
		return pokemonDTO;
	}

	public PokemonDTO removerPokemon(int id) throws RegraDeNegocioException {
		
		PokemonEntity pokemonRemovido = pokemonRepository.getById(id);
		
		pokemonRepository.deleteById(id);
				
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonRemovido, PokemonDTO.class);
		
		return pokemonDTO;
	}

	public PokemonDTO editarPokemon(PokemonCreateDTO createDTO, Integer id) throws RegraDeNegocioException {
		PokemonEntity PokemonConvertido = objectMapper.convertValue(createDTO, PokemonEntity.class);
		
		PokemonEntity pokemonAtualizado = pokemonRepository.save(PokemonConvertido);
		
		PokemonDTO pokemonDTO = objectMapper.convertValue(pokemonAtualizado, PokemonDTO.class);
		
		return pokemonDTO;
	}
}
