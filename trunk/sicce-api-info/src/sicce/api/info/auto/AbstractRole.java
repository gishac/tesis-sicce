package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;
import sicce.api.info.Role;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IUserSicce;

/**
 * Representacion de los roles en el sistema
 * @author gish@c
 */
public abstract class AbstractRole implements java.io.Serializable, IRole {

    // Fields
    
    /**
     * Identificador del rol
     */
    protected Integer idRole;
    
    /**
     * Descripcion del rol
     */
    protected String description;
    
    /**
     * Usuarios que tienen asignado el rol
     */
    protected Set<IUserSicce> usersInRole = new HashSet<IUserSicce>(0);
    
    /**
     * Permisos asociados al rol
     */
    protected Set<IOptionSicce> permissions = new HashSet<IOptionSicce>(0);

    // Constructors
    /**
     * Constructor
     */
    public AbstractRole() {
    }

    // Property accessors
    public Integer getIdRole() {
        return this.idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<IUserSicce> getUsersInRole() {
        return this.usersInRole;
    }

    public void setUsersInRole(Set<IUserSicce> usersInRole) {
        this.usersInRole = usersInRole;
    }

    public Set<IOptionSicce> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<IOptionSicce> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(IOptionSicce permission) {
        permissions.add(permission);
    }

    public void removePermission(IOptionSicce permission) {
        permissions.remove(permission);
    }

    @Override
    public Object clone() {
        Role clone = new Role();
        clone.setIdRole(this.getIdRole());
        clone.setDescription(this.getDescription());
        clone.setPermissions(new HashSet<IOptionSicce>(this.getPermissions()));
        clone.setUsersInRole(new HashSet<IUserSicce>(this.getUsersInRole()));
        return clone;
    }
}
