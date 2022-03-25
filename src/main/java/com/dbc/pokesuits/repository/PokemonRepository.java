package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.PokemonEntity;
import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;

@Repository
public class PokemonRepository {
	
	private ArrayList<PokemonEntity> listPokemons = new ArrayList<>();
	private AtomicInteger COUNTER = new AtomicInteger();
	
	public PokemonRepository() {
		listPokemons.add(new PokemonEntity("bulbosauro",(100.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new PokemonEntity("superbulbosauro",(200.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new PokemonEntity("hyperbulbosauro",(300.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new PokemonEntity("megabulbosauro",(400.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new PokemonEntity("ultimatebulbosauro",(500.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
	}
	
	public Optional<PokemonEntity> getByid(Integer id) {
    	return 	 listPokemons.stream()
                .filter(poke -> poke.getIdPokemon().equals(id))
                .findFirst();
    }
    public PokemonEntity save(PokemonEntity pokemon){
    	pokemon.setIdPokemon(COUNTER.incrementAndGet());
        listPokemons.add(pokemon);
    	return pokemon;
    }

    public List<PokemonEntity> findAll() {
        return listPokemons;
    }

    public PokemonEntity update(Integer id, PokemonEntity pokemon) throws RegraDeNegocioException{
    	
    	Optional<PokemonEntity> pokemonById = this.getByid(id);
    	if(pokemonById.isEmpty())throw new RegraDeNegocioException("Não Existe um Pokemon com o ID Passado");
    	PokemonEntity pokemonRecuperado = pokemonById.get();

        pokemonRecuperado.setDificuldade(pokemon.getDificuldade());
        pokemonRecuperado.setIdMochila(pokemon.getIdMochila());
        pokemonRecuperado.setLevel(pokemon.getLevel());
        pokemonRecuperado.setNome(pokemon.getNome());
        pokemonRecuperado.setPeso(pokemon.getPeso());
        pokemonRecuperado.setRacaPokemon(pokemon.getRacaPokemon());
        pokemonRecuperado.setRaridade(pokemon.getRaridade());
        pokemonRecuperado.setSexo(pokemon.getSexo());
        pokemonRecuperado.setTipo1(pokemon.getTipo1());
        pokemonRecuperado.setTipo2(pokemon.getTipo2());

        return pokemonRecuperado;
    }

    public PokemonEntity deleteById(Integer id) throws RegraDeNegocioException{
    	
    	Optional<PokemonEntity> pokemonById = this.getByid(id);
    	if(pokemonById.isEmpty())throw new RegraDeNegocioException("Não Existe um Pokemon com o ID Passado");
    	PokemonEntity pokemonRecuperado = pokemonById.get();
    	
        listPokemons.remove(pokemonRecuperado);
		return pokemonRecuperado;
    }

    public List<PokemonEntity> findByNameContainsIgnoreCase(String nome) {
        return listPokemons.stream()
                .filter(pokemon -> pokemon.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
	
}
