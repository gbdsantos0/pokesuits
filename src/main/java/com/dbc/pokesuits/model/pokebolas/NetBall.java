package com.dbc.pokesuits.model.pokebolas;


import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.model.entity.Pokemon;
import com.dbc.pokesuits.model.interfaces.Pokebola;

public class NetBall implements Pokebola {
    //Testa se o pokemon é do tipo WATER ou BUG para retornar 3x chance
    @Override
    public Double calcularChance(PokemonCreateDTO pokemon){
        if(pokemon.getTipo1()==TipoPokemon.WATER||pokemon.getTipo1()==TipoPokemon.BUG||pokemon.getTipo2()==TipoPokemon.WATER||pokemon.getTipo2()==TipoPokemon.BUG){
            return 3.0 * pokemon.getDificuldade().getChance();
        }
        return 1.0 * pokemon.getDificuldade().getChance();
    }
}
