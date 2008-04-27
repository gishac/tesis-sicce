/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gish@c
 */
public class PowerMeterWatcherThread extends Thread {

    private IPowerMeterWatcher watcher;
    private static Random generator;
    private int sleepTime;

    public PowerMeterWatcherThread(IPowerMeterWatcher watcher) {
        super(watcher.getPowerMeter().getDescription());
        this.watcher = watcher;
        
        generator = new Random();
        sleepTime = generator.nextInt(10);
    }

    @Override
    public void run() {
        //Pruebas de ejecuciones multiples asincronicas
        try {
            System.out.printf("%s going to sleep for %d seconds.\n",
                    this.getName(), sleepTime);

            Thread.sleep(sleepTime * 1000); // put thread to sleep
        }
        catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        // Fin de pruebas de ejecuciones multiples asincronicas
        watcher.Watch();
    }
}
