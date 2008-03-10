/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IZone;

/**
 *
 * @author gish@c
 */
public class ZoneDB {

    public static void Save(IZone zone) throws Exception {
        try {
            DataAccessManager.getInstance().getZoneDB().save(zone);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IZone zone) throws Exception {
        try {
            DataAccessManager.getInstance().getZoneDB().update(zone);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IZone zone) throws Exception {
        try {
            DataAccessManager.getInstance().getZoneDB().delete(zone);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllZone() {
        return DataAccessManager.getInstance().getZoneDB().findAll();
    }

    public static IZone FindZoneByID(Integer id) {
        return DataAccessManager.getInstance().getZoneDB().findById(id);
    }
}
