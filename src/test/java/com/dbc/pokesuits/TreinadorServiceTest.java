package com.dbc.pokesuits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbc.pokesuits.dto.treinador.TreinadorCreateDTO;
import com.dbc.pokesuits.dto.treinador.TreinadorDTO;
import com.dbc.pokesuits.entity.TreinadorEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.enums.Utils;
import com.dbc.pokesuits.repository.TreinadorRepository;
import com.dbc.pokesuits.repository.UserRepository;
import com.dbc.pokesuits.service.TreinadorService;
import com.dbc.pokesuits.service.UserService;

//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TreinadorServiceTest {
    @InjectMocks
//    @Autowired
    private TreinadorService treinadorService;

    @Mock
    private TreinadorRepository treinadorRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    public void testaTreinadorComNomeAtualizado(){
        try {
            TreinadorEntity treinador = treinadorService.getById(4);
            TreinadorCreateDTO treinadorUpdate = new TreinadorCreateDTO();

            String nomeGeradoAleatoriamente = RandomStringUtils.randomAlphabetic(10);

            treinadorUpdate.setNome(nomeGeradoAleatoriamente);
            treinadorUpdate.setSexo(treinador.getSexo());
            treinadorService.update(treinador.getIdUser(),treinadorUpdate);

            TreinadorEntity treinador2 = treinadorService.getById(4);

            //atualiza o nome para o assert equals
            treinador.setNome(nomeGeradoAleatoriamente);
            assertEquals(treinador.getNome(), treinador2.getNome());
            //teste
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @org.junit.jupiter.api.Test
    public void acessoAoCreate(){
        try {
            TreinadorEntity treinadorSalvo = TreinadorEntity.builder().idTreinador(1).idUser(1).nome("string").sexo(Utils.FEMININO).build();
            UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .username("string")
                    .password("string")
                    .email("string@string.com")
                    .nome("string")
                    /*.treinador(treinadorSalvo)*/.build();
            treinadorSalvo.setUser(userEntity);

            when(userRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(userEntity));
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

//    @Test
//    public void getTreinadorIdInexistente(){
//        RegraDeNegocioException exception =
//                assertThrows(RegraDeNegocioException.class, ()->treinadorService.getById(0),
//                        "Testou getById com id inexistente que deveria propagar uma RegraDeNegocioException, mas não devolveu");
//        assertTrue(exception.getMessage().contains("O id Passado não existe"));
//    }
}
