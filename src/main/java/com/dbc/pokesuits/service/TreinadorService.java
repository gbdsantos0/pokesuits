package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.model.entity.Treinador;
import com.dbc.pokesuits.repository.TreinadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class TreinadorService {
    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public TreinadorDTO create(TreinadorCreateDTO treinadorCreate)throws Exception{
        log.info("chamou o método crate do Treinador!");
        Treinador treinador = objectMapper.convertValue(treinadorCreate, Treinador.class);
        Treinador treinadorCriado = treinadorRepository.create(treinador);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorCriado,TreinadorDTO.class);
        return treinadorDTO;
    }

    public TreinadorDTO update(Integer id, TreinadorCreateDTO treinadorAtualizar) throws Exception{
        log.info("chamou o método update do Treinador!");
        Treinador treinador = objectMapper.convertValue(treinadorAtualizar, Treinador.class);
        Treinador treinadorAtualizado = treinadorRepository.create(treinador);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorAtualizado,TreinadorDTO.class);
        return treinadorDTO;
    }

    public List<TreinadorDTO> list(){
        log.info("chamou o método list do Treinador!");
        return treinadorRepository.list()
                .stream()
                .map(treinador -> objectMapper.convertValue(treinador, TreinadorDTO.class))
                .collect(Collectors.toList());
    }

    public TreinadorDTO delete(Integer id) throws Exception{
        log.info("chamou o metodo delete do Treinador!");
        Treinador treinadorDeletado = treinadorRepository.delete(id);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorDeletado, TreinadorDTO.class);
        return treinadorDTO;
    }

    public TreinadorDTO getById(Integer id)throws Exception{
        log.info("chamou o método getById do Treinador!");
        Treinador treinadorChamado = treinadorRepository.getById(id);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorChamado, TreinadorDTO.class);
        return treinadorDTO;
    }
}
