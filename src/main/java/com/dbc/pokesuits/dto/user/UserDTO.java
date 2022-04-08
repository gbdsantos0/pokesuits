package com.dbc.pokesuits.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO{
	@ApiModelProperty(value = "ID do User")
	private Integer id;
	@ApiModelProperty(value = "Nome do Usuário")
	private String nome;
	@ApiModelProperty(value = "Email Do Usuário")
	private String email;
	@ApiModelProperty(value = "Username do Usuário")
	private String username;
}
