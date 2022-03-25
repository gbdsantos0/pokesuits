package com.dbc.pokesuits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.repository.PokemonBaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonBaseService {
    @Autowired
    private PokemonBaseRepository pokemonBaseRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public PokemonBaseDTO getById(Integer idPokemonBase){
        return objectMapper.convertValue(pokemonBaseRepository.getById(idPokemonBase),PokemonBaseDTO.class);
    }
}
