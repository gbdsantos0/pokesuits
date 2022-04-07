package com.dbc.pokesuits;

import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testaSeUsuarioExiste() {
        try {
            assertNotNull(this.userService.userLogado(6));
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaSeDaExcecaoComUserNaoCriado() {
        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
                this.userService.getById(0);
        });

        assertTrue(exception.getMessage().contains("O ID do User passadso não Existe"));
    }
}
