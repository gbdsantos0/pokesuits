package com.dbc.pokesuits.dto.mochila;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MochilaCreateDTO {

    @ApiModelProperty(value="quantidade de Great Balls")
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeGreatBalls;

    @ApiModelProperty(value="quantidade de Heavy Balls")
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeHeavyBalls;

    @ApiModelProperty(value="quantidade de Master Balls")
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeMasterBalls;

    @ApiModelProperty(value="quantidade de Net Balls")
    @NotNull
    @Size(min=0, max=99)
    private int quantidadeNetBalls;

    @ApiModelProperty(value="quantidade dePoke Balls")
    @NotNull
    @Size(min=0, max=99)
    private int quantidadePokeBalls;
}
