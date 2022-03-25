package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.model.entity.PokemonBase;

@Repository
public class PokemonBaseRepository {
    private static List<PokemonBase> listaPokemonBase = new ArrayList<>();

    public PokemonBaseRepository(){
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(1)
                .racaPokemon("Bulbasaur")
                .pesoMinimo(6.7)
                .pesoMaximo(11.0)
                .porcentagemMacho(87.5)
                .levelMinimo(5)
                .dificuldade(Dificuldades.FACIL)
                .tipo1(TipoPokemon.GRASS)
                .tipo2(TipoPokemon.POISON)
                .raridade(Raridades.COMUM)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(2)
                .racaPokemon("Ivysaur")
                .pesoMinimo(13.0)
                .pesoMaximo(20.0)
                .porcentagemMacho(87.5)
                .levelMinimo(16)
                .dificuldade(Dificuldades.MEDIO)
                .tipo1(TipoPokemon.GRASS)
                .tipo2(TipoPokemon.POISON)
                .raridade(Raridades.RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(3)
                .racaPokemon("Venusaur")
                .pesoMinimo(100.0)
                .pesoMaximo(150.0)
                .porcentagemMacho(87.5)
                .levelMinimo(32)
                .dificuldade(Dificuldades.DIFICIL)
                .tipo1(TipoPokemon.GRASS)
                .tipo2(TipoPokemon.POISON)
                .raridade(Raridades.SUPER_RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(4)
                .racaPokemon("Charmander")
                .pesoMinimo(8.5)
                .pesoMaximo(15.5)
                .porcentagemMacho(87.5)
                .levelMinimo(5)
                .dificuldade(Dificuldades.FACIL)
                .tipo1(TipoPokemon.FIRE)
                .tipo2(null)
                .raridade(Raridades.COMUM)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(5)
                .racaPokemon("Charmeleon")
                .pesoMinimo(19.0)
                .pesoMaximo(36.0)
                .porcentagemMacho(87.5)
                .levelMinimo(16)
                .dificuldade(Dificuldades.MEDIO)
                .tipo1(TipoPokemon.FIRE)
                .tipo2(null)
                .raridade(Raridades.RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(6)
                .racaPokemon("Charizard")
                .pesoMinimo(90.5)
                .pesoMaximo(142.5)
                .porcentagemMacho(87.5)
                .levelMinimo(36)
                .dificuldade(Dificuldades.DIFICIL)
                .tipo1(TipoPokemon.FIRE)
                .tipo2(TipoPokemon.DRAGON)
                .raridade(Raridades.SUPER_RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(7)
                .racaPokemon("Squirtle")
                .pesoMinimo(9.0)
                .pesoMaximo(17.0)
                .porcentagemMacho(87.5)
                .levelMinimo(5)
                .dificuldade(Dificuldades.FACIL)
                .tipo1(TipoPokemon.WATER)
                .tipo2(null)
                .raridade(Raridades.COMUM)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(8)
                .racaPokemon("Wartortle")
                .pesoMinimo(22.5)
                .pesoMaximo(37.5)
                .porcentagemMacho(87.5)
                .levelMinimo(16)
                .dificuldade(Dificuldades.MEDIO)
                .tipo1(TipoPokemon.WATER)
                .tipo2(null)
                .raridade(Raridades.RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(9)
                .racaPokemon("Blastoise")
                .pesoMinimo(85.5)
                .pesoMaximo(120.0)
                .porcentagemMacho(87.5)
                .levelMinimo(36)
                .dificuldade(Dificuldades.DIFICIL)
                .tipo1(TipoPokemon.WATER)
                .tipo2(null)
                .raridade(Raridades.SUPER_RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(74)
                .racaPokemon("Geodude")
                .pesoMinimo(20.0)
                .pesoMaximo(38.0)
                .porcentagemMacho(50.0)
                .levelMinimo(1)
                .dificuldade(Dificuldades.FACIL)
                .tipo1(TipoPokemon.ROCK)
                .tipo2(TipoPokemon.GROUND)
                .raridade(Raridades.COMUM)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(75)
                .racaPokemon("Graveler")
                .pesoMinimo(105.0)
                .pesoMaximo(170.0)
                .porcentagemMacho(50.0)
                .levelMinimo(16)
                .dificuldade(Dificuldades.MEDIO)
                .tipo1(TipoPokemon.ROCK)
                .tipo2(TipoPokemon.GROUND)
                .raridade(Raridades.RARO)
                .build());
        listaPokemonBase.add(PokemonBase.builder()
                .idPokemonBase(76)
                .racaPokemon("Golem")
                .pesoMinimo(300.0)
                .pesoMaximo(420.0)
                .porcentagemMacho(50.0)
                .levelMinimo(36)
                .dificuldade(Dificuldades.DIFICIL)
                .tipo1(TipoPokemon.ROCK)
                .tipo2(TipoPokemon.GROUND)
                .raridade(Raridades.SUPER_RARO)
                .build());
    }

    public List<PokemonBase> listAll(){
        return listaPokemonBase;
    }

    public PokemonBase getById(Integer id){
        PokemonBase pokemonBase = listaPokemonBase.stream()
                .filter(pb -> pb.getIdPokemonBase().equals(id))
                .findFirst()
                .orElse(null);
        return pokemonBase;
    }
}
