/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.LocationDB;
import sicce.api.info.interfaces.ILocation;

/**
 *
 * @author gish@c
 */
public class LocationBizObject {

    public List<ILocation> GetAllLocations() {
        Query query = new SelectQuery(sicce.api.info.Location.class);
        List<ILocation> result = null;
        result = LocationDB.GetLocations(query);
        return result;
    }
}
