package com.dbc.pokesuits.service;

import com.dbc.pokesuits.client.PokemonBaseClient;
import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.model.PokemonBase;
import com.dbc.pokesuits.repository.PokemonBaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonBaseService {
    private final PokemonBaseRepository pokemonBaseRepository;
    private final ObjectMapper objectMapper;
    private final PokemonBaseClient pokemonBaseClient;

    public PokemonBaseDTO getById(Integer idPokemonBase){

        PokemonBase pokemonBase = pokemonBaseRepository.getById(idPokemonBase);

        return objectMapper.convertValue(pokemonBase,PokemonBaseDTO.class);
    }

    public List<PokemonBaseDTO> getAll(){
        return pokemonBaseRepository.listAll().stream().map(pb->objectMapper.convertValue(pb,PokemonBaseDTO.class)).collect(Collectors.toList());
    }

    public void repopulateDB() throws Exception{
        List<PokemonBase> listaExterna = pokemonBaseClient.todosPokemonBase()
        		.stream()
        		.map(pb -> objectMapper.convertValue(pb, PokemonBase.class))
        		.collect(Collectors.toList());
        if(listaExterna.isEmpty()){
            throw new RegraDeNegocioException("Lista externa vazia");
        }
        pokemonBaseRepository.setListaPokemonBase(listaExterna);
    }

    public List<PokemonBaseDTO> getAllExternal(){
        return pokemonBaseClient.todosPokemonBase();
    }
}

