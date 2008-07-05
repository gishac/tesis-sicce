/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.listeners;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.dataaccess.PlainQueryExecutor;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.interfaces.IFilter;
import sicce.api.info.interfaces.ISimpleQueryResult;
import sicce.ui.manager.controls.SearchDialog;

/**
 *
 * @author karu
 */
public class JTableButtonMouseListener implements MouseListener {

    private JTable ptable;
    private ISimpleQueryResult resultQuery;
    private void __forwardEventToButton(MouseEvent e) {
        TableColumnModel columnModel = ptable.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int row = e.getY() / ptable.getRowHeight();
        if (column == 3) {
            try {

                SicceTableModel<IFilter> tableModel = (SicceTableModel<IFilter>) ptable.getModel();
                IFilter filter = tableModel.getRow(row);
                String selectField = "select " + filter.getField().getDescriptionField() + " from " + filter.getField().getTableName();
                System.out.println("quuueryyyyy" + selectField);
                List<ISimpleQueryResult> descriptions = PlainQueryExecutor.ExecuteSimpleQuery(selectField, DataAccessManager.getInstance().getConnectionDB().getConnection());
                
                SearchDialog<ISimpleQueryResult> searchFilterDialog = new SearchDialog<ISimpleQueryResult>(new JFrame(), true, new SearchFilterTableModel(descriptions));
                searchFilterDialog.setVisible(true);
                DialogResult result = searchFilterDialog.getDialogResult();
                if (result == DialogResult.Ok) {
                    resultQuery = searchFilterDialog.getSearchResult();
                    if (resultQuery != null) {
                        
                    }
                }

                ptable.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(JTableButtonMouseListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public JTableButtonMouseListener(JTable table) {
        ptable = table;
    }

    public void mouseClicked(MouseEvent e) {
        __forwardEventToButton(e);

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}
