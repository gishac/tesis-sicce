/*
 * ReportForm3.java
 *
 * Created on 1 de abril de 2008, 11:43 PM
 */
package sicce.wizard.report.panels;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;
import sicce.ui.manager.controls.JOptionPaneExtended;
import sicce.api.info.Field;
import sicce.wizard.reports.models.FieldHandler;
import sicce.api.businesslogic.renderer.FieldsCellRenderer;
import sicce.api.businesslogic.comparator.FieldsComparator;

/**
 *
 * @author  Karu
 */
public class ReportWizardStep2 extends WizardPage {

    private final WizardController controller;
    private final Map wizardData;
    public static final String KEY_FIELD = "KeyField";
    public static final String VALUE_FIELDS = "ValueField";
    public static final String KEY_BL_IS_LOADED = "isloaded";
    private FieldHandler pFieldHandler = new FieldHandler();
    Boolean isloaded = false;
    /** Creates new form ReportDetail */
    public ReportWizardStep2(WizardController controller, Map wizardData) {
        initComponents();
        this.controller = controller;
        this.wizardData = wizardData;
        controller.setProblem("Seleccione el módulo deseado.");
        isloaded = (wizardData.get(KEY_BL_IS_LOADED)==null?false:(Boolean)wizardData.get(KEY_BL_IS_LOADED));
        
        if (isloaded) {
            this.pFieldHandler = (FieldHandler) wizardData.get(KEY_FIELD);
            lstSelectedFields.setCellRenderer(new FieldsCellRenderer());
            lstAvailableFields.setListData(FieldHandler.CompareLists(pFieldHandler.getAvailableFields(), pFieldHandler.getSelectedFields()).toArray());
            lstSelectedFields.setListData(pFieldHandler.getSelectedFields().toArray());
            controller.setForwardNavigationMode(WizardController.MODE_CAN_CONTINUE);
            controller.setProblem(null);
        }
        fillCbModules();
        fillAvailableFields();
        validateContents(this, null);
    }

    public void fillCbModules() {
        cbModules.addItem("Zona");
        cbModules.addItem("Ubicación");
        cbModules.addItem("Medidor");
        cbModules.addItem("Mediciones");

    }

    public void fillAvailableFields() {
        if (cbModules.getSelectedItem().equals("Zona")) {
            pFieldHandler.setListAvailableFields(pFieldHandler.fillZone());
            List<Field> resultList = FieldHandler.CompareLists(pFieldHandler.getAvailableFields(), pFieldHandler.getSelectedFields());
            lstAvailableFields.setCellRenderer(new FieldsCellRenderer());
            lstAvailableFields.setListData(resultList.toArray());
        }

        if (cbModules.getSelectedItem().equals("Ubicación")) {
            pFieldHandler.setListAvailableFields(pFieldHandler.fillLocation());
            List<Field> resultList = FieldHandler.CompareLists(pFieldHandler.getAvailableFields(), pFieldHandler.getSelectedFields());
            lstAvailableFields.setCellRenderer(new FieldsCellRenderer());
            lstAvailableFields.setListData(resultList.toArray());
        }

        if (cbModules.getSelectedItem().equals("Medidor")) {
            pFieldHandler.setListAvailableFields(pFieldHandler.fillPowerMeter());
            List<Field> resultList = FieldHandler.CompareLists(pFieldHandler.getAvailableFields(), pFieldHandler.getSelectedFields());
            lstAvailableFields.setCellRenderer(new FieldsCellRenderer());
            lstAvailableFields.setListData(resultList.toArray());

        }
        if (cbModules.getSelectedItem().equals("Mediciones")) {
            pFieldHandler.setListAvailableFields(pFieldHandler.fillMeasureFields());
            List<Field> resultList = FieldHandler.CompareLists(pFieldHandler.getAvailableFields(), pFieldHandler.getSelectedFields());
            lstAvailableFields.setCellRenderer(new FieldsCellRenderer());
            lstAvailableFields.setListData(resultList.toArray());


        }
    }

    public void updateLists() {
        lstSelectedFields.setCellRenderer(new FieldsCellRenderer());
        lstAvailableFields.setListData(FieldHandler.CompareLists(pFieldHandler.getAvailableFields(), pFieldHandler.getSelectedFields()).toArray());
        lstSelectedFields.setListData(pFieldHandler.getSelectedFields().toArray());
        wizardData.put(KEY_FIELD, pFieldHandler);
    }

