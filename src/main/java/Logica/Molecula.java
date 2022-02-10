package Logica;

public class Molecula implements Proposicao {
    Proposicao esquerda;
    Operador conectivo;
    Proposicao direita;

    enum Operador {
        AND, OR, XOR, IMPLIES, IFF, NAND, NOR
    }

    @Deprecated
    public Molecula(Proposicao e, String s, Proposicao d){
        this.esquerda = e;
        this.conectivo = switch(s) {
            case "AND" -> Operador.AND;
            case "OR" -> Operador.OR;
            case "XOR" -> Operador.XOR;
            case "IMPLIES" -> Operador.IMPLIES;
            case "IFF" -> Operador.IFF;
            case "NAND" -> Operador.NAND;
            case "NOR" -> Operador.NOR;
            default -> null;
        };
        this.direita = d;
    }

    public Molecula(Proposicao e, Operador o, Proposicao d) {
        this.esquerda = e;
        this.conectivo = o;
        this.direita = d;
    }

    @Override
    public boolean valorar(){
        return switch (conectivo) {
            case AND -> esquerda.valorar() & direita.valorar();
            case OR -> esquerda.valorar() | direita.valorar();
            case XOR -> esquerda.valorar() ^ direita.valorar();
            case IMPLIES -> !esquerda.valorar() | direita.valorar();
            case IFF -> esquerda.valorar() == direita.valorar();
            case NAND -> !direita.valorar() | !esquerda.valorar();
            case NOR -> !(direita.valorar() | esquerda.valorar());
        };
    }

    @Override
    public String toString(){
        return switch (conectivo) {
            case AND -> "(" + esquerda.toString() + " ∧ " + direita.toString() + ")";
            case OR -> "(" + esquerda.toString() + " ∨ " + direita.toString() + ")";
            case XOR -> "(" + esquerda.toString() + " ⊕ " + direita.toString() + ")";
            case IMPLIES -> "(" + esquerda.toString() + " → " + direita.toString() + ")";
            case IFF -> "(" + esquerda.toString() + " ↔ " + direita.toString() + ")";
            case NAND -> "(" + esquerda.toString() + " ⊼ " + direita.toString() + ")";
            case NOR -> "(" + esquerda.toString() + " ⊽ " + direita.toString() + ")";
        };
    }
}
