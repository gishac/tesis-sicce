/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.ILocation;

/**
 *
 * @author karu
 */
public class LocationDB {

    public static void Save(ILocation location) throws Exception {
        try {
            DataAccessManager.getInstance().getLocationDB().save(location);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(ILocation location) throws Exception {
        try {
            DataAccessManager.getInstance().getLocationDB().update(location);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(ILocation location) throws Exception {
        try {
            DataAccessManager.getInstance().getLocationDB().delete(location);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllLocations() {
        return DataAccessManager.getInstance().getLocationDB().findAll();
    }
}
