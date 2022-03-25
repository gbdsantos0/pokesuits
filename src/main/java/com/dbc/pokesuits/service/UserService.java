package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	public List<UserDTO> ListarUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> objectMapper.convertValue(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	public UserDTO AdicionarUser(UserCreateDTO createDTO) {
		
		UserEntity userConvertido = objectMapper.convertValue(createDTO, UserEntity.class);
		
		UserEntity userAtualizado = userRepository.saveAndFlush(userConvertido);
		
		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
		
		return userDTO;
	}

	public UserDTO RemoverUser(int id) throws RegraDeNegocioException {
		
		UserEntity userRemovido = userRepository.getById(id);
		
		userRepository.deleteById(id);
		
		UserDTO userDTO = objectMapper.convertValue(userRemovido, UserDTO.class);
		
		return userDTO;
	}

	public UserDTO editarUser(UserCreateDTO createDTO, Integer id) throws RegraDeNegocioException{
		UserEntity userConvertido = objectMapper.convertValue(createDTO, UserEntity.class);
		
		UserEntity userAtualizado = userRepository.save(userConvertido);
		
		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
		
		return userDTO;
	}

	public UserDTO getById(Integer idUser){
		return objectMapper.convertValue(userRepository.getById(idUser), UserDTO.class);
	}
}
