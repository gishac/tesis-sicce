/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.listeners;

import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.businesslogic.model.SearchFilterTableModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.dataaccess.PlainQueryExecutor;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.interfaces.IFilter;
import sicce.api.info.interfaces.ISimpleQueryResult;
import sicce.ui.manager.controls.JOptionPaneExtended;
import sicce.ui.manager.controls.SearchDialog;

/**
 *
 * @author karu
 */
public class JTableButtonMouseListener implements MouseListener {

    private JTable ptable;
    private ISimpleQueryResult resultQuery;
    private List<ISimpleQueryResult> resultList;

    private void __forwardEventToButton(MouseEvent e) {
        TableColumnModel columnModel = ptable.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int row = e.getY() / ptable.getRowHeight();
        if (ptable.getValueAt(row, 1) == null) {
            JOptionPaneExtended.showMessageDialog(null, "Debe selecionar el operador");
            return;
        }
        if (column == 3) {
            try {

                
                
                SicceTableModel<IFilter> tableModel = (SicceTableModel<IFilter>) ptable.getModel();
                IFilter filter = tableModel.getRow(row);
                
                String selectField = "select " + filter.getField().getDescriptionField() + " from " + filter.getField().getTableName();
                System.out.println("query" + selectField);
                List<ISimpleQueryResult> descriptions = PlainQueryExecutor.ExecuteSimpleQuery(selectField, DataAccessManager.getInstance().getConnectionDB().getConnection());

                if (ptable.getValueAt(row, 1).equals("entre")) {
                    SearchDialog<ISimpleQueryResult> searchFilterDialog = new SearchDialog<ISimpleQueryResult>(new JFrame(), true, new SearchFilterTableModel(descriptions), ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    searchFilterDialog.setVisible(true);
                    DialogResult result = searchFilterDialog.getDialogResult();
                    if (result == DialogResult.Ok) {
                        resultList = searchFilterDialog.getResultList();

                        if (resultList != null) {
                            String valueCell = null;
                            for (ISimpleQueryResult tmpresult : resultList) {
                                String valuetmp = tmpresult.getResult();
                                if (valueCell == null) {
                                    valueCell = " '" + valuetmp.concat("'").concat(",");
                                } else {
                                    valueCell = valueCell.concat(" '").concat(valuetmp).concat("'").concat(",");
                                }
                            }
                            String totalValue = valueCell.substring(0, valueCell.length() - 1);
                            ptable.setValueAt(totalValue, row, 2);
                        }
                    }
                    ptable.repaint();
                } else {
                    SearchDialog<ISimpleQueryResult> searchFilterDialog = new SearchDialog<ISimpleQueryResult>(new JFrame(), true, new SearchFilterTableModel(descriptions), ListSelectionModel.SINGLE_SELECTION);
                    searchFilterDialog.setVisible(true);
                    DialogResult result = searchFilterDialog.getDialogResult();
                    if (result == DialogResult.Ok) {
                        resultQuery = searchFilterDialog.getSearchResult();
                        if (resultQuery != null) {
                            System.out.println("result: " + resultQuery.getResult());
                            ptable.setValueAt(resultQuery.getResult(), row, 2);
                        }
                    }
                    ptable.repaint();
                }

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
