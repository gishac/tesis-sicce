/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import java.util.Set;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IZone;

/**
 *
 * @author gish@c
 */
public class LocationZoneTableModel extends SicceTableModel<IOptionSicce> {

    ZoneBizObject zoneBizObject;
    private IZone zone;

    /**
     * Constructor
     * @param optionsList Lista de opciones disponibles
     * @param role Rol al cual van a ser asignados los permisos
     */
    public LocationZoneTableModel(List<IOptionSicce> dataSource, IZone zone) {

        if (zone == null) {
            this.zone = ClassFactory.getZoneInstance();
        } else {
            this.zone = zone;
        }
        this.columns = new String[]{"Ubicación", "Estado"};
        this.dataSource = dataSource;
        this.zoneBizObject= new ZoneBizObject();
    }

    @Override
    public int getRowCount() {
        return getDataSource().size();
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
            IOptionSicce option = getDataSource().get(rowIndex);
            if (checked) {                
                AddLocationsToZone(option, zone);
            }
            if(!checked){
                RemoveLocation(option, zone);
            }
        }
    }

    @Override
    public void setDataSource(List dataSource) {
        if(dataSource == null)
            this.zone = ClassFactory.getZoneInstance();
    }
    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return isReadOnly();
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IOptionSicce option = getDataSource().get(rowIndex);
        boolean state = false;
        for (IOptionSicce activeOption : zone.getLocationsZone()) {
            if (option.getID() == activeOption.getID()) {
                state = true;
                break;
            }
        }
        switch (columnIndex) {
            case 0:
                return option.getDescription();
            case 1:
                return state;
            default:
                return null;
        }
    }

    /**
     * Retorna la coleccion de ubicaciones asignadas
     * @return
     */
    public Set<IOptionSicce> getLocationsZone() {
        return this.zone.getLocationsZone();
    }
    
    /**
     * Agrega un permiso a un rol
     * @param permission
     * @param role
     */
    private void AddLocationsToZone(IOptionSicce location, IZone zone) {
        if (!zoneBizObject.LocationsExists(location.getID(), zone)) {
            zone.addLocations(location);
        }
    }
    
    /**
     * Elimina un permiso de un rol
     * @param permission
     * @param role
     */
    private void RemoveLocation(IOptionSicce location, IZone zone){
        for(IOptionSicce locationsInZone : zone.getLocationsZone())
        {
            if(locationsInZone.getID() == location.getID()){
                location = locationsInZone;
                break;
            } 
        }
        zone.removeLocations(location);
    }

}
