package com.dbc.pokesuits.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.entity.RegraEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.enums.NomesRegras;
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
	private final RegraService regraService;

	public UserDTO userLogado(Integer idUser) throws RegraDeNegocioException {
		log.info("Chamado metodo tranformarDto do User");
		UserEntity byId = getById(idUser);
		UserDTO userDTO = new UserDTO(byId.getId(), byId.getNome(), byId.getEmail(), byId.getUsername());
		return userDTO;
	}

	public Page<UserDTO> listarUsers(Integer pagina) {
		log.info("Chamado metodo ListarUsers;");

		Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10);

		List<UserDTO> collect = userRepository.findAll(pageable).stream().map(user -> {
			UserDTO convertValue = objectMapper.convertValue(user, UserDTO.class);
			/*
			 * convertValue.setTreinadores(user.getTreinadores() .stream() .map(treinador ->
			 * objectMapper.convertValue(treinador, TreinadorCreateDTO.class))
			 * .collect(Collectors.toList()) );
			 */
			return convertValue;
		}).collect(Collectors.toList());

		return new PageImpl<>(collect);
	}

	public UserDTO adicionarUser(UserCreateDTO createDTO) throws Exception {
		log.info("Chamado metodo AdicionarUser;");

		
		findByUsername(createDTO.getUsername()).orElseThrow(() ->new RegraDeNegocioException("Usuário já cadastrado no sistema"));
		
		// buscando grupos
		Set<RegraEntity> regraEntitySet = new HashSet<>();
		regraEntitySet.add(regraService.getById(3));

		// setando usuario
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(createDTO.getUsername());
		userEntity.setEmail(createDTO.getEmail());
		userEntity.setNome(createDTO.getNome());
		userEntity.setPassword(new BCryptPasswordEncoder().encode(createDTO.getPassword()));
		userEntity.setRegras(regraEntitySet);

		UserEntity userAtualizado = userRepository.save(userEntity);
		log.info("Criado o User de ID: " + userAtualizado.getId());

		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);
//		userDTO.setTreinadores(new ArrayList<TreinadorCreateDTO>());

		return userDTO;
	}

	public UserDTO removerUser(int id) throws RegraDeNegocioException {
		log.info("Chamado metodo RemoverUser;");

		UserEntity userRemovido = getById(id);

		UserDTO userDTO = objectMapper.convertValue(userRemovido, UserDTO.class);
		/*
		 * userDTO.setTreinadores(userRemovido.getTreinadores() .stream() .map(treinador
		 * -> objectMapper.convertValue(treinador, TreinadorCreateDTO.class))
		 * .collect(Collectors.toList()) );
		 */

		userRepository.deleteById(id);
		log.info("Removido o User de ID: " + userDTO.getId());

		return userDTO;
	}

	public UserDTO editarUser(UserCreateDTO createDTO, Integer id) throws RegraDeNegocioException {
		log.info("Chamado metodo editarUser;");

		UserEntity userConvertido = objectMapper.convertValue(createDTO, UserEntity.class);

		UserEntity userAtualizado = userRepository.save(userConvertido);
		log.info("Persistido as mudanças no User de ID: " + userAtualizado.getId());

		UserDTO userDTO = objectMapper.convertValue(userAtualizado, UserDTO.class);

		return userDTO;
	}

	public UserEntity getById(Integer idUser) throws RegraDeNegocioException {
		log.info("Chamado metodo getById do User");
		return userRepository.findById(idUser)
				.orElseThrow(() -> new RegraDeNegocioException("O ID do User passadso não Existe"));
	}

	public Optional<UserEntity> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
