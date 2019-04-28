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
public class NoLista<T> {
    
    private NoLista<T> proximo;
    private T info;
    
    public T getInfo(){
        return this.info;
    }
    
    public void setInfo(T info){
        this.info = info;
    }
    
    public NoLista<T> getProximo(){
        return this.proximo;
    }
    
    public void setProximo(NoLista<T> proximo){
        this.proximo = proximo;
    }
    
}
