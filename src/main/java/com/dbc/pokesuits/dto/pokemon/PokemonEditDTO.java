package com.dbc.pokesuits.dto.pokemon;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PokemonEditDTO {
    @ApiModelProperty(value = "Apelido do Pokemon")
    private String nome;
    @ApiModelProperty(value = "Nivel do Pokemon")
    private Integer level;
}
