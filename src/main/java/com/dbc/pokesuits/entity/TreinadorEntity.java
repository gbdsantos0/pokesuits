package com.dbc.pokesuits.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.dbc.pokesuits.enums.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "treinador")
public class TreinadorEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_treinador")
    @SequenceGenerator(name = "seq_treinador", sequenceName = "seq_treinador", allocationSize = 1)
    @Column(name = "id_treinador")
    private Integer idTreinador;
    @Column(name = "id_user", insertable = false, updatable = false)
    private Integer idUser;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sexo")
    private Utils sexo;

    @JsonIgnore
    @OneToOne(mappedBy = "treinador", cascade = CascadeType.ALL, orphanRemoval = true)
    private MochilaEntity mochila;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;
}

