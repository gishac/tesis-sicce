/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.RoleDB;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class RoleBizObject {

    public List<IRole> GetAllRoles()
    {
        Query query = new SelectQuery(sicce.api.info.Role.class);
        List<IRole> result = null;
        result = RoleDB.GetRoles(query);
        return result;
    }
    
}
