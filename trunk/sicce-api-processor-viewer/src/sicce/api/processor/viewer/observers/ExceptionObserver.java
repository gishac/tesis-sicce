/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.observers;

import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.processor.viewer.handlers.ExceptionHandler;

/**
 * Objeto observador de excepciones
 * @author gish@c
 */
public class ExceptionObserver implements Observer {

    /**
     * Manejador de datos del panel de excepciones
     */
    private ExceptionHandler exceptionHandler;

    /**
     * Constructor
     * @param exceptionHandler Manejador de datos del panel de excepciones
     */
    public ExceptionObserver(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * Notificacion de lectura
     * @param powerMeterWatcher Medidor observado
     * @param arg Argumento de la lectura
     */
    public void update(Observable powerMeterWatcher, Object arg) {
        if (arg instanceof Exception) {
            IPowerMeterWatcher watcher = (IPowerMeterWatcher) powerMeterWatcher;
            this.exceptionHandler.HandleException((Exception) arg, watcher.getPowerMeter());
        }
    }
}
