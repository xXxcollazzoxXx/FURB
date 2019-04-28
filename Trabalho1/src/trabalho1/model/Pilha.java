/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.model;

import java.util.Collection;
import trabalho1.model.*;

/**
 *
 * @author Notebook
 */
public interface Pilha<T> {
    
    public void push(T info);    
    public T pop();                 
    public T peek();
    public boolean estaVazia();
    public void liberar();
       
}
