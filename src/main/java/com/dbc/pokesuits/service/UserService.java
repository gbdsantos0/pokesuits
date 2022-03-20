package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.model.entity.User;
import com.dbc.pokesuits.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	public List<UserDTO> ListarUsers() {
		return userRepository.list()
				.stream()
				.map(user -> objectMapper.convertValue(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	public UserDTO AdicionarUser(UserCreateDTO createDTO) {
		
		User userConvertido = objectMapper.convertValue(createDTO, User.class);
		
		User userAtualizado = userRepository.create(userConvertido);
		
		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
		
		return userDTO;
	}

	public UserDTO RemoverUser(int id) throws RegraDeNegocioException {
		
		User userRemovido = userRepository.delete(id);
		
		UserDTO userDTO = objectMapper.convertValue(userRemovido, UserDTO.class);
		
		return userDTO;
	}

	public UserDTO editarUser(UserCreateDTO createDTO, Integer id) throws RegraDeNegocioException{
		User userConvertido = objectMapper.convertValue(createDTO, User.class);
		
		User userAtualizado = userRepository.update(id, userConvertido);
		
		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
		
		return userDTO;
	}
}
