/*
 * AlarmPane.java
 *
 * Created on May 12, 2008, 9:48 PM
 */
package sicce.ui.manager.forms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import sicce.api.businesslogic.AlarmBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.businesslogic.model.PowerMeterTableModelForAlarms;
import sicce.api.businesslogic.UserBizObject;
import sicce.api.businesslogic.comparator.ComboBoxItemComparator;
import sicce.api.businesslogic.model.AlarmTableModel;
import sicce.api.businesslogic.model.SicceComboBoxModel;
import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.businesslogic.model.UserTableModelForAlarms;
import sicce.api.businesslogic.renderer.SicceComboBoxRenderer;
import sicce.api.dataaccess.AlarmDB;
import sicce.api.dataaccess.ScheduledDayDB;
import sicce.api.info.ComboBoxItem;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.DisplayMemberRenderType;
import sicce.api.info.ConstantsProvider.ModbusRegister;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IScheduleDay;
import sicce.api.util.ComponentUtil;
import sicce.api.util.JTextFieldInteger;
import sicce.api.util.JTextFieldLimit;
import sicce.api.util.Validator;
import sicce.ui.manager.controls.JTabExtended;
import sicce.ui.manager.controls.SearchDialog;
import sicce.ui.manager.handlers.ExceptionHandler;

/**
 * Panel para la administracion de alarmas
 * @author  gish@c
 */
public class AlarmPane extends JTabExtended<IAlarm> {

    /**
     * Modelo de tabla para mostrar los medidores disponibles
     */
    private PowerMeterTableModelForAlarms powerMeterTableModel;
    /**
     * Modelo de tabla para mostrar los usuarios disponibles
     */
    private UserTableModelForAlarms userTableModel;
    /**
     * Objeto para manejar logica de los medidores
     */
    private PowerMeterBizObject powerMeterBizObject;
    /**
     * Objeto para manejar logica de los usuarios
     */
    private UserBizObject userBizObject;
    /**
     * Modelo de tabla para mostrar las alarmas existentes
     */
    private AlarmTableModel alarmTableModel;
    /**
     * Objeto para manejar logica de las alarmas
     */
    private AlarmBizObject alarmBizObject;
    /**
     * Registros disponibles a seleccionar para monitorear
     */
    private HashMap<ModbusRegister, String> registersInViews;

    /**
     * Devuelve los registros disponibles a seleccionar para monitorear
     * @return
     */
    public HashMap<ModbusRegister, String> getRegistersInViews() {
        if (registersInViews == null) {
            registersInViews = new HashMap<ModbusRegister, String>();
        }
        return registersInViews;
    }

