package com.dbc.pokesuits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.dto.user.UserEditDto;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.UserRepository;
import com.dbc.pokesuits.service.RegraService;
import com.dbc.pokesuits.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;
	@Mock
	private RegraService regraService;

	@Test
	public void testaSeQualquerUsuarioExiste() {
		try {
			UserEntity userEntity = UserEntity.builder().id(1).username("string").password("string")
					.email("string@string.com").nome("string").build();

			when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(userEntity));

			assertNotNull(this.userService.getById(1));
		} catch (RegraDeNegocioException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testaSeUsuarioLogadoExiste() {
		try {
			UserEntity userEntity = UserEntity.builder().id(1).username("string").password("string")
					.email("string@string.com").nome("string").build();

			when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(userEntity));

			assertNotNull(this.userService.userLogado(1));
		} catch (RegraDeNegocioException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testaSeDaExcecaoComIdDeUmUserQueNaoExiste() {
		when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

		Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
			this.userService.getById(0);
		});
		assertTrue(exception.getMessage().contains("O ID do User passadso não Existe"));
	}

	@Test
	public void testaListarTodosOsUsers() {
		try {
			List<UserEntity> users = Arrays.asList(
					UserEntity.builder().id(1).username("string").password("string").email("string@string.com")
							.nome("string").build(),
					UserEntity.builder().id(2).username("string").password("string").email("string@string.com")
							.nome("string").build(),
					UserEntity.builder().id(3).username("string").password("string").email("string@string.com")
							.nome("string").build());

			when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(users));

			Pageable pageable = PageRequest.of(0, 10);

			List<UserDTO> collect = userRepository.findAll(pageable).stream()
					.map(user -> new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getUsername()))
					.collect(Collectors.toList());

			assertEquals(userService.listarUsers(null), new PageImpl<>(collect));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testaSeEPossivelEditarUmUser() {
		try {
			UserEditDto edit = UserEditDto.builder().email("batata@batata.com").nome("joao").build();
			UserEntity userEntity = UserEntity.builder().id(1).username("string").password("string")
					.email("string@string.com").nome("string").build();
			UserEntity userAtualizado = userEntity;
			userAtualizado.setEmail(edit.getEmail());
			userAtualizado.setNome(edit.getNome());
			
			when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(userEntity));
			when(userRepository.save(userEntity)).thenReturn(userAtualizado);
			UserDTO editarUser = userService.editarUser(edit, 1);
			
			assertEquals(editarUser.getNome(), edit.getNome());
			assertEquals(editarUser.getEmail(), edit.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
