package com.dbc.pokesuits.repository;

import com.dbc.pokesuits.entity.RegraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraRepository extends JpaRepository<RegraEntity, Integer> {}
