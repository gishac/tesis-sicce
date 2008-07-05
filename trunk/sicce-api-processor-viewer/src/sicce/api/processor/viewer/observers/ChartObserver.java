/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.observers;

import sicce.api.processor.viewer.handlers.*;
import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author gish@c
 */
public class ChartObserver implements Observer {

    private ChartHandler chartHandler;
    
    public ChartObserver(ChartHandler chartHandler){
        this.chartHandler = chartHandler;
    }

    public void update(Observable o, Object arg) {
        IMeasure measure = (IMeasure) arg;
        this.chartHandler.ProcessMeasure(measure);
    }
    
    
    
}
