/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a las dependencias
 * @author gish@c
 */
public interface ILocation {

    /**
     * Establece la descripcion de la dependencia
     * @param description Descripcion de la dependencia
     */
    void setDescription(String description);
    
    /**
     * Devuelve la descripcion de la dependencia
     * @return
     */
    String getDescription();
    
    /**
     * Devuelve el identificador de la dependencia
     * @return Identificador de la dependencia
     */
    Integer getID();
    
    /**
     * Establece el identificador de la ubicacion de la dependencia
     * @param idLocation Identificador de la ubicacion de la dependencia
     */
    void setIdLocation (Integer idLocation);
    
    /**
     * Devuelve el identificador de la ubicacion de la dependencia
     * @return
     */
    Integer getIdLocation(); 
    
    /**
     * Establece el tipo de dependencia
     * @param locationType Tipo de dependencia
     * @see ILocationType
     */
    void setLocationType (ILocationType locationType);
    
    /**
     * Devuelve el tipo de dependencia
     * @return  Tipo de dependencia
     * @see ILocationType
     */
    ILocationType getLocationType(); 
    
    /**
     * Establece el medidor asignado a la dependencia
     * @param powerMeter Medidor asignado a la dependencia
     * @see IPowerMeter
     */
    void setPowerMeter (IPowerMeter powerMeter);
    
    /**
     * Devuelve el medidor asignado a la dependenca
     * @return Medidor asignado a la dependencia
     * @see IPowerMeter
     */
    IPowerMeter getPowerMeter();
    
    /**
     * Establece la ubicacion donde se encuentra la dependencia
     * @param location Ubicacion donde se encuentra la dependencia
     * @see ILocation
     */
    void setLocation(ILocation location);
    
    /**
     * Devuelve la ubicacion donde se encuentra la dependencia
     * @return Ubicacion donde se encuentra la dependencia
     * @see ILocation
     */
    ILocation getLocation();
    
    /**
     * Devuelve las dependencias agrupadas dentro de esta ubicacion
     * @return Dependencias agrupadas dentro de esta ubicacion
     * @see ILocation
     */
    Set<ILocation> getLocations();
    
    /**
     * Establece las dependencias agrupadas dentro de esta ubicacion
     * @param locations Dependencias agrupadas dentro de esta ubicacion
     * @see ILocation
     */ 
    void setLocations(Set<ILocation> locations);
    
    /**
     * Devuelve las zonas logicas que agrupan a la dependencia
     * @return Zonas logicas que agrupan a la dependencia
     * @see IZone
     */
    Set<IZone> getZones();
    
    /**
     * Establece las zonas logicas que agrupan a la dependencia
     * @param zones Zonas logicas que agrupan a la dependencia
     * @see IZone
     */
    void setZones(Set<IZone> zones);
    
    /**
     * Devuelve las mediciones realizadas en la dependencia
     * @return Mediciones realizadas en la dependencia
     * @see IMeasure
     */
    Set<IMeasure> getMeasures();
    
    /**
     * Establece las mediciones realizadas en la dependencia
     * @param measures Mediciones realizadas en la dependencia
     * @see IMeasure
     */
    void setMeasures(Set<IMeasure> measures);
}
