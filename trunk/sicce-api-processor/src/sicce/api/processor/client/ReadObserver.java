/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.client;

import java.util.Observable;
import java.util.Observer;

/**
 * Observador para receptar cuando se realizan lecturas
 * @author gish@c
 */
public class ReadObserver implements Observer {

    /**
     * Objeto para procesar las lecturas
     */
    private ProcessorClient processor;

    /**
     * Constructor
     * @param processor Objeto para procesar las lecturas
     */
    public ReadObserver(ProcessorClient processor) {
        this.processor = processor;
    }

    /**
     * Notificacion de lectura
     * @param powerMeterWatcher Medidor observado
     * @param arg Argumento de la lectura
     */
    public void update(Observable powerMeterWatcher, Object arg) {
        synchronized (this) {
            processor.KeepAliveInServer();
        }
    }
}
