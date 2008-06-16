/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;


import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import sicce.api.info.auto.AbstractAlarm;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IAlarmListener;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.info.interfaces.IUserSicce;

/**
 * Representacion de las alarmas a ser usadas por sistema, para el control de los medidores
 * @see AbstractAlarm
 * @author gish@c
 */
public class Alarm extends AbstractAlarm implements Observer  {

    /**
     * Metodo de monitoreo de de la alarma
     * @param observable Objeto que esta siendo monitoreado
     * @param arg Valor que esta siendo monitoreado
     * @see Observer
     */
    public void update(Observable observable, Object arg) {
        IPowerMeterWatcher watcher = (IPowerMeterWatcher) observable;
        int read = Integer.parseInt(arg.toString());        
        if(this.IsActive() && read > this.getMaxValueAllowed() && true){
            //TODO: Terminar la logica de activacion de la alarma. Mover el alarm handler del proyecto sicce.api.alarms a sicce.api.businesslogic
                /*watcher.getPowerMeter().getSerial().equals(this.getPowerMeter().getSerial())){
            if(alarmListener != null){
                alarmListener.actionPerformed(this);
            }*/
        }
    }

    
       
}
