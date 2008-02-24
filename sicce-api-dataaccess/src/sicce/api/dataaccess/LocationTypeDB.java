/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.ILocationType;

/**
 *
 * @author karu
 */
public class LocationTypeDB {

    public static void Save(ILocationType locationType) throws Exception {
        try {
            DataAccessManager.getInstance().getLocationTypeDB().save(locationType);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(ILocationType locationType) throws Exception {
        try {
            DataAccessManager.getInstance().getLocationTypeDB().update(locationType);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(ILocationType locationType) throws Exception {
        try {
            DataAccessManager.getInstance().getLocationTypeDB().delete(locationType);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllLocations() {
        return DataAccessManager.getInstance().getLocationTypeDB().findAll();
    }
}
