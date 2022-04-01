package com.dbc.pokesuits.controller.admin;

import javax.validation.Valid;

import com.dbc.pokesuits.enums.NomesRegras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping(path = "/admin-user-edit")
@Validated
public class UserControllerAdmin{
	@Autowired
	private UserService userService;
	
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
//	@ApiOperation(value = "Recebe um User")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "adiciona um user e o devolve"),
//            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
//            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
//    })
//	@PostMapping
//	public UserDTO adicionarUser(@Valid @RequestBody UserCreateDTO user, @RequestParam("regras") List<NomesRegras> nomesRegrasList) throws Exception {
//		return userService.adicionarUser(user, nomesRegrasList);
//	}
	
	@ApiOperation(value = "Remove um Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remove um Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@DeleteMapping(path = "/{id_user}")
	public void removerUser(@PathVariable("id_user") int idUser) throws RegraDeNegocioException {
		userService.removerUser(idUser);
	}
	@ApiOperation(value = "Recebe um User e um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualiza e Devolve um Users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Devolve a ecxessao gerada"),
    })
	@PutMapping
	public UserDTO editarUser(@Valid @RequestBody UserCreateDTO createDTO,
								 @RequestParam(value = "id_user")Integer idUser) throws RegraDeNegocioException {
		return userService.editarUser(createDTO, idUser);
	}
	
}
