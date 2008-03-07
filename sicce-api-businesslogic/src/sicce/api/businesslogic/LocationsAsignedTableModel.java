/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.Set;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IZone;

/**
 *
 * @author gish@c
 */
public class LocationsAsignedTableModel extends SicceTableModel<ILocation> {

    ZoneBizObject zoneBizObject;
    private IZone zone;

    /**
     * Constructor
     * @param optionsList Lista de opciones disponibles
     * @param role Rol al cual van a ser asignados los permisos
     */
    public LocationsAsignedTableModel(Set<ILocation> dataSource, IZone zone) {

        super(dataSource);
        int c = getDataSource().size();
        if (zone == null) {
            this.zone = ClassFactory.getZoneInstance();
        } else {
            this.zone = zone;
        }
        this.columns = new String[]{"No.", "Zona", "Ubicación Asignada"};
        
        this.zoneBizObject = new ZoneBizObject();
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
                return String.class;
            case 2:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        synchronized (this) {
            if(getDataSource().size() <= rowIndex)
                return;
           
//            IZone pzone = (IZone)value;
//            
//            ILocation location = getDataSource().get(rowIndex);
//            if (pzone != null) {                
//                AddLocationToZone(location, pzone);
//            }
//                     
            
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        ILocation location = getDataSource().get(rowIndex);
        
//        for (ILocation activeLocation : zone.getLocationsInZone()) {
//            if (location.getID() == activeLocation.getID()) {
//                state = true;
//                break;
//            }
//        }
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return location.getDescription();
            case 2:
                return zone.getDescription();
            default:
                return null;
        }
    }

    /**
     * Retorna la coleccion de ubicaciones asignadas
     * @return
     */
    public Set<ILocation> getLocationsZone() {
        return this.zone.getLocationsInZone();
    }
    
    /**
     * Agrega una ubicación a una zona especifica
     * @param locationAsigned
     * @param zone
     */
     public void AddLocationToZone(ILocation locationAsigned, IZone zone) {
       if (!zoneBizObject.LocationsExists(locationAsigned.getID(), zone)) {
            zone.addLocations(locationAsigned);
      }
    }
    
    /**
     * Elimina  una ubicación a una zona especifica
     * @param locationAsigned
     * @param zone
     */
    public void RemoveLocationZone(ILocation locationAsigned, IZone zone){
        for(ILocation locationInRole : zone.getLocationsInZone())
        {
            if(locationInRole.getID() == locationAsigned.getID()){
                locationAsigned = locationInRole;
                break;
            } 
        }
        zone.removeLocations(locationAsigned);
    }

}
