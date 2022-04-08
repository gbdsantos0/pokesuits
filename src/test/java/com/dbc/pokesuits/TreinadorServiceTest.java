package com.dbc.pokesuits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.TreinadorRepository;
import com.dbc.pokesuits.service.TreinadorService;
import com.dbc.pokesuits.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class TreinadorServiceTest {
    @InjectMocks
    private TreinadorService treinadorService;

    @Mock
    private TreinadorRepository treinadorRepository;
    @Mock
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void BeforeEach() {
        ReflectionTestUtils.setField(treinadorService,"objectMapper",objectMapper);
    }

    @Test
    public void acessoAoCreateOk(){
        try {

            UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .username("string")
                    .password("string")
                    .email("string@string.com")
                    .nome("string").build();
            TreinadorEntity treinadorSalvo = TreinadorEntity.builder().idTreinador(1).idUser(1).user(userEntity).nome("string").sexo(Utils.FEMININO).build();

            when(userService.getById(any(Integer.class))).thenReturn(userEntity);
            when(treinadorRepository.save(any(TreinadorEntity.class))).thenReturn(treinadorSalvo);


            TreinadorCreateDTO treinadorCreateDTO = new TreinadorDTO();
            treinadorCreateDTO.setNome("string");
            treinadorCreateDTO.setSexo(Utils.FEMININO);

            TreinadorDTO treinadorCriado = treinadorService.create(treinadorCreateDTO, 1);
            assertEquals(treinadorSalvo.getNome(), treinadorCriado.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acessoAoUpdateOk(){
        try {
            UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .username("string")
                    .password("string")
                    .email("string@string.com")
                    .nome("string").build();
            TreinadorEntity treinador = TreinadorEntity.builder().idTreinador(1).idUser(1).user(userEntity).nome("string").sexo(Utils.FEMININO).build();
            userEntity.setTreinador(treinador);

            when(userService.getById(any(Integer.class))).thenReturn(userEntity);

            //update no treinadorEntity
            treinador.setNome("string2");
            when(treinadorRepository.save(any(TreinadorEntity.class))).thenReturn(treinador);

            TreinadorCreateDTO treinadorCreateDTO = new TreinadorDTO();
            treinadorCreateDTO.setNome("string2");
            treinadorCreateDTO.setSexo(Utils.FEMININO);

            TreinadorDTO treinadorAtualizado = treinadorService.update(1,treinadorCreateDTO);
            assertEquals(treinador.getNome(), treinadorAtualizado.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acessoAoCreateFailTreinadorJaExiste(){
        try {
            UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .username("string")
                    .password("string")
                    .email("string@string.com")
                    .nome("string").build();
            TreinadorEntity treinadorSalvo = TreinadorEntity.builder().idTreinador(1).idUser(1).user(userEntity).nome("string").sexo(Utils.FEMININO).build();
            userEntity.setTreinador(treinadorSalvo);

            TreinadorCreateDTO treinadorCreateDTO = new TreinadorDTO();
            treinadorCreateDTO.setNome("string");
            treinadorCreateDTO.setSexo(Utils.FEMININO);

            when(userService.getById(any(Integer.class))).thenReturn(userEntity);

            RegraDeNegocioException exception =
                    assertThrows(RegraDeNegocioException.class, ()->treinadorService.create(treinadorCreateDTO,0));
            assertTrue(exception.getMessage().contains("O usuário já possui um treinador"));
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void acessoAoUpdateFailSemTreinador(){
        try {
            UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .username("string")
                    .password("string")
                    .email("string@string.com")
                    .nome("string").build();
            TreinadorCreateDTO treinadorCreateDTO = new TreinadorDTO();
            treinadorCreateDTO.setNome("string");
            treinadorCreateDTO.setSexo(Utils.FEMININO);

            when(userService.getById(any(Integer.class))).thenReturn(userEntity);

            RegraDeNegocioException exception =
                    assertThrows(RegraDeNegocioException.class, ()->treinadorService.update(0,treinadorCreateDTO));
            assertTrue(exception.getMessage().contains("O usuário não possui um treinador"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTreinadorIdInexistente(){
        RegraDeNegocioException exception =
                assertThrows(RegraDeNegocioException.class, ()->treinadorService.getById(0),
                        "Testou getById com id inexistente que deveria propagar uma RegraDeNegocioException, mas não devolveu");
        assertTrue(exception.getMessage().contains("O id Passado não existe"));
    }
}
