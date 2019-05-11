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
public class NoPilha<T> {
    
    private NoPilha<T> proximo;
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
     * @param info variável info
     */
    public void setInfo(T info){
        this.info = info;
    }
    
    /**
     * getter da variável proximo
     * @return Retorna a variável proximo
     */
    public NoPilha<T> getProximo(){
        return this.proximo;
    }
    
    /**
     * setter da variável proximo
     * @param proximo Próximo objeto NoLista
     */
    public void setProximo(NoPilha<T> proximo){
        this.proximo = proximo;
    }
    
}
