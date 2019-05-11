/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1.view;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vcalazans
 */
public class ValidadorErrosCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.

        
        if (column == 0) {
            setBackground(Color.LIGHT_GRAY);
        }else if (column == 1){
            setBackground(Color.white);
            table.getColumnModel().getColumn(1).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMinWidth(10);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            
        }else if(column == 2){
            setBackground(Color.GREEN);
        }



        return this;

    }

}
