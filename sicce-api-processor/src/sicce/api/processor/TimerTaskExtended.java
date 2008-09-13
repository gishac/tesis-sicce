/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.TimerTask;
import sicce.api.info.interfaces.ITimerLauncher;

/**
 * Clase que representa una tarea que se ejecuta en un tiempo determinado
 * @author gish@c
 */
public class TimerTaskExtended extends TimerTask {

    /**
     * Objeto que ejecuta el proceso asincronico 
     */
    private ITimerLauncher taskLauncher;
    
    /**
     * Constructor
     * @param taskLauncher
     */
    public TimerTaskExtended(ITimerLauncher taskLauncher){
        this.taskLauncher = taskLauncher;
    }
    
    /**
     * Inicia la tarea
     */
    @Override
    public synchronized void run() {
        taskLauncher.run();
    }

}
