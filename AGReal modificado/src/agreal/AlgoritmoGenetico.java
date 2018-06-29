/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.Collections;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class AlgoritmoGenetico {

    // Tamanho da população
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de mutação - operador secundário
    Double pMutacao;
    // Critério de parada - número de gerações
    Integer geracoes;

    // Dados do problema
    // Problema - DeJong
    Problema problema;
    // Mínimo
    Double minimo;
    // Máximo
    Double maximo;
    // Variáveis
    Integer nVariaveis;
    
    //Modificaçao com ESReal
     // Parametros - ES
    private Integer mu; // Tamanho da populacao
    private Integer lambda; // numero de descendentes

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Double minimo, Double maximo, Integer nVariaveis) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
    }

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public Populacao executar() {

        populacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);

        // Criar a população
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;

  
        
        // Geracao da populacao inicial - aleatoria - tamanho mu
        
   
        // Populacao - descendentes
        Populacao descendentes = new Populacao() ; 
        
        
          // Criterio de parada - numero de geracoes
        for(int g = 1; g <= this.geracoes; g++) {
            mu = tamanho;
            lambda = 1000;
            
            // Para cada individuo, gerar lambda/mu descendentes
            for(int i = 0; i < populacao.getIndividuos().size(); i++) {
                
       
                   
          
                    
          
                
                   
               
        
                // Gerar lambda/mu descendentes
                
                for(int d = 0; d < lambda / mu; d++) {
                    
                    // Clonar individuo
                    IndividuoDouble filho = (IndividuoDouble)populacao.getIndividuos().get(i).clone();
                    
                    
                    // Clonar individuo
                    IndividuoDouble filho1 = (IndividuoDouble)populacao.getIndividuos().get(i).clone();
                    
                    // Aplicar mutacao
                    mutacaoPorVariavel(filho1);
                    
                    // Avaliar
                    problema.calcularFuncaoObjetivo(filho1);
                    
                 
                    
                    
                    
                    // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a operação

                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);
                    
                     // Progenitores
                    //Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2).clone();

                    Individuo desc1 = new IndividuoDouble(minimo, maximo, nVariaveis);
                    Individuo desc2 = new IndividuoDouble(minimo, maximo, nVariaveis);
                    
                       // Aplicar mutacao
                    mutacaoPorVariavel(filho);
                    
                     // Ponto de corte
                    int corte = rnd.nextInt(filho.getCromossomos().size());
                    
                     // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(filho, p2, desc1, corte);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(p2, filho, desc2, corte);
                    
              
                 
                    
                    // Avaliar
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);
                    
                    
                    // Inserir na populacao de descendentes
                    if( desc1.getFuncaoObjetivo() < filho1.getFuncaoObjetivo()  )
                    descendentes.getIndividuos().add(desc1);
                    else
                         descendentes.getIndividuos().add(filho1);
                    descendentes.getIndividuos().add(desc2);
                    
                   
                    
                }
                
            }
               
                


            }
            
             
            // ES(mu + lambda)?
            // Mu + Lambda
            populacao.getIndividuos().addAll(descendentes.getIndividuos());
            // Ordenar Mu+Lambda
            Collections.sort(populacao.getIndividuos());
            // Definir sobreviventes
            populacao.getIndividuos()
                    .subList(this.mu, populacao.getIndividuos().size()).clear();
            // Limpar descendentes
            descendentes.getIndividuos().clear();
            
          //  System.out.println("G = " + g 
           //         + "\t"
            //        + populacao.getMelhorIndividuo().getFuncaoObjetivo());
            
            
            
        }
        
      
       
    
     return populacao;

    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Double valor = alpha * (Double) ind1.getCromossomos().get(i);
            descendente.getCromossomos().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            Double valor = (1.0 - alpha) * (Double)ind2.getCromossomos().get(i);
            descendente.getCromossomos().add(valor);
        }

    }

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getCromossomos().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {

                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = (Double) individuo.getCromossomos().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getCromossomos().set(i, valor);

                }

            }
        }

    }

}
