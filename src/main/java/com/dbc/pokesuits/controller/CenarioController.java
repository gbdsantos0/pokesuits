package com.dbc.pokesuits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dbc.pokesuits.dto.cenario.CenarioDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.service.CenarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.java.Log;

import java.util.List;

@RestController
@RequestMapping("/cenario")
@Log
public class CenarioController {
    @Autowired
    private CenarioService cenarioService;

    @ApiOperation(value = "Altera o cenário atual utilizando o id_cenario alvo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cenário para o qual foi alterado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/alterar-cenario/{id_cenario}")
    public CenarioDTO alterarCenario(@PathVariable("id_cenario") Integer idCenario) throws Exception{
        CenarioDTO cenarioDTO = cenarioService.alterarCenario(idCenario);
        log.info("Cenário alterado");
        return cenarioDTO;
    }

    @ApiOperation(value = "Gera um pokemon para ser utililizado no encontro pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Pokemon conforme o cenário atual com chances divididas por rarirade e quantidade de pokemons"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/gerar-pokemon")
    public PokemonCreateDTO gerarPokemon() throws Exception{
        PokemonCreateDTO pokemonCreateDTO = cenarioService.gerarPokemon();
        log.info("Pokemon gerado com sucesso pelo cenário");
        return pokemonCreateDTO;
    }

    @ApiOperation(value = "Tentativa de capturar o último pokemon encontrado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Pokemon capturado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/capturar-pokemon/{id_treinador}/{pokebola}")//TODO ADICIONAR REQUESTINFO PARA CASO DE NAO CONSEGUIR CAPTURAR
    public PokemonDTO capturarPokemon(@RequestParam(value = "pokebola") String nomePokebola, @PathVariable("id_treinador") Integer idTreinador, @RequestParam(value = "id_mochila") Integer idMochila) throws Exception{//todo adicionar o treinadorDTO
        PokemonDTO pokemonDTO = cenarioService.capturar(nomePokebola, idTreinador, idMochila);
        log.info("Pokemon capturado com sucesso");
        return pokemonDTO;
    }

    @ApiOperation(value = "Mostra o cenário atual")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cenário retornado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public CenarioDTO cenarioAtual() throws Exception{
        CenarioDTO cenarioDTO = cenarioService.cenarioAtual();
        log.info("Cenário retornado");
        return cenarioDTO;
    }

    @ApiOperation(value = "Mostra todos os possíveis cenários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cenários retornados com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("listar-todos-cenarios")
    public List<CenarioDTO> listCenarios() throws Exception{
        List<CenarioDTO> listCenarioDTO = cenarioService.listAll();
        log.info("Cenários retornados");
        return listCenarioDTO;
    }
}
