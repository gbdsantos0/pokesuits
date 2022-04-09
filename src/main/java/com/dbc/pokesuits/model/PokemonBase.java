package com.dbc.pokesuits.model;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PokemonBase {
    private Integer id;
    private String racaPokemon;
    private Double pesoMinimo;
    private Double pesoMaximo;
    private Double porcentagemMacho;
    private Integer levelMinimo;
    private Dificuldades dificuldade;
    private TipoPokemon tipo1;
    private TipoPokemon tipo2;
    private Raridades raridade;
}
