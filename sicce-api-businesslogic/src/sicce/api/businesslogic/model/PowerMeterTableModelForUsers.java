/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.List;
import java.util.Set;
import sicce.api.businesslogic.UserBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class PowerMeterTableModelForUsers extends SicceTableModel<IPowerMeter> {

    private UserBizObject userBizObject;
    private IUserSicce user;

    public PowerMeterTableModelForUsers(List<IPowerMeter> dataSource, IUserSicce user) {
        if (user == null) {
            this.user = ClassFactory.getUserInstance();
        } else {
            this.user = user;
        }
        this.dataSource = dataSource;
        columns = new String[]{"Descripción", "Estado"};
        userBizObject = new UserBizObject();
    }

    @Override
    public void setDataSource(List dataSource) {
        if (dataSource == null) {
            this.user = ClassFactory.getUserInstance();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            boolean result = !isReadOnly();
            result &= !IsAssigned(getRow(rowIndex));
            return result;
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
            if (getDataSource().size() <= rowIndex) {
                return;
            }
            boolean checked = Boolean.parseBoolean(value.toString());
            IPowerMeter option = getDataSource().get(rowIndex);
            if (checked) {
                AddPowerMeterToUser(option, user);
            }
            if (!checked) {
                RemovePowerMeter(option, user);
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IPowerMeter powerMeter = getDataSource().get(rowIndex);
        boolean state = false;
        for (IPowerMeter activePowerMeter : user.getPowerMeters()) {
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
     * Agrega un medidor a ser monitoreado por el usuario
     * @param powerMeter
     * @param alarm
     */
    private void AddPowerMeterToUser(IPowerMeter powerMeter, IUserSicce user) {
        if (!userBizObject.PowerMeterExists(powerMeter.getSerial(), user)) {
            user.addUserPowerMeter(powerMeter);
        }
    }

    /**
     * Elimina un medidor del monitoreo del usuario
     * @param powerMeter
     * @param alarm
     */
    private void RemovePowerMeter(IPowerMeter powerMeter, IUserSicce user) {
        for (IPowerMeter powerMeterInAlarm : user.getPowerMeters()) {
            if (powerMeterInAlarm.getSerial().equals(powerMeter.getSerial())) {
                powerMeter = powerMeterInAlarm;
                break;
            }
        }
        user.removeUserPowerMeter(powerMeter);
    }

    /**
     * Retorna la coleccion de medidores asignados
     * @return
     */
    public Set<IPowerMeter> getPowerMeters() {
        return this.user.getPowerMeters();
    }
    
    private boolean IsAssigned(IPowerMeter powerMeter) {
        if (powerMeter.getUsers().size() > 0) {
            IUserSicce userAssigned = powerMeter.getUsers().iterator().next();
            if (user == null || !userAssigned.getID().equals(user.getID())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
