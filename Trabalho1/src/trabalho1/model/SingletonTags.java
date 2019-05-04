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
    
    public static boolean isSingletonTag( String tag ){
        for(String g : values){
            if( tag.toUpperCase().contains( g ) )
                return true;            
        }    
        return false;
    }            
}


