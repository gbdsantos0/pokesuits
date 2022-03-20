package com.dbc.pokesuits.model.interfaces;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;

public interface Pokebola {
    //Deve retornar um valor de 0 a 100%(pode passar, mas nao tem problema pois o calculo nao gera valores maiores que 100 na comparacao)
    public Double calcularChance(PokemonCreateDTO pokemon);
}
