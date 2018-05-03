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
        

        Integer tamanho=0;

        
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        int repeticoes = 30;

        // Parametros nao modificados
        Double pCrossover = 0.008;
        Double pMutacao = 0.05;

        // Casos de teste
        // 1 - Real 1; 2 - Real 2 ; 
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("REAL1", "REAL2"));
        for (int i = 1; i <= repeticoes; i++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);
            Double maior = 0.0;
            long startTime = System.currentTimeMillis();

            for (int c = 1; c <= casos.size(); c++) {

                tamanho = 50;
                Integer geracoes = 300;

              
                
                int teste = casos.get(c - 1);

                switch (teste) {

                    case 1:
                        tamanho = 50;

                        break;

                    case 2:
                        tamanho = 100;


                        break;


                }
                

             
                    agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
                    Populacao populacao = agReal.executar();
                    
                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;
                    
                    maior = populacao.getIndividuos().get(0).getFuncaoObjetivo();
                    double menor = populacao.getIndividuos().get(populacao.getIndividuos().size()-1).getFuncaoObjetivo();
                    

                System.out.println(nomes.get(teste - 1) + ";" + i + ";" +" Maior "+ ";"+ maior + ";"+"menor "+";"+menor+";" +"time"+";" + totalTime);
                System.out.flush();

                }

          
            }

        }

    }

