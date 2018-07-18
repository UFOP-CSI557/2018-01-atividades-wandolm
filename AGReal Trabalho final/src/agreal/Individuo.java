/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class Individuo implements Comparable<Individuo>{
    
    // Genótipo+Fenotipo
    private ArrayList<Integer> variaveis;
    // Custo da função objetivo
    Double funcaoObjetivo;
    
    // Valor mínimo
    Integer minimo;
    // Valor máximo
    Integer maximo;
    
    // Número de variáveis
    Integer nVar;

    public Individuo(Integer minimo, Integer maximo, Integer nVar) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVar = nVar;
        this.variaveis = new ArrayList<>();
    }

    public ArrayList<Integer> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(ArrayList<Integer> variaveis) {
        this.variaveis = variaveis;
    }

    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    public Integer getnVar() {
        return nVar;
    }

    public void setnVar(Integer nVar) {
        this.nVar = nVar;
    }
    
    // Gerar o genótipo
    public void criar() {
        
        this.variaveis = new ArrayList<>();
        
        Random rnd = new Random();
        Integer valor;
        
        
        for(int i = 0; i < this.getnVar(); i++) {
            valor = rnd.nextInt(nVar);
            this.variaveis.add(valor);
        }        
    }
     
    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }
}
