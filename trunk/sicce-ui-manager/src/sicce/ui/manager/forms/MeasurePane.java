/*
 * LocationPane.java
 *
 * Created on January 26, 2008, 7:43 PM
 */
package sicce.ui.manager.forms;

import java.math.BigDecimal;
import javax.swing.ListSelectionModel;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.businesslogic.MeasureBizObject;
import sicce.api.businesslogic.MeasureTableModel;
import sicce.api.businesslogic.SicceTableModel;
import sicce.api.dataaccess.MeasureDB;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.util.ComponentUtil;
import sicce.api.util.JTextFieldLimit;
import sicce.ui.manager.controls.JTabExtended;

/**
 *
 * @author  gish@c
 */
public class MeasurePane extends JTabExtended {

    private IMeasure measure;
    MeasureBizObject measureBizObject;
    MeasureTableModel measureTableModel;

    /** Creates new form LocationPane */
    public MeasurePane() {
        initComponents();
        getControlsToClear().add(txtLocation);
        getControlsToClear().add(txtPowerMeter);
        getControlsToClear().add(txtValue);
        dtpDateMeasure.cleanup();
        getControlsToEnable().add(txtLocation);
        getControlsToEnable().add(txtPowerMeter);
        getControlsToEnable().add(txtValue);
        ComponentUtil.SetState(false, getControlsToEnable());
        txtValue.setDocument(new JTextFieldLimit(10));
        measureBizObject = new MeasureBizObject();
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
        txtLocation = new javax.swing.JTextField();
        lblDescription2 = new javax.swing.JLabel();
        btnSearchLocation = new javax.swing.JButton();
        lblLocationType = new javax.swing.JLabel();
        lblPowerMeter = new javax.swing.JLabel();
        txtPowerMeter = new javax.swing.JTextField();
        btnSearchPowerMeter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dtpDateMeasure = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        grdMeasure = new javax.swing.JTable();

        setName("Form"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Localización"));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(null);

        lblUbication.setName("lblUbication"); // NOI18N
        jPanel4.add(lblUbication);
        lblUbication.setBounds(10, 30, 0, 0);

        txtLocation.setName("txtLocation"); // NOI18N
        jPanel4.add(txtLocation);
        txtLocation.setBounds(120, 30, 140, 20);

        lblDescription2.setName("lblDescription2"); // NOI18N
        jPanel4.add(lblDescription2);
        lblDescription2.setBounds(10, 140, 0, 0);

        btnSearchLocation.setAlignmentX(0.5F);
        btnSearchLocation.setAlignmentY(1.5F);
        btnSearchLocation.setName("btnSearchLocation"); // NOI18N
        btnSearchLocation.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnSearchLocation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(btnSearchLocation);
        btnSearchLocation.setBounds(270, 30, 30, 20);

        lblLocationType.setName("lblLocationType"); // NOI18N
        jPanel4.add(lblLocationType);
        lblLocationType.setBounds(10, 70, 120, 0);

        lblPowerMeter.setName("lblPowerMeter"); // NOI18N
        jPanel4.add(lblPowerMeter);
        lblPowerMeter.setBounds(10, 110, 120, 0);

        txtPowerMeter.setName("txtPowerMeter"); // NOI18N
        jPanel4.add(txtPowerMeter);
        txtPowerMeter.setBounds(120, 60, 140, 20);

        btnSearchPowerMeter.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSearchPowerMeter.setName("btnSearchPowerMeter"); // NOI18N
        jPanel4.add(btnSearchPowerMeter);
        btnSearchPowerMeter.setBounds(270, 60, 30, 20);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(MeasurePane.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel4.add(jLabel1);
        jLabel1.setBounds(10, 60, 90, 20);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel4.add(jLabel2);
        jLabel2.setBounds(10, 30, 70, 20);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel4.add(jLabel3);
        jLabel3.setBounds(10, 120, 90, 20);

        txtValue.setName("txtValue"); // NOI18N
        jPanel4.add(txtValue);
        txtValue.setBounds(160, 120, 100, 20);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel4.add(jLabel4);
        jLabel4.setBounds(10, 90, 90, 20);

        dtpDateMeasure.setName("dtpDateMeasure"); // NOI18N
        jPanel4.add(dtpDateMeasure);
        dtpDateMeasure.setBounds(160, 90, 100, 20);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        grdMeasure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Object", "Ubicación", "Tipo de Dependencia", "Medidor", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grdMeasure.setName("grdMeasure"); // NOI18N
        jScrollPane1.setViewportView(grdMeasure);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchLocation;
    private javax.swing.JButton btnSearchPowerMeter;
    private com.toedter.calendar.JDateChooser dtpDateMeasure;
    private javax.swing.JTable grdMeasure;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescription2;
    private javax.swing.JLabel lblLocationType;
    private javax.swing.JLabel lblPowerMeter;
    private javax.swing.JLabel lblUbication;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtPowerMeter;
    private javax.swing.JTextField txtValue;
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
            MeasureDB.Delete(measure);
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
        measure = ClassFactory.getMeasureInstance();
        txtLocation.requestFocusInWindow();
    }

    @Override
    public boolean Save() throws Exception {
        cancelAction = false;
        try {
            measure.setDateMeasure(dtpDateMeasure.getDate());
            measure.setValueMeasure(new BigDecimal(txtValue.getText()));
            //measure.setIdLocation();
            //measure.setIdPowerMeter(PROPERTIES);
            if (IsObjectLoaded()) {
                return Update();
            }
            measure = MeasureDB.Save(measure);
            FillGrid();
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
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            MeasureDB.Update(measure);
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }
    
       @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<IMeasure> tableModel = (SicceTableModel<IMeasure>) grdMeasure.getModel();
        measure = tableModel.getRow(selectedIndex);
        txtLocation.setText(measure.getLocation().getDescription());
        txtPowerMeter.setText(measure.getPowerMeter().getDescription());
        dtpDateMeasure.setDate(measure.getDateMeasure());
        if (measure.getValueMeasure()!=null)
            txtValue.setText(measure.getValueMeasure().toString());
    }

    @Override
    public void RegisterSelectionListener() {
        grdMeasure.getSelectionModel().addListSelectionListener(selectionListener);
        grdMeasure.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void FillGrid() {
        measureTableModel = new MeasureTableModel(measureBizObject.GetAllMeasures());
        grdMeasure.setModel(measureTableModel);
    }
}