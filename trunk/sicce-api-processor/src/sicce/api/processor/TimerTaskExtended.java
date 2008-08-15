/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.TimerTask;

/**
 * Clase que representa una tarea que se ejecuta en un tiempo determinado
 * @author gish@c
 */
public class TimerTaskExtended extends TimerTask {

    /**
     * Objeto que ejecuta el proceso asincronico de monitoreo en lotes 
     */
    private TimerTaskLauncher taskLauncher;
    
    /**
     * Constructor
     * @param taskLauncher
     */
    public TimerTaskExtended(TimerTaskLauncher taskLauncher){
        this.taskLauncher = taskLauncher;
    }
    
    /**
     * Inicia la tarea que ejecuta el monitoreo de los medidores
     */
    @Override
    public synchronized void run() {
        taskLauncher.run();
    }

}
