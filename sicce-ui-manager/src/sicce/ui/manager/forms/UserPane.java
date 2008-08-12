/*
 * UserPane.java
 *
 * Created on January 26, 2008, 7:43 PM
 */
package sicce.ui.manager.forms;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.businesslogic.RoleBizObject;
import sicce.api.businesslogic.model.SicceComboBoxModel;
import sicce.api.businesslogic.renderer.SicceComboBoxRenderer;
import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.businesslogic.UserBizObject;
import sicce.api.businesslogic.model.PowerMeterTableModelForUsers;
import sicce.api.businesslogic.model.UserTableModel;
import sicce.api.businesslogic.renderer.PowerMeterUserCellRenderer;
import sicce.api.dataaccess.UserDB;
import sicce.api.dataaccess.UserPowerMeterDB;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.DisplayMemberRenderType;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.util.ComponentUtil;
import sicce.api.util.EncryptionProvider;
import sicce.api.util.JTextFieldLimit;
import sicce.api.util.Validator;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.SearchDialog;

/**
 *
 * @author  gish@c
 */
public class UserPane extends JTabExtended<IUserSicce> {

    private SicceComboBoxModel<IRole> roleComboBoxModel;
    private SicceComboBoxRenderer roleComboBoxRenderer;
    private RoleBizObject roleBizObject;
    private UserBizObject userBizObject;
    private UserTableModel userTableModel;
    private PowerMeterTableModelForUsers powerMeterTableModel;
    private PowerMeterBizObject powerMeterBizObject;
    private PowerMeterUserCellRenderer powerMeterCellRenderer;

    /** Creates new form LocationPane */
    public UserPane() {
        initComponents();
        getControlsToClear().add(txtFirstName);
        getControlsToClear().add(txtLastName);
        getControlsToClear().add(txtName);
        getControlsToClear().add(txtPassword);
        getControlsToClear().add(txtMail);
        getControlsToClear().add(cmbRole);
        getControlsToClear().add(gridPowerMeters);
        getControlsToEnable().add(txtFirstName);
        getControlsToEnable().add(txtLastName);
        getControlsToEnable().add(txtName);
        getControlsToEnable().add(txtPassword);
        getControlsToEnable().add(cmbRole);
        getControlsToEnable().add(gridUsers);
        getControlsToEnable().add(gridPowerMeters);
        getControlsToEnable().add(txtMail);
        ComponentUtil.SetState(false, getControlsToEnable());
        txtName.setDocument(new JTextFieldLimit(20));
        txtLastName.setDocument(new JTextFieldLimit(20));
        txtFirstName.setDocument(new JTextFieldLimit(20));
        txtPassword.setDocument(new JTextFieldLimit(20));
        userBizObject = new UserBizObject();
        roleBizObject = new RoleBizObject();
        powerMeterBizObject = new PowerMeterBizObject();
        powerMeterCellRenderer = new PowerMeterUserCellRenderer();
        gridPowerMeters.setDefaultRenderer(String.class, powerMeterCellRenderer);
        LoadComboBoxes();
        FillPowerMetersGrid();
        FillGrid();
    }

