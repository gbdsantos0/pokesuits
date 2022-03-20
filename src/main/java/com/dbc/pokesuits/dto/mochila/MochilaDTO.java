package com.dbc.pokesuits.dto.mochila;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MochilaDTO extends MochilaCreateDTO {
    @ApiModelProperty(value = "id da mochila")
    private int idMochila;
}
