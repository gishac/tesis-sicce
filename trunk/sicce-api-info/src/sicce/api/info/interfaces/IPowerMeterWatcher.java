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
     * Indica si el medidor esta bloqueado y no se pueden realizar lecturas sobre el
     * @return <strong> True </strong>, si el medidor esta bloqueado; <strong> False </strong>, si esta desbloqueado
     */
    public boolean isLocked();

    /**
     * Establece si se bloquean las lecturas sobre el medidor
     * @param locked <strong> True </strong>, para bloquear el medidor; <strong> False </strong>, para desbloquearlo
     */
    public void setLocked(boolean locked);
    
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
