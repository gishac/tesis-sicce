/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.server;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.info.interfaces.ITimerLauncher;
import sicce.api.processor.TimerTaskExtended;

/**
 * Clase que ejecuta al proceso de verificacion de los clientes desconectados
 * @author gish@c
 */
public class TimerTimeoutLauncher implements ITimerLauncher {

    /**
     * Timer que gestiona la ejecucion en los intervalos
     */
    private TimerTaskExtended timerTask;
    
    /**
     * Intervalo de tiempo para ejecutar la verificacion de los clientes desconectados
     */
    private int readInterval;
    
    /**
     * Tiempo maximo de inactividad permtido a los clientes
     */
    private long maxInactivityTime;
    
    /**
     * Timeout de espera de terminacion de lecturas
     */
    private int READ_TIMEOUT = 60;
    
    /**
     * Objeto que realiza el procesamiento de las lecturas
     */
    private ProcessorServer processor;
    
    /**
     * Constructor
     * @param processor Objeto que realiza el procesamiento de las lecturas
     * @param readInterval Intervalo de tiempo para ejecutar la verificacion de los clientes desconectados
     * @param maxInactivityTime Tiempo maximo de inactividad permtido a los clientes
     */
    public TimerTimeoutLauncher(ProcessorServer processor, int readInterval, long maxInactivityTime){
        this.readInterval = readInterval * 1000;
        this.maxInactivityTime = maxInactivityTime;
        this.timerTask = new TimerTaskExtended(this);
        this.processor = processor;
    }
    
    /**
     * Inicia la tarea que ejecuta el proceso de verificacion de los clientes desconectados cada N segundos
     */
    public void BeginTasks() {
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, readInterval);
    }

    
    public synchronized void run() {
        synchronized (this) {
            try {
                ExecutorService threadExecutor = Executors.newFixedThreadPool(1);
                threadExecutor.execute(new ClientVerifierThread(this.processor,this.maxInactivityTime));
                threadExecutor.shutdown();
                threadExecutor.awaitTermination(READ_TIMEOUT, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimerTimeoutLauncher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
