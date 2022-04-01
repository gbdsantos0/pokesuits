package com.dbc.pokesuits.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEditDto {
	
	@NotEmpty
	@ApiModelProperty(value = "Nome do Usuário")
	private String nome;
	@Email
	@ApiModelProperty(value = "Email Do Usuário")
	private String email;
	
}
