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
import sicce.ui.manager.forms.*;
import sicce.wizard.reports.models.FieldHandler;

/**
 *
 * @author  Karu
 */
public class ReportWizardStep1 extends WizardPage {

    private final WizardController controller;
    private final Map wizardData;
    public static final String KEY_NAME = "name";
    public static final String KEY_FIELD = "KeyField";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ORT_VERTICAL = "ortVertical";
    public static final String KEY_ORT_HORIZONTAL = "ortHorizontal";
    public static final String KEY_BL_IS_LOADED = "isloaded";
    Boolean isloaded = false;    
  //  private FieldHandler pFieldHandler = new FieldHandler();
    /** Creates new form ReportDetail */
    public ReportWizardStep1(WizardController controller, Map wizardData) {
        initComponents();
        this.controller = controller;
        this.wizardData = wizardData;
        
        isloaded = (wizardData.get(KEY_BL_IS_LOADED)==null?false:(Boolean)wizardData.get(KEY_BL_IS_LOADED));
        
        if (isloaded){
          txtReportName.setText((String)wizardData.get(KEY_NAME));
          txtDescription.setText((String)wizardData.get(KEY_DESCRIPTION));
          rbHorizontal.setSelected((Boolean) wizardData.get(KEY_ORT_HORIZONTAL));
          rbVertical.setSelected((Boolean) wizardData.get(KEY_ORT_VERTICAL));
        }
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtReportName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        rbVertical = new javax.swing.JRadioButton();
        rbHorizontal = new javax.swing.JRadioButton();

        setMaximumSize(new java.awt.Dimension(650, 500));
        setMinimumSize(new java.awt.Dimension(650, 500));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(650, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportWizardStep1.class);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtReportName.setName("txtReportName"); // NOI18N
        jPanel1.add(txtReportName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 310, 20));

        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtDescription.setText(resourceMap.getString("txtDescription.text")); // NOI18N
        txtDescription.setName("txtDescription"); // NOI18N
        jPanel1.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 310, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 440, 170));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(rbVertical);
        rbVertical.setText(resourceMap.getString("rbVertical.text")); // NOI18N
        rbVertical.setName("rbVertical"); // NOI18N
        jPanel2.add(rbVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        buttonGroup1.add(rbHorizontal);
        rbHorizontal.setText(resourceMap.getString("rbHorizontal.text")); // NOI18N
        rbHorizontal.setName("rbHorizontal"); // NOI18N
        jPanel2.add(rbHorizontal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 440, 60));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected String validateContents(Component component, Object event) {
    
        if(isloaded){
            return null;
        }
        
        if ((component == txtReportName  || component == null && !rbHorizontal.isSelected() && !rbVertical.isSelected() || component == txtDescription)
                && (txtReportName.getText().trim().length() == 0)){                 
           return "Debe ingresar la información General del reporte...";
        } else {
            wizardData.put(KEY_NAME, txtReportName.getText());  
            wizardData.put(KEY_DESCRIPTION, txtDescription.getText());  
            wizardData.put(KEY_ORT_HORIZONTAL, rbHorizontal.isSelected());
            wizardData.put(KEY_ORT_VERTICAL, rbVertical.isSelected());
            
        }
      return null;
    }

    
    @Override
    public WizardPanelNavResult allowBack(String stepName, Map wizardData, Wizard controller) {
        validateContents(this, controller);
        return super.allowBack(stepName, wizardData, controller);
    }

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rbHorizontal;
    private javax.swing.JRadioButton rbVertical;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtReportName;
    // End of variables declaration//GEN-END:variables
}
