/*
 * LocationTypePane.java
 *
 * Created on January 26, 2008, 7:34 PM
 */
package sicce.ui.manager.forms;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.businesslogic.OptionBizObject;
import sicce.api.businesslogic.PermissionsTableModel;
import sicce.api.businesslogic.RoleBizObject;
import sicce.api.businesslogic.RoleTableModel;
import sicce.api.businesslogic.SicceTableModel;
import sicce.api.dataaccess.RoleDB;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.interfaces.IRole;
import sicce.api.util.ComponentUtil;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.SearchDialog;

/**
 *
 * @author  gish@c
 */
public class RolePane extends JTabExtended {

    private IRole role;
    private RoleTableModel roleTableModel;
    private PermissionsTableModel permissionsTableModel;
    private RoleBizObject roleBizObject;
    private OptionBizObject optionBizObject;

    /** Creates new form LocationTypePane */
    public RolePane() {
        initComponents();
        getControlsToClear().add(txtDescription);
        getControlsToClear().add(gridPermissions);
        getControlsToEnable().add(txtDescription);
        getControlsToEnable().add(gridScrollPane);
        getControlsToEnable().add(gridPermissions);
        roleBizObject = new RoleBizObject();
        optionBizObject = new OptionBizObject();
        FillGrid();
        FillPermissionsGrid();
        ComponentUtil.SetState(false, getControlsToEnable());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDescription = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        gridScrollPane = new javax.swing.JScrollPane();
        gridPermissions = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridRoles = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(RolePane.class);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setEnabled(false);
        txtDescription.setName("txtDescription"); // NOI18N
        jScrollPane2.setViewportView(txtDescription);

        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jScrollPane3.border.title"))); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        gridScrollPane.setName("gridScrollPane"); // NOI18N

        gridPermissions.setModel(new javax.swing.table.DefaultTableModel(
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
        gridPermissions.setName("gridPermissions"); // NOI18N
        gridScrollPane.setViewportView(gridPermissions);

        jScrollPane3.setViewportView(gridScrollPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        gridRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Object", "Código", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        gridRoles.setName("gridRoles"); // NOI18N
        jScrollPane1.setViewportView(gridRoles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable gridPermissions;
    private javax.swing.JTable gridRoles;
    private javax.swing.JScrollPane gridScrollPane;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables
    @Override
    public void Back() {
        super.Back();
    }

    @Override
    public boolean Delete() throws Exception {
        cancelAction = false;
        try {
            RoleDB.Delete(role);
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
        role = ClassFactory.getRoleInstance();
        txtDescription.requestFocusInWindow();
        FillPermissionsGrid();
    }

    @Override
    public boolean Save() throws Exception {
        cancelAction = false;
        try {
            role.setDescription(txtDescription.getText().trim());
            if (IsObjectLoaded()) {
                return Update();
            }
            RoleDB.Save(role);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public DialogResult Search() {
        SearchDialog<IRole> searchRolesDialog = new SearchDialog<IRole>(new JFrame(), true, new RoleTableModel(roleBizObject.GetAllRoles()));
        searchRolesDialog.setVisible(true);
        DialogResult result = searchRolesDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            role = searchRolesDialog.getSearchResult();
            SetUIElements();
        }
        return result;
    }

    @Override
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            RoleDB.Update(role);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<IRole> tableModel = (SicceTableModel<IRole>) gridRoles.getModel();
        role = tableModel.getRow(selectedIndex);
        SetUIElements();

    }

    @Override
    public void RegisterSelectionListener() {
        gridRoles.getSelectionModel().addListSelectionListener(selectionListener);
        gridRoles.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void SetUIElements() {
        txtDescription.setText(role.getDescription());
        FillPermissionsGrid();
    }

    @Override
    public void FillGrid() {
        roleTableModel = new RoleTableModel(roleBizObject.GetAllRoles());
        gridRoles.setModel(roleTableModel);

    }

    /**
     * Carga el grid con los permisos asignados al rol
     */
    private void FillPermissionsGrid() {
        permissionsTableModel = null;
        permissionsTableModel = new PermissionsTableModel(optionBizObject.GetAllOptions(), role);
        gridPermissions.setModel(permissionsTableModel);
        gridPermissions.setEnabled(true);
    }
}




