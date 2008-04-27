/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Observer;

/**
 *
 * @author gish@c
 */
public interface IPowerMeterWatcher {

    void Watch();
    IPowerMeter getPowerMeter();
    void AddObserver(Observer observer);
    
}
