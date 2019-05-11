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
public class SingletonTags{
            
    private static String[] values = new String[]{"AREA",
                                                  "BASE",
                                                  "BR",
                                                  "COL",
                                                  "COMMAND",
                                                  "EMBED",
                                                  "HR",
                                                  "IMG",
                                                  "INPUT",
                                                  "KEYGEN",
                                                  "LINK",
                                                  "META",
                                                  "PARAM",
                                                  "SOURCE",
                                                  "TRACK",
                                                  "WBR",
                                                  "!DOCTYPE"};    
    public static String[] getValues(){
        return SingletonTags.values;
    }        
    
    /**
     * Método responsável por verificar se uma tag recebida como parâmetro é uma singleton tag
     * @param tag Tag que se deseja verificar
     * @return Retorna true se a tag recebida como parâmetro estiver no repositório das singleton tags, false para o contrário
     */    
    public static boolean isSingletonTag( String tag ){
        for(String g : values){
            if( tag.toUpperCase().contains( g ) )
                return true;            
        }    
        return false;
    }            
}


