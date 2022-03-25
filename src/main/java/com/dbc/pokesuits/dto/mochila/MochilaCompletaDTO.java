package com.dbc.pokesuits.dto.mochila;

import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.entity.PokemonEntity;
import com.dbc.pokesuits.entity.TreinadorEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MochilaCompletaDTO extends MochilaDTO {

//    private TreinadorDTO treinador;
    private List<PokemonDTO> pokemons;
}
