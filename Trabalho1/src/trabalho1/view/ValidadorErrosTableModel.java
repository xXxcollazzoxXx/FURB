/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.view;

import javax.swing.table.AbstractTableModel;
import trabalho1.model.ListaEncadeada;
import trabalho1.model.NoLista;
import trabalho1.model.Ocorrencia;

/**
 *
 * @author vcalazans
 */
public class ValidadorErrosTableModel extends AbstractTableModel {

    private static final int COL_ERROS = 0;
    private static final int COL_LINHA = 1;
    private static final int COL_SUGESTAO = 2;

    private ListaEncadeada<Ocorrencia> lista;

    public ValidadorErrosTableModel(ListaEncadeada<Ocorrencia> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.obterComprimento();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        NoLista<Ocorrencia> t = lista.obterNo(rowIndex);

        if (t.getInfo() != null) {
            if (columnIndex == COL_LINHA) {
                return Integer.toString(t.getInfo().getLinha());
            } else if (columnIndex == COL_ERROS) {
                return t.getInfo().getOcorrencia();
            } else if (columnIndex == COL_SUGESTAO) {
                return t.getInfo().getSugestao();
            }
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";

        switch (column) {
            case COL_LINHA:
                coluna = "Linha";
                break;
            case COL_ERROS:
                coluna = "Ocorrência";
                break;
            case COL_SUGESTAO:
                coluna = "Sugestão";
                break;
            default:

        }

        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_ERROS) {
            return Integer.class;
        } else if (columnIndex == COL_LINHA) {
            return String.class;
        } else if (columnIndex == COL_SUGESTAO){
            return String.class;
        }

        return null;
    }

}
