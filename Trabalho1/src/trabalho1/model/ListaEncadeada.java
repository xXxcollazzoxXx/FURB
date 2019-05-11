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
public class ListaEncadeada<T> {
    
    private NoLista<T> primeiro;    
    
    /**
     * Construtor da classe ListaEncadeada
     */
    public ListaEncadeada(){
        primeiro = null;
    } 
    
    /**
     * Método get do objeto primeiro;
     * @return Devolve um NoLista
     */
    public NoLista<T> getPrimeiro(){
        return this.primeiro;
    }
    
    /**
     * Método que inseri uma nova informação na lista encadeada
     * @param info Informação que se deseja inserir na lista encadeada
     */    
    public void inserir(T info){
        NoLista objeto = new NoLista();
        objeto.setInfo(info);
        objeto.setProximo(primeiro);
        primeiro = objeto;
    }
    
    /**
     * Método responsável por verificar se a lista encadeada está vazia
     * @return true se a lista encadeada estiver vazia ou false se a lista encadeada não estiver vazia
     */
    public boolean estaVazia(){
        return getPrimeiro() == null;
    }
 
    /**
     * Método que busca um determinado objeto
     * @param info Informação que se deseja encontrar na lista encadeada
     * @return Retorna o nó encontrado ou null se não encontrar
     */
    public NoLista<T> buscar(T info){
        NoLista objeto = new NoLista();
        objeto = primeiro;
        while(objeto != null){
            if( objeto.getProximo() == null){
                return null;
            }
            if(objeto.getInfo().equals(info)){
                return objeto;
            }
            objeto = objeto.getProximo();           
        }
        return null;
    }
    
    /**
     * Método responsável por retirar um Nó da lista encadeada
     * @param info Informação que se deseja retirar da lista encadeada
     */
    public void retirar(T info){
        NoLista objeto = primeiro;
        NoLista anterior = null;
        
        while(objeto != null
           && !objeto.getInfo().equals(info)){
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
     * Método que devolve o comprimento dessa lista encadeada
     * @return Retorna a quantidade de itens da lista encadeada
     */
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
    
    /**
     * Método que retorna o nó de um índice fornecido como argumento
     * @param idx Índice no qual se encontra o registro
     * @return Retorna o nó do índice fornecido como argumento
     */
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
    
    /**
     * Método responsável por montar a representação textual da lista encadeada
     * @return retorna a representação textual da lista encadeada
     */
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
