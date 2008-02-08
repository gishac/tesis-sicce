/*
 * PowerMeterPane.java
 *
 * Created on January 23, 2008, 12:39 AM
 */

package sicce.ui.manager.forms;

import java.awt.Component;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.dataaccess.PowerMeterDB;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.util.ComponentUtil;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.JTabExtended;

/**
 *
 * @author  gish@c
 */
public class PowerMeterPane extends JTabExtended {
    
       private IPowerMeter pmeter;    
    
    
    /** Creates new form PowerMeterPane */
    public PowerMeterPane() {
        initComponents();
        getControlsToClear().add(txtSerial);
        getControlsToClear().add(txtIpAddress);
        getControlsToClear().add(txtDescription);
         
    }
    
    /** Creates new form PowerMeterPane */
    public PowerMeterPane(Object option) {
        initComponents();
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
        grdPowerMeter = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblSerial = new javax.swing.JLabel();
        lblipAddress = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        txtIpAddress = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();

        setName("PowerMeterPane"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("scrollPanePowerMeter"); // NOI18N

        grdPowerMeter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Object", "Serial", "Dirección IP", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grdPowerMeter.setName("grdPowerMeter"); // NOI18N
        jScrollPane1.setViewportView(grdPowerMeter);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(PowerMeterPane.class);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        lblSerial.setText(resourceMap.getString("lblSerial.text")); // NOI18N
        lblSerial.setName("lblSerial"); // NOI18N

        lblipAddress.setText(resourceMap.getString("lblipAddress.text")); // NOI18N
        lblipAddress.setName("lblipAddress"); // NOI18N

        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        txtSerial.setText(resourceMap.getString("txtSerial.text")); // NOI18N
        txtSerial.setName("txtSerial"); // NOI18N

        txtIpAddress.setText(resourceMap.getString("txtIpAddress.text")); // NOI18N
        txtIpAddress.setName("txtIpAddress"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setName("txtDescription"); // NOI18N
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblipAddress)
                    .addComponent(lblDescription))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSerial, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtIpAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSerial)
                    .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblipAddress)
                    .addComponent(txtIpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grdPowerMeter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblSerial;
    private javax.swing.JLabel lblipAddress;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtIpAddress;
    private javax.swing.JTextField txtSerial;
    // End of variables declaration//GEN-END:variables
 
    @Override
    public void Back() {
        super.Back();
    }

    @Override
    public boolean Delete() throws Exception {
        cancelAction = false;
        try {
            super.Delete();            
            PowerMeterDB.Delete(pmeter);
            
        } catch (Exception ex) {
            cancelAction = true;
            throw ex;
        }
        return cancelAction;
    }

    @Override
    public void New() {
        super.New();
        pmeter = ClassFactory.getPowerMeterInstance();
        ComponentUtil.SetState(true, new Component[]{ txtSerial});
        txtSerial.requestFocusInWindow();
    }

    @Override
    public boolean Save() throws Exception {
          cancelAction = false;        
        try {
           pmeter.setSerial(txtSerial.getText().trim());
        pmeter.setIpAddress(txtIpAddress.getText().trim());
        pmeter.setDescription(txtDescription.getText().trim());
        if(IsObjectLoaded())
        {
            Update();
            
        }
        pmeter = PowerMeterDB.Save(pmeter);
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
        
    }

    @Override
    public void Search() {
        super.Search();
    }
    
     @Override
    public boolean Update() throws Exception{
        cancelAction = false;
        try {
            PowerMeterDB.Update(pmeter);
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }
    
    
    
    
    
}
