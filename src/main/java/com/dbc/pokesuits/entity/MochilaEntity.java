package com.dbc.pokesuits.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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
    @OneToMany(mappedBy="mochila", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<PokemonEntity> pokemons;

    @JsonIgnore
    @OneToOne(mappedBy = "mochila")
    private TreinadorEntity treinador;
}








