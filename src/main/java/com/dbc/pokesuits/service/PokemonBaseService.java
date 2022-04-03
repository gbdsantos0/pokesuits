package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.repository.PokemonBaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonBaseService {
    private final PokemonBaseRepository pokemonBaseRepository;
    private final ObjectMapper objectMapper;

    public PokemonBaseDTO getById(Integer idPokemonBase){
        return objectMapper.convertValue(pokemonBaseRepository.getById(idPokemonBase),PokemonBaseDTO.class);
    }
}
