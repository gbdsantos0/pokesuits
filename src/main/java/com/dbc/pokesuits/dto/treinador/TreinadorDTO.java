package com.dbc.pokesuits.dto.treinador;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TreinadorDTO extends TreinadorCreateDTO {
    @ApiModelProperty(value = "id do treinador")
    private Integer idTreinador;
}
