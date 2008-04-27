/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 *
 * @author gish@c
 */
public class ProcessorObserver implements Observer {

    public void update( Observable observable, Object obj) {
        IPowerMeterWatcher watcher = (IPowerMeterWatcher) observable;
        System.out.println( watcher.getPowerMeter().getDescription()  + " Leido: " + Calendar.getInstance().getTime() + " con una lectura de " + Integer.parseInt(obj.toString()));
    }
    
}
