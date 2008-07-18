/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.observers;

import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.processor.viewer.handlers.MeasureViewHandler;

/**
 *
 * @author gish@c
 */
public class MeasureObserver implements Observer {

    /**
     * 
     */
    private MeasureViewHandler measureHandler;
    
    /**
     * 
     * @param measureHandler
     */
    public MeasureObserver(MeasureViewHandler measureHandler){
        this.measureHandler = measureHandler;
    }
    
    /**
     * 
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        IPowerMeterWatcher powerMeterWatcher = (IPowerMeterWatcher) o;
        IMeasure measure = (IMeasure) arg;
        this.measureHandler.ProcessMeasure(powerMeterWatcher.getPowerMeter(), measure);
    }

}
