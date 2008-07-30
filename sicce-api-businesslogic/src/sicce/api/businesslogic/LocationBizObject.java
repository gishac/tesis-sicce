/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.LocationDB;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class LocationBizObject {

    public List<ILocation> GetAllLocations() {
        return LocationDB.GetAllLocations();
    }
    
    
    /**
     * Indica si la dependencia ya tiene asignado el medidor
     * @param powerMeterID
     * @param location
     * @return
     */
    public boolean PowerMeterExists(String powerMeterID, ILocation location) {
        for (IPowerMeter powerMeter : location.getPowerMeters()) {
            if (powerMeter.getSerial().equals(powerMeterID)) {
                return true;
            }
        }
        return false;
    }
    
}
