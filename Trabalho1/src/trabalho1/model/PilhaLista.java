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
public class PilhaLista<T> implements Pilha<T> {
    
    private PilhaEncadeada<T> lista = new PilhaEncadeada();
    
    public void push(T info){
        lista.inserir(info);
    }
    
    public T pop(){                
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }
    
    public T peek() {
        if(lista.estaVazia())
            throw new PilhaVaziaException("Pilha vazia.");        
        return lista.getPrimeiro().getInfo();
    }
    
    public boolean estaVazia() {
        return lista.estaVazia();
    }
    
    public void liberar() {
        while(!lista.estaVazia()){  
            lista.retirar(lista.getPrimeiro().getInfo());
        }
    }
    
    public int tamanhoPilha(){
        return lista.obterComprimento();
    }
    
    public String toString(){
        return lista.toString();
    }
        
    
}
