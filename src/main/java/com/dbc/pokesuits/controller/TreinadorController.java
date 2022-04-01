package com.dbc.pokesuits.controller;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.service.TreinadorService;
import com.dbc.pokesuits.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/treinador")
@Validated
@RequiredArgsConstructor
public class TreinadorController {
    private final TreinadorService treinadorService;

    @ApiOperation(value = "Cria e retorna um novo treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping
    public TreinadorDTO create(@Valid @RequestBody TreinadorCreateDTO treinador)throws Exception{
        return treinadorService.create(treinador, Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }


    @ApiOperation(value = "Atualiza e retorna um treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador atualizado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping
    public TreinadorDTO update(@Valid @RequestBody TreinadorCreateDTO treinadorAtualizar)throws Exception{
        return treinadorService.update(Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()),treinadorAtualizar);
    }



    @ApiOperation(value = "Deleta e retorna um treinador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador removido do banco com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping
    public TreinadorDTO delete() throws Exception{
        return treinadorService.deleteByIdUser(Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }



    @ApiOperation(value = "Retorna um treinador pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Treinador retornado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public TreinadorDTO getById() throws Exception{
        return treinadorService.getTreinadorDTOByIdUser(Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
}
