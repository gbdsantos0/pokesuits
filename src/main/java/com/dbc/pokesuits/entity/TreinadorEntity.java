package com.dbc.pokesuits.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.dbc.pokesuits.enums.Utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TreinadorEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_treinador")
    @SequenceGenerator(name = "seq_treinador", sequenceName = "seq_treinador", allocationSize = 1)
    @Column(name = "id_treinador")
    private Integer idTreinador;
    @Column(name = "id_mochila", insertable = false, updatable = false)
    private Integer idMochila;
    @Column(name = "id_user", insertable = false, updatable = false)
    private Integer idUser;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sexo")
    private Utils sexo;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mochila", referencedColumnName = "id_mochila")
    private MochilaEntity mochila;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;
}

