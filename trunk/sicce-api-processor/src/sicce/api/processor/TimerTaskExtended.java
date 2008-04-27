/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.TimerTask;

/**
 *
 * @author gish@c
 */
public class TimerTaskExtended extends TimerTask {

    private TimerTaskLauncher taskLauncher;
    
    /**
     * 
     * @param taskLauncher
     */
    public TimerTaskExtended(TimerTaskLauncher taskLauncher){
        this.taskLauncher = taskLauncher;
    }
    
    @Override
    public synchronized void run() {
        taskLauncher.run();
    }

}
