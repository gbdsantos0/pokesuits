package com.dbc.pokesuits.dto.pokemon;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class PokemonDTO extends PokemonCreateDTO {
	@ApiModelProperty(name = "Id do Pokemon")
	private Long idPokemon;
}
