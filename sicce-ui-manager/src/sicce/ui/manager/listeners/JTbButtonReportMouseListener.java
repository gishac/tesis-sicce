/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import sicce.api.businesslogic.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jboss.util.DirectoryBuilder;
import org.jboss.util.DirectoryBuilder;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author karu
 */
public class JTbButtonReportMouseListener implements MouseListener {

    private JTable ptable;

    private void __forwardEventToButton(MouseEvent e) {
        TableColumnModel columnModel = ptable.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int row = e.getY() / ptable.getRowHeight();

        if (column == 2) {
            
                FileOutputStream x = null;
                try {
                    Map pCriterios = new HashMap();
                    
                    
                    SicceTableModel<IReport> tableModel = (SicceTableModel<IReport>) ptable.getModel();
                    IReport preport = tableModel.getRow(row);
                    JasperReport jprint = JasperCompileManager.compileReport(preport.getReportJrxml());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jprint, pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (JRException ex) {
                    Logger.getLogger(JTbButtonReportMouseListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    public

     JTbButtonReportMouseListener(JTable table) {
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
