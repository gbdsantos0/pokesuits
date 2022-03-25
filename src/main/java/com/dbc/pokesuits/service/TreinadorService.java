package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.repository.MochilaRepository;
import com.dbc.pokesuits.repository.TreinadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;

@Service
@Log
public class TreinadorService {
    @Autowired
    private TreinadorRepository treinadorRepository;
    @Autowired
    private MochilaService mochilaService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    public TreinadorDTO create(TreinadorCreateDTO treinadorCreate, Integer idUser)throws Exception{
        log.info("chamou o método crate do Treinador!");
        //busca usuario
        UserEntity userEntity = userService.getById(idUser);
        TreinadorEntity treinador = objectMapper.convertValue(treinadorCreate, TreinadorEntity.class);
        treinador.setMochila(mochilaService.getById(treinadorCreate.getIdMochila()));
        //seta usuario
        treinador.setUser(userEntity);
        TreinadorEntity treinadorCriado = treinadorRepository.save(treinador);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorCriado,TreinadorDTO.class);
        return treinadorDTO;
    }

    public TreinadorDTO update(Integer id, TreinadorCreateDTO treinadorAtualizar) throws Exception{
        log.info("chamou o método update do Treinador!");
        TreinadorEntity treinador = objectMapper.convertValue(treinadorAtualizar, TreinadorEntity.class);
        treinador.setIdTreinador(id);
        TreinadorEntity treinadorAtualizado = treinadorRepository.saveAndFlush(treinador);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorAtualizado,TreinadorDTO.class);
        return treinadorDTO;
    }

    public List<TreinadorDTO> list(){
        log.info("chamou o método list do Treinador!");
        return treinadorRepository.findAll()
                .stream()
                .map(treinador -> objectMapper.convertValue(treinador, TreinadorDTO.class))
                .collect(Collectors.toList());
    }

    public TreinadorDTO delete(Integer id) throws Exception{
        log.info("chamou o metodo delete do Treinador!");
        TreinadorEntity treinadorDeletado = treinadorRepository.getById(id);
        treinadorRepository.deleteById(id);
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorDeletado, TreinadorDTO.class);
        return treinadorDTO;
    }

    public TreinadorDTO getById(Integer id)throws Exception{
        log.info("chamou o método getById do Treinador!");
        TreinadorEntity treinadorChamado = treinadorRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("O id Passado não existe"));
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorChamado, TreinadorDTO.class);
        return treinadorDTO;
    }
}
