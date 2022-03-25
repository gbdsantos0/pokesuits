package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.Pokemon;
import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;

@Repository
public class PokemonRepository {
	
	private ArrayList<Pokemon> listPokemons = new ArrayList<>();
	private AtomicInteger COUNTER = new AtomicInteger();
	
	public PokemonRepository() {
		listPokemons.add(new Pokemon("bulbosauro",(100.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new Pokemon("superbulbosauro",(200.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new Pokemon("hyperbulbosauro",(300.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new Pokemon("megabulbosauro",(400.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
		listPokemons.add(new Pokemon("ultimatebulbosauro",(500.1), Utils.MASCULINO, COUNTER.incrementAndGet(), "bobao", 10
				, Dificuldades.FACIL,TipoPokemon.BUG,null,Raridades.RARO,1));
	}
	
	public Optional<Pokemon> getByid(Integer id) {
    	return 	 listPokemons.stream()
                .filter(poke -> poke.getIdPokemon().equals(id))
                .findFirst();
    }
    public Pokemon save(Pokemon pokemon){
    	pokemon.setIdPokemon(COUNTER.incrementAndGet());
        listPokemons.add(pokemon);
    	return pokemon;
    }

    public List<Pokemon> findAll() {
        return listPokemons;
    }

    public Pokemon update(Integer id, Pokemon pokemon) throws RegraDeNegocioException{
    	
    	Optional<Pokemon> pokemonById = this.getByid(id);
    	if(pokemonById.isEmpty())throw new RegraDeNegocioException("Não Existe um Pokemon com o ID Passado");
    	Pokemon pokemonRecuperado = pokemonById.get();

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

    public Pokemon deleteById(Integer id) throws RegraDeNegocioException{
    	
    	Optional<Pokemon> pokemonById = this.getByid(id);
    	if(pokemonById.isEmpty())throw new RegraDeNegocioException("Não Existe um Pokemon com o ID Passado");
    	Pokemon pokemonRecuperado = pokemonById.get();
    	
        listPokemons.remove(pokemonRecuperado);
		return pokemonRecuperado;
    }

    public List<Pokemon> findByNameContainsIgnoreCase(String nome) {
        return listPokemons.stream()
                .filter(pokemon -> pokemon.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
	
}
