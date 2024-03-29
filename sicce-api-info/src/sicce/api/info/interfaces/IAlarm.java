/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Set;
import sicce.api.info.ConstantsProvider.AlarmType;

/**
 * Define los metodos a ser implementados por las clases 
 * que representen a las alarmas
 * 
 * @author gish@c
 */
public interface IAlarm {

    /**
     * Devuelve el identificador de la alarma
     * @return El identificador de la alarma
     */
    Integer getIdAlarm();
    
    /**
     * Establece el identificador de la alarma
     * @param idAlarm Identificador de la alarma
     */
    void setIdAlarm(Integer idAlarm);
    
        
    /**
     * Devuelve la descripcion de la alarma
     * @return Descripcion de la alarma
     */
    String getDescription();
    
    /**
     * Establece la descripcion de la alarma
     * @param description Descripcion de la alarma
     */
    void setDescription(String description);
    
    /**
     * Devuelve los dias en los que se agendo la alarma
     * @return Dias en los que se agendo la alarma
     * @see IScheduleDay
     */
    Set<IScheduleDay> getScheduledDays();
    
    /**
     * Estable los dias en los que se agendara la alarma
     * @param scheduledDays Dias en los que se agendara la alarma
     * @see IScheduleDay
     */
    void setScheduledDays(Set<IScheduleDay> scheduledDays);
    
    /**
     * Devuelve los usuarios suscritos a la alarma
     * @return Usuarios suscritos a la alarma
     * @see IUserSicce
     */
    Set<IUserSicce> getAlarmUsers();
    
    /**
     * Establece los usuarios suscritos a la alarma
     * @param alarmUsers Usuarios suscritos a la alarma
     * @see IUserSicce
     */
    void setAlarmUsers(Set<IUserSicce> alarmUsers);
    
    /**
     * Devuelve el valor de maximo del parametro configurado en la alarma
     * la alarma
     * @return Valor de maximo del parametro configurado en la alarma
     */
    Integer getMaxValueAllowed();
    
    /**
     * Establece el valor maximo permitido del parametro configurado en la alarma
     * @param maxValueAllowed Valor maximo permitido del parametro configurado en la alarma
     */
    void setMaxValueAllowed(Integer maxValueAllowed);
    
    /**
     * Devuelve el valor de maximo del parametro configurado en la alarma
     * la alarma
     * @return Valor de maximo del parametro configurado en la alarma
     */
    Integer getMinValueAllowed();
    
    /**
     * Establece el valor minimo permitido del parametro configurado en la alarma
     * @param minValueAllowed Valor minimo permitido del parametro configurado en la alarma
     */
    void setMinValueAllowed(Integer minValueAllowed);
    
    /**
     * Devuelve el registro que va a ser monitoreado por la alarma
     * @return Registro que va a ser monitoreado por la alarma
     */
    Integer getMeasure();
    
    /**
     * Establece el registro que va a ser monitoreado por la alarma
     * @param measure Registro que va a ser monitoreado por la alarma
     */
    void setMeasure(Integer measure);
    
    /**
     * Indica si la alarma se encuentra activa
     * @return <strong>True</strong> si esta activa, <strong>False</strong> si no esta activa
     */
    boolean IsActive();
    
    /**
     * Establece el objeto que tomara la accion correspondiente al activarse la alarma
     * @param alarmListener Objecto que hara las acciones correspondientes al activarse la alarma
     * @see IAlarmListener
     */
    void RegisterAlarmListener(IAlarmListener alarmListener);
    
    /**
     * Devuelve los medidores que van a ser monitoreados por la alarma
     * @return Medidores monitoreados por la alarma
     * @see IPowerMeter
     */
    Set<IPowerMeter> getAlarmPowerMeters();
    
    /**
     * Establece los medidores a ser monitoreados por la alarma
     * @param alarmPowerMeters Medidores monitoreados por la alarma
     * @see IPowerMeter
     */
    void setAlarmPowerMeters(Set<IPowerMeter> alarmPowerMeters);
    
    /**
     * Agrega un medidor a ser monitoreado por la alarma
     * @param powerMeter Medidor a ser monitoreado por la alarma
     * @see IPowerMeter
     */
    void addAlarmPowerMeter(IPowerMeter powerMeter);
    
    /**
     * Remueve un medidor del monitoreo de la alarma
     * @param powerMeter Medidor a ser removido
     * @see IPowerMeter
     */
    void removeAlarmPowerMeter(IPowerMeter powerMeter);
    
    /**
     * Agrega un usuario que sera informado cuando la alarma se active
     * @param user Usuario a ser informado de la activacion de la alarma
     * @see IUserSicce
     */
    void addAlarmUser(IUserSicce user);    
    
    /**
     * Remueve un usuario para evitar ser informado de la activacion de la alarma     * 
     * @param user Usuario a ser removido
     * @see IUserSicce
     */
    void removeAlarmUser(IUserSicce user);
    
    /**
     * Devuelve la descripcion del registro que va a ser monitoreado por la alarma
     * @return Descripcion del registro que va a ser monitoreado por la alarma
     */
    String getMeasureDescription();
    
    /**
     * Establece la descripcion del registro que va a ser monitoreado por la alarma
     * @param measureDescription Descripcion del registro que va a ser monitoreado por la alarma
     */
    void setMeasureDescription(String measureDescription);
}
