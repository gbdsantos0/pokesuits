package com.dbc.pokesuits.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;

import feign.Headers;
import feign.RequestLine;

@FeignClient(value="pokemonbase", url="https://pokesuits-base.herokuapp.com")
@Headers("Content-Type: application/json")
public interface PokemonBaseClient {

    @RequestLine("GET /pokemonbase/todos-pokemonbase")
    List<PokemonBaseDTO> todosPokemonBase();
}

