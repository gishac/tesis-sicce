/*
 * ReportForm3.java
 *
 * Created on 1 de abril de 2008, 11:43 PM
 */
package sicce.wizard.report.panels;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;
import sicce.ui.manager.controls.JOptionPaneExtended;
import sicce.ui.manager.reports.Field;
import sicce.ui.manager.reports.FieldHandler;
import sicce.ui.manager.reports.FieldsCellRenderer;
import sicce.ui.manager.reports.FieldsComparator;
import sicce.ui.manager.reports.LocationTemplate;

/**
 *
 * @author  Karu
 */
public class ReportForm3 extends javax.swing.JPanel {

    private final WizardController controller;
    private final Map wizardData;
    public static final String KEY_SELECTED = "selectedFields";
    public static final String KEY_GROUP = "groupFields";
   private List selectedField = null;
  
    /** Creates new form ReportDetail */
    public ReportForm3(WizardController controller, Map wizardData) {
        initComponents();
        this.controller = controller;
        this.wizardData = wizardData;
       
        System.out.println("map" + wizardData.get(KEY_SELECTED));
        selectedField = (List) wizardData.get(KEY_SELECTED);
        
        fillSelectedFields();

        controller.setProblem("Defina los criterios del Reporte...");
        
        
    }

    public void fillSelectedFields() {
      
      lstSelectedFields.setCellRenderer(new FieldsCellRenderer());
      lstSelectedFields.setListData(selectedField.toArray());
    }

   
    
    public void updateLists(){
      lstGroupFields.setCellRenderer(new FieldsCellRenderer());
     
      lstSelectedFields.setListData(FieldHandler.getSelectedFields().toArray());
      lstGroupFields.setListData(FieldHandler.getListGroupFields().toArray());
    
    }

    
     public void fillWizardMap(){
        
        wizardData.put (KEY_GROUP, FieldHandler.getListGroupFields());
        controller.setProblem(null);
        if (FieldHandler.getListGroupFields()!= null) {
            try {

                LocationTemplate template = new LocationTemplate();
                template.buildReport("Reporte", FieldHandler.getSelectedFields(), FieldHandler.getListGroupFields());

                controller.setForwardNavigationMode(controller.MODE_CAN_FINISH);
            } catch (Exception ex) {
                Logger.getLogger(ReportForm3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
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
        lstGroupFields = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstSelectedFields = new javax.swing.JList();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAddField = new javax.swing.JButton();
        btnRemoveField = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dtpFinishDate = new com.toedter.calendar.JDateChooser();
        dtpBeginDate = new com.toedter.calendar.JDateChooser();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportForm3.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), null)); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lstGroupFields.setName("lstGroupFields"); // NOI18N
        lstGroupFields.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstGroupFieldsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstGroupFields);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 140, 170));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        lstSelectedFields.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lstSelectedFields.setName("lstSelectedFields"); // NOI18N
        jScrollPane2.setViewportView(lstSelectedFields);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 130, 170));

        btnUp.setIcon(resourceMap.getIcon("btnUp.icon")); // NOI18N
        btnUp.setName("btnUp"); // NOI18N
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });
        jPanel1.add(btnUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 30, 30));

        btnDown.setIcon(resourceMap.getIcon("btnDown.icon")); // NOI18N
        btnDown.setName("btnDown"); // NOI18N
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });
        jPanel1.add(btnDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 30, 30));

        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 110, -1));

        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        btnAddField.setIcon(resourceMap.getIcon("btnAddField.icon")); // NOI18N
        btnAddField.setName("btnAddField"); // NOI18N
        btnAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFieldActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 40, 30));

        btnRemoveField.setIcon(resourceMap.getIcon("btnRemoveField.icon")); // NOI18N
        btnRemoveField.setName("btnRemoveField"); // NOI18N
        btnRemoveField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFieldActionPerformed(evt);
            }
        });
        jPanel1.add(btnRemoveField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 40, 30));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 110, -1));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, 20));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));

        dtpFinishDate.setName("dtpFinishDate"); // NOI18N
        jPanel1.add(dtpFinishDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 100, -1));

        dtpBeginDate.setName("dtpBeginDate"); // NOI18N
        jPanel1.add(dtpBeginDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 100, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 280));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFieldActionPerformed
        // TODO add your handling code here:
        Object[] field = this.lstSelectedFields.getSelectedValues();
        Field tmp = null;
       
        for (int i = 0; i < field.length; i++) {
            tmp = (Field) field[i];
            if (FieldHandler.CompareList(tmp , FieldHandler.getListGroupFields())){
                JOptionPaneExtended.showMessageDialog(this, "El campo " + tmp.getTitle() + " ya ha sido agregado.");
                return;
            } else {
                FieldHandler.addGroupField(tmp);
            }
        }
        updateLists();
      //  fillWizardMap(evt);
        
}//GEN-LAST:event_btnAddFieldActionPerformed

    private void btnRemoveFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFieldActionPerformed
        // TODO add your handling code here:
        Object[] field = this.lstGroupFields.getSelectedValues();
        Field tmp = null;

        for (int i = 0; i < field.length; i++) {
            tmp = (Field) field[i];
         FieldHandler.removeGroupField(tmp);
        }
        updateLists();
      //  fillWizardMap(evt);
        
    }//GEN-LAST:event_btnRemoveFieldActionPerformed

    private void lstGroupFieldsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstGroupFieldsValueChanged
        // TODO add your handling code here:
        fillWizardMap();
    }//GEN-LAST:event_lstGroupFieldsValueChanged

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        // TODO add your handling code here:
         if(lstGroupFields.getSelectedValue()==null){
            JOptionPaneExtended.showMessageDialog(this, "Debe seleccionar un valor de la lista");
            return;
        }
         
       int index = lstGroupFields.getSelectedIndex();
        
        if (index > 0)
        {
            Field previous = (Field) lstGroupFields.getModel().getElementAt(index - 1 );
            Field selected = (Field) lstGroupFields.getModel().getElementAt(index);
            previous.setOrder(previous.getOrder() + 1);
            selected.setOrder(selected.getOrder() - 1);
            Collections.sort(FieldHandler.getListGroupFields(), new FieldsComparator());
            updateLists();
            lstGroupFields.setSelectedIndex(index - 1);
        }
  
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        // TODO add your handling code here:
        if(lstGroupFields.getSelectedValue()==null){
            JOptionPaneExtended.showMessageDialog(this, "Debe seleccionar un valor de la lista");
            return;
        }
         
       int index = lstGroupFields.getSelectedIndex();
        
        if (index < lstGroupFields.getModel().getSize() - 1 )
        {
            Field selected = (Field) lstGroupFields.getModel().getElementAt(index);
            Field next = (Field) lstGroupFields.getModel().getElementAt(index + 1 );
            selected.setOrder(selected.getOrder() + 1);
            next.setOrder(next.getOrder() - 1);
            
            Collections.sort(FieldHandler.getListGroupFields(), new FieldsComparator());
            updateLists();
            lstGroupFields.setSelectedIndex(index + 1);
        }
       
       
    }//GEN-LAST:event_btnDownActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddField;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnRemoveField;
    private javax.swing.JButton btnUp;
    private com.toedter.calendar.JDateChooser dtpBeginDate;
    private com.toedter.calendar.JDateChooser dtpFinishDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstGroupFields;
    private javax.swing.JList lstSelectedFields;
    // End of variables declaration//GEN-END:variables
}
