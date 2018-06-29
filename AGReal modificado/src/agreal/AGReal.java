/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Wando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
//        Individuo ind = new Individuo(3, -100.0, 100.0, 3);
//        ind.criar();
//        System.out.println(ind.getCromossomos());
//        ind.decodificar();
//        System.out.println(ind.getDecodificacao());
//        System.out.println(ind.getVariaveis());
        
        Problema problema = new Problema();
//        problema.calcularFuncaoObjetivo(ind);
//        System.out.println(ind.getFuncaoObjetivo());

        
        agreal.AlgoritmoGenetico agReal;
        

        Integer tamanho=100;
        
        Integer geracoes = 300;
        
        Double minimo = -500.00;
        Double maximo = 500.00;
        Integer nVariaveis = 50;
        int repeticoes = 30;

        // Parametros nao modificados
        Double pCrossover = 0.90;
        Double pMutacao = 0.09;

        // Casos de teste
        // 1 - Real 1; 2 - Real 2 ; 
     //   ArrayList<String> nomes = new ArrayList<>(Arrays.asList("REAL1", "REAL2"));
        for (int i = 1; i <= repeticoes; i++) {
          //  ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
    //        Collections.shuffle(casos);
            Double maior = 0.0;
            long startTime = System.currentTimeMillis();


                

             
                    agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
                    Populacao populacao = agReal.executar();
                    
                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;
                    
                    maior = populacao.getIndividuos().get(0).getFuncaoObjetivo();
                    double menor = populacao.getIndividuos().get(0).getFuncaoObjetivo();
                    

                System.out.println(i+" Resultado "+";" + menor + ";" +"time"+";" + totalTime);
                System.out.flush();

                }

          
     //     }

        }

    }

