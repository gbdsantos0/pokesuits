package com.dbc.pokesuits.dto.mochila;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MochilaCreateDTO {

    @ApiModelProperty(value="quantidade de GreatBalls")
    @NotNull
    @Min(0)
    @Max(99)
    private int quantidadeGreatBalls;

    @ApiModelProperty(value="quantidade de HeavyBalls")
    @NotNull
    @Min(0)
    @Max(99)
    private int quantidadeHeavyBalls;

    @ApiModelProperty(value="quantidade de MasterBalls")
    @NotNull
    @Min(0)
    @Max(99)
    private int quantidadeMasterBalls;

    @ApiModelProperty(value="quantidade de NetBalls")
    @NotNull
    @Min(0)
    @Max(99)
    private int quantidadeNetBalls;

    @ApiModelProperty(value="quantidade de PokeBalls")
    @NotNull
    @Min(0)
    @Max(99)
    private int quantidadePokeBalls;
}
