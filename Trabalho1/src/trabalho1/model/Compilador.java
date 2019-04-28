/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Notebook
 */
public class Compilador<T> {
    
    private File arquivo;
    private PilhaLista<Tags> pilhaTags;
    private PilhaLista<Tags> pilhaTagsBackup;
    private SituacaoAnalise situacaoAnalise;
    private ListaEncadeada<Ocorrencia> listaOcorrencias;
    private Scanner arquivoHTML;
    
    public SituacaoAnalise getSituacaoAnalise(){
        return this.situacaoAnalise;
    }
    
    public Ocorrencia[] getOcorrencias(){
        Ocorrencia[] ocorrencias = new Ocorrencia[ listaOcorrencias.obterComprimento( ) ];
        NoLista<Ocorrencia> ocorrencia = listaOcorrencias.getPrimeiro();
        int indice = 0;
        while( ocorrencia != null ){
            ocorrencias[ indice ] = ocorrencia.getInfo( );
            ocorrencia = ocorrencia.getProximo( );
            indice++;
        }
        return ocorrencias;
    }
    
    public Compilador(File arquivo){
        atualizaArquivoHTML( arquivo );
    }
    
    public String exibeTags( ){
        return pilhaTagsBackup.toString( );
    }
    
    public int totalTags( ){
        return pilhaTagsBackup.tamanhoPilha( );
    }
    
    public void analisarArquivo( ){
        if( situacaoAnalise == SituacaoAnalise.AguardandoAnalise ){            
            String textoLinha = "";
            int numeroLinha = 1;
            boolean achouErro = false;
            while( !achouErro
                && arquivoHTML.hasNext( ) ){
                textoLinha = arquivoHTML.nextLine( ).replaceAll( " ", "" ); // Remove todo e qualquer espaço em branco
                textoLinha = textoLinha.toUpperCase( ); // Deixa toda e qualquer letra em maiusculo
                for( Tags t : Tags.values( ) ){                    
                    if( textoLinha.indexOf( "<"+ t.name( ) ) > -1 ){
                        pilhaTagsBackup.push( t );
                        pilhaTags.push( t );
                    }    
                }    
                for( Tags t : Tags.values( ) ){                                        
                    String subPalavra = "";
                    int posicaoFechamento = textoLinha.indexOf( "</" );                                                
                    if( posicaoFechamento > -1 ){
                        subPalavra = textoLinha.substring(posicaoFechamento, textoLinha.length());
                        subPalavra = subPalavra.substring(0, subPalavra.indexOf(">") + 1);
                        if( subPalavra.equals("</"+ pilhaTags.peek( ).name( ) +">" ) ){
                            pilhaTags.pop( );
                        }else{    
                            Ocorrencia ocorrencia = new Ocorrencia( numeroLinha,
                                                                    "Esperava </"+ pilhaTags.peek( ).name( ) +">, mas encontrou "+ subPalavra +".",
                                                                    "Realizar o fechamento da tag <"+ pilhaTags.peek( ).name( ) +"> antes da tag "+ subPalavra +".");
                            listaOcorrencias.inserir(ocorrencia);
                            achouErro = true;
                            break;
                        }
                    }                                                
                }    
                numeroLinha++;
            }
            arquivoHTML.close();
            while( !achouErro
                && !pilhaTags.estaVazia( ) ){
                Ocorrencia ocorrencia;
                ocorrencia = new Ocorrencia( 0,
                                             "Tag <"+ pilhaTags.peek( ).name( ) +"> não encerrada com o comando </"+ pilhaTags.peek( ).name( ) +">",
                                             "Realizar o fechamento da tag <"+ pilhaTags.peek( ).name( ) +">");
                listaOcorrencias.inserir(ocorrencia);                
                pilhaTags.pop( );
            }
            achouErro = !listaOcorrencias.estaVazia( );
            if( !achouErro ){
                situacaoAnalise = SituacaoAnalise.AnalisadoSemErro;
            }else{
                situacaoAnalise = SituacaoAnalise.AnalisadoComErro;
            }
        }
    }            
    
    public void atualizaArquivoHTML(File arquivo){
        try{         
          this.arquivo     = arquivo;
          pilhaTags        = new PilhaLista<>();          
          pilhaTagsBackup  = new PilhaLista<>();
          situacaoAnalise  = SituacaoAnalise.AguardandoAnalise;
          listaOcorrencias = new ListaEncadeada<>();
          arquivoHTML      = new Scanner(this.arquivo, "UTF-8"); 
        }catch( FileNotFoundException e ){
            throw new RuntimeException( "Arquivo não encontrado!" );
        }
    }    
}
