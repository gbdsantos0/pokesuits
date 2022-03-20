package com.dbc.pokesuits.model.entity;

import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.model.entity.Entidade;
import com.dbc.pokesuits.model.objetos.Mochila;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Treinador{

    //a mochila guarda todos os pokemons do treinador

    private int idTreinador;
    private int idMochila;
    @NotNull
    private String nome;
    @NotNull
    private Utils sexo;
}

