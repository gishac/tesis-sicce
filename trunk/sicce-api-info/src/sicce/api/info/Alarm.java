/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info;

import java.util.Observable;
import java.util.Observer;
import sicce.api.info.auto.AbstractAlarm;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 * Representacion de las alarmas a ser usadas por sistema, para el control de los medidores
 * @see AbstractAlarm
 * @author gish@c
 */
public class Alarm extends AbstractAlarm implements Observer {

    /**
     * Metodo de monitoreo de de la alarma
     * @param observable Objeto que esta siendo monitoreado
     * @param arg Valor que esta siendo monitoreado
     * @see Observer
     */
    public void update(Observable observable, Object arg) {
        if ((arg instanceof Exception)) {
            return;
        }
        if (alarmListener != null) {
            IPowerMeterWatcher watcher = (IPowerMeterWatcher) observable;
            IMeasure measure = (IMeasure) arg;
            alarmListener.ValidateAlarm(measure, this, watcher.getPowerMeter());
        }
    }
}
