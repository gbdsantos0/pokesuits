package com.dbc.pokesuits.model.entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
	
	private Integer id;
	private String nome;
	private String email;
	private Integer password;
	private String username;
	private ArrayList<Integer> idTreinadores;
	
}
