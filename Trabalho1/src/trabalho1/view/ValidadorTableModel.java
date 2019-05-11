/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.view;

import javax.swing.table.AbstractTableModel;
import trabalho1.model.ListaEncadeada;
import trabalho1.model.NoLista;
import trabalho1.model.PilhaLista;
import trabalho1.model.TagEncontrada;

/**
 *
 * @author vcalazans
 */
public class ValidadorTableModel extends AbstractTableModel {

    private static final int COL_TAG = 0;
    private static final int COL_FREQUENCIA = 1;
    private ListaEncadeada<TagEncontrada> lista;

    public ValidadorTableModel(ListaEncadeada<TagEncontrada> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.obterComprimento();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        NoLista<TagEncontrada> t = lista.obterNo(rowIndex);

        if (t.getInfo() != null) {
            if (columnIndex == COL_TAG) {
                return t.getInfo().getTag();
            } else if (columnIndex == COL_FREQUENCIA) {
                return Integer.toString(t.getInfo().getTotal());
            }
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";

        switch (column) {
            case COL_TAG:
                coluna = "TAG";
                break;
            case COL_FREQUENCIA:
                coluna = "FREQUÊNCIA";
                break;
            default:

        }

        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_TAG) {
            return String.class;
        } else if (columnIndex == COL_FREQUENCIA) {
            return String.class;
        }

        return null;
    }

}
