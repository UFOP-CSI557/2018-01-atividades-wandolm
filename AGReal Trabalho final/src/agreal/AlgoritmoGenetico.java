/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.ArrayList;
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
    Integer minimo;
    // Máximo
    Integer maximo;
    // Variáveis
    Integer nVariaveis;

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Integer minimo, Integer maximo, Integer nVariaveis) {
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

    public Double executar() {

        populacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);

        // Criar a população
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;

        // Enquanto o critério de parada não for satisfeito - Gerações
        for (int g = 1; g <= geracoes; g++) {

            for (int i = 0; i < this.tamanho; i++) {
                // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a operação

                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    Individuo desc1 = new Individuo(minimo, maximo, nVariaveis);
                    Individuo desc2 = new Individuo(minimo, maximo, nVariaveis);

                    // Progenitores
                    Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2);

                    // Ponto de corte
                    int corte = rnd.nextInt(p1.getVariaveis().size());

                    // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(p1, p2, desc1, corte);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(p2, p1, desc2, corte);

                    // Mutação
                    // Descendente 1
                    mutacaoPorVariavel(desc1);
                    // Descendente 2
                    mutacaoPorVariavel(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            // Ordenar populacao;
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao.getIndividuos()
                    .subList(this.tamanho,
                            populacao.getIndividuos().size())
                    .clear();

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            //Imprimir a situacao atual
     //       System.out.println("Gen = " + g +
    //                "\tCusto = "
    //                + populacao.getIndividuos().get(0).getFuncaoObjetivo());
        }

        System.out.println("Melhor resultado: ");
        System.out.println(populacao.getIndividuos().get(0).getVariaveis());
        System.out.println("Melhor resultado barras: ");
        calculaBarras(populacao.getIndividuos().get(0));
        return populacao.getIndividuos().get(0).getFuncaoObjetivo();
        
        

    }
    
    private void calculaBarras(Individuo individuo){
         int v[]   = {40, 40, 40, 20, 20, 20, 20, 55, 48, 48, 5, 5, 5, 5, 5};
        
    ArrayList< Integer > aux = new ArrayList();
    
         int i = 0;
         int tam = 15, item=0;
             Double barras = 1.0;
     Double soma = 0.0;
     int cont = 0;
        aux.add(0);
            for(i = 0 ; i < 15 ; i++){
            int peso = v[individuo.getVariaveis().get(i)];
                if(aux.get(cont) + peso <100){
                    aux.set(cont,(aux.get(cont) + peso) );
                    item++;
                }else{
                    for(int j = 0 ; j< aux.size();j++){
                        if(aux.get(j) + peso <100){
                            aux.set(j,(aux.get(j) + peso) );
                            item++;
                            break;
                        }else{
                        barras++;
                        cont ++;
                        item++;
                        aux.add(cont,peso);
                        break;
                        
                        
                       }  
                    }    
                      
                    }
                    
                   
                }
            System.out.println(barras);
           // System.out.println("Items: "+ item);
             
            
    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Integer alpha = rnd.nextInt();

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Integer valor =  ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            Integer valor = ind2.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

    }

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getVariaveis().size(); i++) {
            if (rnd.nextInt(this.tamanho) <= this.pMutacao) {

                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Integer valor = individuo.getVariaveis().get(i);
                // Multiplica por rnd
                valor *= rnd.nextInt();

                // Inverter o sinal
         //       if (!rnd.nextBoolean()) {
           //         valor = -valor;
             //   }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getVariaveis().set(i, valor);

                }

            }
        }

    }

}
