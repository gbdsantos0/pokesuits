package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.Mochila;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;

@Repository
public class MochilaRepository {
    private static final List<Mochila> listaMochilas = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();



    public MochilaRepository(){
        listaMochilas.add(new Mochila(COUNTER.incrementAndGet(),1,1,1,1,20));
        listaMochilas.add(Mochila.builder()
                        .idMochila(COUNTER.incrementAndGet())
                        .quantidadeGreatBalls(2)
                        .quantidadeHeavyBalls(2)
                        .quantidadeMasterBalls(2)
                        .quantidadeNetBalls(2)
                        .quantidadePokeBalls(15)
                .build());
    }


    public List<Mochila> findAll(){
        return listaMochilas;
    }

    public Mochila getById(Integer id)throws Exception{
        return listaMochilas.stream()
                .filter(mochila -> mochila.getIdMochila()==(id))
                .findFirst().orElseThrow(()->new InvalidCenarioException("Não existe mochila com este ID!"));
    }

    public Mochila update(Integer id, Mochila mochilaAtualizada)throws Exception{
        Mochila mochilaRecuperada = listaMochilas.stream()
                .filter(mochila -> mochila.getIdMochila()==(id))
                .findFirst()
                .orElseThrow(()->new InvalidCenarioException("Mochila não encontrada!"));
//        listaMochilas.remove(mochilaRecuperada);
        mochilaRecuperada.setQuantidadeGreatBalls(mochilaAtualizada.getQuantidadeGreatBalls());
        mochilaRecuperada.setQuantidadeHeavyBalls(mochilaAtualizada.getQuantidadeHeavyBalls());
        mochilaRecuperada.setQuantidadeMasterBalls(mochilaAtualizada.getQuantidadeMasterBalls());
        mochilaRecuperada.setQuantidadeNetBalls(mochilaAtualizada.getQuantidadeNetBalls());
        mochilaRecuperada.setQuantidadePokeBalls(mochilaAtualizada.getQuantidadePokeBalls());
//        listaMochilas.add(mochilaRecuperada);


        return mochilaRecuperada;
    }


}
