package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.model.entity.Pokemon;
import com.dbc.pokesuits.model.interfaces.Pokebola;

public class HeavyBall implements Pokebola {
        //Retorna multiplicadores conforme o peso do pokemon
        @Override
        public Double calcularChance(PokemonCreateDTO pokemon){
            if(pokemon.getDificuldade().getChance()<0){
                throw new IllegalStateException("Peso negativo: " + pokemon.getPeso());
            }

            switch ((int)(pokemon.getPeso()/102.35)){
                case 0:
                    return 0.75 * pokemon.getDificuldade().getChance();
                case 1:
                    return 0.75 * pokemon.getDificuldade().getChance();
                case 2:
                    return 1.0 * pokemon.getDificuldade().getChance();
                case 3:
                    return 1.5 * pokemon.getDificuldade().getChance();
                case 4:
                    return 2.0 * pokemon.getDificuldade().getChance();
                default:
                    return 2.0 * pokemon.getDificuldade().getChance();
            }
        }
}
