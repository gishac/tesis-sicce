/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.io.Serializable;
import java.util.Calendar;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IScheduleDay;

/**
 * Clases que representa a los dias de agenda de alarmas
 * @author gish@c
 */
public class AbstractScheduleDay implements Serializable, IScheduleDay {

    /**
     * Identificador del dia de agendamiento
     */
    protected Integer idScheduleDay;
    
    /**
     * Alarma que tiene asignado el dia
     * @see IAlarm
     */
    protected IAlarm alarm;
    
    /**
     * Dia de agendamiento
     */
    protected Integer dayScheduled;
    
    /**
     * Hora de inicio de la alarma
     */
    protected Integer startTime;
    
    /**
     * Hora de fin da la alarma
     */
    protected Integer endTime;

    // Constructors
    
    /**
     * Constructor
     */
    public AbstractScheduleDay() {
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
