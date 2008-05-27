/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class AlarmBizObject {

    
    /**
     * Indica si la alarma ya tiene asignado el medidor
     * @param powerMeterID
     * @param alarm
     * @return
     */
    public boolean PowerMeterExists(String powerMeterID, IAlarm alarm) {
        for (IPowerMeter powerMeter : alarm.getAlarmPowerMeters()) {
            if (powerMeter.getSerial().equals(powerMeterID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si la alarma ya tiene asignado el usuario
     * @param userID
     * @param alarm
     * @return
     */
    public boolean UserExists(int userID, IAlarm alarm) {
        for (IUserSicce user : alarm.getAlarmUsers()) {
            if(user.getID() == userID){
                return true;
            }
        }
        return false;
    }
}
