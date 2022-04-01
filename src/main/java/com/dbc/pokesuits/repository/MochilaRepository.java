package com.dbc.pokesuits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.MochilaEntity;

import java.util.Optional;

@Repository
public interface MochilaRepository extends JpaRepository<MochilaEntity, Integer> {
}
