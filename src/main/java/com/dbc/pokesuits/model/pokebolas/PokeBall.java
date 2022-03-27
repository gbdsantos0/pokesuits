package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonGeradoDTO;

public class PokeBall implements Pokebola {
    //pokebola com multiplicador base
    @Override
    public Double calcularChance(PokemonGeradoDTO pokemon){
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
