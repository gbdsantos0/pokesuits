package com.dbc.pokesuits.model;

import java.util.List;

import com.dbc.pokesuits.enums.TiposTerreno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cenario{
    private Integer idCenario;
    private TiposTerreno terreno;
    private Integer levelMedio;
    private List<Integer> idPokemonsDisponiveis;

}
