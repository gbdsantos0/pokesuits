package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        if(userEntity.getTreinador()!=null){
            throw new RegraDeNegocioException("O usuário já possui um treinador");
        }

        TreinadorEntity treinador = objectMapper.convertValue(treinadorCreate, TreinadorEntity.class);//converte para TreinadorEntity
        treinador.setUser(userEntity);//seta usuario
        TreinadorEntity treinadorCriado = treinadorRepository.save(treinador);//salva treinador no banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorCriado,TreinadorDTO.class);//converte para dto para retorno
        treinadorDTO.setIdUser(idUser);//setando id user para retorno
        return treinadorDTO;
    }

    public TreinadorDTO update(Integer idUser, TreinadorCreateDTO treinadorAtualizar) throws Exception{
        log.info("chamou o método update do Treinador!");

        UserEntity userEntity = userService.getById(idUser);

        if(userEntity.getTreinador()==null){
            throw new RegraDeNegocioException("O usuário não possui um treinador");
        }

        TreinadorEntity treinadorEntity = userEntity.getTreinador();//busca treinador no banco para verificar se existe para atualizar
        treinadorEntity.setNome(treinadorAtualizar.getNome());//seta nome
        treinadorEntity.setSexo(treinadorAtualizar.getSexo());//seta sexo
        TreinadorEntity treinadorAtualizado = treinadorRepository.save(treinadorEntity);//insere alteracao no banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorAtualizado,TreinadorDTO.class);//converte o treinador para dto

        return treinadorDTO;
    }

    public Page<TreinadorDTO> list(Integer pagina){
        log.info("chamou o método list do Treinador!");
        Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10);
        List<TreinadorDTO> treinadorDTOList = treinadorRepository.findAll(pageable).stream()//busca todos os dados e chama stream
                .map(treinador -> objectMapper.convertValue(treinador, TreinadorDTO.class))//mapeia para TreinadorDTO
                .collect(Collectors.toList());//coleta
        return new PageImpl<>(treinadorDTOList);
    }

    public TreinadorDTO delete(Integer id) throws Exception{
        log.info("chamou o metodo delete do Treinador!");
        TreinadorEntity treinadorDeletado = getById(id);//busca a entrada no banco para retorno
        treinadorRepository.deleteById(id);//deleta o dado do banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorDeletado, TreinadorDTO.class);//converte o dado retornado do banco para TreinadorDTO
        return treinadorDTO;
    }


    //todo consertar delete(parece que preciso salvar o user entity após remover, pois o jpa não deixa remover uma entidade que já está relacionada à outra carregada no sistema)
    public TreinadorDTO deleteByIdUser(Integer idUser) throws Exception{
        log.info("chamou o metodo delete do Treinador!");

        UserEntity userEntity = userService.getById(idUser);//busca usuario

        if(userEntity.getTreinador()==null){
            throw new RegraDeNegocioException("O usuário não possui um treinador");
        }

        TreinadorEntity treinadorDeletado = userEntity.getTreinador();//busca a entrada no banco para retorno

        Integer idTreinadorDelete = treinadorDeletado.getIdTreinador();
        log.info("ID to treinador deletado: "+idTreinadorDelete);

        TreinadorEntity treinadorParaDelete = getById(idTreinadorDelete);
        treinadorRepository.delete(treinadorParaDelete);
//        treinadorRepository.deleteById(idTreinadorDelete);//deleta o dado do banco
        TreinadorDTO treinadorDTO = objectMapper.convertValue(treinadorDeletado, TreinadorDTO.class);//converte o dado retornado do banco para TreinadorDTO
        return treinadorDTO;
    }

    public TreinadorEntity getById(Integer id)throws Exception{
        log.info("chamou o método getById do Treinador!");
        TreinadorEntity treinadorChamado = treinadorRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("O id Passado não existe"));//recebe TreinadorEntity do banco
        return treinadorChamado;
    }

    public TreinadorDTO getTreinadorDTOById(Integer idTreinador) throws Exception {
        log.info("chamou o método getTreinadorDTOById do Treinador!");
        
        return objectMapper.convertValue(getById(idTreinador), TreinadorDTO.class);//converte um TreinadorEntity para TreinadorDTO e retorna
    }

    public TreinadorDTO getTreinadorDTOByIdUser(Integer idUser) throws Exception {
        log.info("chamou o método getTreinadorDTOById do Treinador!");

        UserEntity userEntity = userService.getById(idUser);
        
        TreinadorEntity treinador = userEntity.getTreinador();
        if(treinador == null )throw new RegraDeNegocioException("o treinador não foi criado");

        return objectMapper.convertValue(treinador, TreinadorDTO.class);//converte um TreinadorEntity para TreinadorDTO e retorna
    }
}
