/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a las opciones del sistema
 * @author gish@c
 */
public interface IOptionSicce {

    /**
     * Devuelve el identificador de la opcion
     * @return Identificador de la opcion
     */
    public Integer getIdOptionSicce();

    /**
     * Establece el identificador de la opcion
     * @param idOptionSicce Identificador de la opcion
     */
    public void setIdOptionSicce(Integer idOptionSicce);
    
    /**
     * Establece la descripcion de la opcion
     * @param description Descripcion de la opcion
     */
    void setDescription(String description);
    
    /**
     * Devuelve la descripcion de la opcion
     * @return Descripcion de la opcion
     */
    String getDescription();
    
    /**
     * Devuelve la accion a ser ejecutada por la opcion
     * @return Accion a ser ejecutada por la opcion
     */
    String getActionCommand();
    
    /**
     * Establece la accion a ser ejecutada por la opcion
     * @param actionCommand Accion a ser ejecutada por la opcion
     */
    public void setActionCommand(String actionCommand) ;
    
   /**
     * Devuelve el grupo al que pertenece la opcion
     * @return Grupo al que pertenece la opcion
     */
    IGroup getGroup();
    
    /**
     * Establece el grupo al que pertenece la opcion
     * @param group Grupo al que pertenece la opcion
     */
    void setGroup(IGroup group);
    
    /**
     * Devuelve el icono asignado la opcion
     * @return Icono asignado la opcion
     */
    public String getIcon();

    /**
     * Establece el icono de la opcion
     * @param icon Icono de la opcion
     */
    public void setIcon(String icon);

    /**
     * Devuelve los roles que tienen asignada la opcion
     * @return Roles que tienen asignada la opcion
     * @see IRole
     */
    public Set<IRole> getRoles();

    /**
     * Establece los roles que tienen asignada la opcion
     * @param roles Roles que tienen asignada la opcion
     * @see IRole
     */
    public void setRoles(Set<IRole> roles);
}
