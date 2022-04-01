package com.dbc.pokesuits.dto.user;

import java.util.List;

import com.dbc.pokesuits.config.validation.ValidPassword;
import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO{
	@ApiModelProperty(value = "ID do User")
	private Integer id;
	@ApiModelProperty(value = "Nome do Usuário")
	private String nome;
	@ApiModelProperty(value = "Email Do Usuário")
	private String email;
	@ApiModelProperty(value = "Username do Usuário")
	private String username;
	@ApiModelProperty(value = "token de login")
	private String token;

}
