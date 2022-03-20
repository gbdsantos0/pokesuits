package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.dto.cenario.CenarioDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonCreateDTO;
import com.dbc.pokesuits.dto.pokemon.PokemonDTO;
import com.dbc.pokesuits.dto.pokemonbase.PokemonBaseDTO;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;
import com.dbc.pokesuits.model.interfaces.Pokebola;
import com.dbc.pokesuits.model.pokebolas.*;
import com.dbc.pokesuits.repository.CenarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Log
public class CenarioService {
    @Autowired
    private CenarioRepository cenarioRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PokemonBaseService pokemonBaseService;
    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private TreinadorService treinadorService;
    @Autowired
    private MochilaService mochilaService;

    //todo sempre lembrar de setar como null após pokemon fugir, ser capturado ou o treinador sair do encontro
    private PokemonCreateDTO ultimoPokemonEncontrado;
    //cenario atual
    private Integer cenarioAtual=1;
    //contador para chance de fugir
    private int contador=0;


    public PokemonDTO capturar(String tipoPokebola, Integer idTreinador) throws Exception{
        Random r = new Random();
        TreinadorDTO treinadorDTO = treinadorService.getById(idTreinador);

        Pokebola pokebola;

        if(ultimoPokemonEncontrado==null){
            throw new InvalidCenarioException("Nenhum pokemon encontrado para capturar");
        }

        switch (tipoPokebola.toLowerCase(Locale.ROOT)){
            case "greatball":
                pokebola = new GreatBall();
                break;
            case "pokeball":
                pokebola = new PokeBall();
                break;
            case "heavyball":
                pokebola = new HeavyBall();
                break;
            case "masterball":
                pokebola = new MasterBall();
                break;
            case "netball":
                pokebola = new NetBall();
                break;
            default:
                throw new InvalidCenarioException("Tipo de pokebola inválida, favor utilizar uma das disponíveis (PokeBall, GreatBall, NetBall, HeavyBall ou MasterBall)");
        }
        //jogar pokebola
        mochilaService.usarPokebola(treinadorDTO.getIdMochila(),tipoPokebola);
        //testar chance
        if(r.nextInt(100) <= pokebola.calcularChance(ultimoPokemonEncontrado)){
            //alterar idMochila para qual o pokemon pertence agora
            ultimoPokemonEncontrado.setIdMochila(treinadorDTO.getIdMochila());
            //adicionar o pokemon na lista de pokemons
            PokemonDTO pokemonDTO = pokemonService.adicionarPokemon(ultimoPokemonEncontrado);
            //limpando ultimo encontro
            ultimoPokemonEncontrado = null;
            contador = 0;
            log.info("Pokemon capturado com sucesso");
            return pokemonDTO;
        }else {
            if (r.nextInt(100) > (30 - ultimoPokemonEncontrado.getDificuldade().getChance())+(contador * 2)) {
                //contagem para chance do pokemon escapar
                contador++;
                log.info("Pokemon não capturado");
            } else {
                //pokemon fugiu
                ultimoPokemonEncontrado = null;
                contador = 0;
                log.info("Pokemon escapou");
                throw new InvalidCenarioException("O Pokemon escapou!");
            }
        }

        throw new InvalidCenarioException("Pokemon não capturado");//todo informar alguma outra coisa ao inves de uma excessao?
    };

    public PokemonCreateDTO gerarPokemon() throws Exception{
        Random r = new Random();
        PokemonBaseDTO pokemonBaseDTO;
        pokemonBaseDTO = this.selecionarPokemon();
        contador = 0;
        int randLevel = r.nextInt(8)+cenarioRepository.listAll().get(cenarioAtual-1).getLevelMedio()-4;//variacao de 4 levels pra cima ou pra baixo
        //garantir que não ha niveis nogativos
        if(randLevel<1){
            randLevel=1;
        }

        ultimoPokemonEncontrado = PokemonCreateDTO.builder()
                //raca conforme a base
                .racaPokemon(pokemonBaseDTO.getRacaPokemon())
                //peso no intervalo de peso do pokemon
                .peso(r.nextInt((int)(pokemonBaseDTO.getPesoMaximo()-pokemonBaseDTO.getPesoMinimo()))+pokemonBaseDTO.getPesoMinimo()+((double)r.nextInt(100)/100))
                //sexo de acordo com a chance de ser macho
                .sexo(r.nextInt(100)<=pokemonBaseDTO.getPorcentagemMacho()? Utils.MASCULINO:Utils.FEMININO)
                //apelido nulo por enquanto
                .nome(null)
                //level igual ao calculado pelo local ou o minimo do pokemon encontrado
                .level(Math.max(randLevel,pokemonBaseDTO.getLevelMinimo()))
                //outras informacoes iguais ao pokemon base
                .dificuldade(pokemonBaseDTO.getDificuldade())
                .tipo1(pokemonBaseDTO.getTipo1())
                .tipo2(pokemonBaseDTO.getTipo2())
                .raridade(pokemonBaseDTO.getRaridade())
                .build();
        return ultimoPokemonEncontrado;
    }

