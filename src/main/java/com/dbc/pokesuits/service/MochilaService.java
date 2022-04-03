package com.dbc.pokesuits.service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.mochila.MochilaCompletaDTO;
import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.dto.mochila.MochilaDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.MochilaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class MochilaService {

    private final MochilaRepository mochilaRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public Page<MochilaDTO> listAll(Integer pagina) {
    	log.info("Chamado metodo listAll");
    	
    	Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10);
    	
    	List<MochilaDTO> mochilas = mochilaRepository.findAll(pageable)
    			.stream()
				.map(mochila -> objectMapper.convertValue(mochila, MochilaDTO.class))
				.collect(Collectors.toList());
    
    	return new PageImpl<>(mochilas);
    }

    public MochilaDTO createMochilaLogado(MochilaCreateDTO mochilaCreateDTO, Integer idUser) throws Exception {
        log.info("Chamado metodo create");

        UserEntity userEntity = this.userService.getById(idUser);

        TreinadorEntity treinadorEntity = userEntity.getTreinador();

        if (treinadorEntity == null) throw new RegraDeNegocioException("Treinador não criado.");

        MochilaEntity mochilaEntity = treinadorEntity.getMochila();

        if (mochilaEntity == null) {
            mochilaEntity = objectMapper.convertValue(mochilaCreateDTO, MochilaEntity.class);
            mochilaEntity.setTreinador(treinadorEntity);
            mochilaEntity.setIdTreinador(mochilaEntity.getIdTreinador());
            this.mochilaRepository.save(mochilaEntity);

            return objectMapper.convertValue(mochilaEntity, MochilaDTO.class);
        }

        throw new RegraDeNegocioException("Mochila já existe.");
    }

    public MochilaDTO adicionarPokebola(Integer id, String tipoPokebola, Integer quantidadeAdicionada) throws Exception{
    	log.info("Chamado metodo adicionarPokebola");

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

        MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaRepository.save(mochila), MochilaDTO.class);
        return mochilaDTO;
    }

    public MochilaDTO usarPokebola(Integer id, String tipoPokebola) throws Exception {
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

        MochilaDTO mochilaDTO = objectMapper.convertValue(mochilaRepository.save(mochila), MochilaDTO.class);
        return mochilaDTO;
    }

    public MochilaDTO getMochilaLogado(Integer idUser) throws RegraDeNegocioException {
        MochilaEntity mochilaEntity = this.getMochilaPeloIdUser(idUser);
        mochilaEntity.setIdTreinador(mochilaEntity.getIdTreinador());
        return objectMapper.convertValue(mochilaEntity, MochilaDTO.class);
    }

    public MochilaCompletaDTO getMochilaCompleta(Integer id) throws Exception {
    	log.info("Chamado metodo getMochilaCompleta");

        MochilaEntity mochila = this.getMochilaPeloIdUser(id);
        
        MochilaCompletaDTO mochilaDTO = new MochilaCompletaDTO();
        mochilaDTO.setQuantidadeGreatBalls(mochila.getQuantidadeGreatBalls());
        mochilaDTO.setQuantidadeHeavyBalls(mochila.getQuantidadeHeavyBalls());
        mochilaDTO.setQuantidadeMasterBalls(mochila.getQuantidadeMasterBalls());
        mochilaDTO.setQuantidadeNetBalls(mochila.getQuantidadeNetBalls());
        mochilaDTO.setQuantidadePokeBalls(mochila.getQuantidadePokeBalls());
        mochilaDTO.setPokemons(mochila.getPokemons().stream()
                .map(pokemon -> {
                	PokemonDTO convertValue = objectMapper.convertValue(pokemon, PokemonDTO.class);
                    convertValue.setIdMochila(pokemon.getMochilaPokemon().getIdMochila());
                    if(pokemon.getNome() == null) convertValue.setNome("Não Nomeado");
                	return convertValue;
                })
                .collect(Collectors.toList())
        );
        mochilaDTO.setIdTreinador(mochila.getIdTreinador());
        mochilaDTO.setIdMochila(mochila.getIdMochila());
        
        return mochilaDTO;
    }

    public void deletarMochilaLogado(Integer idUser) throws RegraDeNegocioException {
        log.info("Chamado metodo deleteMochilaLogado");
        MochilaEntity mochila = this.getMochilaPeloIdUser(idUser);
        this.mochilaRepository.deleteById(mochila.getIdMochila());
    }
    
    public MochilaEntity getById(Integer id) throws RegraDeNegocioException {
    	return mochilaRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("O id Passado Não Existe"));
    }

    public MochilaEntity getMochilaPeloIdUser(Integer idUser) throws RegraDeNegocioException {
        TreinadorEntity treinadorEntity = userService.getById(idUser).getTreinador();

        if (treinadorEntity == null) throw new RegraDeNegocioException("Treinador não criado.");

        MochilaEntity mochilaEntity = treinadorEntity.getMochila();
        mochilaEntity.setIdTreinador(mochilaEntity.getIdTreinador());

        if (mochilaEntity == null) {
            throw new RegraDeNegocioException("Mochila não existe.");
        }

        return mochilaEntity;
    }

    public MochilaDTO getMochilaPeloId(Integer id) throws RegraDeNegocioException {
        MochilaEntity mochila = this.getById(id);
        mochila.setIdTreinador(mochila.getIdTreinador());
        return objectMapper.convertValue(mochila, MochilaDTO.class);
    }

    public void deletarMochilaPeloId(Integer id) throws RegraDeNegocioException {
        MochilaEntity mochila = this.getById(id);
        this.mochilaRepository.deleteById(mochila.getIdMochila());
    }
}
