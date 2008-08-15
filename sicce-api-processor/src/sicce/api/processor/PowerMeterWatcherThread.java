/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 * Representacion de un observador de medidor asincronico
 * @author gish@c
 */
public class PowerMeterWatcherThread implements Runnable {

    /**
     * Objeto observador de medidor
     */
    private IPowerMeterWatcher watcher;

    /**
     * Constructor 
     * @param watcher Objeto observador de medidor
     */
    public PowerMeterWatcherThread(IPowerMeterWatcher watcher) {
        
        this.watcher = watcher;
    }

    /**
     * Inicia el proceso asincronico de monitoreo del medidor
     */
    public void run() {
        try {
            watcher.Watch();
        } catch (Exception ex) {
            Logger.getLogger(PowerMeterWatcherThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
