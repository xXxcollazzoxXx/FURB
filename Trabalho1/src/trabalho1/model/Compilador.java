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
 * @authors William Mello e Victor Calazans
 */
public class Compilador {
    
    private File arquivo;
    private PilhaLista<String> pilhaTagsAbertura;    
    private ListaEncadeada<String> listaTagsFechamento;
    private ListaEncadeada<TagEncontrada> listaTagsEncontradas;
    private SituacaoAnalise situacaoAnalise;
    private ListaEncadeada<Ocorrencia> listaOcorrencias;
    private Scanner arquivoHTML;
    private boolean lbComentarioEmAberto = false;
    
    /**
     * Construtor da classe Compilador
     * @param arquivo Arquivo na qual se encontra o atual arquivo HTML
     */
    public Compilador(File arquivo){
        atualizaArquivoHTML( arquivo );
    }        
    
    /**
     * Método responsável por atualizar o arquivo HTML que se deseja analisar
     * @param arquivo Arquivo na qual se encontra o arquivo HTML
     */
    public void atualizaArquivoHTML(File arquivo){
        try{         
          this.arquivo         = arquivo;
          pilhaTagsAbertura    = new PilhaLista<>();          
          listaTagsFechamento  = new ListaEncadeada<>();
          listaTagsEncontradas = new ListaEncadeada<>();
          situacaoAnalise      = SituacaoAnalise.AguardandoAnalise;
          listaOcorrencias     = new ListaEncadeada<>();                    
          arquivoHTML          = new Scanner(this.arquivo, "UTF-8"); 
        }catch( FileNotFoundException e ){
            throw new RuntimeException( "Arquivo ("+ this.arquivo.getName() +") não encontrado!" );
        }
    }    
        
    /**
     * Método responsável por devolver a situação atual do arquivo HTML
     * @return Devolve a situação atual do arquivo HTML
     */
    public SituacaoAnalise getSituacaoAnalise(){
        return this.situacaoAnalise;
    }
    
    /**
     * Método responsável por devolver um vetor das tags encontradas
     * @return Devolve um vetor de tags encontradas para uma melhor varredura das rotinas invocadoras
     */
    public TagEncontrada[] getTagsEncontradas(){
        TagEncontrada[] tagsEncontradas = new TagEncontrada[ listaTagsEncontradas.obterComprimento() ];
        NoLista<TagEncontrada> noTagEncontrada = listaTagsEncontradas.getPrimeiro();
        int indice = 0;
        while( noTagEncontrada != null ){
            tagsEncontradas[ indice ] = noTagEncontrada.getInfo();
            noTagEncontrada = noTagEncontrada.getProximo();
            indice++;
        }
        return tagsEncontradas;
    }
    
    
    /**
     * Método responsável por devolver um vetor das ocorrências encontradas
     * @return Devolve um vetor de Ocorrências para melhor varredura nas rotinas invocadoras
     */
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
    
    /**
     * Método responsável por analisar o arquivo HTML no geral e definir a existência de erros ou não
     */
    public void analisarArquivo( ){
        if( situacaoAnalise == SituacaoAnalise.AguardandoAnalise ){
            lbComentarioEmAberto = false;
            int numeroLinha = 1;            
            while( listaOcorrencias.estaVazia()
                && arquivoHTML.hasNext() ){
                analisarLinha( arquivoHTML.nextLine(),
                               numeroLinha);               
                numeroLinha++;
            }
            arquivoHTML.close();
            if(listaOcorrencias.estaVazia()){
                processaTagsFinais();
            }
            if( listaOcorrencias.estaVazia( ) )
                situacaoAnalise = SituacaoAnalise.AnalisadoSemErro;
            else
                situacaoAnalise = SituacaoAnalise.AnalisadoComErro;            
        }
    }                    
    
