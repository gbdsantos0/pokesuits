package com.dbc.pokesuits.model.pokebolas;

import com.dbc.pokesuits.dto.pokemon.PokemonGeradoDTO;

public interface Pokebola {
    //Deve retornar um valor de 0 a 100%(pode passar, mas nao tem problema pois o calculo nao gera valores maiores que 100 na comparacao)
    public Double calcularChance(PokemonGeradoDTO pokemon);
}
