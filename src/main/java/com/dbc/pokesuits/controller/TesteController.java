package com.dbc.pokesuits.controller;

import com.dbc.pokesuits.client.PokemonBaseClient;
import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.model.PokemonBase;
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

    @GetMapping
    public List<PokemonBaseDTO> getAll(){
        return client.todosPokemonBase();
    }
}
