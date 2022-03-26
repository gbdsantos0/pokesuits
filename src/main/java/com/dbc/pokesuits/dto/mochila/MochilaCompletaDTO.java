package com.dbc.pokesuits.dto.mochila;

import java.util.List;

import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MochilaCompletaDTO extends MochilaDTO {

    private TreinadorDTO treinador;
    private List<PokemonDTO> pokemons;
}
