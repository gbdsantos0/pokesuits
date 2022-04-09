package com.dbc.pokesuits.controller;

import com.dbc.pokesuits.client.PokemonBaseClient;
import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.model.PokemonBase;
import com.dbc.pokesuits.service.PokemonBaseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testes")
@RequiredArgsConstructor
public class TesteController {
    private final PokemonBaseClient client;
    private final PokemonBaseService pokemonBaseService;

    @GetMapping
    public List<PokemonBaseDTO> getAll(){
        return client.todosPokemonBase();
    }

    @GetMapping("/repopular")
    public void repop(){
        pokemonBaseService.repopulateDB();
    }

    @GetMapping("pokemons-locais")
    public List<PokemonBaseDTO> getLocalAll(){
        return pokemonBaseService.getAll();
    }
}
