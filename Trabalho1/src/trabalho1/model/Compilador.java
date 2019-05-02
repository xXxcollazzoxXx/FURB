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
 * @author Mello feat Calazans
 */
public class Compilador {
    
    private File arquivo;
    private PilhaLista<String> pilhaTagsAbertura;
    private PilhaLista<String> pilhaTagsBackup;
    private ListaEncadeada<String> listaTagsFechamento;
    private ListaEncadeada<TagEncontrada> listaTagsEncontradas;
    private SituacaoAnalise situacaoAnalise;
    private ListaEncadeada<Ocorrencia> listaOcorrencias;
    private Scanner arquivoHTML;
    
    public Compilador(File arquivo){
        atualizaArquivoHTML( arquivo );
    }        
    
    public void atualizaArquivoHTML(File arquivo){
        try{         
          this.arquivo         = arquivo;
          pilhaTagsAbertura    = new PilhaLista<>();          
          pilhaTagsBackup      = new PilhaLista<>();
          listaTagsFechamento  = new ListaEncadeada<>();
          listaTagsEncontradas = new ListaEncadeada<>();
          situacaoAnalise      = SituacaoAnalise.AguardandoAnalise;
          listaOcorrencias     = new ListaEncadeada<>();                    
          arquivoHTML          = new Scanner(this.arquivo, "UTF-8"); 
        }catch( FileNotFoundException e ){
            throw new RuntimeException( "Arquivo ("+ this.arquivo.getName() +") não encontrado!" );
        }
    }    
    
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
                    int posicaoBarraFechamento = subPalavra.indexOf("/>");
                    if( posicaoBarraFechamento > -1 ){
                        subPalavra = subPalavra.substring(0, posicaoBarraFechamento) + ">";
                    }                                        
                    int posicaoTagFechamento = subPalavra.indexOf("</");
                    if( !isSingletonTag( subPalavra ) // Testa as Singleton Tags
                     && posicaoTagFechamento > -1 ){
                        //é Fechamento de Tag
                        if( pilhaTagsAbertura.peek( ).equals( subPalavra.replace("/", "") ) ){                            
                            pilhaTagsAbertura.pop();
                        }else{
                            //Gerar ocorrência por as tags não serem iguais
                            gerarOcorrencia( numeroLinha,
                                             "Esperava o fechamento da tag "+ pilhaTagsAbertura.peek() +", mas encontrou "+ subPalavra,
                                             "Realizar o fechamento da tag "+ pilhaTagsAbertura.peek() +" antes da tag "+ subPalavra );
                            break;
                        }                                    
                    }else{
                        //é Abertura de Tag
                        pilhaTagsAbertura.push(subPalavra);
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
            int quantidadeRegistros = pilhaTagsAbertura.tamanhoPilha();
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
                    NoLista<String> tag = listaTagsFechamento.buscar( pilhaTagsAbertura.peek().replace("<", "</") );
                    if( tag != null ){
                        listaTagsFechamento.retirar( tag.getInfo() );                    
                    }else{
                        gerarOcorrencia( 0, 
                                         "Não encontado o fechamento da tag "+ pilhaTagsAbertura.peek() +" no arquivo "+ arquivo.getName(), 
                                         "Relizar o fechamento da tag "+ pilhaTagsAbertura.peek());                    
                    }                    
                    pilhaTagsAbertura.pop();
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
    
    private void gerarOcorrencia(int numeroLinha, String textoOcorrencia, String textoSugestao){
        Ocorrencia ocorrencia;
        ocorrencia = new Ocorrencia( numeroLinha,
                                     textoOcorrencia + ";",
                                     textoSugestao + ";");
        listaOcorrencias.inserir( ocorrencia );
    }
    
    private boolean isSingletonTag(String tag){
        boolean isSingleton = false;
        tag = tag.replace("<", "");
        tag = tag.replace(">", "");
        for( TagsSingleton t : TagsSingleton.values() ){
            if( tag.contains(t.name( ) ) ){
                isSingleton = true;
                break;
            }
        }
        return isSingleton;
    }
            
    public String exibeTags( ){
        return pilhaTagsBackup.toString( );
    }
    
    public int totalTags( ){
        return pilhaTagsBackup.tamanhoPilha( );
    }        
    
    public String contabilizarTags(){
        try{            
            String subPalavra;
            if( this.situacaoAnalise != SituacaoAnalise.AguardandoAnalise ){
                arquivoHTML = new Scanner( this.arquivo, "UTF-8" );
                String textoLinha; 
                while( arquivoHTML.hasNext() ){
                    textoLinha = arquivoHTML.nextLine();
                    while( textoLinha.contains( "<" ) ){
                        int posicaoInicio = textoLinha.indexOf("<");
                        if( posicaoInicio > -1 ){
                            int posicaoFinal = textoLinha.indexOf(">");
                            if( posicaoFinal > -1 ){
                                subPalavra = textoLinha.substring(posicaoInicio, 
                                                                  posicaoFinal + 1);
                                textoLinha = textoLinha.substring(posicaoFinal + 1, 
                                                                  textoLinha.length());
                                int posicaoEspaco = subPalavra.indexOf(" ");
                                if( posicaoEspaco > -1 ){
                                    subPalavra = subPalavra.substring(0, posicaoEspaco) + ">";
                                }
                                int posicaoBarraFechamento = subPalavra.indexOf("/>");
                                if( posicaoBarraFechamento > -1 ){
                                    subPalavra = subPalavra.substring(0, posicaoBarraFechamento) + ">";
                                }
                                adicionaTagEncontrada( subPalavra );                                                                                           
                            }else{
                                subPalavra = textoLinha.substring(posicaoInicio, 
                                                                  textoLinha.length());
                                int posicaoEspaco = subPalavra.indexOf(" ");
                                if( posicaoEspaco > -1 ){
                                    subPalavra = subPalavra.substring(0, posicaoEspaco);
                                }
                                subPalavra += ">";
                                adicionaTagEncontrada( subPalavra );
                                break;
                            }            
                        }    
                    }
                }
            }            
            return listaTagsEncontradas.toString();
        }catch( FileNotFoundException e){
            if( arquivoHTML.hasNext() ){
                arquivoHTML.close();
            }
            throw new RuntimeException("Arquivo ("+ arquivo.getName() +") não encontrado!");
        }
    }
    
    private void adicionaTagEncontrada( String tag ){
        TagEncontrada t = new TagEncontrada();
        t.setTag( tag );
        NoLista<TagEncontrada> reg = listaTagsEncontradas.buscar( t );
        if( reg != null ){
            reg.getInfo().setTotal( reg.getInfo().getTotal() + 1 );
        }else{                                    
            t.setTotal( 1 );
            listaTagsEncontradas.inserir( t );
        }   
    }
    
}
