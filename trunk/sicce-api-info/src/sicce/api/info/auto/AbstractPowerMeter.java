package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 * Representacion de los medidores del sistema
 * @author gish@c
 */
public abstract class AbstractPowerMeter implements java.io.Serializable, IPowerMeter {

    // Fields
    
    /**
     * Identificador del medidor
     */
    protected Integer idPowerMeter;
    
    /**
     * Descripcion del medidor
     */
    protected String description;
    
    /**
     * Direccion ip asignada al medidor
     */
    protected String ipAddress;
    
    /**
     * Codigo serial del medidor
     */
    protected String serial;
    
    /**
     * Identificador del medidor
     */
    protected String deviceID;
    
    /**
     * Mediciones realizadas en el medidor
     */
    protected Set<IMeasure> measures = new HashSet<IMeasure>(0);
    
    /**
     * Alarmas asignadas al medidor
     */
    protected Set<IAlarm> alarms = new HashSet<IAlarm>(0);
    
    /**
     * Ubicacion que tiene asignada el medidor
     */
    protected Set<ILocation> locations = new HashSet<ILocation>(0);
    
    /**
     * Usuario que tiene asignado el medidor
     */
    protected Set<IUserSicce> users = new HashSet<IUserSicce>(0);
    

    // Constructors
    
    /**
     * Constructor
     */
    public AbstractPowerMeter() {
    }

   
    // Property accessors
    public Integer getIdPowerMeter() {
        return this.idPowerMeter;
    }

    public void setIdPowerMeter(Integer idPowerMeter) {
        this.idPowerMeter = idPowerMeter;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

  
    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Set<IMeasure> getMeasures() {
        return this.measures;
    }

    public void setMeasures(Set<IMeasure> measures) {
        this.measures = measures;
    }
    
    public Set<IAlarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(Set<IAlarm> alarms) {
        this.alarms = alarms;
    }
    
    public void setDeviceID(String deviceID){
        this.deviceID = deviceID;
    }
    
    public String getDeviceID(){
        return this.deviceID;
    }
   
    public Set<ILocation> getLocations() {
        return locations;
    }

    public void setLocations(Set<ILocation> locations) {
        this.locations = locations;
    }
    
    public Set<IUserSicce> getUsers() {
        return users;
    }

    public void setUsers(Set<IUserSicce> users) {
        this.users = users;
    }
            
}
