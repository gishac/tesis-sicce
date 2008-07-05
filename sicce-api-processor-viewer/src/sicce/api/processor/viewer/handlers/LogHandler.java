/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.handlers;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.processor.viewer.observers.LogObserver;

/**
 *
 * @author gish@c
 */
public class LogHandler {

    private DefaultListModel logListModel;
    
    public LogHandler(DefaultListModel logListModel){
        this.logListModel = logListModel;
    }
    
    public void ProcessMeasure(IMeasure measure){
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
    
}
