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
@Entity(name = "user_pokesuits")
public class UserEntity {
	
	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bobo")
	@SequenceGenerator(name = "bobo", sequenceName = "seq_user", allocationSize = 1)
	private Integer id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "username")
	private String username;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<TreinadorEntity> treinadores;
	
}
