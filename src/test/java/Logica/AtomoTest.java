package Logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AtomoTest {

    @Test
    void atribuirValorWorksAsExpected() {
        Atomo a = new Atomo("A");
        a.atribuirValor(true);
        assertTrue(a.valor);
        a.atribuirValor(false);
        assertFalse(a.valor);
    }

    @Test
    void valorarWorksAsExpected() {
        Atomo a = new Atomo("A");
        a.valor = true;
        assertTrue(a.valorar());
        a.valor = false;
        assertFalse(a.valorar());
    }
}