package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.exceptions.InvalidCenarioException;

@Repository
public interface MochilaRepository extends JpaRepository<MochilaEntity, Integer> {

}
