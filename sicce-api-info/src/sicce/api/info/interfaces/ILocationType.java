/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que representen a los tipos de dependencia
 * @author gish@c
 */
public interface ILocationType {

    /**
     * Establece la descripcion del tipo de dependencia
     * @param description Descripcion del tipo de dependencia
     */
    void setDescription(String description);
    
    /**
     * Devuelve la descripcion del tipo de dependencia
     * @return Descripcion del tipo de dependencia
     */
    String getDescription();
    
    /**
     * Devuelve el identificador del tipo de dependencia
     * @return Identificador del tipo de dependencia
     */
    int getID();
}
