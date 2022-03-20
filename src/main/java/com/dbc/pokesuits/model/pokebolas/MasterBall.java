package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.model.entity.Pokemon;
import com.dbc.pokesuits.model.interfaces.Pokebola;

public class MasterBall implements Pokebola {
    //sempre retorna 100% de chance
    @Override
    public Double calcularChance(PokemonCreateDTO pokemon){
        return 100.0;
    }
}
