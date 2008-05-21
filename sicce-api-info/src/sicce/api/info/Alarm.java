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
 *
 * @author gish@c
 */
public class Alarm extends AbstractAlarm implements IAlarm, Observer  {

    public void update(Observable observable, Object arg) {
        IPowerMeterWatcher watcher = (IPowerMeterWatcher) observable;
        int read = Integer.parseInt(arg.toString());        
        if(this.IsActive() && read > this.getMaxValueAllowed() && true){
                /*watcher.getPowerMeter().getSerial().equals(this.getPowerMeter().getSerial())){
            if(alarmListener != null){
                alarmListener.actionPerformed(this);
            }*/
        }

    
    }

    public void addAlarmPowerMeter(IPowerMeter powerMeter) {
        this.alarmPowerMeters.add(powerMeter);
    }

    public void removeAlarmPowerMeter(IPowerMeter powerMeter) {
        this.alarmPowerMeters.remove(powerMeter);
    }

    public void addAlarmUser(IUserSicce user) {
        this.alarmUsers.add(user);
    }

    public void removeAlarmUser(IUserSicce user) {
        this.alarmUsers.remove(user);
    }
       
}
