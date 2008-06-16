/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a los grupos
 * @author gish@c
 */
public interface IGroup {

    /**
     * Devuelve el identificador del grupo
     * @return Identificador del grupo
     */
    Integer getIdGroup();
    
    /**
     * Establece el identificador del grupo
     * @param idGroup Identificador del grupo
     */
    void setIdGroup(Integer idGroup);
    
    /**
     * Devuelve la descripcion del grupo
     * @return Descripcion del grupo
     */
    String getDescription();
    
    /**
     * Establece la descripcion del grupo
     * @param description Descripcion del grupo
     */
    void setDescription(String description);
    
    /**
     * Devueve las opciones de menu asociadas al grupo
     * @return Opciones de menu asociadas al grupo
     * @see IOptionSicce
     */
    Set<IOptionSicce> getOptions();
    
    /**
     * Establece las opciones de menu asociadas al grupo
     * @param options Opciones de menu que seran asociadas
     */
    void setOptions(Set<IOptionSicce> options);
    
}
