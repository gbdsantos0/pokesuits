package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.Treinador;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;

@Repository
public class TreinadorRepository {
    private static List<Treinador> listaTreinadores = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public TreinadorRepository(){
        listaTreinadores.add(new Treinador(COUNTER.incrementAndGet(),1,"Ash", Utils.MASCULINO));
        listaTreinadores.add(Treinador.builder()
                .idTreinador(COUNTER.incrementAndGet())
                .idMochila(2)
                .nome("Suits Man")
                .sexo(Utils.MASCULINO)
                .build());
    }

    public Treinador saveAndFlush(Treinador treinador){
        treinador.setIdTreinador(COUNTER.incrementAndGet());
        listaTreinadores.add(treinador);
        return treinador;
    }

    public List<Treinador> findAll(){
        return listaTreinadores;
    }

    public Treinador update(Integer id, Treinador treinadorAtualizar) throws Exception{
        Treinador treinadorRecuperado = listaTreinadores.stream()
                .filter(t-> t.getIdTreinador()==(id))
                .findFirst()
                .orElseThrow(()->new InvalidCenarioException("Treinador não existe!"));
        treinadorRecuperado.setNome(treinadorAtualizar.getNome());
        treinadorRecuperado.setSexo(treinadorAtualizar.getSexo());
        return treinadorRecuperado;
    }
    public Treinador deleteById(Integer id)throws Exception{
        return listaTreinadores.stream()
                .filter(t-> t.getIdTreinador()==(id))
                .findFirst()
                .orElseThrow(()->new InvalidCenarioException("Treinador não existe!"));
    }

    public Treinador getById(Integer id)throws Exception{
        return listaTreinadores.stream()
                .filter(treinador -> treinador.getIdTreinador()==(id))
                .findFirst()
                .orElseThrow(()-> new InvalidCenarioException("Treinador não existe!"));
    }
}
