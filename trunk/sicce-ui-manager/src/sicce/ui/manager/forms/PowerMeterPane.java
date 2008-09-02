/*
 * PowerMeterPane.java
 *
 * Created on January 23, 2008, 12:39 AM
 */
package sicce.ui.manager.forms;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import org.jdesktop.application.ResourceMap;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.dataaccess.PowerMeterDB;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.util.ComponentUtil;
import sicce.api.util.JTextFieldLimit;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.JTabExtended;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.businesslogic.model.PowerMeterTableModel;
import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.util.JTextFieldInteger;
import sicce.api.util.Validator;
import sicce.ui.manager.controls.SearchDialog;
import sicce.ui.manager.handlers.ExceptionHandler;

/**
 *
 * @author  gish@c
 */
public class PowerMeterPane extends JTabExtended<IPowerMeter> {

    /**
     * Objeto para manejar la logica de los medidores
     */
    private PowerMeterBizObject pmeterBizObject;
    /**
     * Modelo de tabla para mostrar los medidores existentes
     */
    private PowerMeterTableModel pmeterTableModel;

    /**
     * Constructor
     * @param resourceMap Manejador de recursos
     */
    public PowerMeterPane(ResourceMap resourceMap) {
        this();
        this.resourceMap = resourceMap;
    }

    /**
     * Constructor
     */
    public PowerMeterPane() {
        initComponents();
        getControlsToClear().add(txtSerial);
        getControlsToClear().add(txtIpAddress);
        getControlsToClear().add(txtDescription);
        getControlsToClear().add(txtDeviceID);
        getControlsToEnable().add(txtSerial);
        getControlsToEnable().add(txtIpAddress);
        getControlsToEnable().add(txtDescription);
        getControlsToEnable().add(txtDeviceID);
        txtSerial.setDocument(new JTextFieldLimit(20));
        txtDeviceID.setDocument(new JTextFieldInteger(20));
        txtIpAddress.setDocument(new JTextFieldLimit(16));
        txtDescription.setDocument(new JTextFieldLimit(200));
        ComponentUtil.SetState(false, getControlsToEnable());
        pmeterBizObject = new PowerMeterBizObject();
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
        grdPowerMeter = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblSerial = new javax.swing.JLabel();
        lblipAddress = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        txtIpAddress = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtDeviceID = new javax.swing.JTextField();

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

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtDeviceID.setText(resourceMap.getString("txtDeviceID.text")); // NOI18N
        txtDeviceID.setName("txtDeviceID"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblipAddress)
                            .addComponent(jLabel1)
                            .addComponent(lblDescription))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtDeviceID, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtIpAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtSerial, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
                .addContainerGap(21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSerial)
                    .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblipAddress)
                    .addComponent(txtIpAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDeviceID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grdPowerMeter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblSerial;
    private javax.swing.JLabel lblipAddress;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtDeviceID;
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

            PowerMeterDB.Delete(currentObject);
            super.Delete();
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
            JOptionPane.showMessageDialog(this, "No se puede eliminar el medidor ya que tiene datos históricos asociados.", resourceMap.getString("ApplicationName"), JOptionPane.INFORMATION_MESSAGE);
            throw ex;
        }
        return cancelAction;
    }

    @Override
    public void New() {
        super.New();
        currentObject = ClassFactory.getPowerMeterInstance();
        ComponentUtil.SetState(true, new Component[]{txtSerial});
        txtSerial.requestFocusInWindow();
    }

    @Override
    public boolean Save() throws Exception {
        cancelAction = false;
        if (!CheckFields()) {
            return true;
        }
        super.Save();
        try {
            currentObject.setSerial(txtSerial.getText().trim());
            currentObject.setIpAddress(txtIpAddress.getText().trim());
            currentObject.setDescription(txtDescription.getText().trim());
            currentObject.setDeviceID(txtDeviceID.getText().trim());
            if (IsObjectLoaded()) {
                return Update();

            }
            PowerMeterDB.Save(currentObject);
            txtSerial.setText(currentObject.getSerial());
            txtIpAddress.setText(currentObject.getIpAddress());
            txtSerial.setText(currentObject.getSerial());
            txtDeviceID.setText(currentObject.getDeviceID());
            FillGrid();
        } catch (Exception ex) {
            ExceptionHandler.DisplayException(ex);
            cancelAction = true;
        }
        return cancelAction;

    }

    @Override
    public DialogResult Search() {
        SearchDialog<IPowerMeter> searchPmeterDialog = new SearchDialog<IPowerMeter>(new JFrame(), true, new PowerMeterTableModel(pmeterBizObject.GetAllPowerMeter()));
        searchPmeterDialog.setVisible(true);
        DialogResult result = searchPmeterDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            currentObject = searchPmeterDialog.getSearchResult();
            SetUIElements();
        }
        return result;
    }

    @Override
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            PowerMeterDB.Update(currentObject);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<IPowerMeter> tableModel = (SicceTableModel<IPowerMeter>) grdPowerMeter.getModel();
        currentObject = tableModel.getRow(selectedIndex);
        SetUIElements();
    }

    @Override
    public void RegisterSelectionListener() {
        grdPowerMeter.getSelectionModel().addListSelectionListener(selectionListener);
        grdPowerMeter.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void FillGrid() {
        pmeterTableModel = new PowerMeterTableModel(pmeterBizObject.GetAllPowerMeter());
        grdPowerMeter.setModel(pmeterTableModel);
    }

    @Override
    public boolean CheckFields() {

        if (!Validator.ValidateField(null, null, 0, txtSerial, true, "el serial del medidor", 3)) {
            return false;
        }
        if (!Validator.validateIpAddress(null, txtIpAddress.getText())) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtDeviceID, true, "el identificador del medidor", 2)) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtDescription, true, "la descripción del medidor", 2)) {
            return false;
        }


        return true;
    }

    @Override
    public void SetUIElements() {
        if (currentObject == null) {
            return;
        }
        txtSerial.setText(currentObject.getSerial());
        txtIpAddress.setText(currentObject.getIpAddress());
        txtDescription.setText(currentObject.getDescription());
        txtDeviceID.setText(currentObject.getDeviceID());
    }
}
    
    
    
    

