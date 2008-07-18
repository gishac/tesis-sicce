/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterDB {

    public static void Save(IPowerMeter powerMeter) throws Exception {
        try {
            DataAccessManager.getInstance().getPowerMeterDB().save(powerMeter);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IPowerMeter powerMeter) throws Exception {
        try {
            DataAccessManager.getInstance().getPowerMeterDB().update(powerMeter);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IPowerMeter powerMeter) throws Exception {
        try {
            DataAccessManager.getInstance().getPowerMeterDB().delete(powerMeter);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllPowerMeter() {
        return DataAccessManager.getInstance().getPowerMeterDB().findAll();
    }
    
    public static List GetActivePowerMeter() {
        return DataAccessManager.getInstance().getPowerMeterDB().findByStatus2(new Byte((byte)1));
    }
}
