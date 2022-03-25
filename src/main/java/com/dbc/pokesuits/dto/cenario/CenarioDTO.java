package com.dbc.pokesuits.dto.cenario;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.dbc.pokesuits.enums.TiposTerreno;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CenarioDTO {
    @ApiModelProperty(value="ID do cenário")
    private Integer idCenario;
    @ApiModelProperty(value="Tipo do terreno do cenário")
    @NotNull
    private TiposTerreno terreno;
    @ApiModelProperty(value="Level médio dos pokemons encontrados no cenário -4/+4")
    private Integer levelMedio;
    @ApiModelProperty(value="Lista dos IDs dos pokemons(Base) disponíveis no cenário")
    private List<Integer> idPokemonsDisponiveis;
}
