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
public class TagEncontrada{
    
    String tag;
    int total;
    
    /**
     * Construtor da clase TagEncontrada
     */
    public TagEncontrada(){
        setTag( "" );
        setTotal( 0 );
    }
    
    /**
     * setter da variável tag
     * @param tag variável tag
     */
    public void setTag(String tag){
        this.tag = tag;
    }
            
    /**
     * getter da variável tag
     * @return retorna a variável tag
     */
    public String getTag(){
        return this.tag;
    }
    
    /**
     * setter da variável total
     * @param total variável total
     */
    public void setTotal(int total){
        this.total = total;
    }
    
    /**
     * getter da variável total
     * @return Retorna a variável total
     */
    public int getTotal(){
        return this.total;
    }
        
    /**
     * Método equals sobrescrito que a comparação da variável Tag seja única
     * @param obj Objeto a ser comparado
     * @return Retorna true se for igual ou false se não for igual
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if (obj == null) {
            return false;
        }
        if(obj.getClass() != getClass()){
            return false;
        }             
        final TagEncontrada other = (TagEncontrada) obj;
        if (!this.tag.equals( other.tag )) {
            return false;
        }
        return true;        
    }
    
    /**
     * Método toString sobrescrito para melhor representar a textualidade da classe TagEncontrada
     * @return Retorna a representação textual da classe TagEncontrada
     */
    @Override
    public String toString(){
        return "Tag: "+ this.tag +
               ", Total: "+ this.total;
    }
}
