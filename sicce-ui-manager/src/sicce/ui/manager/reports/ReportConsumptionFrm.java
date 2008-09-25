/*
 * ReportConsumptionFrm.java
 *
 * Created on August 3, 2008, 9:24 PM
 */
package sicce.ui.manager.reports;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.ResourceMap;
import sicce.api.businesslogic.ParameterBizObject;
import sicce.api.dataaccess.ParameterDAO;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.dataaccess.ReportDAO;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.ui.manager.controls.JOptionPaneExtended;

/**
 *
 * @author  gish@c
 */
public class ReportConsumptionFrm extends javax.swing.JFrame {

    /** Creates new form ReportConsumptionFrm */
    Double costPeriod1;
    Double costPeriod2;
    Double feeStreetLightning;
    Double feeGarbageCollect;
    Double feeFireDepartment;
   
    GenerateStaticReport staticReport;
    private static ResourceMap resourceMap;
    private static IUserSicce currentUser;
    private static  Map parameters;

    public ReportConsumptionFrm(ResourceMap resourceMap, IUserSicce currentUser) {
        try {
            initComponents();
            this.resourceMap = resourceMap;
            this.currentUser = currentUser;
           
            IParameter paramKwhPeriod1 = ParameterDB.GetParameterByKey(ConstantsProvider.KWH_VALUE_1);
            IParameter paramKwhPeriod2 = ParameterDB.GetParameterByKey(ConstantsProvider.KWH_VALUE_2);
            IParameter paramfeeStreetLightning = ParameterDB.GetParameterByKey(ConstantsProvider.FEE_STREET_LIGHTNING);
            IParameter paramfeeGarbageCollect = ParameterDB.GetParameterByKey(ConstantsProvider.FEE_GARBAGE_COLLECT);
            IParameter paramfeeFireDepartment = ParameterDB.GetParameterByKey(ConstantsProvider.FEE_FIRE_DEPARTMENT);
            
            txtKwhPeriod1.setText(paramKwhPeriod1.getValue());
            txtKwhPeriod2.setText(paramKwhPeriod2.getValue());
            txtfeeStreetLightning.setText(paramfeeStreetLightning.getValue());
            txtfeeGarbageCollect.setText(paramfeeGarbageCollect.getValue());
            txtfeeFireDepartment.setText(paramfeeFireDepartment.getValue());
            costPeriod1 = Double.valueOf(paramKwhPeriod1.getValue());
            costPeriod2 = Double.valueOf(paramKwhPeriod2.getValue());
            feeStreetLightning = Double.valueOf(paramfeeStreetLightning.getValue());
            feeGarbageCollect = Double.valueOf(paramfeeGarbageCollect.getValue());
            feeFireDepartment = Double.valueOf(paramfeeFireDepartment.getValue());
            
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
        jLabel2 = new javax.swing.JLabel();
        dtpEndDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtKwhPeriod2 = new javax.swing.JTextField();
        txtKwhPeriod1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtfeeStreetLightning = new javax.swing.JTextField();
        txtfeeGarbageCollect = new javax.swing.JTextField();
        txtfeeFireDepartment = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rbLocation = new javax.swing.JRadioButton();
        rbZone = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        rbUser = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(ReportConsumptionFrm.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dtpStartDate.setName("dtpStartDate"); // NOI18N
        jPanel1.add(dtpStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, -1));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 120, -1));

        dtpEndDate.setName("dtpEndDate"); // NOI18N
        jPanel1.add(dtpEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 100, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        btnGenerate.setText(resourceMap.getString("btnGenerate.text")); // NOI18N
        btnGenerate.setName("btnGenerate"); // NOI18N
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(null);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 30, 72, 14);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 50, 72, 14);

        txtKwhPeriod2.setEnabled(false);
        txtKwhPeriod2.setName("txtKwhPeriod2"); // NOI18N
        jPanel2.add(txtKwhPeriod2);
        txtKwhPeriod2.setBounds(90, 50, 100, 20);

        txtKwhPeriod1.setText(resourceMap.getString("txtKwhPeriod1.text")); // NOI18N
        txtKwhPeriod1.setEnabled(false);
        txtKwhPeriod1.setName("txtKwhPeriod1"); // NOI18N
        jPanel2.add(txtKwhPeriod1);
        txtKwhPeriod1.setBounds(90, 20, 100, 20);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        txtfeeStreetLightning.setText(resourceMap.getString("txtfeeStreetLightning.text")); // NOI18N
        txtfeeStreetLightning.setEnabled(false);
        txtfeeStreetLightning.setName("txtfeeStreetLightning"); // NOI18N

        txtfeeGarbageCollect.setEnabled(false);
        txtfeeGarbageCollect.setName("txtfeeGarbageCollect"); // NOI18N

        txtfeeFireDepartment.setEnabled(false);
        txtfeeFireDepartment.setName("txtfeeFireDepartment"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(txtfeeStreetLightning, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtfeeFireDepartment)
                            .addComponent(txtfeeGarbageCollect, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfeeStreetLightning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtfeeGarbageCollect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtfeeFireDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel6.border.title"))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        buttonGroup1.add(rbLocation);
        rbLocation.setText(resourceMap.getString("rbLocation.text")); // NOI18N
        rbLocation.setName("rbLocation"); // NOI18N

        buttonGroup1.add(rbZone);
        rbZone.setText(resourceMap.getString("rbZone.text")); // NOI18N
        rbZone.setName("rbZone"); // NOI18N
        rbZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbZoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbLocation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbZone)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLocation)
                    .addComponent(rbZone))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        buttonGroup1.add(rbUser);
        rbUser.setText(resourceMap.getString("rbUser.text")); // NOI18N
        rbUser.setName("rbUser"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(rbUser)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(rbUser)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerate)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerate)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void rbZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbZoneActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_rbZoneActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        // TODO add your handling code here:
        if (rbLocation.isSelected() || rbZone.isSelected() || rbUser.isSelected()) {
            ReportDAO report = new ReportDAO();
            Map parameters = new HashMap();
            staticReport = new GenerateStaticReport(resourceMap);
            
            parameters.put("iduser", currentUser.getIdUserSicce());
            parameters.put("startDate", dtpStartDate.getDate());
            parameters.put("endDate", dtpEndDate.getDate());
            parameters.put("costPr1", costPeriod1);
            parameters.put("costPr2", costPeriod2);
            parameters.put("feeStreetLightning", feeStreetLightning);
            parameters.put("feeGarbageCollect", feeGarbageCollect);
            parameters.put("feeFireDepartment", feeFireDepartment);
            
            report.callSpConsumption(dtpStartDate.getDate(),dtpEndDate.getDate() );
            if (rbUser.isSelected()){
                staticReport.GenerateStaticIndividualConsumptionReport(null, parameters);
            } else{
               staticReport.GenerateStaticTotalConsumptionReport(null, rbLocation.isSelected(), rbZone.isSelected(), parameters);
            }
            
        }
        else
        {
           JOptionPaneExtended.showMessageDialog(null, "Seleccione el tipo de Reporte");
        }
}//GEN-LAST:event_btnGenerateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ReportConsumptionFrm(resourceMap, currentUser).setVisible(true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton rbLocation;
    private javax.swing.JRadioButton rbUser;
    private javax.swing.JRadioButton rbZone;
    private javax.swing.JTextField txtKwhPeriod1;
    private javax.swing.JTextField txtKwhPeriod2;
    private javax.swing.JTextField txtfeeFireDepartment;
    private javax.swing.JTextField txtfeeGarbageCollect;
    private javax.swing.JTextField txtfeeStreetLightning;
    // End of variables declaration//GEN-END:variables
}
