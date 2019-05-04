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
public class Ocorrencia {
        
    private int linha;
    private String ocorrencia;    
    private String sugestao;
    
    public void setLinha(int linha){
        this.linha = linha;
    }
    
    public int getLinha(){
        return this.linha;
    }
    
    public void setOcorrencia(String ocorrencia){
        this.ocorrencia = ocorrencia;
    }
    
    public String ocorrencia(){
        return this.ocorrencia;
    }        
    
    public void setSugestao(String sugestao){
        this.sugestao = sugestao;
    }
    
    public String getSugestao(){
        return this.sugestao;
    }
   
    public Ocorrencia( int linha,
                       String ocorrencia,                     
                       String sugestao ){
        this.linha      = linha;
        this.ocorrencia = ocorrencia;        
        this.sugestao   = sugestao;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ocorrencia other = (Ocorrencia) obj;
        if (this.linha != other.linha) {
            return false;
        }
        return true;
    }
        
    @Override
    public String toString(){
        String toString;
        toString = "Linha: "+ this.linha;
        if( !this.ocorrencia.equals("") )
            toString += " Ocorrência: "+ this.ocorrencia;
        if( !this.sugestao.equals("") )
            toString += " Sugestao: "+ this.sugestao;
        return toString;
    }
    
}
