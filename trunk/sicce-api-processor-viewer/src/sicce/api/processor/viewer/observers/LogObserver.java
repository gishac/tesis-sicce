/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.observers;

import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.processor.viewer.handlers.LogHandler;

/**
 *
 * @author gish@c
 */
public class LogObserver implements Observer {

    private LogHandler logHandler;
    
    /**
     * 
     * @param logHandler
     */
    public LogObserver(LogHandler logHandler){
        this.logHandler = logHandler;
    }
    
    /**
     * 
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        logHandler.ProcessMeasure((IMeasure) arg);
    }

}