    public PokemonBaseDTO selecionarPokemon() throws Exception{
        if(cenarioRepository.getById(cenarioAtual).getIdPokemonsDisponiveis().isEmpty()){
            throw new InvalidCenarioException("Lista de pokemons do cenário atual vazia");
        }

        List<PokemonBaseDTO> superRaros = cenarioRepository.getIdPokemonsDisponiveis(cenarioAtual).stream()
                .filter(c -> pokemonBaseService.getById(c).getRaridade() == Raridades.SUPER_RARO)
                .map(c->pokemonBaseService.getById(c))
                .collect(Collectors.toList());
        List<PokemonBaseDTO> raros = cenarioRepository.getIdPokemonsDisponiveis(cenarioAtual).stream()
                .filter(c -> pokemonBaseService.getById(c).getRaridade() == Raridades.RARO)
                .map(c->pokemonBaseService.getById(c))
                .collect(Collectors.toList());
        List<PokemonBaseDTO> comuns = cenarioRepository.getIdPokemonsDisponiveis(cenarioAtual).stream()
                .filter(c -> pokemonBaseService.getById(c).getRaridade() == Raridades.COMUM)
                .map(c->pokemonBaseService.getById(c))
                .collect(Collectors.toList());

        Random r = new Random();

        int valorAleatorio;
        if(comuns.isEmpty()){
            //somente super raros = retorna um super raro
            if(raros.isEmpty()){
                return superRaros.get(r.nextInt(superRaros.size()));
            }
            //somente raros = retorna um raro
            else if(superRaros.isEmpty()){
                return raros.get(r.nextInt(raros.size()));
            }
            //raros e super raros = calcula probabilidade proporcional
            else{
                valorAleatorio = r.nextInt(25);

                if(valorAleatorio<5){
                    return superRaros.get(r.nextInt(superRaros.size()));
                }else{
                    return raros.get(r.nextInt(raros.size()));
                }
            }
        }
        else if(raros.isEmpty()){
            //somente comuns
            if(superRaros.isEmpty()){
                return comuns.get(r.nextInt(comuns.size()));
            }
            //comuns e super raros = calcula probabilidade proporcional
            else{
                valorAleatorio = r.nextInt(80);
                if(valorAleatorio<5){
                    return superRaros.get(r.nextInt(superRaros.size()));
                }else{
                    return comuns.get(r.nextInt(comuns.size()));
                }
            }
        }
        //comuns e raros = calcula probabilidade proporcional
        else if(superRaros.isEmpty()){
            valorAleatorio = r.nextInt(95);
            if(valorAleatorio<20){
                return raros.get(r.nextInt(raros.size()));
            }else{
                return comuns.get(r.nextInt(comuns.size()));
            }
        }
        //todos disponiveis
        else{
            valorAleatorio = r.nextInt(100);

            if(valorAleatorio<5){
                return superRaros.get(r.nextInt(superRaros.size()));
            }else if(valorAleatorio<25){
                return raros.get(r.nextInt(raros.size()));
            }else{
                return comuns.get(r.nextInt(comuns.size()));
            }
        }
    }

    public CenarioDTO alterarCenario(Integer id) throws Exception{
        CenarioDTO cenarioDTO = objectMapper.convertValue(cenarioRepository.getById(id), CenarioDTO.class);
        cenarioAtual=id;
        return cenarioDTO;
    }

    public  CenarioDTO cenarioAtual() throws Exception{
        CenarioDTO cenarioDTO = objectMapper.convertValue(cenarioRepository.getById(cenarioAtual), CenarioDTO.class);
        return cenarioDTO;
    }

}
