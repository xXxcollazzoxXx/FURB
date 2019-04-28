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
public class ListaEncadeada<T> {
    
    private NoLista<T> primeiro;    
    
    public ListaEncadeada(){
        primeiro = null;
    } 
    
    public NoLista<T> getPrimeiro(){
        return this.primeiro;
    }
    
    public void inserir(T info){
        NoLista objeto = new NoLista();
        objeto.setInfo(info);
        objeto.setProximo(primeiro);
        primeiro = objeto;
    }
    
    public boolean estaVazia(){
        return getPrimeiro() == null;
    }
 
    public NoLista<T> buscar(T info){
        NoLista objeto = new NoLista();
        objeto = primeiro;
        while(true){            
            if(objeto.getProximo() == null){
                return null;
            }
            if(objeto.getInfo() == info){
                return objeto;
            }
            objeto = objeto.getProximo();           
        }
    }
    
    public void retirar(T info){
        NoLista objeto = primeiro;
        NoLista anterior = null;
        
        while(objeto != null && objeto.getInfo() != info){
            anterior = objeto;
            objeto = objeto.getProximo();                                    
        }
        
        if(objeto != null){
            if(anterior == null){
                this.primeiro = objeto.getProximo();                
            }else{
                anterior.setProximo(objeto.getProximo());
            }
        }        
    }
    
    public int obterComprimento(){
        if(primeiro == null){
            return 0;
        }
        int quantidade = 1;        
        NoLista p;        
        p = primeiro;
        while(true){
            if(p.getProximo() == null){
                break;
            }
            p = p.getProximo();
            quantidade++;
        }
        return quantidade;
    }
    
    public NoLista<T> obterNo(int idx){        
        if(idx < 0){
            throw new IndexOutOfBoundsException();
        }else{                        
            if(idx> obterComprimento() - 1){                
                throw new IndexOutOfBoundsException();
            }        
            int qtdRegistros = 0;
            NoLista p = primeiro;
            while(true){
                if(qtdRegistros == idx){
                    return p;
                }        
                p = p.getProximo();
                qtdRegistros++;
            }            
        }        
    }
    
    public String toString(){
        NoLista p = primeiro;
        String conteudo = "";
        while(true){
            if(p == null){
                break;
            }else{
                conteudo = conteudo + ((conteudo.equals("")) ? p.getInfo() : ", " + p.getInfo());            
            }
            p = p.getProximo();
        }
        return conteudo;
    }             
}
