package UI;

import Logica.Atomo;
import Logica.Molecula;
import Logica.Negacao;
import Logica.Proposicao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JanelaPrincipalTest {

    private Proposicao p;

    @Test
    void interpretarAtomo() {
        JanelaPrincipal j = new JanelaPrincipal();
        Proposicao p = j.interpretar("atomo");
        assertInstanceOf(Atomo.class, p);
        assertEquals(p.toString(), "atomo");
    }

    @Test
    void interpretarNegacao() {
        JanelaPrincipal j = new JanelaPrincipal();
        Proposicao p = j.interpretar("NOT atomo");
        assertInstanceOf(Negacao.class, p);
    }

    @Test
    void interpretarMolecula() {
        JanelaPrincipal j = new JanelaPrincipal();
        Proposicao p = j.interpretar("(a AND b)");
        assertInstanceOf(Molecula.class, p);
    }
}