/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class RoleDB {

    public static void Save(IRole role) throws Exception {
        try {
            DataAccessManager.getInstance().getRoleDB().save(role);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IRole role) throws Exception {
        try {
            DataAccessManager.getInstance().getRoleDB().update(role);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IRole role) throws Exception {
        try {
            DataAccessManager.getInstance().getRoleDB().delete(role);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllRoles() {
        return DataAccessManager.getInstance().getRoleDB().findAll();
    }

    public static IRole FindRoleByID(int id) {
        return DataAccessManager.getInstance().getRoleDB().findById(id);
    }
}
