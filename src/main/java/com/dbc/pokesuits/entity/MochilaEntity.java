package com.dbc.pokesuits.entity;

import java.util.Set;

import javax.persistence.*;

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
    private Integer idMochila;

    @Column(name = "id_treinador", insertable = false, updatable = false)
    private Integer idTreinador;

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
    @OneToMany(mappedBy="mochilaPokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PokemonEntity> pokemons;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_treinador", referencedColumnName = "id_treinador")
    private TreinadorEntity treinador;
}








