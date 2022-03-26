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


    @ApiOperation(value = "Cria e retorna um novo treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/{id_user}")
    public TreinadorDTO create(@Valid @RequestBody TreinadorCreateDTO treinador, @PathVariable("id_user") Integer idUser)throws Exception{
        return treinadorService.create(treinador, idUser);
    }


    @ApiOperation(value = "Retorna uma lista de treinadores")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de treinadores retornada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public List<TreinadorDTO> list(){
        return treinadorService.list();
    }


    @ApiOperation(value = "Atualiza e retorna um treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador atualizado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/{id_treinador}")
    public TreinadorDTO update(@PathVariable("id_treinador") Integer idTreinador,
                               @Valid @RequestBody TreinadorCreateDTO treinadorAtualizar)throws Exception{
        return treinadorService.update(idTreinador,treinadorAtualizar);
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
