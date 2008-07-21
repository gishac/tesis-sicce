/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.observers;

import java.awt.TrayIcon;
import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.processor.viewer.handlers.ExceptionHandler;

/**
 *
 * @author gish@c
 */
public class ExceptionObserver implements Observer {

    
    private ExceptionHandler exceptionHandler;
    
   
    /**
     * 
     * @param trayIcon
     */
    public ExceptionObserver(ExceptionHandler exceptionHandler){
        this.exceptionHandler = exceptionHandler;
    }
    
    /**
     * 
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        if(arg instanceof Exception){
            IPowerMeterWatcher powerMeterWatcher = (IPowerMeterWatcher) o;
            this.exceptionHandler.HandleException((Exception)arg, powerMeterWatcher.getPowerMeter());
        }
    }

}
