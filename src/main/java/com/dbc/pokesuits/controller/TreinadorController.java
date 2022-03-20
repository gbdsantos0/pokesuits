package com.dbc.pokesuits.controller;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.service.TreinadorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/treinador")
@Validated
public class TreinadorController {
    @Autowired
    private TreinadorService treinadorService;


    @ApiOperation(value = "Retornar um novo treinador!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "criou um treinador!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping
    public TreinadorDTO create(@Valid @RequestBody TreinadorCreateDTO treinador)throws Exception{
        return treinadorService.create(treinador);
    }


    @ApiOperation(value = "Retornar uma lista de treinadores!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de treinadores!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public List<TreinadorDTO> list(){
        return treinadorService.list();
    }


    @ApiOperation(value = "Retornar um treinador atualizado!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizou um treinador!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{idTreinador}")
    public TreinadorDTO update(@PathVariable("idTreinador") Integer id,
                               @Valid @RequestBody TreinadorCreateDTO treinadorAtualizar)throws Exception{
        return treinadorService.update(id,treinadorAtualizar);
    }



    @ApiOperation(value = "Retornar um treinador deletado!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "deletou um treinador!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/{idTreinador}")
    public TreinadorDTO delete(@PathVariable("idTreinador") Integer id) throws Exception{
        return treinadorService.delete(id);
    }



    @ApiOperation(value = "Retornar um treinador pelo seu id!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "listar um treinador pelo seu id!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{idTreinador}")
    public TreinadorDTO getById(@PathVariable("idTreinador") Integer id) throws Exception{
        return treinadorService.getById(id);
    }
}
