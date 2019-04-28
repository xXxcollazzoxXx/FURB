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
public class NoPilha<T> {
    
    private NoPilha<T> proximo;
    private T info;
    
    public T getInfo(){
        return this.info;
    }
    
    public void setInfo(T info){
        this.info = info;
    }
    
    public NoPilha<T> getProximo(){
        return this.proximo;
    }
    
    public void setProximo(NoPilha<T> proximo){
        this.proximo = proximo;
    }
    
}
