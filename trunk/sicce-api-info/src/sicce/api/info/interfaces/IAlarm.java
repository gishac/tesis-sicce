/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Set;
import sicce.api.info.ConstantsProvider.AlarmType;
import sicce.api.info.ConstantsProvider.ScheduleType;

/**
 *
 * @author gish@c
 */
public interface IAlarm {

    Integer getIdAlarm();
    void setIdAlarm(Integer idAlarm);
    Integer getScheduleType();
    void setScheduleType(Integer scheduleType);
    ScheduleType getScheduleTypeEnum();
    void setScheduleTypeEnum(ScheduleType scheduleType);
    Integer getAlarmType();
    void setAlarmType(Integer alarmType);
    AlarmType getAlarmTypeEnum();
    void setAlarmTypeEnum(AlarmType alarmType);
    String getDescription();
    void setDescription(String description);
    Set<IScheduleDay> getScheduledDays();
    void setScheduledDays(Set<IScheduleDay> scheduledDays);
    Set<IUserSicce> getAlarmUsers();
    void setAlarmUsers(Set<IUserSicce> alarmUsers);
    Integer getMaxValueAllowed();
    void setMaxValueAllowed(Integer maxValueAllowed);
    boolean IsActive();
    void RegisterAlarmListener(IAlarmListener alarmListener);
    Set<IPowerMeter> getAlarmPowerMeters();
    void setAlarmPowerMeters(Set<IPowerMeter> alarmPowerMeters);
}
