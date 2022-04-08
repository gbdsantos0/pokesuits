package com.dbc.pokesuits;

import com.dbc.pokesuits.dto.mochila.MochilaCreateDTO;
import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.MochilaRepository;
import com.dbc.pokesuits.service.MochilaService;
import com.dbc.pokesuits.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MochilaServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private MochilaRepository mochilaRepository;

    @InjectMocks
    private MochilaService mochilaService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void BeforeEach() {
        ReflectionTestUtils.setField(mochilaService,"objectMapper",objectMapper);
    }

    @Test
    public void testaSeAPokebolaFoiRetirada() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("string")
                .password("string")
                .email("string@string.com")
                .nome("string")
                .build();
        TreinadorEntity treinadorEntity = TreinadorEntity.builder()
                .idTreinador(1)
                .idUser(1)
                .user(userEntity)
                .nome("string")
                .sexo(Utils.FEMININO)
                .build();
        MochilaEntity mochilaEntity = MochilaEntity.builder()
                .idMochila(1)
                .idTreinador(1)
                .treinador(treinadorEntity)
                .quantidadeGreatBalls(0)
                .quantidadeHeavyBalls(6)
                .quantidadePokeBalls(12)
                .quantidadeMasterBalls(1)
                .quantidadeNetBalls(0)
                .build();

        userEntity.setTreinador(treinadorEntity);
        treinadorEntity.setMochila(mochilaEntity);

        try {
            when(this.userService.getById(any(Integer.class))).thenReturn(userEntity);
            when(this.mochilaRepository.save(any(MochilaEntity.class))).thenReturn(mochilaEntity);

            int valorInicial = mochilaService.getMochilaPeloIdUser(1).getQuantidadePokeBalls();

            this.mochilaService.usarPokebola(1, "pokeball");

            assertEquals(valorInicial - 1, this.mochilaService.getMochilaPeloIdUser(1).getQuantidadePokeBalls());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaSeAQuantidadeDePokebolasFoiIncrementadaNaMochila() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("string")
                .password("string")
                .email("string@string.com")
                .nome("string")
                .build();
        TreinadorEntity treinadorEntity = TreinadorEntity.builder()
                .idTreinador(1)
                .idUser(1)
                .user(userEntity)
                .nome("string")
                .sexo(Utils.FEMININO)
                .build();
        MochilaEntity mochilaEntity = MochilaEntity.builder()
                .idMochila(1)
                .idTreinador(1)
                .treinador(treinadorEntity)
                .quantidadeGreatBalls(0)
                .quantidadeHeavyBalls(6)
                .quantidadePokeBalls(12)
                .quantidadeMasterBalls(1)
                .quantidadeNetBalls(0)
                .build();

        userEntity.setTreinador(treinadorEntity);
        treinadorEntity.setMochila(mochilaEntity);

        try {
            when(this.userService.getById(any(Integer.class))).thenReturn(userEntity);
            when(this.mochilaRepository.save(any(MochilaEntity.class))).thenReturn(mochilaEntity);

            int valorInicial = this.mochilaService.getMochilaPeloIdUser(1).getQuantidadePokeBalls();

            this.mochilaService.adicionarPokebola(1, "pokeball", 2);

            assertEquals(valorInicial + 2, this.mochilaService.getMochilaPeloIdUser(1).getQuantidadePokeBalls());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaSeTreinadorDaMochilaNaoExiste() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("string")
                .password("string")
                .email("string@string.com")
                .nome("string")
                .build();

        try {
            when(this.userService.getById(any(Integer.class))).thenReturn(userEntity);

            Exception exception = assertThrows(RegraDeNegocioException.class, () -> this.mochilaService.getMochilaPeloIdUser(1));

            assertTrue(exception.getMessage().contains("Treinador não criado."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaSeMochilaFoiCriada() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("string")
                .password("string")
                .email("string@string.com")
                .nome("string")
                .build();
        TreinadorEntity treinadorEntity = TreinadorEntity.builder()
                .idTreinador(1)
                .idUser(1)
                .user(userEntity)
                .nome("string")
                .sexo(Utils.FEMININO)
                .build();

        userEntity.setTreinador(treinadorEntity);

        try {
            when(this.mochilaRepository.save(any())).thenReturn(any());

            MochilaCreateDTO mochilaCreateDTO = new MochilaCreateDTO();
            mochilaCreateDTO.setQuantidadeGreatBalls(0);
            mochilaCreateDTO.setQuantidadeHeavyBalls(0);
            mochilaCreateDTO.setQuantidadeMasterBalls(0);
            mochilaCreateDTO.setQuantidadeNetBalls(0);
            mochilaCreateDTO.setQuantidadePokeBalls(0);

            MochilaEntity mochila = MochilaEntity.builder()
                    .idMochila(1)
                    .idTreinador(1)
                    .quantidadeGreatBalls(mochilaCreateDTO.getQuantidadeGreatBalls())
                    .quantidadeHeavyBalls(mochilaCreateDTO.getQuantidadeHeavyBalls())
                    .quantidadeMasterBalls(mochilaCreateDTO.getQuantidadeMasterBalls())
                    .quantidadeNetBalls(mochilaCreateDTO.getQuantidadeNetBalls())
                    .quantidadePokeBalls(mochilaCreateDTO.getQuantidadePokeBalls())
                    .treinador(treinadorEntity)
                    .pokemons(null)
                    .build();

            this.mochilaRepository.save(mochila);

            verify(mochilaRepository, Mockito.times(1)).save(mochila);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaExceptionCriacaoDeMochilaEmTreinadorNaoExistente() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("string")
                .password("string")
                .email("string@string.com")
                .nome("string")
                .build();

        MochilaCreateDTO mochilaCreateDTO = new MochilaCreateDTO();
        mochilaCreateDTO.setQuantidadeGreatBalls(0);
        mochilaCreateDTO.setQuantidadeHeavyBalls(0);
        mochilaCreateDTO.setQuantidadeMasterBalls(0);
        mochilaCreateDTO.setQuantidadeNetBalls(0);
        mochilaCreateDTO.setQuantidadePokeBalls(0);

        try {
            when(this.userService.getById(any(Integer.class))).thenReturn(userEntity);

            Exception exception = assertThrows(RegraDeNegocioException.class, () -> this.mochilaService.createMochilaLogado(mochilaCreateDTO, 1));

            assertNotNull(mochilaCreateDTO);
            assertTrue(exception.getMessage().contains("Treinador não criado."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
