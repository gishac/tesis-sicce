/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.observers;

import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.processor.viewer.handlers.LogViewHandler;

/**
 * Objeto observador para el panel de logs
 * @author gish@c
 */
public class LogObserver implements Observer {

    /**
     * Manejador de datos del panel de logs
     */
    private LogViewHandler logHandler;

    /**
     * Constructor
     * @param logHandler Manejador de datos del panel de logs
     */
    public LogObserver(LogViewHandler logHandler) {
        this.logHandler = logHandler;
    }

    /**
     * Notificacion de lectura
     * @param powerMeterWatcher Medidor observado
     * @param arg Argumento de la lectura
     */
    public void update(Observable powerMeterWatcher, Object arg) {
        if (!(arg instanceof Exception)) {
            logHandler.ProcessMeasure((IMeasure) arg);
        }
    }
}
