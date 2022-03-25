package com.dbc.pokesuits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.PokemonEntity;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer>{
	
	
	
}
