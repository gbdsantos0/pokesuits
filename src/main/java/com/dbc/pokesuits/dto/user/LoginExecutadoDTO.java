package com.dbc.pokesuits.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginExecutadoDTO {
    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String token;
}
