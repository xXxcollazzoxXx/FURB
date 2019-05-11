/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import trabalho1.model.ListaEncadeada;

/**
 *
 * @author vcalazans
 */
public class ValidadorHTMLCellRender extends DefaultTableCellRenderer {

    private int linhaErro;

    public ValidadorHTMLCellRender(int linhaErro) {
        this.linhaErro = linhaErro;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.

            setBackground(Color.WHITE);
            
            if(isSelected){
                setForeground(Color.BLACK);
                setBackground(Color.LIGHT_GRAY);
            }
            if((linhaErro - 1) == row)
                setBackground(Color.red);

            return this;
    }

}
