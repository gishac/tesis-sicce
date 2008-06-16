package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.ILocationType;

/**
 * Representacion de los tipos de dependencia
 * @author gish@c
 */
public abstract class AbstractLocationType implements java.io.Serializable, ILocationType {

    // Fields
    
    
    /**
     * Identificador del tipo de dependencia 
     */
    protected Integer idLocationType;
    
    /**
     * Descripcion del tipo de dependencia
     */
    protected String description;
    
    /**
     * Dependencias del tipo actual
     */
    protected Set<ILocation> locations = new HashSet<ILocation>(0);

    // Constructors
    /**
     * Constructor
     */
    public AbstractLocationType() {
    }

    /** minimal constructor */
    public AbstractLocationType(Integer idLocationType,
            String description) {
        this.idLocationType = idLocationType;
        this.description = description;
    }

    /** full constructor */
    public AbstractLocationType(Integer idLocationType,
            String description, Set<ILocation> locations) {
        this.idLocationType = idLocationType;
        this.description = description;
        this.locations = locations;
    }

    // Property accessors
    public Integer getIdLocationType() {
        return this.idLocationType;
    }

    public void setIdLocationType(Integer idLocationType) {
        this.idLocationType = idLocationType;
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
}
