/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.LocationDB;
import sicce.api.info.interfaces.ILocation;

/**
 *
 * @author gish@c
 */
public class LocationBizObject {

    public List<ILocation> GetAllLocations() {
        return LocationDB.GetAllLocations();
    }
    
}
