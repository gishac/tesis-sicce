/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.LocationTypeDB;
import sicce.api.info.interfaces.ILocationType;

/**
 *
 * @author gish@c
 */
public class LocationTypeBizObject {

    public List<ILocationType> getAllLocations()
    {
        Query query = new SelectQuery(sicce.api.info.LocationType.class);
        List<ILocationType> result = null;
        result = LocationTypeDB.GetLocations(query);
        return result;
    }
    
}
