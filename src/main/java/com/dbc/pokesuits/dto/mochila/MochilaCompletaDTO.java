package com.dbc.pokesuits.dto.mochila;

import java.util.List;

import com.dbc.pokesuits.dto.pokemon.PokemonDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class MochilaCompletaDTO extends MochilaDTO {

//    private TreinadorDTO treinador;
	@ApiModelProperty(value="Lista de pokemons salvos na mochila")
    private List<PokemonDTO> pokemons;
}
