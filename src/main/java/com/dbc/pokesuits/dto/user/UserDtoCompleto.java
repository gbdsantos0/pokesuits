package com.dbc.pokesuits.dto.user;

import com.dbc.pokesuits.dto.mochila.MochilaCompletaDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class UserDtoCompleto extends UserDTO{
	
	@ApiModelProperty(value = "Password do Usuário")
	private String password;
	@ApiModelProperty(value = "Mochila Completa do Usuário")
	private MochilaCompletaDTO mochilaCompleta;
	
}
