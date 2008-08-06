package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IZone;


/**
 * Representacion de las zonas del sistema
 * @author gish@c
 */
public abstract class AbstractZone implements java.io.Serializable, IZone {

    // Fields
    
    /**
     * Identificador de la zona
     */
    protected Integer idZone;
    
    /**
     * Codigo de la zona
     */
    protected String code;
    
    /**
     * Descripcion de la zona
     */
    protected String description;
    
    /**
     * Dependencias de la zona
     */
    protected Set<ILocation> locations = new HashSet<ILocation>(0);

    // Constructors
    /**
     * Constructor
     */
    public AbstractZone() {
    }

    // Property accessors
    public Integer getIdZone() {
        return this.idZone;
    }

    public void setIdZone(Integer idZone) {
        this.idZone = idZone;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ILocation> getLocations() {
        return this.locations;
    }

    public void setLocations(Set<ILocation> locations) {
        this.locations = locations;
    }

    public void addLocations(ILocation locationsInZone) {
        locations.add(locationsInZone);
    }

    public void removeLocations(ILocation locationsInZone) {
        locations.remove(locationsInZone);
    }
}
