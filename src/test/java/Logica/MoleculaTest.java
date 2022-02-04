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

    @Test
    void valorarWorksOnIMPLIES() {
        // F -> F === V
        Molecula allFalse = new Molecula(new alwaysFalse(), "IMPLIES", new alwaysFalse());
        assertTrue(allFalse.valorar());
        // F -> V === V
        Molecula falseLeft = new Molecula(new alwaysFalse(), "IMPLIES", new alwaysTrue());
        assertTrue(falseLeft.valorar());
        // V -> F === F
        Molecula falseRight = new Molecula(new alwaysTrue(), "IMPLIES", new alwaysFalse());
        assertFalse(falseRight.valorar());
        // V -> V === V
        Molecula allTrue = new Molecula(new alwaysTrue(), "IMPLIES", new alwaysTrue());
        assertTrue(allTrue.valorar());
    }

    @Test
    void valorarWorksOnIFF() {
        // F <-> F === V
        Molecula allFalse = new Molecula(new alwaysFalse(), "IFF", new alwaysFalse());
        assertTrue(allFalse.valorar());
        // F <-> V === F
        Molecula falseLeft = new Molecula(new alwaysFalse(), "IFF", new alwaysTrue());
        assertFalse(falseLeft.valorar());
        // V <-> F === F
        Molecula falseRight = new Molecula(new alwaysTrue(), "IFF", new alwaysFalse());
        assertFalse(falseRight.valorar());
        // V <-> V === V
        Molecula allTrue = new Molecula(new alwaysTrue(), "IFF", new alwaysTrue());
        assertTrue(allTrue.valorar());
    }

    @Test
    void valorarWorksOnXOR() {
        // F ⊕ F = F
        Molecula allFalse = new Molecula(new alwaysFalse(), "XOR", new alwaysFalse());
        assertFalse(allFalse.valorar());
        // F ⊕ V = V
        Molecula falseLeft = new Molecula(new alwaysFalse(), "XOR", new alwaysTrue());
        assertTrue(falseLeft.valorar());
        // V ⊕ F = V
        Molecula falseRight = new Molecula(new alwaysTrue(), "XOR", new alwaysFalse());
        assertTrue(falseRight.valorar());
        // V ⊕ V = F
        Molecula allTrue = new Molecula(new alwaysTrue(), "XOR", new alwaysTrue());
        assertFalse(allTrue.valorar());
    }

    @Test
    void valorarWorksOnNAND() {
        // F ⊼ F = V
        Molecula allFalse = new Molecula(new alwaysFalse(), "NAND", new alwaysFalse());
        assertTrue(allFalse.valorar());
        // F ⊼ V = V
        Molecula falseLeft = new Molecula(new alwaysFalse(), "NAND", new alwaysTrue());
        assertTrue(falseLeft.valorar());
        // V ⊼ F = V
        Molecula falseRight = new Molecula(new alwaysTrue(), "NAND", new alwaysFalse());
        assertTrue(falseRight.valorar());
        // V ⊼ V = F
        Molecula allTrue = new Molecula(new alwaysTrue(), "NAND", new alwaysTrue());
        assertFalse(allTrue.valorar());
    }

    @Test
    void valorarWorksOnNOR() {
        // F ⊽ F = V
        Molecula allFalse = new Molecula(new alwaysFalse(), "NOR", new alwaysFalse());
        assertTrue(allFalse.valorar());
        // F ⊽ V = F
        Molecula falseLeft = new Molecula(new alwaysFalse(), "NOR", new alwaysTrue());
        assertFalse(falseLeft.valorar());
        // V ⊽ F = F
        Molecula falseRight = new Molecula(new alwaysTrue(), "NOR", new alwaysFalse());
        assertFalse(falseRight.valorar());
        // V ⊽ V = F
        Molecula allTrue = new Molecula(new alwaysTrue(), "NOR", new alwaysTrue());
        assertFalse(allTrue.valorar());
    }

    @Test
    void toStringWorksOnAND() {
        Molecula m = new Molecula(new alwaysA(), "AND", new alwaysA());
        String expected = "(A ∧ A)";
        assertEquals(expected, m.toString());
    }

    @Test
    void toStringWorksOnOR() {
        Molecula m = new Molecula(new alwaysA(), "OR", new alwaysA());
        String expected = "(A ∨ A)";
        assertEquals(expected, m.toString());
    }

    @Test
    void toStringWorksOnIMPLIES() {
        Molecula m = new Molecula(new alwaysA(), "IMPLIES", new alwaysA());
        String expected = "(A → A)";
        assertEquals(expected, m.toString());
    }

    @Test
    void toStringWorksOnIFF() {
        Molecula m = new Molecula(new alwaysA(), "IFF", new alwaysA());
        String expected = "(A ↔ A)";
        assertEquals(expected, m.toString());
    }

    @Test
    void toStringWorksOnXOR() {
        Molecula m = new Molecula(new alwaysA(), "XOR", new alwaysA());
        String expected = "(A ⊕ A)";
        assertEquals(expected, m.toString());
    }

    @Test
    void toStringWorksOnNAND() {
        Molecula m = new Molecula(new alwaysA(), "NAND", new alwaysA());
        String expected = "(A ⊼ A)";
        assertEquals(expected, m.toString());
    }

    @Test
    void toStringWorksOnNOR() {
        Molecula m = new Molecula(new alwaysA(), "NOR", new alwaysA());
        String expected = "(A ⊽ A)";
        assertEquals(expected, m.toString());
    }
}