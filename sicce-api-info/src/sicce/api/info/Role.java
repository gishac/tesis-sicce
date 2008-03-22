package sicce.api.info;

// Generated by MyEclipse Persistence Tools
import java.util.HashSet;
import java.util.Set;

import sicce.api.info.auto.AbstractRole;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IUserSicce;

/**
 * Role generated by MyEclipse Persistence Tools
 */
public class Role extends AbstractRole implements java.io.Serializable, IRole {

    // Constructors
    /** default constructor */
    public Role() {
    }

    /** minimal constructor */
    public Role(Integer idRole) {
        super(idRole);
    }

    /** full constructor */
    public Role(Integer idRole, String description,
            Set<IUserSicce> userSicces, Set<IOptionSicce> permissions) {
        super(idRole, description, userSicces, permissions);
    }

    public Integer getID() {
        return super.getIdRole();
    }

    public void setID(int id) {
        super.setIdRole(id);
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
        clone.setID(this.getID());
        clone.setDescription(this.getDescription());
        clone.setPermissions(new HashSet<IOptionSicce>(this.getPermissions()));
        clone.setUsersInRole(new HashSet<IUserSicce>(this.getUsersInRole()));
        return clone;
    }
}