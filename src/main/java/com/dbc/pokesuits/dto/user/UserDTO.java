package com.dbc.pokesuits.dto.user;

import java.util.ArrayList;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class UserDTO extends UserCreateDTO {
	@ApiModelProperty(value = "ID do User")
	private Integer id;
	@ApiModelProperty(value = "IDs dos Treinadores Pokemon")
	private ArrayList<TreinadorCreateDTO> treinadores;
	
}
