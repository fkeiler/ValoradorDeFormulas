package Logica;

public class Molecula implements Proposicao {
    Proposicao esquerda;
    String conectivo;
    Proposicao direita;

    public Molecula(Proposicao e, String s, Proposicao d){
        this.esquerda = e;
        this.conectivo = s;
        this.direita = d;
    }

    @Override
    public boolean valorar(){
        if(conectivo == "AND")
            return esquerda.valorar() & direita.valorar();

        if(conectivo == "OR") //OR
            return esquerda.valorar() | direita.valorar();

        if(conectivo == "XOR") //XOR
            return esquerda.valorar() ^ direita.valorar();

        if(conectivo == "IMPLIES")
            return !esquerda.valorar() | direita.valorar();

        if(conectivo == "IFF")
            return !(esquerda.valorar() ^ direita.valorar());

        if(conectivo == "NAND")
            return !direita.valorar() | !esquerda.valorar();

        if(conectivo == "NOR")
            return !(direita.valorar() | esquerda.valorar());

        return false;
    }

    @Override
    public String toString(){
        if(this.conectivo == "AND") //AND
            return "(" + esquerda.toString() + " ∧ " + direita.toString() + ")";

        if(this.conectivo == "OR")
            return "(" + esquerda.toString() + " ∨ " + direita.toString() + ")";

        if(this.conectivo == "XOR")
            return "(" + esquerda.toString() + " ⊕ " + direita.toString() + ")";

        if(this.conectivo == "IMPLIES")
            return "(" + esquerda.toString() + " → " + direita.toString() + ")";

        if(this.conectivo == "IFF")
            return "(" + esquerda.toString() + " ↔ " + direita.toString() + ")";

        if(this.conectivo == "NAND")
            return "(" + esquerda.toString() + " ⊼ " + direita.toString() + ")";

        if(this.conectivo == "NOR")
            return "(" + esquerda.toString() + " ⊽ " + direita.toString() + ")";

        return "Molécula Inválida";
    }
}
