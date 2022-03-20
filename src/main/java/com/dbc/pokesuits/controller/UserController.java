package com.dbc.pokesuits.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/User")
@Validated
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Devolve uma lista de Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping
	public List<UserDTO> ListarPokemons(){
		return userService.ListarUsers();
	}
	@ApiOperation(value = "Recebe um User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "adiciona um user e o devolve"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PostMapping
	public UserDTO AdicionarPokemon(@Valid @RequestBody UserCreateDTO pokemon){
		return userService.AdicionarUser(pokemon);
	}
	
	@ApiOperation(value = "Remove um Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove um Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/{id}")
	public void RemoverPokemon(@PathVariable("id") int id) throws RegraDeNegocioException {
		userService.RemoverUser(id);
	}
	@ApiOperation(value = "Recebe um User e um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza e Devolve um Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PutMapping
	public UserDTO editarPokemon(@Valid @RequestBody UserCreateDTO createDTO, Integer id) throws RegraDeNegocioException {
		return userService.editarUser(createDTO, id);
	}
	
}
