/*
 * ReportsPane.java
 *
 * Created on 16 de junio de 2008, 23:57
 */

package sicce.ui.manager.forms;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.application.ResourceMap;
import sicce.api.businesslogic.renderer.JTableButtonRenderer;
import sicce.wizard.reports.models.ReportTableModel;
import sicce.ui.manager.controls.JTabExtended;
import sicce.api.info.ToolBarStateInfo;
import sicce.ui.manager.listeners.JTbButtonReportMouseListener;
import sicce.api.util.SerializableUtil;
import sicce.wizard.reports.models.ReportModel;
/**
 *
 * @author  Karu
 */
public class ReportsPane extends JTabExtended {
    
    ReportTableModel reportModel;
     JButton searchField;
    TableCellRenderer defaultRenderer;
    Icon imgReport;
    
    /** Creates new form ReportsPane */
    public ReportsPane(ResourceMap resourceMap) {
        initComponents();
        imgReport = resourceMap.getIcon("reportIcon");
        TableColumn reportColumn = grdSavedReport.getColumnModel().getColumn(2);
        reportColumn.setPreferredWidth(10);
        defaultRenderer = grdSavedReport.getDefaultRenderer(JButton.class);
        grdSavedReport.setDefaultRenderer(JButton.class,
			       new JTableButtonRenderer(defaultRenderer));
        grdSavedReport.setPreferredScrollableViewportSize(new Dimension(150, 200));
        grdSavedReport.addMouseListener(new JTbButtonReportMouseListener(grdSavedReport, resourceMap, this));

        this.setHandleToolBarStates(false);
        this.setToolBarStateInfo(new ToolBarStateInfo(false, false, false, false, false, false));
        FillGrid();
         
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdSavedReport = new javax.swing.JTable();

        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportsPane.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        grdSavedReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        grdSavedReport.setName("grdSavedReport"); // NOI18N
        jScrollPane1.setViewportView(grdSavedReport);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 320, 150));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 390, 210));
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
          if (currentObject == null) {  
            FillGrid();
        }
    }//GEN-LAST:event_formComponentShown
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grdSavedReport;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
  
  
    @Override
    public void FillGrid() {
        
        List<ReportModel> reports= new ArrayList(0);
        String directory = System.getProperty("user.dir") + File.separator + "rptSicce";
        File reportsDirectory = new File(directory);
        File[] files = reportsDirectory.listFiles();
        
        for(File reportFile : files){
            try {
                if(reportFile.isDirectory()) continue;
                ReportModel report = (ReportModel) SerializableUtil.Deserialize(reportFile.getAbsolutePath());
                reports.add(report);
            } catch (IOException ex) {
                Logger.getLogger(ReportsPane.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReportsPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        reportModel = new ReportTableModel(reports, imgReport);
        grdSavedReport.setModel(reportModel);
    }

  
  
    
    
}
