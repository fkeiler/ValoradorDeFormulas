package Logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Atomo implements Proposicao {
    private static Map<String, Atomo> atomos = new HashMap<>();
    boolean valor;
    String texto;

    public Atomo(String s){
        this.texto = s;
        this.valor = false;
    }

    public static Atomo fromString(String s) {
        if (!atomos.containsKey(s)) {
            atomos.put(s, new Atomo(s));
        }
        return atomos.get(s);
    }

    public static Vector<Atomo> getAll() {
        return new Vector<>(atomos.values());
    }

    public static void resetAll() {
        atomos = new HashMap<>();
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
