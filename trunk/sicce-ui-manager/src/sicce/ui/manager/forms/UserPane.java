/*
 * LocationPane.java
 *
 * Created on January 26, 2008, 7:43 PM
 */

package sicce.ui.manager.forms;

import sicce.ui.manager.controls.JTabExtended;

/**
 *
 * @author  gish@c
 */
public class UserPane extends JTabExtended {
    
    /** Creates new form LocationPane */
    public UserPane() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        txtUbication = new javax.swing.JTextField();
        btnSearchUbication = new javax.swing.JButton();
        txtLocationType = new javax.swing.JTextField();
        txtPowerMeter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPowerMeter1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLocationType1 = new javax.swing.JTextField();
        txtPowerMeter2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdLocation = new javax.swing.JTable();

        setName("Form"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Localización"));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(null);

        txtUbication.setName("txtUbication"); // NOI18N
        jPanel4.add(txtUbication);
        txtUbication.setBounds(120, 30, 100, 20);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(UserPane.class);
        btnSearchUbication.setIcon(resourceMap.getIcon("btnSearchUbication.icon")); // NOI18N
        btnSearchUbication.setAlignmentX(0.5F);
        btnSearchUbication.setAlignmentY(1.5F);
        btnSearchUbication.setName("btnSearchUbication"); // NOI18N
        btnSearchUbication.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSearchUbication.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(btnSearchUbication);
        btnSearchUbication.setBounds(270, 50, 30, 30);

        txtLocationType.setName("txtLocationType"); // NOI18N
        jPanel4.add(txtLocationType);
        txtLocationType.setBounds(120, 90, 140, 20);

        txtPowerMeter.setName("txtPowerMeter"); // NOI18N
        jPanel4.add(txtPowerMeter);
        txtPowerMeter.setBounds(120, 120, 140, 20);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel4.add(jLabel1);
        jLabel1.setBounds(20, 120, 100, 14);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel4.add(jLabel3);
        jLabel3.setBounds(20, 90, 100, 14);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel4.add(jLabel4);
        jLabel4.setBounds(20, 30, 60, 14);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel4.add(jLabel2);
        jLabel2.setBounds(20, 60, 80, 14);

        txtPowerMeter1.setName("txtPowerMeter1"); // NOI18N
        jPanel4.add(txtPowerMeter1);
        txtPowerMeter1.setBounds(120, 60, 140, 20);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel4.add(jLabel5);
        jLabel5.setBounds(20, 180, 60, 14);

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel4.add(jLabel6);
        jLabel6.setBounds(20, 150, 60, 14);

        txtLocationType1.setName("txtLocationType1"); // NOI18N
        jPanel4.add(txtLocationType1);
        txtLocationType1.setBounds(120, 150, 200, 20);

        txtPowerMeter2.setName("txtPowerMeter2"); // NOI18N
        jPanel4.add(txtPowerMeter2);
        txtPowerMeter2.setBounds(120, 180, 200, 20);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        grdLocation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Object", "Nombre", "Rol", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grdLocation.setName("grdLocation"); // NOI18N
        jScrollPane1.setViewportView(grdLocation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchUbication;
    private javax.swing.JTable grdLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtLocationType;
    private javax.swing.JTextField txtLocationType1;
    private javax.swing.JTextField txtPowerMeter;
    private javax.swing.JTextField txtPowerMeter1;
    private javax.swing.JTextField txtPowerMeter2;
    private javax.swing.JTextField txtUbication;
    // End of variables declaration//GEN-END:variables
    
}
