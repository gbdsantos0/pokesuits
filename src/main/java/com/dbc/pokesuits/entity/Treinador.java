package com.dbc.pokesuits.entity;

import javax.validation.constraints.NotNull;

import com.dbc.pokesuits.enums.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Treinador{

    private int idTreinador;
    private int idMochila;
    @NotNull
    private String nome;
    @NotNull
    private Utils sexo;
}

