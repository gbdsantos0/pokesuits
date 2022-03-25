package com.dbc.pokesuits.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@SequenceGenerator(name = "bobo", sequenceName = "seq_user", initialValue = 1)
	private Integer id;
	@Column(name = "id_user")
	private String nome;
	@Column(name = "id_user")
	private String email;
	@Column(name = "id_user")
	private Integer password;
	@Column(name = "id_user")
	private String username;
	@JsonIgnore
	@OneToMany(mappedBy = "treinadores", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name="id_User", referencedColumnName = "id_User")
	private Set<TreinadorEntity> treinadores;
	
}
