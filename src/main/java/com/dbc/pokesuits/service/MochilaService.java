package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.MochilaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
@Log4j2
public class MochilaService {

    private final MochilaRepository mochilaRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public MochilaDTO createMochilaLogado(MochilaCreateDTO mochilaCreateDTO, Integer idUser) throws Exception {
        log.info("Chamado metodo create");

        UserEntity userEntity = this.userService.getById(idUser);

        TreinadorEntity treinadorEntity = userEntity.getTreinador();

        if (treinadorEntity == null) throw new RegraDeNegocioException("Treinador não criado.");

        MochilaEntity mochilaEntity = treinadorEntity.getMochila();

        if (mochilaEntity == null) {
            mochilaEntity = objectMapper.convertValue(mochilaCreateDTO, MochilaEntity.class);
            mochilaEntity.setTreinador(treinadorEntity);
            MochilaEntity save = this.mochilaRepository.save(mochilaEntity);
            
            MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaEntity, MochilaDTO.class);
            mochilaDTO.setIdTreinador(save.getTreinador().getIdTreinador());
            return mochilaDTO;
        }

        throw new RegraDeNegocioException("Mochila já existe.");
    }

    public MochilaDTO adicionarPokebola(Integer id, String tipoPokebola, Integer quantidadeAdicionada) throws Exception{
    	log.info("Chamado metodo adicionarPokebola");

        if (quantidadeAdicionada <= 0) {
            throw new RegraDeNegocioException("O valor adicionado não pode ser menor ou igual a zero.");
        }

        MochilaEntity mochila = this.getMochilaPeloIdUser(id);
        mochila.setIdTreinador(mochila.getIdTreinador());

        switch (tipoPokebola.toLowerCase(Locale.ROOT)){
            case "greatball":

                if(mochila.getQuantidadeGreatBalls()+quantidadeAdicionada>99){
                    throw new InvalidCenarioException("A quantidade de pokebolas não pode ser maior que 99!");
                }
                mochila.setQuantidadeGreatBalls(mochila.getQuantidadeGreatBalls()+quantidadeAdicionada);

                break;
            case "pokeball":
                if(mochila.getQuantidadePokeBalls()+quantidadeAdicionada>99){
                    throw new  InvalidCenarioException("A quantidade de pokebolas não pode ser maior que 99!");
                }
                mochila.setQuantidadePokeBalls(mochila.getQuantidadePokeBalls()+quantidadeAdicionada);
                break;
            case "heavyball":
                if(mochila.getQuantidadeHeavyBalls()+quantidadeAdicionada>99){
                    throw new  InvalidCenarioException("A quantidade de pokebolas não pode ser maior que 99!");
                }
                mochila.setQuantidadeHeavyBalls(mochila.getQuantidadeHeavyBalls()+quantidadeAdicionada);
                break;
            case "masterball":
                if(mochila.getQuantidadeMasterBalls()+quantidadeAdicionada>99){
                    throw new  InvalidCenarioException("A quantidade de pokebolas não pode ser maior que 99!");
                }
                mochila.setQuantidadeMasterBalls(mochila.getQuantidadeMasterBalls()+quantidadeAdicionada);
                break;
            case "netball":
                if(mochila.getQuantidadeNetBalls()+quantidadeAdicionada>99){
                    throw new  InvalidCenarioException("A quantidade de pokebolas não pode ser maior que 99!");
                }
                mochila.setQuantidadeNetBalls(mochila.getQuantidadeNetBalls()+quantidadeAdicionada);
                break;
            default:
                throw new InvalidCenarioException("A pokebola escolhida não existe!");
        }

        return objectMapper.convertValue(mochilaRepository.save(mochila), MochilaDTO.class);
    }

    public void usarPokebola(Integer id, String tipoPokebola) throws Exception {
    	log.info("Chamado metodo usarPokebola");

        MochilaEntity mochila = this.getMochilaPeloIdUser(id);
        mochila.setIdTreinador(mochila.getIdTreinador());

        switch (tipoPokebola.toLowerCase(Locale.ROOT)) {
            case "greatball":
                if(mochila.getQuantidadeGreatBalls()-1<0){
                    throw new InvalidCenarioException("A quantidade de pokebolas não pode ser negativa!");
                }
                mochila.setQuantidadeGreatBalls(mochila.getQuantidadeGreatBalls()-1);
                break;
            case "pokeball":
                if(mochila.getQuantidadePokeBalls()-1<0){
                    throw new InvalidCenarioException("A quantidade de pokebolas não pode ser negativa!");
                }
                mochila.setQuantidadePokeBalls(mochila.getQuantidadePokeBalls()-1);
                break;
            case "heavyball":
                if(mochila.getQuantidadeHeavyBalls()-1<0){
                    throw new InvalidCenarioException("A quantidade de pokebolas não pode ser negativa!");
                }
                mochila.setQuantidadeHeavyBalls(mochila.getQuantidadeHeavyBalls()-1);
                break;
            case "masterball":
                if(mochila.getQuantidadeMasterBalls()-1<0){
                    throw new InvalidCenarioException("A quantidade de pokebolas não pode ser negativa!");
                }
                mochila.setQuantidadeMasterBalls(mochila.getQuantidadeMasterBalls()-1);
                break;
            case "netball":
                if(mochila.getQuantidadeNetBalls()-1<0){
                    throw new InvalidCenarioException("A quantidade de pokebolas não pode ser negativa!");
                }
                mochila.setQuantidadeNetBalls(mochila.getQuantidadeNetBalls()-1);
                break;
            default:
                throw new InvalidCenarioException("A pokebola escolhida não existe!");
        }

       mochilaRepository.save(mochila);
    }

    public MochilaDTO getMochilaLogado(Integer idUser) throws RegraDeNegocioException {
        MochilaEntity mochilaEntity = this.getMochilaPeloIdUser(idUser);
        mochilaEntity.setIdTreinador(mochilaEntity.getIdTreinador());
        return objectMapper.convertValue(mochilaEntity, MochilaDTO.class);
    }

    public void deletarMochilaLogado(Integer idUser) throws RegraDeNegocioException {
        log.info("Chamado metodo deleteMochilaLogado");
        
        TreinadorEntity treinadorEntity = userService.getById(idUser).getTreinador();

        if (treinadorEntity == null) throw new RegraDeNegocioException("Treinador não criado.");

        MochilaEntity mochilaEntity = treinadorEntity.getMochila();

        if (mochilaEntity == null)throw new RegraDeNegocioException("Mochila não existe.");
        
        mochilaEntity.setIdTreinador(mochilaEntity.getIdTreinador());
        
        treinadorEntity.setMochila(null);
        
        this.mochilaRepository.deleteById(mochilaEntity.getIdMochila());
    }
    
    public MochilaEntity getById(Integer id) throws RegraDeNegocioException {
    	return mochilaRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("O id Passado Não Existe"));
    }

    public MochilaEntity getMochilaPeloIdUser(Integer idUser) throws RegraDeNegocioException {
        TreinadorEntity treinadorEntity = userService.getById(idUser).getTreinador();

        if (treinadorEntity == null) throw new RegraDeNegocioException("Treinador não criado.");

        MochilaEntity mochilaEntity = treinadorEntity.getMochila();

        if (mochilaEntity == null)throw new RegraDeNegocioException("Mochila não existe.");
        
        mochilaEntity.setIdTreinador(mochilaEntity.getIdTreinador());

        return mochilaEntity;
    }

    public void deletarMochilaPeloId(Integer id) throws RegraDeNegocioException {
        MochilaEntity mochila = this.getById(id);
        this.mochilaRepository.deleteById(mochila.getIdMochila());
    }
}
