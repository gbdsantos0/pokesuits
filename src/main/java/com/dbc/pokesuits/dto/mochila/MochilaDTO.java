package com.dbc.pokesuits.dto.mochila;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MochilaDTO extends MochilaCreateDTO {
    @ApiModelProperty(value = "id da mochila")
    private Integer idMochila;
    @ApiModelProperty(value = "id do treinador")
    private Integer idTreinador;
}
