package com.dbc.pokesuits.client;

import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="pokesuits-base", url="https://dados-pessoais-vemserdbc.herokuapp.com")
@Headers("Content-Type: application/json")
public interface PokemonBaseClient {

    @RequestLine("GET /get-by-id")
    PokemonBaseDTO get(@Param("id_pokemon_base")Integer idPokemonBase);
}
