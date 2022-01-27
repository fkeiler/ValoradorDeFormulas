package Logica;

public class Negacao implements Proposicao {
    Proposicao neg;

    public Negacao(Proposicao p){
        this.neg = p;
    }

    @Override
    public boolean valorar(){
        return !((this.neg).valorar());
    }

    @Override
    public String toString(){
        return "¬" + neg.toString();
    }
}
