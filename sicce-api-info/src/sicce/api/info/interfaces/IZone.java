/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a las zonas del sistema
 * @author gish@c
 */
public interface IZone extends Cloneable {

    /**
     * Devuelve el identificador de la zona
     * @return Identificador de la zona
     */
    public Integer getIdZone();
    
    /**
     * Establece el identificador de la zona
     * @param idZone Identificador de la zona
     */
    public void setIdZone(Integer idZone);
    
    /**
     * Establece la descripcion de la zona
     * @param description Descripcion de la zona
     */
    void setDescription(String description);
    
    /**
     * Devuelve la descripcion de la zona
     * @return Descripcion de la zona
     */
    String getDescription();
    
    /**
     * Devuelve el codigo de la zona
     * @return Codigo de la zona
     */
    String getCode();
    
    /**
     * Establece el codigo de la zona
     * @param code
     */
    void setCode(String code); 
    
    /**
     * Devuelve las dependencias dentro de la zona
     * @return Dependencias dentro de la zona
     * @see ILocation
     */
    Set<ILocation> getLocations();
    
    /**
     * Agrega una dependencia a la zona
     * @param locations Dependencia que sera parte de la zona
     * @see ILocation
     */
    void addLocations(ILocation locations);
    
    /**
     * Remueve una dependencia de la zona
     * @param locations Dependencia a ser removida de la zona
     * @see ILocation
     */
    void removeLocations(ILocation locations);
    
    /**
     * Establece las dependencias de la zona
     * @param locationsInZone Dependencias de la zona
     * @see ILocation
     */
    void setLocations(Set<ILocation> locationsInZone);
}
