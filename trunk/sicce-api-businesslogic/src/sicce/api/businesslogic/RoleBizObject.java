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
     * 
     * @return Devuelve todos los roles
     */
    public List<IRole> GetAllRoles() {
        return RoleDB.GetAllRoles();
    }

    public IRole GetRoleByID(int roleID) {
        return RoleDB.FindRoleByID(roleID);
    }
    
    public boolean PermissionExists(int optionID, IRole role){
        for(IOptionSicce permission : role.getPermissions())
        {
            if(permission.getID() == optionID)
                return true;
        }
        return false;
    }
}
