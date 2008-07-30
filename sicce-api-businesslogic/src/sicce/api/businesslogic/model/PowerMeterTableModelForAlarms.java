/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.model;

import sicce.api.businesslogic.*;
import sicce.api.businesslogic.factory.ClassFactory;
import java.util.List;
import java.util.Set;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterTableModelForAlarms extends SicceTableModel<IPowerMeter> {

    private AlarmBizObject alarmBizObject;
    private IAlarm alarm;
    
    public PowerMeterTableModelForAlarms(List<IPowerMeter> dataSource, IAlarm alarm) {
        if (alarm == null) {
            this.alarm = ClassFactory.getAlarmInstance();
        } else {
            this.alarm = alarm;
        }
        this.dataSource = dataSource;
        columns = new String[]{"Descripción","Estado"};
        alarmBizObject = new AlarmBizObject();
    }
    
    
     @Override
    public void setDataSource(List dataSource) {
        if(dataSource == null)
            this.alarm = ClassFactory.getAlarmInstance();
    }
     
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return !isReadOnly();
        }
        return false;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        synchronized (this) {
            if(getDataSource().size() <= rowIndex)
                return;
            boolean checked = Boolean.parseBoolean(value.toString());
            IPowerMeter option = getDataSource().get(rowIndex);
            if (checked) {                
                AddPowerMeterToAlarm(option, alarm);
            }
            if(!checked){
                RemovePowerMeter(option, alarm);
            }
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IPowerMeter powerMeter = getDataSource().get(rowIndex);
        boolean state = false;
        for (IPowerMeter activePowerMeter : alarm.getAlarmPowerMeters()) {
            if (powerMeter.getSerial().equals(activePowerMeter.getSerial())) {
                state = true;
                break;
            }
        }
        switch (columnIndex) {
            case 0:
                return powerMeter.getDescription();
            case 1:
                return state;
            default:
                return null;
        }
    }
    
    /**
     * Agrega un medidor a ser monitoreado por la alarma
     * @param powerMeter
     * @param alarm
     */
    private void AddPowerMeterToAlarm(IPowerMeter powerMeter, IAlarm alarm) {
        if (!alarmBizObject.PowerMeterExists(powerMeter.getSerial(), alarm)) {
            alarm.addAlarmPowerMeter(powerMeter);
        }
    }
    
    /**
     * Elimina un medidor del monitoreo de la alarma
     * @param powerMeter
     * @param alarm
     */
    private void RemovePowerMeter(IPowerMeter powerMeter, IAlarm alarm){
        for(IPowerMeter powerMeterInAlarm : alarm.getAlarmPowerMeters())
        {
            if(powerMeterInAlarm.getSerial().equals(powerMeter.getSerial())){
                powerMeter = powerMeterInAlarm;
                break;
            } 
        }
        alarm.removeAlarmPowerMeter(powerMeter);
    }
    
    /**
     * Retorna la coleccion de medidores asignados
     * @return
     */
    public Set<IPowerMeter> getPowerMeters() {
        return this.alarm.getAlarmPowerMeters();
    }
    
}
