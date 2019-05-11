/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.view;

import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import trabalho1.model.ListaEncadeada;
import trabalho1.model.NoLista;
import trabalho1.model.TagEncontrada;

/**
 *
 * @author vcalazans
 */
public class ValidadorHTMLTableModel extends AbstractTableModel {

    private static final int COL_HTML = 0;
    ListaEncadeada<String> lista;

    public ValidadorHTMLTableModel(ListaEncadeada<String> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {

        return lista.obterComprimento();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //NoLista<String> t = lista.obterNo(rowIndex);

        String ret[] = lista.toString().split(",");

        int count = 0;
        if (lista != null) {
            if (columnIndex == COL_HTML) {
                return ret[(ret.length - 1) - rowIndex];
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";

        switch (column) {
            case COL_HTML:
                coluna = "HTML";
                break;
            default:

        }

        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_HTML) {
            return String.class;
        }

        return null;
    }

}
