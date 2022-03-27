package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonGeradoDTO;
import com.dbc.pokesuits.enums.TipoPokemon;

public class NetBall implements Pokebola {
    //Testa se o pokemon é do tipo WATER ou BUG para retornar 3x chance
    @Override
    public Double calcularChance(PokemonGeradoDTO pokemon){
        if(pokemon.getTipo1()==TipoPokemon.WATER||pokemon.getTipo1()==TipoPokemon.BUG||pokemon.getTipo2()==TipoPokemon.WATER||pokemon.getTipo2()==TipoPokemon.BUG){
            return 3.0 * pokemon.getDificuldade().getChance();
        }
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
