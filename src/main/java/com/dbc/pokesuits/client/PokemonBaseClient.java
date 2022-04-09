package com.dbc.pokesuits.client;

import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="pokemonbase", url="https://pokesuits-base.herokuapp.com")
@Headers("Content-Type: application/json")
public interface PokemonBaseClient {

    /*@RequestLine("GET /get-by-id")
    PokemonBaseDTO get(@Param("id_pokemon_base")Integer idPokemonBase);*/
    @RequestLine("GET /pokemonbase/todos-pokemonbase")
    List<PokemonBaseDTO> todosPokemonBase();
}
