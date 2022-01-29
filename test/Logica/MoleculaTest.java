package Logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Classes auxiliares para não depender da corretude do Átomo
class alwaysTrue implements Proposicao {
    @Override
    public boolean valorar() {
        return true;
    }
}

class alwaysFalse implements Proposicao {
    @Override
    public boolean valorar() {
        return false;
    }
}

class MoleculaTest {
    @Test
    void valorarWorksOnAND() {
        // F ∧ F === F
        Molecula allFalse = new Molecula(new alwaysFalse(), "AND", new alwaysFalse());
        assertFalse(allFalse.valorar());
        // F ∧ V === F
        Molecula falseLeft = new Molecula(new alwaysFalse(), "AND", new alwaysTrue());
        assertFalse(falseLeft.valorar());
        // V ∧ F === F
        Molecula falseRight = new Molecula(new alwaysTrue(), "AND", new alwaysFalse());
        assertFalse(falseRight.valorar());
        // V ∧ V === V
        Molecula allTrue = new Molecula(new alwaysTrue(), "AND", new alwaysTrue());
        assertTrue(allTrue.valorar());
    }

    @Test
    void valorarWorksOnOR() {
        // F ∨ F === F
        Molecula allFalse = new Molecula(new alwaysFalse(), "OR", new alwaysFalse());
        assertFalse(allFalse.valorar());
        // F ∨ V === V
        Molecula falseLeft = new Molecula(new alwaysFalse(), "OR", new alwaysTrue());
        assertTrue(falseLeft.valorar());
        // V ∨ F === V
        Molecula falseRight = new Molecula(new alwaysTrue(), "OR", new alwaysFalse());
        assertTrue(falseRight.valorar());
        // V ∨ V === V
        Molecula allTrue = new Molecula(new alwaysTrue(), "OR", new alwaysTrue());
        assertTrue(allTrue.valorar());
    }
}