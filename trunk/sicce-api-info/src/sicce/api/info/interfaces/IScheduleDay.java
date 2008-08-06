/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;


/**
 * Define los metodos a ser implementados por las clases que representen a los dias de agenda de alarmas
 * @author gish@c
 */
public interface IScheduleDay {

    /**
     * Devuelve identificador del dia de agendamiento
     * @return Identificador del dia de agendamiento
     */
    Integer getIdScheduleDay();
    
    /**
     * Establece el identificador del dia de agendamiento
     * @param idScheduleDay Identificador del dia de agendamiento
     */
    void setIdScheduleDay(Integer idScheduleDay);
    
    /**
     * Devuelve la alarma que tiene asignado el dia
     * @return Alarma que tiene asignado el dia
     */
    IAlarm getAlarm();
    
    /**
     * Establece la alarma que tiene asignado el dia
     * @param alarm Alarma que tiene asignado el dia
     */
    void setAlarm(IAlarm alarm);
    
    /**
     * Devuelve el dia de agendamiento
     * @return Dia de agendamiento
     */
    Integer getDayScheduled();
    
    /**
     * Establece el dia de agendamiento
     * @param dayScheduled Dia de agendamiento
     */
    void setDayScheduled(Integer dayScheduled);
    
    /**
     * Devuelve la hora de inicio de la alarma
     * @return Hora de inicio de la alarma
     */
    Integer getStartTime();
    
    /**
     * Establece la hora de inicio de la alarma
     * @param startTime Hora de inicio de la alarma
     */
    void setStartTime(Integer startTime);
    
    /**
     * Devuelve la hora de fin da la alarma
     * @return Hora de fin da la alarma
     */
    Integer getEndTime();
    
    /**
     * Establece la hora de fin de la alarma
     * @param endTime Hora de fin de la alarma
     */
    void setEndTime(Integer endTime);
 
    /**
     * Devuelve si la alarma esta activa basado en las horas asignadas
     * @return
     */
    boolean IsActive();
}
