package com.dbc.pokesuits.service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.dbc.pokesuits.entity.TreinadorEntity;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.mochila.MochilaCompletaDTO;
import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.MochilaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MochilaService {

    private final MochilaRepository mochilaRepository;
    private final ObjectMapper objectMapper;
    private final TreinadorService treinadorService;


    public List<MochilaDTO> listAll(){
        return mochilaRepository.findAll().stream()
                .map(m -> objectMapper.convertValue(m,MochilaDTO.class))
                .collect(Collectors.toList());
    }

    public MochilaDTO create(MochilaCreateDTO mochila, Integer idTreinador) throws Exception {
        MochilaEntity mochilaEntity = objectMapper.convertValue(mochila, MochilaEntity.class);
        //buscando treinador
        TreinadorEntity treinador = treinadorService.getById(idTreinador);
        mochilaEntity.setTreinador(treinador);

        MochilaEntity mochilaCriada = mochilaRepository.save(mochilaEntity);

        return objectMapper.convertValue(mochilaCriada, MochilaDTO.class);
    }

    public MochilaDTO adicionarPokebola(Integer id,String tipoPokebola, Integer quantidadeAdicionada)throws Exception{
        MochilaEntity mochila = mochilaRepository.getById(id);

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

        MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaRepository.save(mochila), MochilaDTO.class);
        return mochilaDTO;
    }


    public MochilaDTO usarPokebola(Integer id, String tipoPokebola) throws Exception {
        MochilaEntity mochila = mochilaRepository.getById(id);
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

        MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaRepository.save(mochila), MochilaDTO.class);
        return mochilaDTO;
    }

    public MochilaCompletaDTO getMochilaCompleta(Integer id) throws RegraDeNegocioException {
        MochilaEntity mochila = getById(id);
        

        MochilaCompletaDTO mochilaDTO = objectMapper.convertValue(mochila, MochilaCompletaDTO.class);
        mochilaDTO.setTreinador(objectMapper.convertValue(mochila.getTreinador(), TreinadorDTO.class));
        mochilaDTO.setPokemons(mochila.getPokemons().stream()
                .map(pokemon -> {
                	PokemonDTO convertValue = objectMapper.convertValue(pokemon, PokemonDTO.class);
                	return convertValue;
                })
                .collect(Collectors.toList())
        );

        return mochilaDTO;
    }
    
    public MochilaEntity getById(Integer id) throws RegraDeNegocioException {
    	return mochilaRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("O id Passado Não Existe"));
    }
}
