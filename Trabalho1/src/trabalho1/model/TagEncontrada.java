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
public class TagEncontrada{
    
    String tag;
    int total;
    
    public TagEncontrada(){
        this.tag = "";
        this.total = 0;
    }
    
    public void setTag(String tag){
        this.tag = tag;
    }
            
    public String getTag(){
        return this.tag;
    }
    
    public void setTotal(int total){
        this.total = total;
    }
    
    public int getTotal(){
        return this.getTotal();
    }
        
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
        if (this.tag != other.tag) {
            return false;
        }
        return true;        
    }
    
    @Override
    public String toString(){
        return "Tag: "+ this.tag +
               ", Total: "+ this.total;
    }
}
