/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author gish@c
 */
public class ProcessorObserver implements Observer {

    public void update( Observable observable, Object obj) {
        IPowerMeterWatcher watcher = (IPowerMeterWatcher) observable;
        System.out.println("Object Observed : " + watcher.getPowerMeter().getDescription());
    }
    
}
