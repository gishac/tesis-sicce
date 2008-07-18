/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.handlers;

import javax.swing.JTable;
import sicce.api.businesslogic.model.PhaseValuesTableModel;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class MeasureViewHandler {

    private IPowerMeter activePowerMeter;
    private JTable measuresTable;
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
    public MeasureViewHandler(JTable measuresTable){
        this.measuresTable = measuresTable;
        measurestableModel = new PhaseValuesTableModel(null);
        this.measuresTable.setModel(measurestableModel);
    }
    
    /**
     * 
     * @param powerMeter
     * @param measure
     */
    public void ProcessMeasure(IPowerMeter powerMeter, IMeasure measure){
        if(activePowerMeter == null)
            return;
        if(activePowerMeter.getSerial().equals(powerMeter.getSerial()))
            measurestableModel.setMeasure(measure);
    }

}
