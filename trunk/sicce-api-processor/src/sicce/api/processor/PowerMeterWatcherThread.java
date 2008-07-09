/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 *
 * @author gish@c
 */
public class PowerMeterWatcherThread implements Runnable {

    private IPowerMeterWatcher watcher;

    /**
     * 
     * @param watcher
     */
    public PowerMeterWatcherThread(IPowerMeterWatcher watcher) {
        
        this.watcher = watcher;
    }

    /**
     * 
     */
    public void run() {
        try {
            watcher.Watch();
        } catch (Exception ex) {
            Logger.getLogger(PowerMeterWatcherThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
