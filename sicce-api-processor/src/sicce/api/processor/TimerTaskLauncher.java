/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.info.interfaces.ITimerLauncher;

/**
 * Clase que ejecuta al proceso de lecturas en lotes de manera asincronica
 * @author gish@c
 */
public class TimerTaskLauncher implements ITimerLauncher {

    /**
     * Medidores a ser controlados
     */
    private List<IPowerMeterWatcher> powerMetersToWatch;
    
    /**
     * Tiempo entre lecturas en segundos
     */
    private int readInterval;
    
    /**
     * Timer que gestiona la ejecucion en los intervalos
     */
    private TimerTaskExtended timerTask;
    
    
    /**
     * Timeout de espera de terminacion de lecturas
     */
    private int READ_TIMEOUT = 60;

    /**
     * Constructor
     * @param powerMetersToWatch Medidores a monitorear 
     * @param readInterval Tiempo entre lecturas en segundos
     */
    public TimerTaskLauncher(List<IPowerMeterWatcher> powerMetersToWatch, int readInterval) {
        this.powerMetersToWatch = powerMetersToWatch;
        this.readInterval = readInterval * 1000; //Se convierte a milisegundos
        timerTask = new TimerTaskExtended(this);
    }

    /**
     * Ejecuta las lecturas de todos los medidores
     */
    public synchronized void run() {
        synchronized (this) {
            try {
                ExecutorService threadExecutor = Executors.newFixedThreadPool(powerMetersToWatch.size());
                for (IPowerMeterWatcher powerMeterToWatch : powerMetersToWatch) {
                    threadExecutor.execute(new PowerMeterWatcherThread(powerMeterToWatch));
                }
                threadExecutor.shutdown();
                threadExecutor.awaitTermination(READ_TIMEOUT, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimerTaskLauncher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Inicia la tarea que ejecuta el proceso de lecturas cada N segundos, basado en la propiedad 
     * <strong>readInterval</strong>
     */
    public void BeginTasks() {
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, readInterval);
    }
}
