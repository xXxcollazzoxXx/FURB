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
public class PilhaEncadeada<T> {
    
    private NoPilha<T> primeiro;
    
    /**
     * Construtor da classe PilhaEncadeada
     */
    public PilhaEncadeada(){
        primeiro = null;
    } 
    
    /**
     * getter da variável primeiro
     * @return Retorna a variável primeiro
     */
    public NoPilha<T> getPrimeiro(){
        return this.primeiro;
    }
        
    /**
     * Método que inseri um dado na pilha encadeada
     * @param info Informação que se deseja empilhar
     */
    public void inserir(T info){
        NoPilha objeto = new NoPilha();
        objeto.setInfo(info);
        objeto.setProximo(primeiro);
        primeiro = objeto;
    }
    
    /**
     * Método que verifica se a pilha está vazia
     * @return Retorna true se estiver e false se não estiver vazia
     */
    public boolean estaVazia(){
        return getPrimeiro() == null;
    }
 
    /**
     * Método que buscar uma informação na pilha
     * @param info Informação que se deseja buscar
     * @return returna um NoLista da informação encontrada ou nulo se não encontrar
     */
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
    
    /**
     * Método que retira um dado da pilha
     * @param info Informação que se deseja retirar
     */
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
    
    /**
     * Método que retorna a quantidade de elementos na pilha
     * @return Quantidade de elementos na pilha
     */
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
    
    /**
     * Método que retorna o nó de um índice fornecido como argumento
     * @param idx Índice que um nó se encontra
     * @return Devolve um nó do índice forneido como argumento
     */
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
    
    /**
     * Método que devolve a representação textual da pilha encadeada
     * @return Devolve a representação textual da pilha encadeada
     */
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
