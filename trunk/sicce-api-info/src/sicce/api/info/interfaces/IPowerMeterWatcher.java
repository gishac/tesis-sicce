/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Observer;

/**
 * Define los metodos a ser implementados por las clases que representen a los observadores de medidores
 * @author gish@c
 */
public interface IPowerMeterWatcher {

    /**
     * Realiza el proceso de lectura del medidor
     */
    void Watch();
    
    /**
     * Devuelve el medidor a ser observado
     * @return Medidor a ser observado
     */
    IPowerMeter getPowerMeter();
    
    /**
     * Objeto observador que puede monitorear las actividades del medidor
     * @param observer Observador que puede monitorear las actividades del medidor
     */
    void AddObserver(Observer observer);
    
    /**
     * Notifica la ocurrencia de alguna excepcion durante el proceso de lectura
     * @param ex Excepcion
     */
    void NotifyException(Exception ex);
    
}
