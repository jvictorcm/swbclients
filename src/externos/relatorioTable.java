/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package externos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor
 */
public class relatorioTable extends DefaultTableModel {

    public relatorioTable(String[] string, int i) {
        super(string, i);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;

    }
}
