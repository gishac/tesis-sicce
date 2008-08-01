/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.Set;
import sicce.api.info.interfaces.IScheduleDay;

/**
 *
 * @author gish@c
 */
public class ScheduledDayDB {

    public static void Save(Set<IScheduleDay> schedules) throws Exception {
        try {
            for (IScheduleDay schedule : schedules) {
                Save(schedule);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Save(IScheduleDay schedule) throws Exception {
        try {

            DataAccessManager.getInstance().getScheduleDayDB().save(schedule);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(Set<IScheduleDay> schedules) throws Exception {
        try {
            for (IScheduleDay schedule : schedules) {
                DataAccessManager.getInstance().getScheduleDayDB().attachDirty(schedule);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IScheduleDay schedule) throws Exception {
        try {
            DataAccessManager.getInstance().getScheduleDayDB().update(schedule);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IScheduleDay schedule) throws Exception {
        try {
            DataAccessManager.getInstance().getScheduleDayDB().delete(schedule);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
