package com.dbc.pokesuits.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonEditDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.MochilaService;
import com.dbc.pokesuits.service.PokemonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/mochila")
@AllArgsConstructor
public class MochilaController {

    private MochilaService mochilaService;
    private PokemonService pokemonService;

    @ApiOperation(value = "Retorna a mochila do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retorna a mochila do usuário logado!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public MochilaDTO pegarMochila() throws Exception {
        Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.mochilaService.getMochilaLogado(Integer.parseInt((String) userb));
    }

    @ApiOperation(value = "Adiciona uma mochila ao treinador do uisuario logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastra uma mochila em um treinador e a devolve!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping
    @Validated
    public MochilaDTO criarMochila(@Valid @RequestBody MochilaCreateDTO mochila) throws Exception {
        Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mochilaService.createMochilaLogado(mochila, Integer.parseInt((String) userb));
    }

    @ApiOperation(value = "Retorna uma mochila com quantidade de pokebola atualizada!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizou a quantidade de pokebolas de uma mochila!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping
    public MochilaDTO adicionarPokebola(@RequestParam(value = "tipo_pokebola") String tipoPokebola,
                                        @RequestParam(value = "quantidade_adicionada") Integer quantidadeAdicionada) throws Exception {
        Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mochilaService.adicionarPokebola(Integer.parseInt((String) userb), tipoPokebola, quantidadeAdicionada);
    }

    @ApiOperation(value = "Deleta a mochila do usuário logado!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deletou a mochila do usuário logado!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping
    public void deletaMochilaLogado() throws Exception {
        Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.mochilaService.deletarMochilaLogado(Integer.parseInt((String) userb));
    }
    
    @ApiOperation(value = "Recebe um id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove o Pokemons com o id passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/remover-pokemon/{id}")
	public void removerPokemon(@PathVariable("id") int id) throws RegraDeNegocioException {
    	Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pokemonService.removerPokemonPorUserLogado(Integer.parseInt((String) userb), id);
	}
	
	@ApiOperation(value = "Recebe um id e um pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "substitui os dados do pokemon com id passado pelos dados do pokemon passado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PutMapping("/editar-pokemon")
	public PokemonDTO editarPokemon(@Valid @RequestBody PokemonEditDTO createDTO, Integer id) throws Exception {
		Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return pokemonService.editarPokemon(createDTO, Integer.parseInt((String)userb), id);
	}
    
	@ApiOperation(value = "Devolve os Pokemons do User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devolve uma lista de Pokemons"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@GetMapping("/listar-pokemons")
	public Page<PokemonDTO> listarPokemons(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina) throws Exception{
		Object userb = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return pokemonService.listarPokemonsPorUser(pagina,Integer.parseInt((String)userb));
	}
}
