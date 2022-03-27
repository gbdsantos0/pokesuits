package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonGeradoDTO;

public class GreatBall implements Pokebola {
    //multiplicador 1.5x
    @Override
    public Double calcularChance(PokemonGeradoDTO pokemon){
        return 1.5 * pokemon.getDificuldade().getChance();
    }
}
