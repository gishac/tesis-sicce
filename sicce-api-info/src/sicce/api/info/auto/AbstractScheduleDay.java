/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.util.Calendar;
import sicce.api.info.Alarm;
import sicce.api.info.interfaces.IAlarm;

/**
 *
 * @author gish@c
 */
public class AbstractScheduleDay {

    protected Integer idScheduleDay;
    protected IAlarm alarm;
    protected Integer dayScheduled;
    protected Integer startTime;
    protected Integer endTime;

    // Constructors
    /** default constructor */
    public AbstractScheduleDay() {
    }

    /** minimal constructor */
    public AbstractScheduleDay(Integer idScheduleDay, Alarm alarm) {
        this.idScheduleDay = idScheduleDay;
        this.alarm = alarm;
    }

    /** full constructor */
    public AbstractScheduleDay(Integer idScheduleDay, Alarm alarm,
            Integer dayScheduled, Integer startTime, Integer endTime) {
        this.idScheduleDay = idScheduleDay;
        this.alarm = alarm;
        this.dayScheduled = dayScheduled;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Property accessors
    public Integer getIdScheduleDay() {
        return this.idScheduleDay;
    }

    public void setIdScheduleDay(Integer idScheduleDay) {
        this.idScheduleDay = idScheduleDay;
    }

    public IAlarm getAlarm() {
        return this.alarm;
    }

    public void setAlarm(IAlarm alarm) {
        this.alarm = alarm;
    }

    public Integer getDayScheduled() {
        return this.dayScheduled;
    }

    public void setDayScheduled(Integer dayScheduled) {
        this.dayScheduled = dayScheduled;
    }

    public Integer getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
    
    public boolean IsActive(){
        boolean isActive = false;
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(getStartTime() > getEndTime()){
            if(currentHour >= getStartTime().intValue() || (currentHour >= 0 && currentHour < getEndTime().intValue()))
                isActive = true;            
        }
        else
        {
            if(currentHour >= getStartTime().intValue() && currentHour < getEndTime().intValue())
                isActive = true;
        }
        return isActive;
    }
}
