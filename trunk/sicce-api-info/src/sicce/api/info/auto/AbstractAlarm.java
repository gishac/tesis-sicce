/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;
import sicce.api.info.ConstantsProvider.AlarmType;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IAlarmListener;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IScheduleDay;
import sicce.api.info.interfaces.IUserSicce;

/**
 * Representacion de las alarmas a ser usadas por sistema, para el control de los medidores
 * @author gish@c
 */
public abstract class AbstractAlarm implements IAlarm {

    /**
     * Identificador de la alarma
     */
    protected Integer idAlarm;
    
    /**
     * Tipo de alarma
     */
    protected Integer alarmType;
    
    /**
     * Descripcion de la alarma
     */
    protected String description;
    
    /**
     * Dias de agendamiento de la alarma
     * @see IScheduleDay
     */
    protected Set<IScheduleDay> scheduledDays = new HashSet(0);
    
    /**
     * Usuarios suscritos a la alarma
     * @see IUserSicce
     */
    protected Set<IUserSicce> alarmUsers = new HashSet(0);
    
    /**
     * Medidores a ser monitoreados por la alarma
     * @see IPowerMeter
     */
    protected Set<IPowerMeter> alarmPowerMeters = new HashSet(0);

    /**
     * Objecto que ejecutara las acciones respectivas cuando se activa la alarma
     * @see IAlarmListener
     */
    protected IAlarmListener alarmListener;
    
    /**
     * Valor maximo permitido 
     */
    protected Integer maxValueAllowed;
    
    /**
     * Valor minimo permitido
     */
    private Integer minValueAllowed;
    
    /**
     * Parametro a monitorear
     */
    private String measure;
    

    // Constructors
    
    /**
     * Constructor base
     */
    public AbstractAlarm() {
    }

    /**
     * Constructor
     * @param idAlarm Identificador de la alarma
     */
    public AbstractAlarm(Integer idAlarm) {
        this.idAlarm = idAlarm;
    }

    /**
     * Constructor
     * @param idAlarm Identificador de la alarma
     * @param alarmType Tipo de alarma
     * @param description Descripcion de la alarma
     * @param scheduledDays Dias de agendamiento
     * @param alarmUsers Usuarios suscritos a la alarma
     */
    public AbstractAlarm(Integer idAlarm,
            int alarmType, String description, Set scheduledDays,
            Set alarmUsers) {
        this.idAlarm = idAlarm;
        this.alarmType = alarmType;
        this.description = description;
        this.scheduledDays = scheduledDays;
        this.alarmUsers = alarmUsers;
    }

    // Property accessors
    public Integer getIdAlarm() {
        return this.idAlarm;
    }

    public void setIdAlarm(Integer idAlarm) {
        this.idAlarm = idAlarm;
    }

    public Integer getAlarmType() {
        return this.alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<IScheduleDay> getScheduledDays() {
        return this.scheduledDays;
    }

    public void setScheduledDays(Set<IScheduleDay> scheduledDays) {
        this.scheduledDays = scheduledDays;
    }

    public Set<IUserSicce> getAlarmUsers() {
        return this.alarmUsers;
    }

    public void setAlarmUsers(Set<IUserSicce> alarmUsers) {
        this.alarmUsers = alarmUsers;
    }

    public AlarmType getAlarmTypeEnum() {
        if(getAlarmType().intValue() == 1)
            return AlarmType.Mail;
        return AlarmType.SMS;
    }

    public void setAlarmTypeEnum(AlarmType alarmType) {
        setAlarmType(alarmType.getAlarmType());
    }

    public boolean IsActive() {
        for (IScheduleDay scheduledDay : getScheduledDays()) {
            if (scheduledDay.IsActive()) {
                return true;
            }
        }
        return false;
    }
    
    public Integer getMaxValueAllowed() {
        return maxValueAllowed;
    }

    public void setMaxValueAllowed(Integer maxValueAllowed) {
        this.maxValueAllowed = maxValueAllowed;
    }

    public void RegisterAlarmListener(IAlarmListener alarmListener) {
        this.alarmListener = alarmListener;
    }
    
    public Set<IPowerMeter> getAlarmPowerMeters() {
        return alarmPowerMeters;
    }

    public void setAlarmPowerMeters(Set<IPowerMeter> alarmPowerMeters) {
        this.alarmPowerMeters = alarmPowerMeters;
    }
    
    public void addAlarmPowerMeter(IPowerMeter powerMeter) {
        this.alarmPowerMeters.add(powerMeter);
    }

    public void removeAlarmPowerMeter(IPowerMeter powerMeter) {
        this.alarmPowerMeters.remove(powerMeter);
    }

    public void addAlarmUser(IUserSicce user) {
        this.alarmUsers.add(user);
    }

    public void removeAlarmUser(IUserSicce user) {
        this.alarmUsers.remove(user);
    }

    public Integer getMinValueAllowed() {
        return minValueAllowed;
    }

    public void setMinValueAllowed(Integer minValueAllowed) {
        this.minValueAllowed = minValueAllowed;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

}
