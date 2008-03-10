/*
 * LocationPane.java
 *
 * Created on January 26, 2008, 7:43 PM
 */
package sicce.ui.manager.forms;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import sicce.api.businesslogic.ClassFactory;

import sicce.api.businesslogic.LocationBizObject;
import sicce.api.businesslogic.LocationTableModel;
import sicce.api.businesslogic.LocationTypeBizObject;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.businesslogic.PowerMeterTableModel;
import sicce.api.businesslogic.SicceComboBoxModel;
import sicce.api.businesslogic.SicceComboBoxRenderer;
import sicce.api.businesslogic.SicceTableModel;
import sicce.api.dataaccess.LocationDB;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.DisplayMemberRenderType;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.ILocationType;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.util.ComponentUtil;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.SearchDialog;

/**
 *
 * @author  gish@c
 */
public class LocationPane extends JTabExtended<ILocation> {

    SicceComboBoxModel<ILocationType> locationTypeComboBoxModel;
    SicceComboBoxRenderer locationTypeComboBoxRenderer;
    LocationTypeBizObject locationTypeBizObject;
    LocationBizObject locationBizObject;
    LocationTableModel locationTableModel;
    PowerMeterBizObject pmeterBizObject;
    private ILocation location;
    private ILocation plocation;
    private IPowerMeter pmeter;
    private ILocationType ltype;
    /** Creates new form LocationPane */
    public LocationPane() {
        initComponents();
        getControlsToClear().add(txtDescription);
        getControlsToClear().add(txtUbication);
        getControlsToClear().add(txtPowerMeter);
        getControlsToClear().add(btnSearchPowerMeter);
        getControlsToClear().add(btnSearchUbication);
        getControlsToClear().add(cmbLocationType);
        getControlsToEnable().add(txtDescription);
        getControlsToEnable().add(txtUbication);
        getControlsToEnable().add(txtPowerMeter);
        getControlsToEnable().add(btnSearchPowerMeter);
        getControlsToEnable().add(btnSearchUbication);
        getControlsToEnable().add(cmbLocationType);
        ComponentUtil.SetState(false, getControlsToEnable());

        pmeterBizObject = new PowerMeterBizObject();
        locationBizObject = new LocationBizObject();
        
        FillGrid();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        lblUbication = new javax.swing.JLabel();
        txtUbication = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDescription2 = new javax.swing.JLabel();
        btnSearchUbication = new javax.swing.JButton();
        lblLocationType = new javax.swing.JLabel();
        lblPowerMeter = new javax.swing.JLabel();
        txtPowerMeter = new javax.swing.JTextField();
        btnSearchPowerMeter = new javax.swing.JButton();
        cmbLocationType = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdLocation = new javax.swing.JTable();

        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(LocationPane.class);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        lblUbication.setText(resourceMap.getString("lblUbication.text")); // NOI18N
        lblUbication.setName("lblUbication"); // NOI18N

        txtUbication.setName("txtUbication"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setName("txtDescription"); // NOI18N
        jScrollPane4.setViewportView(txtDescription);

        lblDescription2.setText(resourceMap.getString("lblDescription2.text")); // NOI18N
        lblDescription2.setName("lblDescription2"); // NOI18N

        btnSearchUbication.setIcon(resourceMap.getIcon("btnSearchUbication.icon")); // NOI18N
        btnSearchUbication.setText(resourceMap.getString("btnSearchUbication.text")); // NOI18N
        btnSearchUbication.setAlignmentX(0.5F);
        btnSearchUbication.setAlignmentY(1.5F);
        btnSearchUbication.setName("btnSearchUbication"); // NOI18N
        btnSearchUbication.setPreferredSize(new java.awt.Dimension(50, 21));
        btnSearchUbication.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSearchUbication.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSearchUbication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUbicationActionPerformed(evt);
            }
        });

        lblLocationType.setText(resourceMap.getString("lblLocationType.text")); // NOI18N
        lblLocationType.setName("lblLocationType"); // NOI18N

        lblPowerMeter.setText(resourceMap.getString("lblPowerMeter.text")); // NOI18N
        lblPowerMeter.setName("lblPowerMeter"); // NOI18N

        txtPowerMeter.setName("txtPowerMeter"); // NOI18N

        btnSearchPowerMeter.setIcon(resourceMap.getIcon("btnSearchPowerMeter.icon")); // NOI18N
        btnSearchPowerMeter.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSearchPowerMeter.setName("btnSearchPowerMeter"); // NOI18N
        btnSearchPowerMeter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPowerMeterActionPerformed(evt);
            }
        });

        cmbLocationType.setName("cmbLocationType"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLocationType, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUbication))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUbication, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(cmbLocationType, 0, 216, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addComponent(btnSearchUbication, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPowerMeter, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescription2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtPowerMeter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchPowerMeter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUbication)
                            .addComponent(txtUbication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocationType)
                            .addComponent(cmbLocationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPowerMeter)
                            .addComponent(txtPowerMeter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSearchUbication, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSearchPowerMeter)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription2))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        grdLocation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Ubicación", "Tipo de Dependencia", "Medidor", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        locationTypeBizObject = new LocationTypeBizObject();
        locationTypeComboBoxModel = new SicceComboBoxModel<ILocationType>(locationTypeBizObject.GetAllLocationsType());
        locationTypeComboBoxRenderer = new SicceComboBoxRenderer("getDescription", DisplayMemberRenderType.Method);
        cmbLocationType.setModel(locationTypeComboBoxModel);
        cmbLocationType.setRenderer(locationTypeComboBoxRenderer);
    }//GEN-LAST:event_formComponentShown

    private void btnSearchPowerMeterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPowerMeterActionPerformed
        SearchDialog<IPowerMeter> searchPowerMeterDialog = new SearchDialog<IPowerMeter>(new JFrame(), true, new PowerMeterTableModel(pmeterBizObject.GetAllPowerMeter()));
        searchPowerMeterDialog.setVisible(true);
        DialogResult result = searchPowerMeterDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            pmeter = searchPowerMeterDialog.getSearchResult();
            if (pmeter != null) {
                txtPowerMeter.setText(pmeter.getDescription());
            }
        }
    }//GEN-LAST:event_btnSearchPowerMeterActionPerformed

    private void btnSearchUbicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUbicationActionPerformed
        SearchDialog<ILocation> searchLocationDialog = new SearchDialog<ILocation>(new JFrame(), true, new LocationTableModel(locationBizObject.GetAllLocations()));
        searchLocationDialog.setVisible(true);
        DialogResult result = searchLocationDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            plocation = searchLocationDialog.getSearchResult();
            if (plocation != null) {
                txtUbication.setText(plocation.getDescription());
            }
        }
        
    }//GEN-LAST:event_btnSearchUbicationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchPowerMeter;
    private javax.swing.JButton btnSearchUbication;
    private javax.swing.JComboBox cmbLocationType;
    private javax.swing.JTable grdLocation;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblDescription2;
    private javax.swing.JLabel lblLocationType;
    private javax.swing.JLabel lblPowerMeter;
    private javax.swing.JLabel lblUbication;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtPowerMeter;
    private javax.swing.JTextField txtUbication;
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
            LocationDB.Delete(currentObject);
            FillGrid();

        } catch (Exception ex) {
            cancelAction = true;
            throw ex;
        }
        return cancelAction;
    }

    @Override
    public void New() {
        super.New();
        currentObject = ClassFactory.getLocationInstance();
        txtDescription.requestFocusInWindow();
    }

    @Override
    public boolean Save() throws Exception {
        cancelAction = false;
        try {
           // if (cmbLocationType.getSelectedItem()!=null)
            ltype = (ILocationType) cmbLocationType.getSelectedItem();
            currentObject.setLocationType(ltype);
            if (pmeter!=null)
                currentObject.setPowerMeter(pmeter);
            currentObject.setDescription(txtDescription.getText());
            currentObject.setLocation(plocation);
            if (IsObjectLoaded()) {
                return Update();
            }
            LocationDB.Save(currentObject);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
   
 public DialogResult Search() {
       SearchDialog<ILocation> searchLocationDialog = new SearchDialog<ILocation>(new JFrame(), true, new LocationTableModel(locationBizObject.GetAllLocations()));
        searchLocationDialog.setVisible(true);
        DialogResult result = searchLocationDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            currentObject = searchLocationDialog.getSearchResult();
            //SetUIElements();
        }
        return result;
    }
    @Override
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            LocationDB.Update(currentObject);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }
    
    
     @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<ILocation> tableModel = (SicceTableModel<ILocation>) grdLocation.getModel();
        currentObject = tableModel.getRow(selectedIndex);
        txtDescription.setText(currentObject.getDescription());
        txtPowerMeter.setText(currentObject.getPowerMeter().getDescription());
        if (currentObject.getLocation()!=null)
            txtUbication.setText(currentObject.getLocation().getDescription());
      }

    @Override
    public void RegisterSelectionListener() {
        grdLocation.getSelectionModel().addListSelectionListener(selectionListener);
        grdLocation.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void FillGrid() {
        locationTableModel = new LocationTableModel(locationBizObject.GetAllLocations());
        grdLocation.setModel(locationTableModel);
    }
    
     @Override
    public void CancelSave() {
        if (currentObject != null) {
            if (currentObject.getID() != null) {
                ILocation originalInstance = LocationDB.FindLocationByID(currentObject.getID());
                this.currentObject = originalInstance;
            } else {
                this.currentObject = ClassFactory.getLocationInstance();
            }
        }
    }
    
 
    
}
