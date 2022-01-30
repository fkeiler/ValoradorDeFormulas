package Logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegacaoTest {

    @Test
    void valorarWorksOnTrueStatements() {
        Negacao n = new Negacao(new alwaysTrue());
        assertFalse(n.valorar());
    }

    @Test
    void valorarWorksOnFalseStatements() {
        Negacao n = new Negacao(new alwaysFalse());
        assertTrue(n.valorar());
    }
}