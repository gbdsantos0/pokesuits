package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.model.interfaces.Pokebola;

public class GreatBall implements Pokebola {
    //multiplicador 1.5x
    @Override
    public Double calcularChance(PokemonCreateDTO pokemon){
        return 1.5 * pokemon.getDificuldade().getChance();
    }
}
