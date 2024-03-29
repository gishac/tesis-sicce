/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que estan a la expectativa de las alarmas
 * @author gish@c
 */
public interface IAlarmListener {

    /**
     * Metodo que ejecuta la accion que se realiza cuando la alarma es activada
     * @param alarm Alarma que fue activada
     * @param powerMeter Medidor que activo la alarma
     */
    void actionPerformed(IAlarm alarm, IPowerMeter powerMeter);
    
    /**
     * Verifica si la alarma debe ser ejecutada 
     * @param measure Medicion realizada
     * @param alarm Alarma a verificar
     * @param powerMeter Medidor sobre el que se realizo la medicion
     */
    void ValidateAlarm(IMeasure measure, IAlarm alarm, IPowerMeter powerMeter);
    
}
