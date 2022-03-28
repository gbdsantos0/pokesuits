package com.dbc.pokesuits.dto.pokemon;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.enums.Utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PokemonGeradoDTO {
    @ApiModelProperty(value = "Raça do Pokemon Ex.: Bulbassauro")
    private String racaPokemon;
    @ApiModelProperty(value = "Peso do Pokemon")
    private Double peso;
    @ApiModelProperty(value = "Sexo do Pokemon")
    private Utils sexo;
    @ApiModelProperty(value = "Nivel do Pokemon")
    private Integer level;
    @ApiModelProperty(value = "Nivel de Dificuldade de a Captura do Pokemon")
    private Dificuldades dificuldade;
    @ApiModelProperty(value = "Tipo de Pokemon Ex.: INSETO, ELÉTRICO ... ")
    private TipoPokemon tipo1;
    @ApiModelProperty(value = "Segundo Tipo do Pokemon")
    private TipoPokemon tipo2;
    @ApiModelProperty(value = "Chance De Aparição do Pokemon")
    private Raridades raridade;
}
