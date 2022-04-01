package com.dbc.pokesuits.service;

import com.dbc.pokesuits.entity.RegraEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.RegraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegraService {
    private final RegraRepository regraRepository;

    public RegraEntity getById(Integer idRegra) throws Exception{
        return regraRepository.findById(idRegra).orElseThrow(()->new RegraDeNegocioException("Regra não encontrada"));
    }
}
