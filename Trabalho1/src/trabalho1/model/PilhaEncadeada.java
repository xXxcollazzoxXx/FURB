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
public class PilhaEncadeada<T> {
    
    private NoPilha<T> primeiro;
    
    public PilhaEncadeada(){
        primeiro = null;
    } 
    
    public NoPilha<T> getPrimeiro(){
        return this.primeiro;
    }
    
    public void inserir(T info){
        NoPilha objeto = new NoPilha();
        objeto.setInfo(info);
        objeto.setProximo(primeiro);
        primeiro = objeto;
    }
    
    public boolean estaVazia(){
        return getPrimeiro() == null;
    }
 
    public NoPilha<T> buscar(T info){
        NoPilha objeto = new NoPilha();
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
        NoPilha objeto = primeiro;
        NoPilha anterior = null;
        
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
        NoPilha p;        
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
    
    public NoPilha<T> obterNo(int idx){        
        if(idx < 0){
            throw new IndexOutOfBoundsException();
        }else{                        
            if(idx> obterComprimento() - 1){                
                throw new IndexOutOfBoundsException();
            }        
            int qtdRegistros = 0;
            NoPilha p = primeiro;
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
        NoPilha p = primeiro;
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
