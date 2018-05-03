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
        
        for (int i= 1; i<=individuo.getVariaveis().size();i++){
            soma += ((Math.pow(individuo.getVariaveis().get(i-1), 2)) - 10 * Math.cos(2*Math.PI*individuo.getVariaveis().get(i-1)));
        }
        
        Double resultado = (10 * individuo.getVariaveis().size()) + soma;
      
        
        individuo.setFuncaoObjetivo(resultado);
        
    }
    
}
