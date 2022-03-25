package com.dbc.pokesuits.controller;


import com.dbc.pokesuits.dto.mochila.MochilaCompletaDTO;
import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.MochilaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public List<MochilaDTO> listAll(){
        return mochilaService.listAll();
    }

    @GetMapping("/{idMochila}")
    public MochilaCompletaDTO getMochilaCompleta(@PathVariable("idMochila") Integer id) throws RegraDeNegocioException {
        return mochilaService.getMochilaCompleta(id);
    }

    @PostMapping("{idTreinador}")
    @Validated
    public MochilaDTO criarMochila(@Valid @RequestBody MochilaCreateDTO mochila, @PathVariable("idTreinador") Integer idTreinador) throws Exception {
        return mochilaService.create(mochila, idTreinador);
    }

    @ApiOperation(value = "Retornar uma mochila com quantidade de pokebola atualizada!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizou a quantidade de pokebolas de uma mochila!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idMochila}")
    public MochilaDTO adicionarPokebola(@PathVariable("idMochila")Integer id,
                                        @RequestParam String tipoPokebola,
                                        @RequestParam Integer quantidadeAdicionada) throws Exception {
        return mochilaService.adicionarPokebola(id,tipoPokebola,quantidadeAdicionada);
    }

}
