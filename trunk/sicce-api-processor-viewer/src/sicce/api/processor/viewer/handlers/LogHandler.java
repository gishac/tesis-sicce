/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.processor.viewer.observers.LogObserver;

/**
 *
 * @author gish@c
 */
public class LogHandler implements IViewHandler {

    private DefaultListModel logListModel;
    private List<String> nonAllowedPowerMeters;
    
    /**
     * 
     * @return
     */
    private List<String> getNonAllowedPowerMeters()
    {
        if(nonAllowedPowerMeters == null)
            nonAllowedPowerMeters = new ArrayList<String>();
        return nonAllowedPowerMeters;
    }
    
    /**
     * 
     * @param logListModel
     */
    public LogHandler(DefaultListModel logListModel){
        this.logListModel = logListModel;
    }
    
    /**
     * 
     * @param measure
     */
    public void ProcessMeasure(IMeasure measure){
        if(getNonAllowedPowerMeters().contains(measure.getPowerMeter().getSerial()))
            return;
        String log = "Lectura del medidor " + measure.getPowerMeter().getDescription() + " realizada el " + 
                measure.getDateMeasure().toString() + ", con un valor de " + measure.getPhaseToNeutralVoltagePhase3();
        this.logListModel.addElement(log);
    }
    
    /**
     * 
     * @return
     */
    public LogObserver getLogObserver(){
        return new LogObserver(this);
    }

    /**
     * 
     * @param powerMeterSerial
     * @param state
     */
    public void HandlePowerMeterVisibility(String powerMeterSerial, boolean state) {
        if(!state){
            getNonAllowedPowerMeters().add(powerMeterSerial);
        }
        else{
            getNonAllowedPowerMeters().remove(powerMeterSerial);
        }
    }
    
    
}
