package com.dbc.pokesuits.controller;

import javax.validation.Valid;

import com.dbc.pokesuits.dto.user.LoginExecutadoDTO;
import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.dbc.pokesuits.dto.user.LoginDTO;
import com.dbc.pokesuits.security.TokenService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

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

    @PostMapping("/sign-up")
    public LoginExecutadoDTO signUp(@RequestBody @Valid UserCreateDTO userCreateDTO) throws Exception{
        userService.adicionarUser(userCreateDTO);
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