package com.dbc.pokesuits.repository;

import com.dbc.pokesuits.enums.TiposTerreno;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;
import com.dbc.pokesuits.model.cenario.Cenario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class CenarioRepository {
    private static List<Cenario> listaCenarios = new ArrayList<>();
    private static AtomicInteger COUNTER = new AtomicInteger();

    public CenarioRepository(){
        listaCenarios.add(Cenario.builder()//CHAO (NENHUM POKEMON)
                .idCenario(COUNTER.incrementAndGet())
                .terreno(TiposTerreno.CHAO)
                .levelMedio(1)
                .idPokemonsDisponiveis(List.of())
                .build());
        listaCenarios.add(Cenario.builder()//GRAMA (BULBASAUR E EVOS)
                .idCenario(COUNTER.incrementAndGet())
                .terreno(TiposTerreno.GRAMA)
                .levelMedio(4)
                .idPokemonsDisponiveis(Arrays.asList(1,2,3))
                .build());
        listaCenarios.add(Cenario.builder()//AGUA (SQUIRTLE E EVOLUCOES)
                .idCenario(COUNTER.incrementAndGet())
                .terreno(TiposTerreno.AGUA)
                .levelMedio(10)
                .idPokemonsDisponiveis(Arrays.asList(7,8,9))
                .build());
        listaCenarios.add(Cenario.builder()//CAVERNA (CHARMANDER E EVOS + GEODUDE E EVOS)
                .idCenario(COUNTER.incrementAndGet())
                .terreno(TiposTerreno.CAVERNA)
                .levelMedio(7)
                .idPokemonsDisponiveis(Arrays.asList(4,5,6,74,75,76))
                .build());
    }

    public List<Cenario> listAll(){
        return listaCenarios;
    }

    public Cenario getById(Integer idCenario) throws Exception{
        Cenario cenario = listaCenarios.stream()
                .filter(c -> c.getIdCenario().equals(idCenario))
                .findFirst()
                .orElseThrow(() -> new InvalidCenarioException("ID de cenário inválido"));
        return cenario;
    }

    public List<Cenario> listByTipoTerreno(TiposTerreno tipoTerreno){
        List<Cenario> cenarioList = listaCenarios.stream()
                .filter(c-> c.getTerreno().equals(tipoTerreno))
                .collect(Collectors.toList());
        return cenarioList;
    }

    public List<Integer> getIdPokemonsDisponiveis(Integer idCenario) throws Exception{
        Cenario cenario = listaCenarios.stream()
                .filter(c -> c.getIdCenario().equals(idCenario))
                .findFirst()
                .orElseThrow(() -> new InvalidCenarioException("ID de cenário inválido"));
        return cenario.getIdPokemonsDisponiveis();
    }

}
