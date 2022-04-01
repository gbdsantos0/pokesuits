package com.dbc.pokesuits.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.mochila.MochilaCompletaDTO;
import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.service.MochilaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/mochila")
public class MochilaController {

    @Autowired
    private MochilaService mochilaService;

    @ApiOperation(value = "Retorna todas as mochilas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou todas as mochilas com sucesso!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public Page<MochilaDTO> listAll(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina){
        return mochilaService.listAll(pagina);
    }

    @ApiOperation(value = "Retorna uma mochila pelo id, com todos seus pokemons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou a mochila com sucesso!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{id_mochila}")
    public MochilaCompletaDTO getMochilaCompleta(@PathVariable("id_mochila") Integer id) throws Exception {
        return mochilaService.getMochilaCompleta(id);
    }
    
    @ApiOperation(value = "Adiciona uma mochila a um treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastra uma mochila em um treinador e a devolve!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/{id_treinador}")
    @Validated
    public MochilaDTO criarMochila(@Valid @RequestBody MochilaCreateDTO mochila, @PathVariable("id_treinador") Integer idTreinador) throws Exception {
        return mochilaService.create(mochila, idTreinador);
    }

    @ApiOperation(value = "Retorna uma mochila com quantidade de pokebola atualizada!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizou a quantidade de pokebolas de uma mochila!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{id_mochila}")
    public MochilaDTO adicionarPokebola(@PathVariable("id_mochila")Integer id,
                                        @RequestParam(value = "tipo_pokebola") String tipoPokebola,
                                        @RequestParam(value = "quantidade_adicionada") Integer quantidadeAdicionada) throws Exception {
        return mochilaService.adicionarPokebola(id,tipoPokebola,quantidadeAdicionada);
    }

}
