/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.ZoneDB;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IZone;

/**
 *
 * @author gish@c
 */
public class ZoneBizObject {

    /**
     * 
     * @return Devuelve todos los roles
     */
    public List<IZone> GetAllZones() {
        return ZoneDB.GetAllZone();
    }

    public IZone GetZoneByID(int zoneID) {
        return ZoneDB.FindZoneByID(zoneID);
    }

    public boolean LocationsExists(int optionID, IZone zone) {
    
            for (ILocation locations : zone.getLocationsInZone()) {
                if (locations.getID() == optionID) {
                    return true;
                }
            }   
        return false;
    }
}