    /**
     * Método responsável por analisar minuciosamente algum erro da linha corrente do arquivo HTML
     * @param textoLinha Linha na qual se deseja analisar
     * @param numeroLinha Número da linha na qual essa linha se encontra no arquivo HTML
     */
    private void analisarLinha(String textoLinha, int numeroLinha){
        textoLinha = textoLinha.toUpperCase( ); // Deixa toda e qualquer letra em maiusculo                                
        String subPalavra = "";        
        while( textoLinha.contains( "<" ) ){
            if( !lbComentarioEmAberto ){
                int posicaoAberturaComentario = textoLinha.indexOf("<!--");
                while( posicaoAberturaComentario > -1 ){
                    lbComentarioEmAberto = true;
                    int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                    if( posicaoFechamentoComentario > -1 ){                    
                        lbComentarioEmAberto = false;
                        textoLinha = textoLinha.substring(0, posicaoAberturaComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                    
                    }else{
                        textoLinha = textoLinha.substring(0, posicaoAberturaComentario);
                    }
                    posicaoAberturaComentario = textoLinha.indexOf("<!--");
                }
            }else{                
                int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                if( posicaoFechamentoComentario > -1 ){
                    lbComentarioEmAberto = false;
                    textoLinha = textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());
                }else{
                    if( !textoLinha.contains("<!--") );                    
                        break;                   
                }    
                int posicaoAberturaComentario = textoLinha.indexOf("<!--");
                while( posicaoAberturaComentario > -1 ){
                    lbComentarioEmAberto = true;
                    posicaoFechamentoComentario = textoLinha.indexOf("-->");
                    if( posicaoFechamentoComentario > -1 ){                    
                        lbComentarioEmAberto = false;
                        textoLinha = textoLinha.substring(0, posicaoAberturaComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                    
                    }else{
                        textoLinha = textoLinha.substring(0, posicaoAberturaComentario);
                    }
                    posicaoAberturaComentario = textoLinha.indexOf("<!--");
                }    
            }
            boolean adicionou = false;
            int posicaoFechamentoComentario = textoLinha.indexOf("-->");
            while( posicaoFechamentoComentario > -1 ){
                textoLinha = textoLinha.substring(0, posicaoFechamentoComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                
                if( !adicionou ){
                    adicionou = true;
                    gerarOcorrencia(numeroLinha,
                                    "Fechamento(s) do(s) comentário(s) (-->) incorreto(s).",
                                    "Verifique seu arquivo, pois existe(m) algum(uns) fechamento(s) de comentário(s) incorreto(s)!");                    
                }                    
                posicaoFechamentoComentario = textoLinha.indexOf("-->");
            }
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
                    if( posicaoTagFechamento > -1 ){
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
                        if( !SingletonTags.isSingletonTag( subPalavra ) )// Não pode empilhar SingletonTags
                            pilhaTagsAbertura.push( subPalavra );
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
    
    /**
     * Método responsável por processar as tags que faltaram fechamento, caso o arquivo não contenha nenhum problema eventual na primeira análise
     */
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
                        if( !lbComentarioEmAberto ){
                            int posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            while( posicaoAberturaComentario > -1 ){
                                lbComentarioEmAberto = true;
                                int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                                if( posicaoFechamentoComentario > -1 ){                    
                                    lbComentarioEmAberto = false;
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                    
                                }else{
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario);
                                }
                                posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            }
                        }else{                        
                            int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                            if( posicaoFechamentoComentario > -1 ){
                                lbComentarioEmAberto = false;
                                textoLinha = textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());
                            }else{
                                if( !textoLinha.contains("<!--") );                    
                                    break;                   
                            }    
                            int posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            while( posicaoAberturaComentario > -1 ){
                                lbComentarioEmAberto = true;
                                posicaoFechamentoComentario = textoLinha.indexOf("-->");
                                if( posicaoFechamentoComentario > -1 ){                    
                                    lbComentarioEmAberto = false;
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                    
                                }else{
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario);
                                }
                                posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            }    
                        }    
                        boolean adicionou = false;
                        int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                        while( posicaoFechamentoComentario > -1 ){
                            textoLinha = textoLinha.substring(0, posicaoFechamentoComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());
                            posicaoFechamentoComentario = textoLinha.indexOf("-->");
                        }
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
                while( listaOcorrencias.estaVazia()
                    && quantidadeRegistros > 0 ){
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
    
    /**
     * Método responsável por adicionar uma ocorrência na lista de ocorrências/problemas encontradas(os)
     * @param numeroLinha Número da linha em que a ocorrência foi detectada
     * @param textoOcorrencia Texto do problema encontrada
     * @param textoSugestao Sugestão para o usuário corrigir o trecho do arquivo HTML
     */
    private void gerarOcorrencia(int numeroLinha, String textoOcorrencia, String textoSugestao){
        Ocorrencia ocorrencia;
        ocorrencia = new Ocorrencia( numeroLinha,
                                     textoOcorrencia + ";",
                                     textoSugestao + ";");
        listaOcorrencias.inserir( ocorrencia );
    }        
            
    /**
     * Método responsável por devolver a representação textual da lista de tags encontradas, caso o arqiuvo já tenha sido analisado
     * @return Retorna a representação textual das lista de tags encontradas, caso o arquivo já tenha sido analisado
     */
    public String exibeTags( ){
       if( this.situacaoAnalise != SituacaoAnalise.AguardandoAnalise ){
           return listaTagsEncontradas.toString();
       }else{
           return "";
       }
    }
    
    /**
     * Método responsável por fornecer a quantidade de tags encontradas no arquivo HTML
     * @return Retorna o total de tags encontradas
     */    
    public int totalTags( ){
        if( this.situacaoAnalise != SituacaoAnalise.AguardandoAnalise ){
            int totalTags = 0;
            NoLista<TagEncontrada> noTag = listaTagsEncontradas.getPrimeiro();
            while( noTag != null ){
                totalTags += noTag.getInfo().getTotal();
                noTag = noTag.getProximo();
            }
            return totalTags;
        }else{
            return 0;
        }    
    }        
    
    /**
     * Método responsável por contabalizar as tags do arquivo HTML
     */    
    public void contabilizarTags(){
        try{                        
            if( this.situacaoAnalise != SituacaoAnalise.AguardandoAnalise ){
                arquivoHTML = new Scanner( this.arquivo, "UTF-8" );
                String textoLinha; 
                String subPalavra;                
                while( arquivoHTML.hasNext() ){
                    textoLinha = arquivoHTML.nextLine();
                    while( textoLinha.contains( "<" ) ){
                        if( !lbComentarioEmAberto ){
                            int posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            while( posicaoAberturaComentario > -1 ){
                                lbComentarioEmAberto = true;
                                int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                                if( posicaoFechamentoComentario > -1 ){                    
                                    lbComentarioEmAberto = false;
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                    
                                }else{
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario);
                                }
                                posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            }    
                        }else{                        
                            int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                            if( posicaoFechamentoComentario > -1 ){
                                lbComentarioEmAberto = false;
                                textoLinha = textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());
                            }else{
                                if( !textoLinha.contains("<!--") );                    
                                    break;                   
                            }    
                            int posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            while( posicaoAberturaComentario > -1 ){
                                lbComentarioEmAberto = true;
                                posicaoFechamentoComentario = textoLinha.indexOf("-->");
                                if( posicaoFechamentoComentario > -1 ){                    
                                    lbComentarioEmAberto = false;
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());                    
                                }else{
                                    textoLinha = textoLinha.substring(0, posicaoAberturaComentario);
                                }
                                posicaoAberturaComentario = textoLinha.indexOf("<!--");
                            }    
                        } 
                        boolean adicionou = false;
                        int posicaoFechamentoComentario = textoLinha.indexOf("-->");
                        while( posicaoFechamentoComentario > -1 ){
                            textoLinha = textoLinha.substring(0, posicaoFechamentoComentario) + textoLinha.substring(posicaoFechamentoComentario + 3, textoLinha.length());
                            posicaoFechamentoComentario = textoLinha.indexOf("-->");
                        }
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
        }catch( FileNotFoundException e){
            if( arquivoHTML.hasNext() ){
                arquivoHTML.close();
            }
            throw new RuntimeException("Arquivo ("+ arquivo.getName() +") não encontrado!");
        }
    }
    
    /**
     * Método responsável por adicionar uma tag na lista de tags encontradas, caso ela não exista, ou incrementar a frequência, caso ela já exista
     * @param tag Tag encontrada no analisador
     */    
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
