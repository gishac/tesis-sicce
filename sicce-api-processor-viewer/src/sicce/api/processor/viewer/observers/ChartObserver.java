/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.observers;

import sicce.api.processor.viewer.handlers.*;
import java.util.Observable;
import java.util.Observer;
import sicce.api.info.interfaces.IMeasure;

/**
 * Objeto observador para el grafico
 * @author gish@c
 */
public class ChartObserver implements Observer {

    /**
     * Manejador de datos del grafico
     */
    private ChartViewHandler chartHandler;

    /**
     * Constructor
     * @param chartHandler Manejador de datos del grafico
     */
    public ChartObserver(ChartViewHandler chartHandler) {
        this.chartHandler = chartHandler;
    }

    /**
     * Notificacion de lectura
     * @param powerMeterWatcher Medidor observado
     * @param arg Argumento de la lectura
     */
    public void update(Observable powerMeterWatcher, Object arg) {
        if (!(arg instanceof Exception)) {
            IMeasure measure = (IMeasure) arg;
            this.chartHandler.ProcessMeasure(measure);
        }

    }
}
