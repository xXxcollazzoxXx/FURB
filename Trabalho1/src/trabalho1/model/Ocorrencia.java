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
public class Ocorrencia {
        
    private int linha;
    private String ocorrencia;    
    private String sugestao;
    
    /**
     * setter da variável linha
     * @param linha variável linha
     */
    public void setLinha(int linha){
        this.linha = linha;
    }
    
    /**
     * getter da variável linha
     * @return variável linha
     */
    public int getLinha(){
        return this.linha;
    }
    
    /**
     * setter da variável ocorrencia
     * @param ocorrencia variável ocorrencia
     */
    public void setOcorrencia(String ocorrencia){
        this.ocorrencia = ocorrencia;
    }
    
    /**
     * getter da variável ocorrencia
     * @return variável ocorrencia
     */
    public String getOcorrencia(){
        return this.ocorrencia;
    }        
    
    /**
     * setter da variável sugestao
     * @param sugestao variável sugestao
     */
    public void setSugestao(String sugestao){
        this.sugestao = sugestao;
    }
    
    /**
     * getter da variável sugestao
     * @return variável sugestao
     */
    public String getSugestao(){
        return this.sugestao;
    }
   
    /**
     * Construtor da classe Ocorrencia
     * @param linha variável linha
     * @param ocorrencia variável ocorrencia
     * @param sugestao  variável sugestao
     */
    public Ocorrencia( int linha,
                       String ocorrencia,                     
                       String sugestao ){
        setLinha( linha );
        setOcorrencia( ocorrencia );
        setSugestao( sugestao );
    }
    
    /**
     * Método equals sobrescrito para verificar já existe um registro com o mesmo número de linhas
     * @param obj
     * @return 
     */
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
        
    /**
     * Método sobrescrito que devolve a representação textual da ocorrência
     * @return Devolve a representação textual da ocorrencia
     */
    @Override
    public String toString(){
        String toString;
        toString = "Linha: "+ this.linha;
        if( !this.ocorrencia.equals("") )
            toString += " Ocorrência: "+ this.ocorrencia;
        if( !this.sugestao.equals("") )
            toString += " Sugestão: "+ this.sugestao;
        return toString;
    }
    
}
