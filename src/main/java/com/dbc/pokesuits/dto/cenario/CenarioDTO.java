package com.dbc.pokesuits.dto.cenario;

import com.dbc.pokesuits.enums.TiposTerreno;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
