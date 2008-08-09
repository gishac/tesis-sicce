/*
 * ReportConsumptionFrm.java
 *
 * Created on August 3, 2008, 9:24 PM
 */

package sicce.ui.manager.reports;

import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.ParameterBizObject;
import sicce.api.dataaccess.ParameterDAO;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.dataaccess.ReportDAO;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IParameter;

/**
 *
 * @author  gish@c
 */
public class ReportConsumptionFrm extends javax.swing.JFrame {
    
    /** Creates new form ReportConsumptionFrm */
    Double cost;
    public ReportConsumptionFrm() {
        try {
            initComponents();
            IParameter paramKwh = ParameterDB.GetParameterByKey(ConstantsProvider.KWH_VALUE);
            txtKwh.setText(paramKwh.getValue());
            cost = Double.valueOf(paramKwh.getValue());
        } catch (Exception ex) {
            Logger.getLogger(ReportConsumptionFrm.class.getName()).log(Level.SEVERE, null, ex);
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
        dtpStartDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dtpEndDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtKwh = new javax.swing.JTextField();
        rbZone = new javax.swing.JRadioButton();
        rbLocation = new javax.swing.JRadioButton();
        btnGenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportConsumptionFrm.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dtpStartDate.setName("dtpStartDate"); // NOI18N
        jPanel1.add(dtpStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, -1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        dtpEndDate.setName("dtpEndDate"); // NOI18N
        jPanel1.add(dtpEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 100, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtKwh.setText(resourceMap.getString("txtKwh.text")); // NOI18N
        txtKwh.setEnabled(false);
        txtKwh.setName("txtKwh"); // NOI18N
        jPanel1.add(txtKwh, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 80, -1));

        buttonGroup1.add(rbZone);
        rbZone.setText(resourceMap.getString("rbZone.text")); // NOI18N
        rbZone.setName("rbZone"); // NOI18N
        rbZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbZoneActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbLocation);
        rbLocation.setText(resourceMap.getString("rbLocation.text")); // NOI18N
        rbLocation.setName("rbLocation"); // NOI18N

        btnGenerate.setText(resourceMap.getString("btnGenerate.text")); // NOI18N
        btnGenerate.setName("btnGenerate"); // NOI18N
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(rbZone)
                        .addGap(32, 32, 32)
                        .addComponent(rbLocation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(btnGenerate)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbZone)
                    .addComponent(rbLocation)
                    .addComponent(btnGenerate))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbZoneActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_rbZoneActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        // TODO add your handling code here:
     ReportDAO report = new ReportDAO();
     report.callSpConsumption(dtpStartDate.getDate(), dtpEndDate.getDate());
     GenerateStaticReport.GenerateStaticConsumptionReport(null, rbLocation.isSelected(),rbZone.isSelected(), dtpStartDate.getDate(), dtpEndDate.getDate(), cost);          
        
}//GEN-LAST:event_btnGenerateActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportConsumptionFrm().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dtpEndDate;
    private com.toedter.calendar.JDateChooser dtpStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbLocation;
    private javax.swing.JRadioButton rbZone;
    private javax.swing.JTextField txtKwh;
    // End of variables declaration//GEN-END:variables
    
}
