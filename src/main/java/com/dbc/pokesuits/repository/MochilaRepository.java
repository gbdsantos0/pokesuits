package com.dbc.pokesuits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.MochilaEntity;

@Repository
public interface MochilaRepository extends JpaRepository<MochilaEntity, Integer> {
}
