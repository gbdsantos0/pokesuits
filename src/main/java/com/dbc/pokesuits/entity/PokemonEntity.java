package com.dbc.pokesuits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.dbc.pokesuits.enums.Dificuldades;
import com.dbc.pokesuits.enums.Raridades;
import com.dbc.pokesuits.enums.TipoPokemon;
import com.dbc.pokesuits.enums.Utils;
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
@Entity(name = "pokemon")
public class PokemonEntity{
	
	@Id
	@Column(name = "id_pokemon")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bobobo")
	@SequenceGenerator(name = "bobobo", sequenceName = "seq_pokemon", allocationSize = 1)
	private Integer idPokemon;
	@Column(name = "racapokemon")
	private String racaPokemon;
	@Column(name = "peso")
    private Double peso;
	@Column(name = "sexo")
    private Utils sexo;
	@Column(name = "nome")
    private String nome;
	@Column(name = "level")
    private Integer level;
	@Column(name = "dificuldade")
    private Dificuldades dificuldade;
	@Column(name = "tipo1")
    private TipoPokemon tipo1;
	@Column(name = "tipo2")
    private TipoPokemon tipo2;
	@Column(name = "raridade")
    private Raridades raridade;
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_mochila", referencedColumnName = "id_mochila")
    private MochilaEntity mochilaPokemon;
    
}
