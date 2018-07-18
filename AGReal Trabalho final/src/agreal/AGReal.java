/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

/**
 *
 * @author fernando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema problema = new Problema();

        Integer tamanho = 15;
        Double pCrossover = 0.6;
        Double pMutacao = 0.05;
        Integer geracoes = 100;
        
        Integer minimo = 1;
        Integer maximo = 4;
        Integer nVariaveis = 15;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
        for(int i=0; i<10; i++)
        ag.executar();
    }
    
}
