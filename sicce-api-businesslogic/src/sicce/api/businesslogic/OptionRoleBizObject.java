/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.OptionRoleDB;
import sicce.api.info.OptionRole;
import sicce.api.info.interfaces.IOptionRole;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class OptionRoleBizObject {

    /**
     * Devuelve todos los permisos asociados al rol
     * @param role Rol del que se quieren obtener los permisos
     * @return
     */
    public List<IOptionRole> GetPermissionsByRole(IRole role) {
        Expression expression = Expression.fromString("toRole = $toRole");
        SelectQuery prototype = new SelectQuery(OptionRole.class, expression);
        prototype.setDistinct(true);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("toRole", String.valueOf(role.getID()));
        SelectQuery query = prototype.queryWithParameters(parameters);
        return OptionRoleDB.GetOptionRoles(query);
    }

    /**
     * Borra todos los permisos de un rol
     * @param role Rol del que se van a borrar los permisos
     * @throws java.lang.Exception Posible excepcion lanzada por la transaccion
     */
    public void DeletePermisisionsByRole(IRole role) throws Exception {
        List<IOptionRole> permissions = GetPermissionsByRole(role);
        if (permissions != null) {
            for (IOptionRole permission : permissions) {
                OptionRoleDB.Delete(permission);
            }
        }
    }
    
   /**
    *Graba los permisos asignados al rol
    * @param table Tabla que contiene los permisos 
    * @param role Rol
    * @return
    * @throws java.lang.Exception
    */
    public void SavePermissionsToRole(JTable table, IRole role) throws Exception {
        DeletePermisisionsByRole(role);
        OptionRoleTableModel model = (OptionRoleTableModel) table.getModel();
        List<IOptionRole> permissions = model.getDataSource();
        for (IOptionRole permission : permissions) {
            permission.setRole(role);
        }
        OptionRoleDB.Save(permissions);
        
    }
}