    /**
     * Constructor
     */
    public AlarmPane() {
        initComponents();
        txtDescription.setDocument(new JTextFieldLimit(254));
        getControlsToClear().add(txtDescription);
        getControlsToClear().add(txtMinValue);
        getControlsToClear().add(txtMaxValue);
        getControlsToClear().add(cmbStartTime);
        getControlsToClear().add(cmbEndTime);
        getControlsToClear().add(chkMonday);
        getControlsToClear().add(chkTuesday);
        getControlsToClear().add(chkWednesday);
        getControlsToClear().add(chkThursday);
        getControlsToClear().add(chkFriday);
        getControlsToClear().add(chkSaturday);
        getControlsToClear().add(chkSunday);
        getControlsToClear().add(cmbRegister);
        getControlsToEnable().add(txtDescription);
        getControlsToEnable().add(txtMaxValue);
        getControlsToEnable().add(txtMinValue);
        getControlsToEnable().add(cmbStartTime);
        getControlsToEnable().add(cmbEndTime);
        getControlsToEnable().add(gridPowerMeters);
        getControlsToEnable().add(gridUsers);
        getControlsToEnable().add(chkMonday);
        getControlsToEnable().add(chkTuesday);
        getControlsToEnable().add(chkWednesday);
        getControlsToEnable().add(chkThursday);
        getControlsToEnable().add(chkFriday);
        getControlsToEnable().add(chkSaturday);
        getControlsToEnable().add(chkSunday);
        getControlsToEnable().add(cmbRegister);
        alarmBizObject = new AlarmBizObject();
        txtMaxValue.setDocument(new JTextFieldInteger(4));
        txtMinValue.setDocument(new JTextFieldInteger(4));
        powerMeterBizObject = new PowerMeterBizObject();
        userBizObject = new UserBizObject();
        FillPowerMetersGrid();
        FillUsersGrid();
        ComponentUtil.SetState(false, getControlsToEnable());
        
        getRegistersInViews().put(ModbusRegister.ActiveEnergyIn, "Total Energía Activa Consumida");
        getRegistersInViews().put(ModbusRegister.PhaseToPhaseVoltagePhase1To2, "Tensión Fase a Fase, Fase 1 a 2");
        getRegistersInViews().put(ModbusRegister.PhaseToPhaseVoltagePhase2To3, "Tensión Fase a Fase, Fase 2 a 3");
        getRegistersInViews().put(ModbusRegister.PhaseToPhaseVoltagePhase3To1, "Tensión Fase a Fase, Fase 3 a 1");
        getRegistersInViews().put(ModbusRegister.PhaseToNeutralVoltagePhase1, "Tensión Fase a Neutro, Fase 1");
        getRegistersInViews().put(ModbusRegister.PhaseToNeutralVoltagePhase2, "Tensión Fase a Neutro, Fase 2");
        getRegistersInViews().put(ModbusRegister.PhaseToNeutralVoltagePhase3, "Tensión Fase a Neutro, Fase 3");
        getRegistersInViews().put(ModbusRegister.Frequency, "Frecuencia");
        getRegistersInViews().put(ModbusRegister.TotalActivePower, "Potencia Activa Total");
        getRegistersInViews().put(ModbusRegister.TotalReactivePower, "Potencia Reactiva Total");
        getRegistersInViews().put(ModbusRegister.TotalApparentPower, "Potencia Aparente Total");
        getRegistersInViews().put(ModbusRegister.ActivePowerPhase1, "Potencia Activa, Fase 1");
        getRegistersInViews().put(ModbusRegister.ActivePowerPhase2, "Potencia Activa, Fase 2");
        getRegistersInViews().put(ModbusRegister.ActivePowerPhase3, "Potencia Activa, Fase 3");
        getRegistersInViews().put(ModbusRegister.ReactivePowerPhase1, "Potencia Reactiva, Fase 1");
        getRegistersInViews().put(ModbusRegister.ReactivePowerPhase2, "Potencia Reactiva, Fase 2");
        getRegistersInViews().put(ModbusRegister.ReactivePowerPhase3, "Potencia Reactiva, Fase 3");
        getRegistersInViews().put(ModbusRegister.ApparentPowerPhase1, "Potencia Aparente, Fase 1");
        getRegistersInViews().put(ModbusRegister.ApparentPowerPhase2, "Potencia Aparente, Fase 2");
        FillMeasures();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridAlarms = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        lblRegister = new javax.swing.JLabel();
        cmbRegister = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtMaxValue = new javax.swing.JTextField();
        lblMinValue = new javax.swing.JLabel();
        txtMinValue = new javax.swing.JTextField();
        lblStartTime = new javax.swing.JLabel();
        cmbStartTime = new javax.swing.JComboBox();
        cmbEndTime = new javax.swing.JComboBox();
        lblEndTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        chkMonday = new javax.swing.JCheckBox();
        chkThursday = new javax.swing.JCheckBox();
        chkSaturday = new javax.swing.JCheckBox();
        chkTuesday = new javax.swing.JCheckBox();
        chkWednesday = new javax.swing.JCheckBox();
        chkFriday = new javax.swing.JCheckBox();
        chkSunday = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridPowerMeters = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        gridUsers = new javax.swing.JTable();
        txtDescription = new javax.swing.JTextField();

        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(665);
        jSplitPane2.setName("jSplitPane2"); // NOI18N

        jPanel6.setName("jPanel6"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        gridAlarms.setModel(new javax.swing.table.DefaultTableModel(
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
        gridAlarms.setName("gridAlarms"); // NOI18N
        jScrollPane1.setViewportView(gridAlarms);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel6);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.ui.manager.forms.SicceuimanagerApp.class).getContext().getResourceMap(AlarmPane.class);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N

        lblRegister.setText(resourceMap.getString("lblRegister.text")); // NOI18N
        lblRegister.setName("lblRegister"); // NOI18N

        cmbRegister.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRegister.setMaximumSize(new java.awt.Dimension(250, 20));
        cmbRegister.setMinimumSize(new java.awt.Dimension(250, 20));
        cmbRegister.setName("cmbRegister"); // NOI18N
        cmbRegister.setPreferredSize(new java.awt.Dimension(250, 20));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        txtMaxValue.setText(resourceMap.getString("txtMaxValue.text")); // NOI18N
        txtMaxValue.setMaximumSize(new java.awt.Dimension(20, 250));
        txtMaxValue.setMinimumSize(new java.awt.Dimension(20, 250));
        txtMaxValue.setName("txtMaxValue"); // NOI18N

        lblMinValue.setText(resourceMap.getString("lblMinValue.text")); // NOI18N
        lblMinValue.setName("lblMinValue"); // NOI18N

        txtMinValue.setText(resourceMap.getString("txtMinValue.text")); // NOI18N
        txtMinValue.setMaximumSize(new java.awt.Dimension(250, 20));
        txtMinValue.setMinimumSize(new java.awt.Dimension(250, 20));
        txtMinValue.setName("txtMinValue"); // NOI18N
        txtMinValue.setPreferredSize(new java.awt.Dimension(250, 20));

        lblStartTime.setText(resourceMap.getString("lblStartTime.text")); // NOI18N
        lblStartTime.setName("lblStartTime"); // NOI18N

        cmbStartTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbStartTime.setMaximumSize(new java.awt.Dimension(250, 20));
        cmbStartTime.setMinimumSize(new java.awt.Dimension(250, 20));
        cmbStartTime.setName("cmbStartTime"); // NOI18N

        cmbEndTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "19", "20", "21", "22", "23" }));
        cmbEndTime.setMaximumSize(new java.awt.Dimension(250, 20));
        cmbEndTime.setMinimumSize(new java.awt.Dimension(250, 20));
        cmbEndTime.setName("cmbEndTime"); // NOI18N

        lblEndTime.setText(resourceMap.getString("lblEndTime.text")); // NOI18N
        lblEndTime.setName("lblEndTime"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        chkMonday.setText(resourceMap.getString("chkMonday.text")); // NOI18N
        chkMonday.setName("chkMonday"); // NOI18N

        chkThursday.setText(resourceMap.getString("chkThursday.text")); // NOI18N
        chkThursday.setName("chkThursday"); // NOI18N

        chkSaturday.setText(resourceMap.getString("chkSaturday.text")); // NOI18N
        chkSaturday.setName("chkSaturday"); // NOI18N

        chkTuesday.setText(resourceMap.getString("chkTuesday.text")); // NOI18N
        chkTuesday.setName("chkTuesday"); // NOI18N

        chkWednesday.setText(resourceMap.getString("chkWednesday.text")); // NOI18N
        chkWednesday.setName("chkWednesday"); // NOI18N

        chkFriday.setText(resourceMap.getString("chkFriday.text")); // NOI18N
        chkFriday.setName("chkFriday"); // NOI18N

        chkSunday.setText(resourceMap.getString("chkSunday.text")); // NOI18N
        chkSunday.setName("chkSunday"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSunday)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkThursday)
                            .addComponent(chkMonday))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkFriday)
                            .addComponent(chkTuesday))
                        .addGap(29, 29, 29)))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSaturday)
                    .addComponent(chkWednesday))
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkTuesday)
                    .addComponent(chkWednesday)
                    .addComponent(chkMonday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkThursday)
                    .addComponent(chkFriday)
                    .addComponent(chkSaturday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSunday)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jScrollPane3.border.title"))); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        gridUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        gridUsers.setName("gridUsers"); // NOI18N
        jScrollPane3.setViewportView(gridUsers);

        txtDescription.setText(resourceMap.getString("txtDescription.text")); // NOI18N
        txtDescription.setName("txtDescription"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(lblMinValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(lblEndTime))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMinValue, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                            .addComponent(cmbStartTime, 0, 251, Short.MAX_VALUE)
                                            .addComponent(txtMaxValue, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                            .addComponent(cmbEndTime, 0, 251, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblRegister)
                                            .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDescription)
                                            .addComponent(cmbRegister, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(360, 360, 360)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblStartTime, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(603, 603, 603))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(310, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescription)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegister)
                    .addComponent(cmbRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMinValue)
                    .addComponent(txtMinValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartTime)
                    .addComponent(cmbStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndTime)
                    .addComponent(cmbEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        if (currentObject == null) {
            FillGrid();
            FillUsersGrid();
            FillPowerMetersGrid();
        }
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkFriday;
    private javax.swing.JCheckBox chkMonday;
    private javax.swing.JCheckBox chkSaturday;
    private javax.swing.JCheckBox chkSunday;
    private javax.swing.JCheckBox chkThursday;
    private javax.swing.JCheckBox chkTuesday;
    private javax.swing.JCheckBox chkWednesday;
    private javax.swing.JComboBox cmbEndTime;
    private javax.swing.JComboBox cmbRegister;
    private javax.swing.JComboBox cmbStartTime;
    private javax.swing.JTable gridAlarms;
    private javax.swing.JTable gridPowerMeters;
    private javax.swing.JTable gridUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblEndTime;
    private javax.swing.JLabel lblMinValue;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel lblStartTime;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtMaxValue;
    private javax.swing.JTextField txtMinValue;
    // End of variables declaration//GEN-END:variables
    @Override
    public void New() {
        super.New();
        currentObject = ClassFactory.getAlarmInstance();
        txtDescription.requestFocusInWindow();
        FillPowerMetersGrid();
        FillUsersGrid();
    }

    /**
     * Llena la tabla de medidores con los medidores disponibles
     */
    private void FillPowerMetersGrid() {
        powerMeterTableModel = null;
        powerMeterTableModel = new PowerMeterTableModelForAlarms(powerMeterBizObject.GetAllPowerMeter(), currentObject);
        gridPowerMeters.setModel(powerMeterTableModel);
        gridPowerMeters.setEnabled(true);
    }

    /**
     * Llena la tabla de usuarios con los usuarios disponibles
     */
    private void FillUsersGrid() {
        userTableModel = null;
        userTableModel = new UserTableModelForAlarms(userBizObject.GetAllUsers(), currentObject);
        gridUsers.setModel(userTableModel);
        gridUsers.setEnabled(true);

    }

    @Override
    public boolean CheckFields() {
        if (!Validator.ValidateField(null, null, 0, txtDescription, true, "la descripción de la alarma", 5)) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtMaxValue, true, "el maximo valor permitido", 1)) {
            return false;
        }
        if (!Validator.ValidateField(null, null, 0, txtMinValue, true, "el minimo valor permitido", 1)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean Save() throws Exception {
        super.Save();
        cancelAction = false;
        if (!CheckFields()) {
            return true;
        }
        try {
            currentObject.setDescription(txtDescription.getText().trim());
            currentObject.setMaxValueAllowed(Integer.parseInt(txtMaxValue.getText().trim()));
            currentObject.setMinValueAllowed(Integer.parseInt(txtMinValue.getText().trim()));
            ComboBoxItem<ModbusRegister> selectedRegister = (ComboBoxItem<ModbusRegister>) cmbRegister.getSelectedItem();
            currentObject.setMeasure(selectedRegister.getValue().ordinal());
            currentObject.setMeasureDescription(selectedRegister.getId());
            SetScheduledDays(currentObject);
            if (IsObjectLoaded()) {
                return Update();
            }
            AlarmDB.Save(currentObject);
            ScheduledDayDB.Save(currentObject.getScheduledDays());
            FillGrid();
        } catch (Exception ex) {
            ExceptionHandler.DisplayException(ex);
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public boolean Update() throws Exception {
        cancelAction = false;
        try {
            AlarmDB.Update(currentObject);
            ScheduledDayDB.Update(currentObject.getScheduledDays());
            FillGrid();
        } catch (Exception ex) {
            cancelAction = true;
        }
        return cancelAction;
    }

    @Override
    public boolean Delete() throws Exception {
        cancelAction = false;
        try {
            AlarmDB.Delete(currentObject);
            super.Delete();
            FillGrid();

        } catch (Exception ex) {
            cancelAction = true;
            throw ex;
        }
        return cancelAction;
    }

    @Override
    public void FillGrid() {
        alarmTableModel = new AlarmTableModel(alarmBizObject.GetAllAlarms());
        gridAlarms.setModel(alarmTableModel);
    }

    @Override
    public void CancelSave() {
        if (currentObject != null) {
            if (currentObject.getIdAlarm() != null) {
                this.currentObject = AlarmDB.FindAlarmByID(currentObject.getIdAlarm());
            } else {
                this.currentObject = ClassFactory.getAlarmInstance();
            }
        }
    }

    @Override
    public void RegisterSelectionListener() {
        gridAlarms.getSelectionModel().addListSelectionListener(selectionListener);
        gridAlarms.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void ItemSelected(int selectedIndex) {
        super.ItemSelected(selectedIndex);
        SicceTableModel<IAlarm> tableModel = (SicceTableModel<IAlarm>) gridAlarms.getModel();
        currentObject = tableModel.getRow(selectedIndex);
        SetUIElements();
    }

    @Override
    public void SetUIElements() {
        if (currentObject == null) {
            return;
        }
        cmbStartTime.setSelectedIndex(0);
        cmbEndTime.setSelectedIndex(0);
        if (currentObject.getMeasure() != null) {
            for (int itemIndex = 0; itemIndex <= cmbRegister.getItemCount(); itemIndex++) {
                ComboBoxItem<ModbusRegister> item = (ComboBoxItem<ModbusRegister>) cmbRegister.getItemAt(itemIndex);
                if (item.getValue().ordinal() == currentObject.getMeasure().intValue()) {
                    cmbRegister.setSelectedIndex(itemIndex);
                    break;
                }
            }
        }
        txtDescription.setText(currentObject.getDescription());
        txtMaxValue.setText((currentObject.getMaxValueAllowed() != null) ? String.valueOf(currentObject.getMaxValueAllowed()) : null);
        txtMinValue.setText((currentObject.getMinValueAllowed() != null) ? String.valueOf(currentObject.getMinValueAllowed()) : null);
        chkMonday.setSelected(false);
        chkTuesday.setSelected(false);
        chkWednesday.setSelected(false);
        chkThursday.setSelected(false);
        chkFriday.setSelected(false);
        chkSaturday.setSelected(false);
        chkSunday.setSelected(false);

        for (IScheduleDay dayInSchedule : currentObject.getScheduledDays()) {
            switch (dayInSchedule.getDayScheduled()) {
                case Calendar.MONDAY:
                    chkMonday.setSelected(true);
                    break;
                case Calendar.TUESDAY:
                    chkTuesday.setSelected(true);
                    break;
                case Calendar.WEDNESDAY:
                    chkWednesday.setSelected(true);
                    break;
                case Calendar.THURSDAY:
                    chkThursday.setSelected(true);
                    break;
                case Calendar.FRIDAY:
                    chkFriday.setSelected(true);
                    break;
                case Calendar.SATURDAY:
                    chkSaturday.setSelected(true);
                    break;
                case Calendar.SUNDAY:
                    chkSunday.setSelected(true);
                    break;
            }
            cmbStartTime.setSelectedItem(dayInSchedule.getStartTime().toString());
            cmbEndTime.setSelectedItem(dayInSchedule.getEndTime().toString());
        }
        FillUsersGrid();
        FillPowerMetersGrid();
    }

    /**
     * Establece los dias en los que se configura la alarma
     * @param alarm Alarma a la que se le asignaran los dias
     */
    public void SetScheduledDays(IAlarm alarm) {

        for (IScheduleDay scheduledDay : alarm.getScheduledDays()) {
            try {
                ScheduledDayDB.Delete(scheduledDay);
            } catch (Exception ex) {
                Logger.getLogger(AlarmPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        alarm.getScheduledDays().clear();
        SetScheduledDay(alarm, chkMonday.isSelected(), Calendar.MONDAY);
        SetScheduledDay(alarm, chkTuesday.isSelected(), Calendar.TUESDAY);
        SetScheduledDay(alarm, chkWednesday.isSelected(), Calendar.WEDNESDAY);
        SetScheduledDay(alarm, chkThursday.isSelected(), Calendar.THURSDAY);
        SetScheduledDay(alarm, chkFriday.isSelected(), Calendar.FRIDAY);
        SetScheduledDay(alarm, chkSaturday.isSelected(), Calendar.SATURDAY);
        SetScheduledDay(alarm, chkSunday.isSelected(), Calendar.SUNDAY);
    }

    /**
     * Establece el dia en el que estara disponible la alarma
     * @param alarm Alarma a la que se le asignara el dia
     * @param checked <strong> True </string>, si el dia es asignado a la alarma; <strong> False </strong>,
     * si el dia no es asignado a la alarma
     * @param day Dia en el cual se quiere habilitar o deshabilitar la alarma
     */
    public void SetScheduledDay(IAlarm alarm, boolean checked, int day) {
        if (checked) {
            IScheduleDay scheduledDay = ClassFactory.getScheduleInstance();
            scheduledDay.setDayScheduled(day);
            scheduledDay.setStartTime(Integer.parseInt(cmbStartTime.getSelectedItem().toString()));
            scheduledDay.setEndTime(Integer.parseInt(cmbEndTime.getSelectedItem().toString()));
            alarm.getScheduledDays().add(scheduledDay);
            scheduledDay.setAlarm(currentObject);
        }
    }

    @Override
    public DialogResult Search() {
        SearchDialog<IAlarm> searchPmeterDialog = new SearchDialog<IAlarm>(new JFrame(), true, new AlarmTableModel(alarmBizObject.GetAllAlarms()));
        searchPmeterDialog.setVisible(true);
        DialogResult result = searchPmeterDialog.getDialogResult();
        if (result == DialogResult.Ok) {
            currentObject = searchPmeterDialog.getSearchResult();
            SetUIElements();
        }
        return result;
    }

    /**
     * Carga los registros que pueden ser utilizados por la alarma
     */
    private void FillMeasures() {
        List<ComboBoxItem<ModbusRegister>> registers = new ArrayList<ComboBoxItem<ModbusRegister>>();
        for (Entry<ModbusRegister, String> entry : getRegistersInViews().entrySet()) {
            ComboBoxItem<ModbusRegister> item = new ComboBoxItem<ModbusRegister>();
            item.setId(entry.getValue());
            item.setValue(entry.getKey());
            registers.add(item);
        }
        Collections.sort(registers, new ComboBoxItemComparator());
        SicceComboBoxRenderer comboRenderer = new SicceComboBoxRenderer("getId", DisplayMemberRenderType.Method, "getValue", DisplayMemberRenderType.Method);
        cmbRegister.setRenderer(comboRenderer);
        cmbRegister.setModel(new SicceComboBoxModel(registers));
        cmbRegister.setSelectedIndex(1);
    }
}
