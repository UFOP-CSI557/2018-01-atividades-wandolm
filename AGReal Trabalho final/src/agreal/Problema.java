/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class Problema {
                //0  1   2   3   4  5    6   7   8   9  10 11  12 13 14
    int v[]   = {40, 40, 40, 20, 20, 20, 20, 55, 48, 48, 5, 5, 5, 5, 5};
        
    ArrayList< Integer > aux = new ArrayList();
    ArrayList< Integer > vet = new ArrayList(Arrays.asList(40, 40, 40, 20, 20, 20, 20, 55, 48, 48, 5, 5, 5, 5, 5));
    
         int i = 0,item=0;
         int tam = 15;
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
     Double barras = 1.0;
     Double soma = 0.0;
     
     int cont = 0;
        aux.add(0);
            for(int i = 0 ; i < 15 ; i++){
                
            int peso = v[individuo.getVariaveis().get(i)];
                if(aux.get(cont) + peso <100){
                    aux.set(cont,(aux.get(cont) + peso) );
                    item ++;
                }else{
                   for(int j = 0 ; j< aux.size();j++){
                        if(aux.get(j) + peso <100){
                            aux.set(j,(aux.get(j) + peso) );
                            item++;
                            break;
                        }
                   }
                     
                        barras++;
                        cont ++;
                        item++;
                        aux.add(cont,peso);
                        break;
                        
                      }  
                      
                      
                    }
                    
                   
                
             
            
    
             for(int k = 0; k <15;k++){
            
             soma += Math.pow(((100 - v[individuo.getVariaveis().get(k)])/10),2)/barras;
             
         }     
   
       // System.out.print("Itens:"+ item);       
        individuo.setFuncaoObjetivo(barras);

        
    }
    
}




      
      