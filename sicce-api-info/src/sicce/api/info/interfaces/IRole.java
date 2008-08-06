/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a los roles en el sistema
 * @author gish@c
 */
public interface IRole extends Cloneable {

    /**
     * Devuelve el identificador del rol
     * @return Identificador del rol
     */
    Integer getIdRole();
    
    /**
     * Establece el identificador del rol
     * @param idRole Identificador del rol
     */
    void setIdRole(Integer idRole);
    
    /**
     * Establece la descripcion del rol
     * @param description Descripcion del rol
     */
    void setDescription(String description);
    
    /**
     * Devuelve la descripcion del rol
     * @return Descripcion del rol
     */
    String getDescription();
    
    /**
     * Devuelve los permisos asociados al rol
     * @return Permisos asociados al rol
     * @see IOptionSicce
     */
    Set<IOptionSicce> getPermissions();
    
    /**
     * Establece los permisos asociados al rol
     * @param permissions Permisos asociados al rol
     * @see IOptionSicce
     */
    void setPermissions(Set<IOptionSicce> permissions);
    
    /**
     * Devuelve los usuarios que tienen asignado el rol
     * @return Usuarios que tienen asignado el rol
     * @see IUserSicce
     */
    Set<IUserSicce> getUsersInRole();
    
    /**
     * Establece los usuarios que tienen asignado el rol
     * @param usersInRole Usuarios que tienen asignado el rol
     * @see IUserSicce
     */
    void setUsersInRole(Set<IUserSicce> usersInRole); 
    
    /**
     * Agrega un permiso que sera asignado al rol
     * @param permission Permiso que sera asignado al rol
     * @see IOptionSicce
     */
    void addPermission(IOptionSicce permission);
    
    /**
     * Remueve un permiso del rol
     * @param permission Permiso a ser removido del rol
     * @see IOptionSicce
     */
    void removePermission(IOptionSicce permission);
    
    /**
     * Crea una nueva instancia clonada con los valores del rol actual
     * @return
     */
    public Object clone();
}