    private void LoadComboBoxes() {
        roleComboBoxModel = new SicceComboBoxModel<IRole>(roleBizObject.GetAllRoles());
        roleComboBoxRenderer = new SicceComboBoxRenderer("getDescription", DisplayMemberRenderType.Method, "getIdRole", DisplayMemberRenderType.Method);
        cmbRole.setModel(roleComboBoxModel);
        cmbRole.setRenderer(roleComboBoxRenderer);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        cmbRole = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridPowerMeters = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridUsers = new javax.swing.JTable();

        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(UserPane.class);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        txtName.setName("txtName"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setBackground(resourceMap.getColor("jLabel4.background")); // NOI18N
        jLabel4.setForeground(resourceMap.getColor("jLabel4.foreground")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setOpaque(true);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtFirstName.setName("txtFirstName"); // NOI18N

        txtLastName.setName("txtLastName"); // NOI18N

        cmbRole.setName("cmbRole"); // NOI18N
        cmbRole.setOpaque(false);

        txtPassword.setText(resourceMap.getString("txtPassword.text")); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jScrollPane2.border.title"))); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N

        gridPowerMeters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        gridPowerMeters.setName("gridPowerMeters"); // NOI18N
        jScrollPane2.setViewportView(gridPowerMeters);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtMail.setText(resourceMap.getString("txtMail.text")); // NOI18N
        txtMail.setName("txtMail"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMail)
                            .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        gridUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        gridUsers.setName("gridUsers"); // NOI18N
        jScrollPane1.setViewportView(gridUsers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.getAccessibleContext().setAccessibleName(resourceMap.getString("jPanel4.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        if (currentObject == null) {
            LoadComboBoxes();
            FillGrid();
        }
    }//GEN-LAST:event_formComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbRole;
    private javax.swing.JTable gridPowerMeters;
    private javax.swing.JTable gridUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
    @Override
    public boolean Delete() throws Exception {
        cancelAction = false;
        try {
            UserDB.Delete(currentObject);
            super.Delete();
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
        currentObject = ClassFactory.getUserInstance();
        txtName.requestFocusInWindow();
        powerMeterCellRenderer.setCurrentUser(currentObject);
        FillPowerMetersGrid();
    }

    @Override
    public boolean Save() throws Exception {
        super.Save();
        cancelAction = false;
        if (!CheckFields()) {
            return true;
        }
        try {
            currentObject.setName(txtFirstName.getText());
            currentObject.setRole((IRole) cmbRole.getSelectedItem());
            currentObject.setPasswordSicce(EncryptionProvider.Encrypt(new String(txtPassword.getPassword())));
            currentObject.setUsernameSicce(txtName.getText());
            currentObject.setLastname(txtLastName.getText());
            currentObject.setEmail(txtMail.getText());

            if (IsObjectLoaded()) {
                return Update();
            }
            UserDB.Save(currentObject);
            UserPowerMeterDB.Save(currentObject.getUserPowerMeters());
            powerMeterCellRenderer.setCurrentUser(currentObject);
            FillGrid();
        } catch (Exception ex) {
            ex.printStackTrace();
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public DialogResult Search() {
        SearchDialog<IUserSicce> searchRolesDialog = new SearchDialog<IUserSicce>(new JFrame(), true, new UserTableModel(userBizObject.GetAllUsers()));
        searchRolesDialog.setVisible(true);
        DialogResult result = searchRolesDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            currentObject = searchRolesDialog.getSearchResult();
            SetUIElements();
        }
        return result;
    }

    @Override
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            UserDB.Update(currentObject);
            UserPowerMeterDB.Update(currentObject.getUserPowerMeters());
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<IUserSicce> tableModel = (SicceTableModel<IUserSicce>) gridUsers.getModel();
        currentObject = tableModel.getRow(selectedIndex);
        SetUIElements();
    }

    @Override
    public void SetUIElements() {
        if (currentObject == null) {
            return;
        }
        txtName.setText(currentObject.getUsernameSicce());
        txtFirstName.setText(currentObject.getName());
        roleComboBoxModel.setSelectedItem(currentObject.getRole(), roleComboBoxRenderer);
        txtPassword.setText(EncryptionProvider.Decrypt(currentObject.getPasswordSicce()));
        txtLastName.setText(currentObject.getLastname());
        txtMail.setText(currentObject.getEmail());
        FillPowerMetersGrid();
        powerMeterTableModel.setReadOnly(true);
        powerMeterCellRenderer.setCurrentUser(currentObject);
    }

    @Override
    public void RegisterSelectionListener() {
        gridUsers.getSelectionModel().addListSelectionListener(selectionListener);
        gridUsers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void FillGrid() {
        userTableModel = new UserTableModel(userBizObject.GetAllUsers());
        gridUsers.setModel(userTableModel);
    }

    @Override
    public void CancelSave() {
        if (currentObject != null) {
            if (currentObject.getIdUserSicce() != null) {
                IUserSicce originalInstance = UserDB.FindUserByID(currentObject.getIdUserSicce());
                this.currentObject = originalInstance;
            } else {
                this.currentObject = ClassFactory.getUserInstance();
            }
        }
    }

    @Override
    public boolean CheckFields() {
        if (!Validator.ValidateField(null, null, 0, txtName, true, "el login del usuario", 3)) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtFirstName, true, "el nombre del usuario", 3)) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtLastName, true, "el apellido del usuario", 3)) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtPassword, true, "la contraseña del usuario", 3)) {
            return false;
        }
        return true;
    }

    /**
     *
     */
    private void FillPowerMetersGrid() {
        powerMeterTableModel = null;
        powerMeterTableModel = new PowerMeterTableModelForUsers(powerMeterBizObject.GetAllPowerMeter(), currentObject);
        gridPowerMeters.setModel(powerMeterTableModel);
        gridPowerMeters.setEnabled(true);
    }
}
