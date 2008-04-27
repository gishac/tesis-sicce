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
    private static Random generator;
    private int sleepTime;

    public PowerMeterWatcherThread(IPowerMeterWatcher watcher) {
        //super(watcher.getPowerMeter().getDescription());
        this.watcher = watcher;
        
        generator = new Random();
        sleepTime = generator.nextInt(10);
    }

   
    public void run() {
        
        try {
            
            System.out.printf("%s going to sleep for %d seconds.\n", watcher.getPowerMeter().getDescription(), sleepTime);

            Thread.sleep(sleepTime * 1000); // put thread to sleep
            // Fin de pruebas de ejecuciones multiples asincronicas
            watcher.Watch();
        } catch (InterruptedException ex) {
            Logger.getLogger(PowerMeterWatcherThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
