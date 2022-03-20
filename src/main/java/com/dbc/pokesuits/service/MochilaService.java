package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;
import com.dbc.pokesuits.model.objetos.Mochila;
import com.dbc.pokesuits.repository.MochilaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MochilaService {

    @Autowired
    private MochilaRepository mochilaRepository;
    @Autowired
    private ObjectMapper objectMapper;


    public List<MochilaDTO> listAll(){
        return mochilaRepository.list().stream()
                .map(m -> objectMapper.convertValue(m,MochilaDTO.class))
                .collect(Collectors.toList());
    }

    public MochilaDTO adicionarPokebola(Integer id,String tipoPokebola, Integer quantidadeAdicionada)throws Exception{
        Mochila mochila = mochilaRepository.getById(id);

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

        MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaRepository.update(id,mochila), MochilaDTO.class);
                return mochilaDTO;

    }


    public MochilaDTO usarPokebola(Integer id, String tipoPokebola) throws Exception {
        Mochila mochila = mochilaRepository.getById(id);
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
        MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaRepository.update(id,mochila), MochilaDTO.class);
        return mochilaDTO;
    }

}
