/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import sicce.api.info.interfaces.IGroup;
import sicce.api.info.interfaces.IOptionSicce;

/**
 * Representacion de los grupos a ser usadas por sistema, para la organizacion de las opciones de menu
 * @author gish@c
 */
public class AbstractGroup implements Serializable, IGroup {

    /**
     * Identificador del grupo
     */
    protected Integer idGroup;
    
    /**
     * Descripcion del grupo
     */
    protected String description;
    
    /**
     * Opciones de menu asociadas al grupo
     */
    protected Set<IOptionSicce> options = new HashSet<IOptionSicce>(0);

    
    /**
     * Constructor
     */
    public AbstractGroup() {
    }

    
    /**
     * Constructor
     * @param idGroup Identificador del grupo
     */
    public AbstractGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    
    /**
     * Constructor
     * @param idGroup Identificador del grupo
     * @param description Descripcion del grupo
     * @param options Opciones de menu asociadas al grupo
     */
    public AbstractGroup(Integer idGroup, String description, Set<IOptionSicce> options) {
        this.idGroup = idGroup;
        this.description = description;
        this.options = options;
    }

    // Property accessors
    public Integer getIdGroup() {
        return this.idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<IOptionSicce> getOptions() {
        return this.options;
    }

    public void setOptions(Set<IOptionSicce> options) {
        this.options = options;
    }
}
