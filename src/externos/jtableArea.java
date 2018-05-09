package externos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor
 */
public class jtableArea extends DefaultTableModel {

    public jtableArea(String[] string, int i) {
        super(string, i);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return !(column == 0);
    }

}
