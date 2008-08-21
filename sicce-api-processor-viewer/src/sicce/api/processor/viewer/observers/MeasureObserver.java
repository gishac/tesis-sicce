/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.observers;

import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.processor.viewer.handlers.MeasureViewHandler;

/**
 * Objeto observador para el panel de parametros generales
 * @author gish@c
 */
public class MeasureObserver implements Observer {

    /**
     * Manejador de datos del panel de parametros generales
     */
    private MeasureViewHandler measureHandler;

    /**
     * Constructor
     * @param measureHandler Manejador de datos del panel de parametros generales
     */
    public MeasureObserver(MeasureViewHandler measureHandler) {
        this.measureHandler = measureHandler;
    }

    /**
     * Notificacion de lectura
     * @param powerMeterWatcher Medidor observado
     * @param arg Argumento de la lectura
     */
    public void update(Observable powerMeterWatcher, Object arg) {
        if (!(arg instanceof Exception)) {
            IPowerMeterWatcher watcher = (IPowerMeterWatcher) powerMeterWatcher;
            IMeasure measure = (IMeasure) arg;
            this.measureHandler.ProcessMeasure(watcher.getPowerMeter(), measure);
        }
    }
}
