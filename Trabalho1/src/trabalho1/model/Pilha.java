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
public interface Pilha<T> {
    
    /**
     * Método que empilha um dado na pilha
     * @param info Dado a ser empilhado
     */
    public void push(T info);
    
    /**
     * Método que desempilha o último dado da pilha
     * @return Retorna o dado desempilhado
     */
    public T pop();
    
    /**
     * Método que retorna o último dado empilhado
     * @return retorna o último dado empilhado
     */
    public T peek();
    
    /**
     * Método que verifica se a pilha está vazia
     * @return Retorna true para vazia ou falso para não vazia
     */
    public boolean estaVazia();
    
    /**
     * Método que desempilha todos os dados empilhados
     */
    public void liberar();
       
}
