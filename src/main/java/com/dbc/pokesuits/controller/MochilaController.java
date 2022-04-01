package com.dbc.pokesuits.controller;

import com.dbc.pokesuits.dto.mochila.MochilaCompletaDTO;
import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.service.MochilaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/mochila-admin")
@AllArgsConstructor
public class MochilaController {

    private MochilaService mochilaService;

//    @ApiOperation(value = "Retorna todas as mochilas")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Retornou todas as mochilas com sucesso!"),
//            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
//            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
//    })
//    @GetMapping
//    public Page<MochilaDTO> listAll(@RequestParam(value = "pagina_solicitada", required = false) Integer pagina){
//        return mochilaService.listAll(pagina);
//    }

//    @ApiOperation(value = "Retorna uma mochila pelo id, com todos seus pokemons")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Retornou a mochila com sucesso!"),
//            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
//            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
//    })
//    @GetMapping("/{id_mochila}")
//    public MochilaCompletaDTO getMochilaCompleta(@PathVariable("id_mochila") Integer id) throws Exception {
//        return mochilaService.getMochilaCompleta(id);
//    }
    
    @ApiOperation(value = "Adiciona uma mochila a um treinador")
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


}
