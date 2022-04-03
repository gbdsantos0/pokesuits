package com.dbc.pokesuits.controller;

import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.pokesuits.dto.user.LoginDTO;
import com.dbc.pokesuits.dto.user.LoginExecutadoDTO;
import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.security.TokenService;
import com.dbc.pokesuits.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    
    @ApiOperation(value = "Retorna o token do usuário logado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retorna o token"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping()
    public String auth(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getSenha()
                );

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.getToken(authenticate);
        return token;
    }
    
    @ApiOperation(value = "Recebe o user a criar e Retorna o user criado e o token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "retorna a mochila do usuário logado!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/sign-up")
    public LoginExecutadoDTO signUp(@RequestBody @Valid UserCreateDTO userCreateDTO) throws Exception{
        userService.criaUser(userCreateDTO);
        LoginExecutadoDTO loginExecutadoDTO = new LoginExecutadoDTO();
        loginExecutadoDTO.setLogin(userCreateDTO.getUsername());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userCreateDTO.getUsername(), userCreateDTO.getPassword());//gera UPAT de login

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);//autentica com o UPAT

        String token = tokenService.getToken(authenticate);//retorna o token a partir do Authentication

        loginExecutadoDTO.setToken(token);

        return loginExecutadoDTO;
    }

}