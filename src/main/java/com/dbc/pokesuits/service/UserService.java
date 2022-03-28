package com.dbc.pokesuits.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ObjectMapper objectMapper;
	
	public Page<UserDTO> ListarUsers(Integer pagina) {
		log.info("Chamado metodo ListarUsers;");
		
		Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10);
		
		List<UserDTO> collect = userRepository.findAll(pageable)
				.stream()
				.map(user -> {
					UserDTO convertValue = objectMapper.convertValue(user, UserDTO.class);
					convertValue.setTreinadores(user.getTreinadores()
							.stream()
							.map(treinador -> objectMapper.convertValue(treinador, TreinadorCreateDTO.class))
							.toList()
					);
					return convertValue;
				})
				.collect(Collectors.toList());
		
		 return new PageImpl<>(collect);
	}

	public UserDTO AdicionarUser(UserCreateDTO createDTO) {
		log.info("Chamado metodo AdicionarUser;");
		
		UserEntity userConvertido = objectMapper.convertValue(createDTO, UserEntity.class);
		
		UserEntity userAtualizado = userRepository.save(userConvertido);
		log.info("Criado o User de ID: ", userAtualizado.getId());
		
		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
		
		return userDTO;
	}

	public UserDTO RemoverUser(int id) throws RegraDeNegocioException {
		log.info("Chamado metodo RemoverUser;");
		
		UserEntity userRemovido = getById(id);
		
		UserDTO userDTO = objectMapper.convertValue(userRemovido, UserDTO.class);
		userDTO.setTreinadores(userRemovido.getTreinadores()
				.stream()
				.map(treinador -> objectMapper.convertValue(treinador, TreinadorCreateDTO.class))
				.toList()
				);
		
		userRepository.deleteById(id);
		log.info("Removido o User de ID: ", userDTO.getId());
		
		
		return userDTO;
	}

	public UserDTO editarUser(UserCreateDTO createDTO, Integer id) throws RegraDeNegocioException{
		log.info("Chamado metodo editarUser;");
		
		UserEntity userConvertido = objectMapper.convertValue(createDTO, UserEntity.class);
		
		UserEntity userAtualizado = userRepository.save(userConvertido);
		log.info("Persistido as mudanças no User de ID: ", userAtualizado.getId());
		
		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
		
		return userDTO;
	}

	public UserEntity getById(Integer idUser) throws RegraDeNegocioException{
		log.info("Chamado metodo getById do User");
		return userRepository.findById(idUser).orElseThrow(() -> new RegraDeNegocioException("O ID do User passadso não Existe"));
	}
}
