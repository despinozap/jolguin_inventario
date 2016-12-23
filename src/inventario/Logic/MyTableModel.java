/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.Logic;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daid
 */
public class MyTableModel extends DefaultTableModel
{

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

}
