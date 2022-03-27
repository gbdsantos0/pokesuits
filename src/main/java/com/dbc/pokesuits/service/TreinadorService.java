package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.TreinadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@Log
@RequiredArgsConstructor
public class TreinadorService {
    private final TreinadorRepository treinadorRepository;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public TreinadorDTO create(TreinadorCreateDTO treinadorCreate, Integer idUser)throws Exception{
        log.info("chamou o método crate do Treinador!");
        UserEntity userEntity = userService.getById(idUser);//busca usuario
        TreinadorEntity treinador = objectMapper.convertValue(treinadorCreate, TreinadorEntity.class);//converte para TreinadorEntity
        treinador.setUser(userEntity);//seta usuario
        TreinadorEntity treinadorCriado = treinadorRepository.save(treinador);//salva treinador no banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorCriado,TreinadorDTO.class);//converte para dto para retorno
        return treinadorDTO;
    }
    //todo consertar parte de dar update no id do user, talvez um DTO novo?
    public TreinadorDTO update(Integer idTreinador, TreinadorCreateDTO treinadorAtualizar) throws Exception{
        log.info("chamou o método update do Treinador!");
        TreinadorEntity treinadorEntity = treinadorRepository.findById(idTreinador)//busca treinador no banco para verificar se existe para atualizar
                .orElseThrow(()->new RegraDeNegocioException("ID não encontrado para o treinador"));//caso de treinador nao encontrado pelo id
        treinadorEntity.setNome(treinadorAtualizar.getNome());//seta nome
        treinadorEntity.setSexo(treinadorAtualizar.getSexo());//seta sexo
        TreinadorEntity treinadorAtualizado = treinadorRepository.save(treinadorEntity);//insere alteracao no banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorAtualizado,TreinadorDTO.class);//converte o treinador para dto

        return treinadorDTO;
    }

    public List<TreinadorDTO> list(){
        log.info("chamou o método list do Treinador!");
        return treinadorRepository.findAll().stream()//busca todos os dados e chama stream
                .map(treinador -> objectMapper.convertValue(treinador, TreinadorDTO.class))//mapeia para TreinadorDTO
                .collect(Collectors.toList());//coleta
    }

    //todo talvez haja necessidade de chamar outro delete em caso de nao poder usar cascata.
    public TreinadorDTO delete(Integer id) throws Exception{
        log.info("chamou o metodo delete do Treinador!");
        TreinadorEntity treinadorDeletado = getById(id);//busca a entrada no banco para retorno
        treinadorRepository.deleteById(id);//deleta o dado do banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorDeletado, TreinadorDTO.class);//converte o dado retornado do banco para TreinadorDTO
        return treinadorDTO;
    }

    public TreinadorEntity getById(Integer id)throws Exception{
        log.info("chamou o método getById do Treinador!");
        TreinadorEntity treinadorChamado = treinadorRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("O id Passado não existe"));//recebe TreinadorEntity do banco
        return treinadorChamado;
    }

    public TreinadorDTO getTreinadorDTOById(Integer idTreinador) throws Exception {
        return objectMapper.convertValue(getById(idTreinador), TreinadorDTO.class);//converte um TreinadorEntity para TreinadorDTO e retorna
    }
}
