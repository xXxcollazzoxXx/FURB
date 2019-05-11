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
public class PilhaLista<T> implements Pilha<T> {
    
    private PilhaEncadeada<T> lista = new PilhaEncadeada();
    
    /**
     * Método que empilha um dado na pilha
     * @param info Dado a ser empilhado
     */
    public void push(T info){
        lista.inserir(info);
    }
    
    /**
     * Método que desempilha o último dado da pilha
     * @return Retorna o dado desempilhado
     */
    public T pop(){                
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }
    
    /**
     * Método que retorna o último dado empilhado
     * @return retorna o último dado empilhado
     */
    public T peek() {
        if(lista.estaVazia())
            throw new PilhaVaziaException("Pilha vazia.");        
        return lista.getPrimeiro().getInfo();
    }
    
    /**
     * Método que verifica se a pilha está vazia
     * @return Retorna true para vazia ou falso para não vazia
     */
    public boolean estaVazia() {
        return lista.estaVazia();
    }
    
    /**
     * Método que desempilha todos os dados empilhados
     */
    public void liberar() {
        while(!lista.estaVazia()){  
            lista.retirar(lista.getPrimeiro().getInfo());
        }
    }
    
    /**
     * Método que retorna o tamanho da pilha
     * @return Retorna o tamanho da pilha
     */
    public int tamanhoPilha(){
        return lista.obterComprimento();
    }
    
    /**
     * Método que devolve a representação textual da pilha
     * @return Representação textual da pilha
     */   
    public String toString(){
        return lista.toString();
    }
        
    
}
