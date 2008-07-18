/*
 * LogPane.java
 *
 * Created on July 5, 2008, 4:38 AM
 */

package sicce.api.processor.viewer.controls;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jdesktop.application.ResourceMap;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.info.ComboBoxItem;
import sicce.api.info.ConstantsProvider.ModbusRegister;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.processor.Processor;
import sicce.api.processor.viewer.handlers.LogViewHandler;
import sicce.api.processor.viewer.handlers.MeasureVisibilityHandler;

/**
 *
 * @author  gish@c
 */
public class LogPane extends javax.swing.JPanel {
    
    /** Creates new form LogPane */
    public LogPane(ResourceMap resourceMap) {
        initComponents();
        this.resourceMap = resourceMap;
        this.logList.setModel(getListModel());
        LoadAvailablePowerMeters();
        measureVisibilityHandler = MeasureVisibilityHandler.getInstance();
        measureVisibilityHandler.FillMeasures(cmbMeasures);
        AttachLogToDataProcessor();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logList = new javax.swing.JList();
        logPaneToolbar = new javax.swing.JToolBar();
        lblCurrentMeasure = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmbMeasures = new javax.swing.JComboBox();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        logList.setName("logList"); // NOI18N
        jScrollPane1.setViewportView(logList);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        logPaneToolbar.setFloatable(false);
        logPaneToolbar.setRollover(true);
        logPaneToolbar.setMaximumSize(new java.awt.Dimension(47, 25));
        logPaneToolbar.setMinimumSize(new java.awt.Dimension(47, 25));
        logPaneToolbar.setName("logPaneToolbar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.api.processor.viewer.SicceapiprocessorviewerApp.class).getContext().getResourceMap(LogPane.class);
        lblCurrentMeasure.setFont(resourceMap.getFont("lblCurrentMeasure.font")); // NOI18N
        lblCurrentMeasure.setText(resourceMap.getString("lblCurrentMeasure.text")); // NOI18N
        lblCurrentMeasure.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lblCurrentMeasure.setName("lblCurrentMeasure"); // NOI18N
        logPaneToolbar.add(lblCurrentMeasure);

        jSeparator1.setName("jSeparator1"); // NOI18N
        logPaneToolbar.add(jSeparator1);

        cmbMeasures.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMeasures.setMaximumSize(new java.awt.Dimension(200, 25));
        cmbMeasures.setMinimumSize(new java.awt.Dimension(200, 25));
        cmbMeasures.setName("cmbMeasures"); // NOI18N
        cmbMeasures.setPreferredSize(new java.awt.Dimension(200, 25));
        cmbMeasures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMeasuresActionPerformed(evt);
            }
        });
        logPaneToolbar.add(cmbMeasures);

        add(logPaneToolbar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMeasuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMeasuresActionPerformed
        ComboBoxItem<ModbusRegister> selectedItem = (ComboBoxItem<ModbusRegister>)cmbMeasures.getSelectedItem();
        lblCurrentMeasure.setText(selectedItem.getId());
        getLogHandler().HandleMeasureChanged(selectedItem.getValue().ordinal(),selectedItem.getId());
    }//GEN-LAST:event_cmbMeasuresActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbMeasures;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JLabel lblCurrentMeasure;
    private javax.swing.JList logList;
    private javax.swing.JToolBar logPaneToolbar;
    // End of variables declaration//GEN-END:variables
    
    private DefaultListModel listModel;
    private JPanel powerMetersPane;
    private ResourceMap resourceMap;
    
    
    private MeasureVisibilityHandler measureVisibilityHandler;
    
    private DefaultListModel getListModel(){
        if(listModel == null){
            listModel = new DefaultListModel();
        }
        return listModel;
    }
    
    private LogViewHandler logHandler;
    private LogViewHandler getLogHandler(){
        if(logHandler == null){
            logHandler = new LogViewHandler(getListModel());
        }
        return logHandler;
    }
    
    /**
     * 
     * @return
     */
     public JPanel getPowerMetersPane() {
        if (powerMetersPane == null) {
            powerMetersPane = new JPanel();
            powerMetersPane.setPreferredSize(new Dimension(200, Toolkit.getDefaultToolkit().getScreenSize().height));
            powerMetersPane.setBorder(new TitledBorder(resourceMap.getString("PowerMeterPaneTitle")));
            powerMetersPane.setLayout(new BoxLayout(powerMetersPane, BoxLayout.PAGE_AXIS));
        }
        return powerMetersPane;
    }
    
    /**
     * 
     */
    private void AttachLogToDataProcessor(){
        Processor.AddObserver(getLogHandler().getLogObserver());
    }

    
    /**
     * 
     */
    public void LoadAvailablePowerMeters(){
        PowerMeterBizObject powerMeterBizObject = new PowerMeterBizObject();
        List<IPowerMeter> powerMeters = powerMeterBizObject.GetAllPowerMeter();
        IPowerMeter virtual1 = ClassFactory.getPowerMeterInstance();
        virtual1.setSerial("v1");
        virtual1.setDescription("Medidor virtual 1");
        
        powerMeters.add(virtual1);
        
        IPowerMeter virtual2 = ClassFactory.getPowerMeterInstance();
        virtual2.setSerial("v2");
        virtual2.setDescription("Medidor virtual 2");
        
        powerMeters.add(virtual2);
        
        for (IPowerMeter powerMeter : powerMeters) {
            JCheckBox checkbox = new JCheckBox(powerMeter.getDescription());
            checkbox.setName(powerMeter.getSerial());
            checkbox.setSelected(true);
            getPowerMetersPane().add(checkbox);
            checkbox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JCheckBox sender = (JCheckBox) evt.getSource();
                    getLogHandler().HandlePowerMeterVisibility(sender.getName(), sender.isSelected());
                }
            });
        }
        add(getPowerMetersPane(),BorderLayout.WEST);
    }
    
}
