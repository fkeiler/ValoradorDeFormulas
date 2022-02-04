package Logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class alwaysA implements Proposicao {
    @Override
    public boolean valorar() {
        return false;
    }

    @Override
    public String toString() {
        return "A";
    }
}

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

    @Test
    void toStringWorks() {
        Negacao n = new Negacao(new alwaysA());
        String expected = "¬A";
        assertEquals(expected, n.toString());
    }
}