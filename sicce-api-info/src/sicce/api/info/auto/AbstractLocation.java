package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.ILocationType;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.info.interfaces.IZone;

/**
 * Representacion de las dependencias para organizar la division de los sectores y la ubicacion de los medidores
 * @author gish@c
 */
public abstract class AbstractLocation implements java.io.Serializable, ILocation {

    /**
     * Identificador de la dependencia
     */
    protected Integer idLocation;
    /**
     * Tipo de dependencia
     * @see ILocationType
     */
    protected ILocationType locationType;
    /**
     * Ubicacion de la dependencia
     * @see ILocation
     */
    protected ILocation location;
    
    /**
     * Descripcion de la dependencia
     */
    protected String description;
    /**
     * Dependencias agrupadas dentro de esta ubicacion
     * @see ILocation
     */
    protected Set<ILocation> locations = new HashSet<ILocation>(0);
    /**
     * Zonas logicas que agrupan a la dependencia
     * @see IZone
     */
    protected Set<IZone> zones = new HashSet<IZone>(0);
    /**
     * Mediciones realizadas sobre la dependencia
     * @see IMeasure
     */
    protected Set<IMeasure> measures = new HashSet<IMeasure>(0);
    
    /**
     * Medidores asignados a la dependencia
     */
    protected Set<IPowerMeter> powerMeters = new HashSet<IPowerMeter>(0);
    
   


    // Constructors
    /**
     * Constructor
     */
    public AbstractLocation() {
    }

    /**
     * Constructor
     * @param idLocation Identificador de la dependencia
     * @param locationType Tipo de dependenca
     * @param location Ubicacion de la dependencia
     * @param description Descripcion de la dependencia
     */
    public AbstractLocation(Integer idLocation, ILocationType locationType,
            ILocation location, String description) {
        this.idLocation = idLocation;
        this.locationType = locationType;
        this.location = location;
        this.description = description;
    }

    /**
     * Constructor
     * @param idLocation Identificador de la dependencia
     * @param locationType Tipo de dependencia
     * @param location Ubicacion de la dependencia
     * @param description Descripcion de la dependencia
     * @param locations Dependencias agrupadas dentro de esta ubicacion
     * @param zones Zonas logicas que agrupan a la dependencia
     * @param measures Mediciones realizadas sobre la dependencia
     */
    public AbstractLocation(Integer idLocation, ILocationType locationType,
            ILocation location, String description,
            Set<ILocation> locations, Set<IZone> zones,
            Set<IMeasure> measures) {
        this.idLocation = idLocation;
        this.locationType = locationType;
        this.location = location;
        this.description = description;
        this.locations = locations;
        this.zones = zones;
        this.measures = measures;
    }

    // Property accessors
    public Integer getID() {
        return this.idLocation;
    }

    public Integer getIdLocation() {
        return this.idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public ILocationType getLocationType() {
        return this.locationType;
    }

    public void setLocationType(ILocationType locationType) {
        this.locationType = locationType;
    }

    public ILocation getLocation() {
        return this.location;
    }

    public void setLocation(ILocation location) {
        this.location = location;
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

    public Set<IZone> getZones() {
        return this.zones;
    }

    public void setZones(Set<IZone> zones) {
        this.zones = zones;
    }

    public Set<IMeasure> getMeasures() {
        return this.measures;
    }

    public void setMeasures(Set<IMeasure> measures) {
        this.measures = measures;
    }
    
    public Set<IPowerMeter> getPowerMeters() {
        return this.powerMeters;
    }

    public void setPowerMeters(Set<IPowerMeter> powerMeters) {
        this.powerMeters = powerMeters;
    }
    
    
}
