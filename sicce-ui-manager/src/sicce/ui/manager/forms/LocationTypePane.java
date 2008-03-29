/*
 * LocationTypePane.java
 *
 * Created on January 26, 2008, 7:34 PM
 */

package sicce.ui.manager.forms;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.dataaccess.LocationTypeDB;
import sicce.api.info.interfaces.ILocationType;
import sicce.api.util.ComponentUtil;
import sicce.api.util.JTextFieldLimit;
import sicce.ui.manager.controls.JTabExtended;
import sicce.api.businesslogic.LocationTypeBizObject;
import sicce.api.businesslogic.LocationTypeTableModel;
import sicce.api.businesslogic.SicceTableModel;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.util.Validator;
import sicce.ui.manager.controls.SearchDialog;
import sicce.ui.manager.handlers.ExceptionHandler;
/**
 *
 * @author  gish@c
 */
public class LocationTypePane extends JTabExtended<ILocationType> {
    
    private ILocationType locationType;
    LocationTypeBizObject ltypeBizObject;
    LocationTypeTableModel ltypeTableModel;
    /** Creates new form LocationTypePane */
    public LocationTypePane() {
        initComponents();
        getControlsToClear().add(txtDescription); 
        getControlsToEnable().add(txtDescription);
        ComponentUtil.SetState(false, getControlsToEnable());  
        txtDescription.setDocument(new JTextFieldLimit(30));
        ltypeBizObject = new LocationTypeBizObject();
        FillGrid();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdLocationType = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(LocationTypePane.class);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        txtDescription.setText(resourceMap.getString("txtDescription.text")); // NOI18N
        txtDescription.setName("txtDescription"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescription)
                .addGap(18, 18, 18)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescription)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        grdLocationType.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Código", "Descripción"
            }
        ));
        grdLocationType.setName("grdLocationType"); // NOI18N
        jScrollPane1.setViewportView(grdLocationType);
        grdLocationType.getColumnModel().getColumn(0).setPreferredWidth(15);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grdLocationType;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JTextField txtDescription;
    // End of variables declaration//GEN-END:variables
  
    @Override
    public void Back() {
        super.Back();
    }

    @Override
    public boolean Delete() throws Exception {
        cancelAction = false;
        try {
            LocationTypeDB.Delete(currentObject);
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
        currentObject = ClassFactory.getLocationTypeInstance();
        txtDescription.requestFocusInWindow();
    }

    @Override
    public boolean Save() throws Exception {
        cancelAction = false;
        if (!CheckFields()) {
            return true;
        }
        super.Save();
        try {
            currentObject.setDescription(txtDescription.getText().trim());
            if (IsObjectLoaded()) {
                return Update();
            }
            LocationTypeDB.Save(currentObject);
            FillGrid();
          
        } catch (Exception ex) {
            ExceptionHandler.DisplayException(ex);
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public DialogResult Search() {
        SearchDialog<ILocationType> searchLTypeDialog = new SearchDialog<ILocationType>(new JFrame(), true, new LocationTypeTableModel(ltypeBizObject.GetAllLocationsType()));
        searchLTypeDialog.setVisible(true);
        DialogResult result = searchLTypeDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            currentObject = searchLTypeDialog.getSearchResult();
        //SetUIElements();
        }
        return result;
    }

    @Override
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            LocationTypeDB.Update(currentObject);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }
    
     @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<ILocationType> tableModel = (SicceTableModel<ILocationType>) grdLocationType.getModel();
        currentObject = tableModel.getRow(selectedIndex);
        txtDescription.setText(currentObject.getDescription());
        
    }

    @Override
    public void RegisterSelectionListener() {
        grdLocationType.getSelectionModel().addListSelectionListener(selectionListener);
        grdLocationType.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    @Override
    public void FillGrid() {
        ltypeTableModel = new LocationTypeTableModel(ltypeBizObject.GetAllLocationsType());
        grdLocationType.setModel(ltypeTableModel);
    }
    
     @Override
       public boolean CheckFields() {
         
          if (!Validator.ValidateField(null,null,0, txtDescription, true, "la descripción",1)) {
            return false;
        }
         return true;
       }
    
}
