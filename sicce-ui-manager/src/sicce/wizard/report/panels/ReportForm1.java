/*
 * ReportDetail.java
 *
 * Created on 31 de marzo de 2008, 10:06 PM
 */
package sicce.wizard.report.panels;

import java.awt.Component;
import java.util.Map;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelNavResult;
import sicce.ui.manager.controls.JOptionPaneExtended;
import sicce.ui.manager.forms.*;

/**
 *
 * @author  Karu
 */
public class ReportForm1 extends WizardPage {

    private final WizardController controller;
    private final Map wizardData;
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";

    /** Creates new form ReportDetail */
    public ReportForm1(WizardController controller, Map wizardData) {
        initComponents();
        this.controller = controller;
        this.wizardData = wizardData;
      

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtReportName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();

        setName("Form"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), null));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtReportName.setName("txtReportName"); // NOI18N
        jPanel1.add(txtReportName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 250, 20));

        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportForm1.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtDescription.setText(resourceMap.getString("txtDescription.text")); // NOI18N
        txtDescription.setName("txtDescription"); // NOI18N
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 250, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 200));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected String validateContents(Component component, Object event) {
    
        
        if (((component == txtReportName  || component == null || component == txtDescription)  && txtReportName.getText().trim().length() == 0)){                 
           return "Debe ingresar la información General del reporte...";
        } else {
            wizardData.put(KEY_NAME, txtReportName.getText());  
            wizardData.put(KEY_DESCRIPTION, txtDescription.getText());  
        }
      return null;
    }

    
    @Override
    public WizardPanelNavResult allowBack(String stepName, Map wizardData, Wizard controller) {
        validateContents(this, controller);
        return super.allowBack(stepName, wizardData, controller);
    }
////
////    @Override
////    public WizardPanelNavResult allowNext(String stepName, Map wizardData, Wizard controller) {
////        validateContents(this, controller);
////        return super.allowNext(stepName, wizardData, controller);
////    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtReportName;
    // End of variables declaration//GEN-END:variables
}
