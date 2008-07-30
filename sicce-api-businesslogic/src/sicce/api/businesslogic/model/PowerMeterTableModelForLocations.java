/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.model;

import java.util.List;
import java.util.Set;
import sicce.api.businesslogic.LocationBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterTableModelForLocations extends SicceTableModel<IPowerMeter> {

    private LocationBizObject locationBizObject;
    private ILocation location;
    
    public PowerMeterTableModelForLocations(List<IPowerMeter> dataSource, ILocation location) {
        if (location == null) {
            this.location = ClassFactory.getLocationInstance();
        } else {
            this.location = location;
        }
        this.dataSource = dataSource;
        columns = new String[]{"Descripción","Estado"};
        locationBizObject = new LocationBizObject();
    }
    
     @Override
    public void setDataSource(List dataSource) {
        if (dataSource == null) {
            this.location = ClassFactory.getLocationInstance();
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
                AddPowerMeterToLocation(option, location);
            }
            if (!checked) {
                RemovePowerMeter(option, location);
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IPowerMeter powerMeter = getDataSource().get(rowIndex);
        boolean state = false;
        for (IPowerMeter activePowerMeter : location.getPowerMeters()) {
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
     * Agrega un medidor a la dependencia
     * @param powerMeter
     * @param alarm
     */
    private void AddPowerMeterToLocation(IPowerMeter powerMeter, ILocation location) {
        if (!locationBizObject.PowerMeterExists(powerMeter.getSerial(), location)) {
            location.addLocationPowerMeter(powerMeter);
        }
    }

    /**
     * Elimina un medidor del monitoreo del usuario
     * @param powerMeter
     * @param alarm
     */
    private void RemovePowerMeter(IPowerMeter powerMeter, ILocation location) {
        for (IPowerMeter powerMeterInAlarm : location.getPowerMeters()) {
            if (powerMeterInAlarm.getSerial().equals(powerMeter.getSerial())) {
                powerMeter = powerMeterInAlarm;
                break;
            }
        }
        location.removeLocationPowerMeter(powerMeter);
    }

    /**
     * Retorna la coleccion de medidores asignados
     * @return
     */
    public Set<IPowerMeter> getPowerMeters() {
        return this.location.getPowerMeters();
    }
    
    private boolean IsAssigned(IPowerMeter powerMeter) {
        if (powerMeter.getLocations().size() > 0) {
            ILocation locationAssigned = powerMeter.getLocations().iterator().next();
            if (location == null || !locationAssigned.getID().equals(location.getID())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
}
