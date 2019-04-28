/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.model;

/**
 *
 * @author Notebook
 */
public class PilhaVaziaException extends RuntimeException{
    
    public PilhaVaziaException(){
        super();
    }
    
    public PilhaVaziaException(String string){
        super(string);
    }
    
    
}
