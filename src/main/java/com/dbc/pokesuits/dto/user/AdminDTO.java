package com.dbc.pokesuits.dto.user;

import com.dbc.pokesuits.enums.NomesRegras;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminDTO extends UserDTO{
    @ApiModelProperty(value = "lista de regras do usuário")
    private List<NomesRegras> regras;
}
