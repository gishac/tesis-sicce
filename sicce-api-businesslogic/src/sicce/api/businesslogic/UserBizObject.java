/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.UserDB;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserBizObject {

    public List<IUserSicce> GetAllUsers() {
        return UserDB.GetAllUsers();
    }
    
    /**
     * Indica si el usuario ya tiene asignado el medidor
     * @param powerMeterID
     * @param alarm
     * @return
     */
    public boolean PowerMeterExists(String powerMeterID, IUserSicce user) {
        for (IPowerMeter powerMeter : user.getPowerMeters()) {
            if (powerMeter.getSerial().equals(powerMeterID)) {
                return true;
            }
        }
        return false;
    }
}
