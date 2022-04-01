package com.dbc.pokesuits.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/user-logado")
@Validated
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Devolve o Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping
	public UserDTO devolverUserLogado() throws Exception{
		Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		return userService.userLogado(Integer.parseInt((String) userb));
	}
	
	@ApiOperation(value = "Remove o User logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove um Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping
	public void removerUserLogado(@AuthenticationPrincipal UserEntity user) throws RegraDeNegocioException {
		userService.removerUser(user.getId());
	}
	
	@ApiOperation(value = "Recebe um User e um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza e Devolve um Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PutMapping
	public UserDTO editarUserLogado(@Valid @RequestBody UserCreateDTO createDTO,
					@AuthenticationPrincipal UserEntity user) throws RegraDeNegocioException {
		return userService.editarUser(createDTO, user.getId());
	}
	
}