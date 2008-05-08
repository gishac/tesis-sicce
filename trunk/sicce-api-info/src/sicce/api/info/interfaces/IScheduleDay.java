/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;


/**
 *
 * @author gish@c
 */
public interface IScheduleDay {

    Integer getIdScheduleDay();
    void setIdScheduleDay(Integer idScheduleDay);
    IAlarm getAlarm();
    void setAlarm(IAlarm alarm);
    Integer getDayScheduled();
    void setDayScheduled(Integer dayScheduled);
    Integer getStartTime();
    void setStartTime(Integer startTime);
    Integer getEndTime();
    void setEndTime(Integer endTime);
    boolean IsActive();
}
