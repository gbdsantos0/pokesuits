package com.dbc.pokesuits.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.PokemonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/pokemon")
@Validated
public class PokemonController {
	@Autowired
	private PokemonService pokemonService;
	
	@ApiOperation(value = "Devolve uma lista de Pokemons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Pokemons"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping
	public Page<PokemonDTO> ListarPokemons(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina){
		return pokemonService.listarPokemons(pagina);
	}
	
	@ApiOperation(value = "Recebe um Pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastra um Pokemons e o Devolve "),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PostMapping
	public PokemonDTO AdicionarPokemon(@Valid @RequestBody PokemonCreateDTO pokemon) throws RegraDeNegocioException{
		return pokemonService.adicionarPokemon(pokemon);
	}
	
	
	@ApiOperation(value = "Recebe um id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove o Pokemons com o id passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/{id_pokemon}")
	public PokemonDTO RemoverPokemon(@PathVariable("id_pokemon") Integer idPokemon) throws RegraDeNegocioException {
		return pokemonService.removerPokemon(idPokemon);
	}
	
	@ApiOperation(value = "Recebe um id e um pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "substitui os dados do pokemon com id passado pelos dados do pokemon passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PutMapping
	public PokemonDTO editarPokemon(@Valid @RequestBody PokemonCreateDTO createDTO, @RequestParam(value = "id_pokemon") Integer idPokemon) throws RegraDeNegocioException {
		return pokemonService.editarPokemon(createDTO, idPokemon);
	}
	
	
}
