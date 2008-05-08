/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IAlarm;

/**
 *
 * @author gish@c
 */
public class AlarmDB {

    public static void Save(IAlarm alarm) throws Exception {
        try {
            DataAccessManager.getInstance().getAlarmDB().save(alarm);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IAlarm alarm) throws Exception {
        try {
            DataAccessManager.getInstance().getAlarmDB().update(alarm);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IAlarm alarm) throws Exception {
        try {
            DataAccessManager.getInstance().getAlarmDB().delete(alarm);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllAlarms() {
        return DataAccessManager.getInstance().getAlarmDB().findAll();
    }
    
    public static IAlarm FindAlarmByID(Integer id) {
        return DataAccessManager.getInstance().getAlarmDB().findById(id);
    }
    
}
