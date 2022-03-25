package com.dbc.pokesuits.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "MOCHILA")
public class MochilaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOCHILA_SEQ")
    @SequenceGenerator(name = "MOCHILA_SEQ", sequenceName = "seq_mochila", allocationSize = 1)
    @Column(name = "id_mochila")
    private int idMochila;

    @Column(name = "quantidadegreatballs")
    private int quantidadeGreatBalls;

    @Column(name = "quantidadeheavyballs")
    private int quantidadeHeavyBalls;

    @Column(name = "quantidademasterballs")
    private int quantidadeMasterBalls;

    @Column(name = "quantidadenetballs")
    private int quantidadeNetBalls;

    @Column(name = "quantidadepokeballs")
    private int quantidadePokeBalls;

    @JsonIgnore
    @OneToMany(mappedBy="mochilaPokemon", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<PokemonEntity> pokemons;

    @JsonIgnore
    @OneToOne(mappedBy = "mochila", fetch = FetchType.LAZY)
    private TreinadorEntity treinador;
}








