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
public class NoLista<T> {
    
    private NoLista<T> proximo;
    private T info;
    
    /**
     * getter da variável info
     * @return Retorna a variável info
     */
    public T getInfo(){
        return this.info;
    }
    
    /**
     * setter da variável info
     * @param info Informação que se deseja armazenar
     */
    public void setInfo(T info){
        this.info = info;
    }
    
    /**
     * getter da variável proximo
     * @return Retorna a variável proximo
     */
    public NoLista<T> getProximo(){
        return this.proximo;
    }
    
    /**
     * setter da variável proximo
     * @param proximo Próximo objeto NoLista
     */
    public void setProximo(NoLista<T> proximo){
        this.proximo = proximo;
    }
    
}
