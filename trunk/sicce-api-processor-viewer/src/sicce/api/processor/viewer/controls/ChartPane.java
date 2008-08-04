/*
 * ChartPane.java
 *
 * Created on July 5, 2008, 4:18 AM
 */
package sicce.api.processor.viewer.controls;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.jdesktop.application.ResourceMap;
import org.jfree.chart.ChartPanel;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.info.ComboBoxItem;
import sicce.api.info.ConstantsProvider.ModbusRegister;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.processor.Processor;
import sicce.api.processor.viewer.handlers.ChartViewHandler;
import sicce.api.processor.viewer.handlers.MeasureVisibilityHandler;

/**
 *
 * @author  gish@c
 */
public class ChartPane extends javax.swing.JPanel {

    private ChartViewHandler chartHandler;
    private String chartTitle;
    private MeasureVisibilityHandler measureVisibilityHandler;
    private JPanel powerMetersPane;
    private ResourceMap resourceMap;
    private Set<IPowerMeter> powerMetersForCurrentUser;
    
    /**
     * 
     * @return
     */
    private ChartViewHandler getChartHandler() {
        if (chartHandler == null) {
            chartHandler = new ChartViewHandler(powerMetersForCurrentUser);
        }
        return chartHandler;
    }

    /** Creates new form ChartPane */
    public ChartPane(String chartTitle, ResourceMap resourceMap,Set<IPowerMeter> powerMetersForCurrentUser) {
        initComponents();
        this.chartTitle = chartTitle;
        this.resourceMap = resourceMap;
        this.powerMetersForCurrentUser = powerMetersForCurrentUser;
        LoadAvailablePowerMeters();
        BuildChart();
        AttachChartToDataProcessor();
        measureVisibilityHandler = MeasureVisibilityHandler.getInstance();
        measureVisibilityHandler.FillMeasures(cmbMeasures);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartToolbar = new javax.swing.JToolBar();
        btnSaveGraph = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lblCurrentMeasure = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        cmbMeasures = new javax.swing.JComboBox();

        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        chartToolbar.setFloatable(false);
        chartToolbar.setRollover(true);
        chartToolbar.setMaximumSize(new java.awt.Dimension(273, 25));
        chartToolbar.setMinimumSize(new java.awt.Dimension(265, 25));
        chartToolbar.setName("chartToolbar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sicce.api.processor.viewer.SicceapiprocessorviewerApp.class).getContext().getResourceMap(ChartPane.class);
        btnSaveGraph.setIcon(resourceMap.getIcon("btnSaveGraph.icon")); // NOI18N
        btnSaveGraph.setText(resourceMap.getString("btnSaveGraph.text")); // NOI18N
        btnSaveGraph.setToolTipText(resourceMap.getString("btnSaveGraph.toolTipText")); // NOI18N
        btnSaveGraph.setFocusable(false);
        btnSaveGraph.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaveGraph.setName("btnSaveGraph"); // NOI18N
        btnSaveGraph.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSaveGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGraphActionPerformed(evt);
            }
        });
        chartToolbar.add(btnSaveGraph);

        jSeparator1.setName("jSeparator1"); // NOI18N
        chartToolbar.add(jSeparator1);

        lblCurrentMeasure.setFont(resourceMap.getFont("lblCurrentMeasure.font")); // NOI18N
        lblCurrentMeasure.setText(resourceMap.getString("lblCurrentMeasure.text")); // NOI18N
        lblCurrentMeasure.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lblCurrentMeasure.setName("lblCurrentMeasure"); // NOI18N
        chartToolbar.add(lblCurrentMeasure);

        jSeparator2.setName("jSeparator2"); // NOI18N
        chartToolbar.add(jSeparator2);

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
        chartToolbar.add(cmbMeasures);

        add(chartToolbar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt
     */
    private void btnSaveGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGraphActionPerformed
        SaveChart();
    }//GEN-LAST:event_btnSaveGraphActionPerformed

    /**
     * 
     * @param evt
     */
    private void cmbMeasuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMeasuresActionPerformed
        ComboBoxItem<ModbusRegister> selectedItem = (ComboBoxItem<ModbusRegister>)cmbMeasures.getSelectedItem();
        lblCurrentMeasure.setText(selectedItem.getId());
        getChartHandler().HandleMeasureChanged(selectedItem.getValue().ordinal(),selectedItem.getId());
    }//GEN-LAST:event_cmbMeasuresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveGraph;
    private javax.swing.JToolBar chartToolbar;
    private javax.swing.JComboBox cmbMeasures;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel lblCurrentMeasure;
    // End of variables declaration//GEN-END:variables
    
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
    private void BuildChart() {
        ChartViewHandler chartUIHandler = getChartHandler();
        ChartPanel chartPanel = chartUIHandler.BuildChart(chartTitle);
        this.add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * 
     */
    private void AttachChartToDataProcessor() {
        Processor.AddObserver(getChartHandler().getChartObserver());
    }

    /**
     * 
     */
    public void SaveChart(){
        getChartHandler().SaveChart();
    }
    
    /**
     * 
     */
    public void LoadAvailablePowerMeters(){
//        IPowerMeter virtual1 = ClassFactory.getPowerMeterInstance();
//        virtual1.setSerial("v1");
//        virtual1.setDescription("Medidor virtual 1");
//        
//        powerMetersForCurrentUser.add(virtual1);
//        
//        IPowerMeter virtual2 = ClassFactory.getPowerMeterInstance();
//        virtual2.setSerial("v2");
//        virtual2.setDescription("Medidor virtual 2");
//        
//        powerMetersForCurrentUser.add(virtual2);
        
        for (IPowerMeter powerMeter : powerMetersForCurrentUser) {
            JCheckBox checkbox = new JCheckBox(powerMeter.getDescription());
            checkbox.setName(powerMeter.getSerial());
            checkbox.setSelected(true);
            getPowerMetersPane().add(checkbox);
            checkbox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JCheckBox sender = (JCheckBox) evt.getSource();
                    getChartHandler().HandlePowerMeterVisibility(sender.getName(), sender.isSelected());
                }
            });
        }
        
        add(getPowerMetersPane(),BorderLayout.WEST);
    }
}
