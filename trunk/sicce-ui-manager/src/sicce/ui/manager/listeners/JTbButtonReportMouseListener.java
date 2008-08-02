/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.listeners;

import java.awt.Rectangle;
import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.businesslogic.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import org.jdesktop.application.ResourceMap;
import org.netbeans.api.wizard.WizardDisplayer;
import sicce.ui.manager.forms.ReportsPane;
import sicce.wizard.report.NewWizard;
import sicce.wizard.reports.models.ReportModel;

/**
 *
 * @author karu
 */
public class JTbButtonReportMouseListener implements MouseListener {

    private JTable ptable;
    ResourceMap resourceMap;
    ReportsPane reportPane;

    private void __forwardEventToButton(MouseEvent e) {
        TableColumnModel columnModel = ptable.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int row = e.getY() / ptable.getRowHeight();

        if (column == 2) { 
                    SicceTableModel<ReportModel> tableModel = (SicceTableModel<ReportModel>) ptable.getModel();
                    ReportModel report = tableModel.getRow(row);
                    WizardDisplayer.showWizard(new NewWizard(resourceMap, report).createWizard(), new Rectangle(20, 20, 700, 500));             
                    reportPane.FillGrid();
            }
        }

    public

     JTbButtonReportMouseListener(JTable table, ResourceMap resourceMap, ReportsPane reportPane) {
        ptable = table;
        this.resourceMap = resourceMap;
        this.reportPane = reportPane;
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
