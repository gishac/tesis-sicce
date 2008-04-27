/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.Observer;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public interface IPowerMeterWatcher {

    void Watch();
    IPowerMeter getPowerMeter();
    void AddObserver(Observer observer);
    
}
