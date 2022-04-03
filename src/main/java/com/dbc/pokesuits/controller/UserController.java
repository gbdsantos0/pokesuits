package com.dbc.pokesuits.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.dto.user.UserEditDto;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/user")
@Validated
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Devolve o User Logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve um User"),
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
            @ApiResponse(code = 200, message = "Remove um User"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping
	public void removerUserLogado() throws RegraDeNegocioException {
		Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		userService.removerUser(Integer.parseInt((String) userb));
	}
	
	@ApiOperation(value = "Recebe um Email e um Username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza e Devolve um User"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PutMapping
	public UserDTO editarUserLogado(@Valid @RequestBody UserEditDto editDTO) throws RegraDeNegocioException {
		Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		return userService.editarUser(editDTO, Integer.parseInt((String) userb));
	}
	
}