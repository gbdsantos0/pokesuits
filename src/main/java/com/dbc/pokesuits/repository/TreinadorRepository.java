package com.dbc.pokesuits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.TreinadorEntity;

@Repository
public interface TreinadorRepository extends JpaRepository<TreinadorEntity, Integer> {
}
