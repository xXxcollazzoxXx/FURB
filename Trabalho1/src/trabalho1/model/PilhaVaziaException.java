/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.model;

/**
 *
 * @authors William Mello e Victor Calazans
 */
public class PilhaVaziaException extends RuntimeException{
    
    /**
     * Construtor padrão da classe PilhaVaziaException
     */
    public PilhaVaziaException(){
        super();
    }
    
    /**
     * Construtor da classe PilhaVaziaException que recebe como argumento um texto String
     * @param string texto a ser exibido;
     */
    public PilhaVaziaException(String string){
        super(string);
    }
    
    
}
