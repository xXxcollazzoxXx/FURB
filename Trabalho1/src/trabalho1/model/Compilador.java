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
public class Compilador {
    
    private File arquivo;
    private PilhaLista<String> pilhaTags;
    private PilhaLista<String> pilhaTagsBackup;
    private ListaEncadeada<String> listaTagsFechamento;
    private SituacaoAnalise situacaoAnalise;
    private ListaEncadeada<Ocorrencia> listaOcorrencias;
    private Scanner arquivoHTML;
    
    public SituacaoAnalise getSituacaoAnalise(){
        return this.situacaoAnalise;
    }
    
    public Ocorrencia[] getOcorrencias(){
        Ocorrencia[] ocorrencias = new Ocorrencia[ listaOcorrencias.obterComprimento( ) ];
        NoLista<Ocorrencia> NoOcorrencia = listaOcorrencias.getPrimeiro();
        int indice = 0;
        while( NoOcorrencia != null ){
            ocorrencias[ indice ] = NoOcorrencia.getInfo( );
            NoOcorrencia = NoOcorrencia.getProximo( );
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
    
    private void gerarOcorrencia(int numeroLinha, String textoOcorrencia, String textoSugestao){
        Ocorrencia ocorrencia;
        ocorrencia = new Ocorrencia( numeroLinha,
                                     textoOcorrencia + ";",
                                     textoSugestao + ";");
        listaOcorrencias.inserir( ocorrencia );
    }
    
    private void analisarLinha(String textoLinha, int numeroLinha){
        textoLinha = textoLinha.toUpperCase( ); // Deixa toda e qualquer letra em maiusculo                                
        String subPalavra = "";
        while( textoLinha.contains( "<" ) ){
            int posicaoInicio = textoLinha.indexOf("<");
            if( posicaoInicio > -1 ){
                int posicaoFinal = textoLinha.indexOf(">");
                if( posicaoFinal > -1 ){                                                    
                    subPalavra = textoLinha.substring(posicaoInicio, posicaoFinal + 1);
                    textoLinha = textoLinha.substring(posicaoFinal + 1, textoLinha.length());                        
                    int posicaoEspaco = subPalavra.indexOf(" ");
                    if( posicaoEspaco > -1 ){
                        subPalavra = subPalavra.substring(0, posicaoEspaco) + ">";
                    }
                    int posicaoBarra = subPalavra.indexOf("/>");
                    if( posicaoBarra > -1 ){
                        subPalavra = subPalavra.substring(0, posicaoBarra) + ">";
                    }                                        
                    int posicaoTagFechamento = subPalavra.indexOf("</");
                    if( !isSingletonTag( subPalavra ) // Testa as Singleton Tags
                     && posicaoTagFechamento > -1 ){
                        //é Fechamento
                        if( pilhaTags.peek( ).equals( subPalavra.replace("/", "") ) ){
                            pilhaTags.pop();
                        }else{
                            //Gerar ocorrência por as tags não serem iguais
                            gerarOcorrencia( numeroLinha,
                                             "Esperava o fechamento da tag "+ pilhaTags.peek() +", mas encontrou "+ subPalavra,
                                             "Realizar o fechamento da tag "+ pilhaTags.peek() +" antes da tag "+ subPalavra );
                            break;
                        }                                    
                    }else{
                        //é Abertura
                        pilhaTags.push(subPalavra);
                        pilhaTagsBackup.push(subPalavra);
                    }                            
                }else{
                    int posicaoEspaco = subPalavra.indexOf(" ");
                    if( posicaoEspaco > -1 ){
                        subPalavra = subPalavra.substring(0, posicaoEspaco);
                    }
                    subPalavra = subPalavra.replace( "<", "" );
                    gerarOcorrencia( numeroLinha,
                                     "Não encontrado o caracter de fechamento ('>') da tag "+ subPalavra,
                                     "Realizar o fechamento da tag "+ subPalavra );
                    break;
                }            
            }    
        }
    }    
    
    private void processaTagsFinais(){
        try{
            int quantidadeRegistros = pilhaTags.tamanhoPilha();
            if( quantidadeRegistros > 0 ){
                arquivoHTML = new Scanner( this.arquivo, "UTF-8" );
                String textoLinha;            
                while( arquivoHTML.hasNext() ){
                    textoLinha = arquivoHTML.nextLine().replaceAll( " ", "" ); // Remove todo e qualquer espaço em branco
                    textoLinha = textoLinha.toUpperCase(); // Transformar toda e qualquer letra em maiuscula
                    while( textoLinha.contains( "</" ) ){        
                        textoLinha = textoLinha.substring( textoLinha.indexOf( "</" ),
                                                           textoLinha.length() );
                        int posicaoFechamento = textoLinha.indexOf( ">" );
                        if( posicaoFechamento > -1 ){
                            String subPalavra = textoLinha.substring( 0,
                                                                      posicaoFechamento + 1);
                            listaTagsFechamento.inserir( subPalavra );
                            textoLinha = textoLinha.substring( posicaoFechamento + 1, 
                                                               textoLinha.length() );
                        }else{
                            break;
                        }
                    }
                }
                arquivoHTML.close();
                while( quantidadeRegistros > 0 ){
                    NoLista<String> tag = listaTagsFechamento.buscar( pilhaTags.peek().replace("<", "</") );
                    if( tag != null ){
                        listaTagsFechamento.retirar( tag.getInfo() );                    
                    }else{
                        gerarOcorrencia( 0, 
                                         "Não encontado o fechamento da tag "+ pilhaTags.peek() +" no arquivo "+ arquivo.getName(), 
                                         "Relizar o fechamento da tag "+ pilhaTags.peek());                    
                    }                    
                    pilhaTags.pop();
                    quantidadeRegistros--;
                }   
            }            
        }catch( FileNotFoundException e ){
            if( arquivoHTML.hasNext() ){
                arquivoHTML.close();
            }
            throw new RuntimeException( "Arquivo ("+ arquivo.getName() +") não encontrado!" );
        }
    }
    
    public void analisarArquivo( ){
        if( situacaoAnalise == SituacaoAnalise.AguardandoAnalise ){
            int numeroLinha = 1;            
            while( listaOcorrencias.estaVazia()
                && arquivoHTML.hasNext() ){
                analisarLinha( arquivoHTML.nextLine(),
                               numeroLinha);               
                numeroLinha++;
            }
            arquivoHTML.close();
            processaTagsFinais();                        
            if( listaOcorrencias.estaVazia( ) )
                situacaoAnalise = SituacaoAnalise.AnalisadoSemErro;
            else
                situacaoAnalise = SituacaoAnalise.AnalisadoComErro;            
        }
    }            
    
    public void atualizaArquivoHTML(File arquivo){
        try{         
          this.arquivo        = arquivo;
          pilhaTags           = new PilhaLista<>();          
          pilhaTagsBackup     = new PilhaLista<>();
          situacaoAnalise     = SituacaoAnalise.AguardandoAnalise;
          listaOcorrencias    = new ListaEncadeada<>();
          listaTagsFechamento = new ListaEncadeada<>();
          arquivoHTML         = new Scanner(this.arquivo, "UTF-8"); 
        }catch( FileNotFoundException e ){
            throw new RuntimeException( "Arquivo ("+ this.arquivo.getName() +") não encontrado!" );
        }
    }    
    
    private boolean isSingletonTag(String tag){
        boolean isSingleton = false;
        tag = tag.replace("<", "");
        tag = tag.replace(">", "");
        for( TagsSingleton t : TagsSingleton.values() ){
            if( tag.equals( t.name( ) ) ){
                isSingleton = true;
                break;
            }
        }
        return isSingleton;
    }
    
}
