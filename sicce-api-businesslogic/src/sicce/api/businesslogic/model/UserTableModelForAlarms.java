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
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserTableModelForAlarms extends SicceTableModel<IUserSicce> {

    private AlarmBizObject alarmBizObject;
    private IAlarm alarm;

    public UserTableModelForAlarms(List<IUserSicce> dataSource, IAlarm alarm) {
        if (alarm == null) {
            this.alarm = ClassFactory.getAlarmInstance();
        } else {
            this.alarm = alarm;
        }
        this.dataSource = dataSource;
        columns = new String[]{"Nombre", "Asignar"};
        alarmBizObject = new AlarmBizObject();
    }

    @Override
    public int getRowCount() {
        return getDataSource().size();
    }

    @Override
    public void setDataSource(List dataSource) {
        if (dataSource == null) {
            this.alarm = ClassFactory.getAlarmInstance();
        }
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
            IUserSicce user = getDataSource().get(rowIndex);
            if (checked) {                
                AddUserToAlarm(user, alarm);
            }
            if(!checked){
                RemoveUserFromAlarm(user, alarm);
            }
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IUserSicce user = getDataSource().get(rowIndex);
        boolean state = false;
        for (IUserSicce activeUser : alarm.getAlarmUsers()) {
            if (user.getIdUserSicce().equals(activeUser.getIdUserSicce())) {
                state = true;
                break;
            }
        }
        switch (columnIndex) {
            case 0:
                return user.getName() + " " + user.getLastname();
            case 1:
                return state;
            default:
                return null;
        }
    }
    

    /**
     * Agrega un usuario a ser notificado por la alarma
     * @param user
     * @param alarm
     */
    private void AddUserToAlarm(IUserSicce user, IAlarm alarm) {
        if (!alarmBizObject.UserExists(user.getIdUserSicce(), alarm)) {
            alarm.addAlarmUser(user);
        }
    }

    /**
     * Remueve un usuario del monitoreo
     * @param user
     * @param alarm
     */
    private void RemoveUserFromAlarm(IUserSicce user, IAlarm alarm) {
        for (IUserSicce userInAlarm : alarm.getAlarmUsers()) {
            if (userInAlarm.getIdUserSicce().equals(user.getIdUserSicce())) {
                user = userInAlarm;
                break;
            }
        }
        alarm.removeAlarmUser(user);
    }
    
     /**
     * Retorna la coleccion de usuarios asignados
     * @return
     */
    public Set<IUserSicce> getPowerMeters() {
        return this.alarm.getAlarmUsers();
    }
}
