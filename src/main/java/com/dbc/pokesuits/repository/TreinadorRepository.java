package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;

@Repository
public class TreinadorRepository {
    private static List<TreinadorEntity> listaTreinadores = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public TreinadorRepository(){
        listaTreinadores.add(new TreinadorEntity(COUNTER.incrementAndGet(),1,"Ash", Utils.MASCULINO));
        listaTreinadores.add(TreinadorEntity.builder()
                .idTreinador(COUNTER.incrementAndGet())
                .idMochila(2)
                .nome("Suits Man")
                .sexo(Utils.MASCULINO)
                .build());
    }

    public TreinadorEntity saveAndFlush(TreinadorEntity treinador){
        treinador.setIdTreinador(COUNTER.incrementAndGet());
        listaTreinadores.add(treinador);
        return treinador;
    }

    public List<TreinadorEntity> findAll(){
        return listaTreinadores;
    }

    public TreinadorEntity update(Integer id, TreinadorEntity treinadorAtualizar) throws Exception{
        TreinadorEntity treinadorRecuperado = listaTreinadores.stream()
                .filter(t-> t.getIdTreinador()==(id))
                .findFirst()
                .orElseThrow(()->new InvalidCenarioException("Treinador não existe!"));
        treinadorRecuperado.setNome(treinadorAtualizar.getNome());
        treinadorRecuperado.setSexo(treinadorAtualizar.getSexo());
        return treinadorRecuperado;
    }
    public TreinadorEntity deleteById(Integer id)throws Exception{
        return listaTreinadores.stream()
                .filter(t-> t.getIdTreinador()==(id))
                .findFirst()
                .orElseThrow(()->new InvalidCenarioException("Treinador não existe!"));
    }

    public TreinadorEntity getById(Integer id)throws Exception{
        return listaTreinadores.stream()
                .filter(treinador -> treinador.getIdTreinador()==(id))
                .findFirst()
                .orElseThrow(()-> new InvalidCenarioException("Treinador não existe!"));
    }
}
