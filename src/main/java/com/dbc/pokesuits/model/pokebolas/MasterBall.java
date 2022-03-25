package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;

public class MasterBall implements Pokebola {
    //sempre retorna 100% de chance
    @Override
    public Double calcularChance(PokemonCreateDTO pokemon){
        return 100.0;
    }
}
