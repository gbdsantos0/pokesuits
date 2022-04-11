package com.dbc.pokesuits.controller.admin;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.MochilaService;
import com.dbc.pokesuits.service.PokemonBaseService;
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
	private final MochilaService mochilaService;
	private final PokemonBaseService pokemonBaseService;
	
	//inicio da parte de user do admin
	@ApiOperation(value = "Devolve uma lista de Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping("/listar-users")
	public Page<UserDTO> listarUser(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina){
		return userService.listarUsers(pagina);
	}
	
	@ApiOperation(value = "Remove um User Pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove um User"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/deletar-user/{id_user}")
	public void removerUser(@PathVariable("id_user") int idUser) throws RegraDeNegocioException {
		userService.removerUser(idUser);
	}
	
	//fim da parte de user do admin
	
	//começo da parte do trinador do admin
	
	@ApiOperation(value = "Retorna um treinador pelo ID do User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador retornado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/treinador/{id_user}")
    public TreinadorDTO getTreinadorByIdUser(@PathVariable("id_user") Integer idUser) throws Exception{
        return treinadorService.getTreinadorDTOByIdUser(idUser);
    }
	
	@ApiOperation(value = "Deleta e retorna um treinador pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador removido do banco com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/deletar-treinador/{id_treinador}")
    public TreinadorDTO deleteTreinadorById(@PathVariable("id_treinador") Integer id) throws Exception{
        return treinadorService.delete(id);
    }
	
  //fim da parte do trinador do admin
	
  //começo da parte da mochila do admin 
  //começo da parte do pokemon do admin(faz parte da mochila) 
    
	@ApiOperation(value = "Devolve uma lista dos Pokemons de um User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Pokemons"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping("/listar-pokemons-por-user/{id_user}")
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
	@DeleteMapping("/deletar-pokemon/{id_pokemon}")
	public PokemonDTO removerPokemon(@PathVariable("id_pokemon") Integer idPokemon) throws RegraDeNegocioException {
		return pokemonService.removerPokemon(idPokemon);
	}
	
	//fim da parte do pokemon do admin (faz parte da mochila) 
	
	@ApiOperation(value = "Devolve a mochila de um User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma mochila"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping("/mochila/{id_user}")
	public MochilaDTO getMochilaByIdUser(@RequestParam(value = "id_user") Integer idUser) throws RegraDeNegocioException{
		return mochilaService.getMochilaLogado(idUser);
	}
	
	@ApiOperation(value = "Recebe Um ID de Mochila")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove a mochila com o id passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping("/deletar-mochila/{id_mochila}")
	public void removerMochila(@PathVariable("id_mochila") Integer idMochila) throws RegraDeNegocioException {
		mochilaService.deletarMochilaPeloId(idMochila);
	}
	//fim da parte da mochila do admin

	//parte de repopular base dos pokemons
	@ApiOperation(value = "Repopula o jogo com os pokemons da API externa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Repopulado com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
	})
	@PostConstruct
	@GetMapping("/repopular")
	public void repop() throws Exception{
		pokemonBaseService.repopulateDB();
	}

	@ApiOperation(value = "Lista os pokemons base no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista devolvida com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
	})
	@GetMapping("pokemons-locais")
	public List<PokemonBaseDTO> getLocalAll(){
		return pokemonBaseService.getAll();
	}

	@ApiOperation(value = "Lista os pokemons base no sistema externo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista devolvida com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
	})
	@GetMapping("pokemons-api-externa")
	public List<PokemonBaseDTO> getAll(){
		return pokemonBaseService.getAllExternal();
	}
}
