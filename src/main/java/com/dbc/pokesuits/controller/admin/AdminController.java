package com.dbc.pokesuits.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.PokemonService;
import com.dbc.pokesuits.service.TreinadorService;
import com.dbc.pokesuits.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {
	private final UserService userService;
	private final PokemonService pokemonService;
	private final TreinadorService treinadorService;
	
	//inicio da parte de user do admin
	@ApiOperation(value = "Devolve uma lista de Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping
	public Page<UserDTO> listarUser(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina){
		return userService.listarUsers(pagina);
	}
	
	@ApiOperation(value = "Remove um User Pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove um User"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/{id_user}")
	public void removerUser(@PathVariable("id_user") int idUser) throws RegraDeNegocioException {
		userService.removerUser(idUser);
	}
	
	//fim da parte de user do admin
	
	@ApiOperation(value = "Devolve uma lista dos Pokemons de um User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Pokemons"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping
	public Page<PokemonDTO> listarPokemons(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina, 
			@RequestParam(value = "id_user") Integer idUser) throws RegraDeNegocioException{
		return pokemonService.listarPokemonsPorUser(pagina, idUser);
	}
	
	@ApiOperation(value = "Recebe Um ID de Pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove o Pokemon com o id passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/{id_pokemon}")
	public PokemonDTO removerPokemon(@PathVariable("id_pokemon") Integer idPokemon) throws RegraDeNegocioException {
		return pokemonService.removerPokemon(idPokemon);
	}
	
	@ApiOperation(value = "Deleta e retorna um treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador removido do banco com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/{id_treinador}")
    public TreinadorDTO delete(@PathVariable("id_treinador") Integer id) throws Exception{
        return treinadorService.delete(id);
    }



    @ApiOperation(value = "Retorna um treinador pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador retornado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{id_treinador}")
    public TreinadorDTO getById(@PathVariable("id_treinador") Integer idTreinador) throws Exception{
        return treinadorService.getTreinadorDTOById(idTreinador);
    }
}
