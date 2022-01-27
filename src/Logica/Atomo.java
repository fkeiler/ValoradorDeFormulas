package Logica;

public class Atomo implements Proposicao {
    boolean valor;
    String texto;

    public Atomo(String s){
        this.texto = s;
        this.valor = false;
    }

    @Override
    public boolean valorar(){
        return valor;
    }

    public void atribuirValor(boolean b){
        this.valor = b;
    }

    @Override
    public String toString(){
        return this.texto;
    }
}
