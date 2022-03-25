package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.model.interfaces.Pokebola;

public class PokeBall implements Pokebola {
    //pokebola com multiplicador base
    @Override
    public Double calcularChance(PokemonCreateDTO pokemon){
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
