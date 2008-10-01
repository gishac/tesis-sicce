/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.RoleDB;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class RoleBizObject {

    /**
     * Devuelve todos los roles
     * @return Devuelve todos los roles
     */
    public List<IRole> GetAllRoles() {
        return RoleDB.GetAllRoles();
    }

    /**
     * Devuelve un rol basado en el identificador
     * @param roleID
     * @return
     */
    public IRole GetRoleByID(int roleID) {
        return RoleDB.FindRoleByID(roleID);
    }
    
    /**
     * Indica si el rol tiene asignado un permiso
     * @param optionID Permiso a buscar
     * @param role Rol sobre el que se buscara el permiso
     * @return
     */
    public boolean PermissionExists(int optionID, IRole role){
        for(IOptionSicce permission : role.getPermissions())
        {
            if(permission.getIdOptionSicce().equals(optionID))
                return true;
        }
        return false;
    }
}
