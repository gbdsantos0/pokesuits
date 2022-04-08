package com.dbc.pokesuits;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.UserRepository;
import com.dbc.pokesuits.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
//    @Mock
//    private RegraService regraService;

    @Before
    public void BeforeEach() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testaSeUsuarioExiste() {
        try {
        	UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .username("string")
                    .password("string")
                    .email("string@string.com")
                    .nome("string").build();

            when(userService.getById(anyInt())).thenReturn(userEntity);
        	
        	UserDTO userLogado = this.userService.userLogado(1);
        	
        	System.out.println(userLogado.getId());

            assertNotNull(userLogado);
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testaSeDaExcecaoComUserNaoCriado() {
//    	try {
//			when(userService.userLogado(anyInt())).thenReturn(null);
//    	} catch (RegraDeNegocioException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
//            this.userService.getById(0);
//    	});
//        assertTrue(exception.getMessage().contains("O ID do User passadso não Existe"));
//    }
}
