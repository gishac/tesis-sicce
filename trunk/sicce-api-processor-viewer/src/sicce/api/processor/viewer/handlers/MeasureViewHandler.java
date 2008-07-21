/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import javax.swing.JTable;
import javax.swing.JTextField;
import sicce.api.businesslogic.model.PhaseValuesTableModel;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.processor.viewer.observers.MeasureObserver;

/**
 *
 * @author gish@c
 */
public class MeasureViewHandler {

    private IPowerMeter activePowerMeter;
    private JTable measuresTable;
    private JTextField txtPhaseToPhaseVoltage1To2;
    private JTextField txtPhaseToPhaseVoltage2To3;
    private JTextField txtPhaseToPhaseVoltage3To1;
    private JTextField txtTotalActivePower;
    private JTextField txtTotalReactivePower;
    private JTextField txtTotalApparentPower;
    private PhaseValuesTableModel measurestableModel;

    /**
     * 
     * @return
     */
    public IPowerMeter getActivePowerMeter() {
        return activePowerMeter;
    }

    /**
     * 
     * @param activePowerMeter
     */
    public void setActivePowerMeter(IPowerMeter activePowerMeter) {
        this.activePowerMeter = activePowerMeter;
    }

    /**
     * 
     * @return
     */
    public JTable getMeasuresTable() {
        return measuresTable;
    }

    /**
     * 
     * @param measuresTable
     */
    public void setMeasuresTable(JTable measuresTable) {
        this.measuresTable = measuresTable;
    }

    /**
     * 
     * @param measuresTable
     */
    public MeasureViewHandler(JTable measuresTable, JTextField txtPhaseToPhaseVoltage1To2,
            JTextField txtPhaseToPhaseVoltage2To3, JTextField txtPhaseToPhaseVoltage3To1, JTextField txtTotalActivePower,
            JTextField txtTotalReactivePower, JTextField txtTotalApparentPower) {
        this.measuresTable = measuresTable;
        measurestableModel = new PhaseValuesTableModel(null);
        this.measuresTable.setModel(measurestableModel);
        this.txtPhaseToPhaseVoltage1To2 = txtPhaseToPhaseVoltage1To2;
        this.txtPhaseToPhaseVoltage2To3 = txtPhaseToPhaseVoltage2To3;
        this.txtPhaseToPhaseVoltage3To1 = txtPhaseToPhaseVoltage3To1;
        this.txtTotalActivePower = txtTotalActivePower;
        this.txtTotalReactivePower = txtTotalReactivePower;
        this.txtTotalApparentPower = txtTotalApparentPower;
    }

    /**
     * 
     * @param powerMeter
     * @param measure
     */
    public void ProcessMeasure(IPowerMeter powerMeter, IMeasure measure) {
        if (activePowerMeter == null) {
            return;
        }
        if (activePowerMeter.getSerial().equals(powerMeter.getSerial())) {
            measurestableModel.setMeasure(measure);
            this.txtPhaseToPhaseVoltage1To2.setText(measure.getPhaseToPhaseVoltagePhase1To2().toString());
            this.txtPhaseToPhaseVoltage2To3.setText(measure.getPhaseToPhaseVoltagePhase2To3().toString());
            this.txtPhaseToPhaseVoltage3To1.setText(measure.getPhaseToPhaseVoltagePhase3To1().toString());
            this.txtTotalActivePower.setText(measure.getTotalActivePower().toString());
            this.txtTotalApparentPower.setText(measure.getTotalApparentPower().toString());
            this.txtTotalReactivePower.setText(measure.getTotalReactivePower().toString());
        }
    }

    /**
     * 
     * @return
     */
    public MeasureObserver getMeasureObserver() {
        return new MeasureObserver(this);
    }
}
