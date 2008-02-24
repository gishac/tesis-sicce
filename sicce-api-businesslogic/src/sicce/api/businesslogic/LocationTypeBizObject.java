/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.LocationTypeDB;
import sicce.api.info.interfaces.ILocationType;

/**
 *
 * @author gish@c
 */
public class LocationTypeBizObject {

    public List<ILocationType> GetAllLocationsType() {
       return LocationTypeDB.GetAllLocations();
    }
}
