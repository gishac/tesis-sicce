/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sicce.api.businesslogic.UserBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class PowerMeterTableModelForUsers extends SicceTableModel<IPowerMeter> {

    private IUserSicce user;
    private Map<Integer, IUserPowerMeter> powerMetersForUser;

    public PowerMeterTableModelForUsers(List<IPowerMeter> dataSource, IUserSicce user) {
        if (user == null) {
            this.user = ClassFactory.getUserInstance();
        } else {
            this.user = user;
        }
        this.dataSource = dataSource;
        columns = new String[]{"Descripción", "Asignado", "Monitorear"};
        powerMetersForUser = new HashMap<Integer, IUserPowerMeter>();
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
            return !isReadOnly();
        } else if (columnIndex == 2) {
            boolean isReadOnly = !isReadOnly();
            boolean ismonitored = IsMonitoredByOtherUser(getRow(rowIndex));
            return isReadOnly && !ismonitored;
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
            case 2:
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
            IPowerMeter powerMeter = getDataSource().get(rowIndex);
            switch (columnIndex) {
                case 1:
                    SetPowerMeterAssignment(powerMeter, user, checked);
                    break;
                case 2:
                    SetPowerMeterDoMonitor(powerMeter, user, checked);
                    break;
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IPowerMeter powerMeter = getDataSource().get(rowIndex);
        boolean assigned = IsAssigned(powerMeter);
        boolean monitor = IsMonitored(powerMeter);
        switch (columnIndex) {
            case 0:
                return powerMeter.getDescription();
            case 1:
                return assigned;
            case 2:
                return monitor;
            default:
                return null;
        }
    }

    /**
     * 
     * @param powerMeter
     * @param user
     * @param assign
     */
    private void SetPowerMeterAssignment(IPowerMeter powerMeter, IUserSicce user, boolean assign) {
        IUserPowerMeter userPowerMeter;
        if (powerMetersForUser.containsKey(powerMeter.getIdPowerMeter())) {
            userPowerMeter = powerMetersForUser.get(powerMeter.getIdPowerMeter());
        } else {
            userPowerMeter = ClassFactory.getUserPowerMeterInstance();
            userPowerMeter.setPowerMeter(powerMeter);
            userPowerMeter.setUserSicce(user);
            powerMetersForUser.put(powerMeter.getIdPowerMeter(), userPowerMeter);
        }
        userPowerMeter.setAssigned(assign ? Byte.parseByte("1") : Byte.parseByte("0"));
        boolean exists = false;
        for (IUserPowerMeter item : user.getUserPowerMeters()) {
            if (item.getPowerMeter().getIdPowerMeter().equals(powerMeter.getIdPowerMeter())) {
                item.setAssigned(userPowerMeter.getAssigned());
                exists = true;
                break;
            }
        }
        if (!exists) {
            user.getUserPowerMeters().add(userPowerMeter);
        }
    }

    /**
     * 
     * @param powerMeter
     * @param user
     * @param assign
     */
    private void SetPowerMeterDoMonitor(IPowerMeter powerMeter, IUserSicce user, boolean doMonitor) {
        IUserPowerMeter userPowerMeter;
        if (powerMetersForUser.containsKey(powerMeter.getIdPowerMeter())) {
            userPowerMeter = powerMetersForUser.get(powerMeter.getIdPowerMeter());
        } else {
            userPowerMeter = ClassFactory.getUserPowerMeterInstance();
            userPowerMeter.setPowerMeter(powerMeter);
            userPowerMeter.setUserSicce(user);
            powerMetersForUser.put(powerMeter.getIdPowerMeter(), userPowerMeter);
            
        }
        userPowerMeter.setMonitor(doMonitor ? Byte.parseByte("1") : Byte.parseByte("0"));
        boolean exists = false;
        for (IUserPowerMeter item : user.getUserPowerMeters()) {
            if (item.getPowerMeter().getIdPowerMeter().equals(powerMeter.getIdPowerMeter())) {
                item.setMonitor(userPowerMeter.getMonitor());
                exists = true;
                break;
            }
        }
        if(!exists){
            user.getUserPowerMeters().add(userPowerMeter);
        }
    }

    /**
     * 
     * @return
     */
    public Set<IUserPowerMeter> getPowerMeters() {
        return this.user.getUserPowerMeters();
    }

    /**
     * 
     * @param powerMeter
     * @return
     */
    private boolean IsAssigned(IPowerMeter powerMeter) {
        if (this.user.getUserPowerMeters().size() > 0) {
            for (IUserPowerMeter userPowerMeterInUser : user.getUserPowerMeters()) {
                if (userPowerMeterInUser.getPowerMeter().getIdPowerMeter().equals(powerMeter.getIdPowerMeter())) {
                    if (userPowerMeterInUser.getAssigned() != null) {
                        return userPowerMeterInUser.getAssigned().equals(Byte.valueOf("1"));
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param powerMeter
     * @return
     */
    private boolean IsMonitored(IPowerMeter powerMeter) {
        if (this.user.getUserPowerMeters().size() > 0) {
            for (IUserPowerMeter userPowerMeterInUser : user.getUserPowerMeters()) {
                if (userPowerMeterInUser.getPowerMeter().getIdPowerMeter().equals(powerMeter.getIdPowerMeter())) {
                    if (userPowerMeterInUser.getMonitor() != null) {
                        return userPowerMeterInUser.getMonitor().equals(Byte.valueOf("1"));
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param powerMeter
     * @return
     */
    private boolean IsMonitoredByOtherUser(IPowerMeter powerMeter) {
        if (powerMeter.getUserPowerMeters().size() > 0) {
            for (IUserPowerMeter userPowerMeter : powerMeter.getUserPowerMeters()) {
                if ((userPowerMeter.getPowerMeter().getIdPowerMeter().equals(powerMeter.getIdPowerMeter())) &&
                        (userPowerMeter.getMonitor() != null && userPowerMeter.getMonitor().equals(Byte.valueOf("1")))) {
                    if (!userPowerMeter.getUserSicce().getIdUserSicce().equals(user.getIdUserSicce() != null ? user.getIdUserSicce() : Integer.valueOf("-1"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
