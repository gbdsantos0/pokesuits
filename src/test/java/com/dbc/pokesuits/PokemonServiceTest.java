package com.dbc.pokesuits;

import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PokemonServiceTest {
    @Autowired
    private PokemonService pokemonService;

    @Test
    public void userNaoExiste(){
        RegraDeNegocioException exception =
                assertThrows(RegraDeNegocioException.class, ()->pokemonService.listarPokemonsPorUser(null,0),
                        "Testou listarPokemonsPorUser com id inexistente que deveria propagar uma RegraDeNegocioException, mas não devolveu");
        assertTrue(exception.getMessage().contains("O ID do User passadso não Existe"));
    }

    @Test
    public void pokemonNaoExiste(){
        RegraDeNegocioException exception =
                assertThrows(RegraDeNegocioException.class, ()->pokemonService.getById(0),
                        "Testou getById com id inexistente que deveria propagar uma RegraDeNegocioException, mas não devolveu");
        assertTrue(exception.getMessage().contains("O ID do Pokemon Passado não existe"));
    }
}
