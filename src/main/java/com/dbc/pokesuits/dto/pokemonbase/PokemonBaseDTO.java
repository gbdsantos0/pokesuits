package com.dbc.pokesuits.dto.pokemonbase;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PokemonBaseDTO {
    @ApiModelProperty(value="Id do pokemon")
    private Integer id;
    @ApiModelProperty(value="Raça do pokemon")
    private String racaPokemon;
    @ApiModelProperty(value="Peso mínimo do pokemon")
    private Double pesoMinimo;
    @ApiModelProperty(value="Peso máximo do pokemon")
    private Double pesoMaximo;
    @ApiModelProperty(value="Chance do pokemon ser Macho")
    private Double porcentagemMacho;
    @ApiModelProperty(value="Nível mínimo para encontro do pokemon")
    private Integer levelMinimo;
    @ApiModelProperty(value="Dificuldade de captura do pokemon")
    private Dificuldades dificuldade;
    @ApiModelProperty(value="Primeiro tipo do pokemon")
    private TipoPokemon tipo1;
    @ApiModelProperty(value="Segundo tipo do pokemon(pode ser nulo)")
    private TipoPokemon tipo2;
    @ApiModelProperty(value="Raridade de encontro do pokemon")
    private Raridades raridade;
}
