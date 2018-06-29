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
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double soma = 0.0;
        
        for (int i= 0; i<individuo.getCromossomos().size();i++){
            soma += ((Double)individuo.getCromossomos().get(i) ) * Math.sin(Math.sqrt(Math.abs((Double)individuo.getCromossomos().get(i)))) ;
          //  System.out.print(soma);
        }
        
        Double resultado = (418.9829 *individuo.getCromossomos().size()) - soma;
      
        
        individuo.setFuncaoObjetivo(resultado);
        
    }
    
}
