package com.dbc.pokesuits;

import com.dbc.pokesuits.entity.MochilaEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.service.MochilaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MochilaServiceTest {

    @Autowired
    private MochilaService mochilaService;

    @Test
    public void testaSeAPokebolaFoiRetirada() {
        try {
            String pokebola = "pokeball";

            this.mochilaService.adicionarPokebola(6, pokebola, 1);

            Integer quantidadeInicial = this.mochilaService.getMochilaPeloIdUser(6).getQuantidadePokeBalls();

            this.mochilaService.usarPokebola(6, pokebola);
            MochilaEntity mochilaComValorRetirado = this.mochilaService.getMochilaPeloIdUser(6);

            assertTrue((quantidadeInicial - 1) == mochilaComValorRetirado.getQuantidadePokeBalls());
        } catch (RegraDeNegocioException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testaSeAQuantidadeDePokebolasFoiIncrementadaNaMochila() {
        try {
            String pokebola = "pokeball";
            Integer quantidadeInicial = this.mochilaService.getMochilaPeloIdUser(6).getQuantidadePokeBalls();
            Integer quantidadeAdicionada = 1;

            this.mochilaService.adicionarPokebola(6, pokebola, quantidadeAdicionada);
            MochilaEntity mochilaComValorIncrementado = this.mochilaService.getMochilaPeloIdUser(6);

            assertTrue((quantidadeInicial + quantidadeAdicionada) == mochilaComValorIncrementado.getQuantidadePokeBalls());
        } catch (RegraDeNegocioException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testaSeTreinadorDaMochilaNaoExiste() {
        Exception exception = assertThrows(RegraDeNegocioException.class, () -> {
            this.mochilaService.getMochilaPeloIdUser(1);
        });

        assertTrue(exception.getMessage().contains("Treinador não criado."));
    }
}