    @Override
    protected String validateContents(Component component, Object event) {

        if (isloaded) {
            return null;
        }
        if (component == null) {
            return "Seleccione los campos del reporte...";
        }
 
        if ((component == lstAvailableFields || component == lstSelectedFields || component == cbModules) && (lstSelectedFields.getModel().getSize() == 0)) {
            return "Defina los criterios del reporte...";
        } else {
            controller.setForwardNavigationMode(WizardController.MODE_CAN_CONTINUE);
            wizardData.put(KEY_FIELD, pFieldHandler);
        }

        return null;
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
        lstSelectedFields = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAvailableFields = new javax.swing.JList();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbModules = new javax.swing.JComboBox();
        btnAddField = new javax.swing.JButton();
        btnRemoveField = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(650, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportWizardStep2.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lstSelectedFields.setName("lstSelectedFields"); // NOI18N
        lstSelectedFields.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lstSelectedFieldsPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(lstSelectedFields);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 70, 160, 260));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        lstAvailableFields.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lstAvailableFields.setName("lstAvailableFields"); // NOI18N
        jScrollPane2.setViewportView(lstAvailableFields);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 160, 260));

        btnUp.setIcon(resourceMap.getIcon("btnUp.icon")); // NOI18N
        btnUp.setText(resourceMap.getString("btnUp.text")); // NOI18N
        btnUp.setName("btnUp"); // NOI18N
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });
        jPanel1.add(btnUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 30, 30));

        btnDown.setIcon(resourceMap.getIcon("btnDown.icon")); // NOI18N
        btnDown.setText(resourceMap.getString("btnDown.text")); // NOI18N
        btnDown.setName("btnDown"); // NOI18N
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });
        jPanel1.add(btnDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 30, 30));

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 110, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        cbModules.setName("cbModules"); // NOI18N
        cbModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModulesActionPerformed(evt);
            }
        });
        jPanel1.add(cbModules, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 130, -1));

        btnAddField.setIcon(resourceMap.getIcon("btnAddField.icon")); // NOI18N
        btnAddField.setText(resourceMap.getString("btnAddField.text")); // NOI18N
        btnAddField.setName("btnAddField"); // NOI18N
        btnAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFieldActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 40, 30));

        btnRemoveField.setIcon(resourceMap.getIcon("btnRemoveField.icon")); // NOI18N
        btnRemoveField.setText(resourceMap.getString("btnRemoveField.text")); // NOI18N
        btnRemoveField.setName("btnRemoveField"); // NOI18N
        btnRemoveField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFieldActionPerformed(evt);
            }
        });
        jPanel1.add(btnRemoveField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 40, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 340));
    }// </editor-fold>//GEN-END:initComponents
    private void cbModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModulesActionPerformed
        // TODO add your handling code here:
        validateContents(cbModules, evt);
        fillAvailableFields();
        
    }//GEN-LAST:event_cbModulesActionPerformed

    private void btnAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFieldActionPerformed
        // TODO add your handling code here:
        Object[] field = this.lstAvailableFields.getSelectedValues();
        Field tmp = null;

        for (int i = 0; i < field.length; i++) {
            tmp = (Field) field[i];
            tmp.setOrder(pFieldHandler.getSelectedFields().size());
            pFieldHandler.addSelectedField(tmp);
        }
        updateLists();
        
        
}//GEN-LAST:event_btnAddFieldActionPerformed

    private void btnRemoveFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFieldActionPerformed

        Object[] field = this.lstSelectedFields.getSelectedValues();
        Field tmp = null;

        for (int i = 0; i < field.length; i++) {
            tmp = (Field) field[i];
            pFieldHandler.removeSelectedField(tmp);
            
        }
     
     updateLists();
        
    }//GEN-LAST:event_btnRemoveFieldActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        // TODO add your handling code here:
        if (lstSelectedFields.getSelectedValue() == null) {
            JOptionPaneExtended.showMessageDialog(this, "Debe seleccionar un valor de la lista");
            return;
        }

        int index = lstSelectedFields.getSelectedIndex();

        if (index > 0) {
            Field previous = (Field) lstSelectedFields.getModel().getElementAt(index - 1);
            Field selected = (Field) lstSelectedFields.getModel().getElementAt(index);
            previous.setOrder(index + 1);
            selected.setOrder(index - 1);
            Collections.sort(pFieldHandler.getSelectedFields(), new FieldsComparator());
            updateLists();
            lstSelectedFields.setSelectedIndex(index - 1);

        }
  
        
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed
        // TODO add your handling code here:
        if (lstSelectedFields.getSelectedValue() == null) {
            JOptionPaneExtended.showMessageDialog(this, "Debe seleccionar un valor de la lista");
            return;
        }

        int index = lstSelectedFields.getSelectedIndex();

        if (index < lstSelectedFields.getModel().getSize() - 1) {
            Field selected = (Field) lstSelectedFields.getModel().getElementAt(index);
            Field next = (Field) lstSelectedFields.getModel().getElementAt(index + 1);
            selected.setOrder(index + 1);
            next.setOrder(index - 1);

            Collections.sort(pFieldHandler.getSelectedFields(), new FieldsComparator());
            updateLists();
            lstSelectedFields.setSelectedIndex(index + 1);

        }
       
    }//GEN-LAST:event_btnDownActionPerformed

    private void lstSelectedFieldsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lstSelectedFieldsPropertyChange
    // TODO add your handling code here:
       
    }//GEN-LAST:event_lstSelectedFieldsPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddField;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnRemoveField;
    private javax.swing.JButton btnUp;
    private javax.swing.JComboBox cbModules;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstAvailableFields;
    private javax.swing.JList lstSelectedFields;
    // End of variables declaration//GEN-END:variables
}
