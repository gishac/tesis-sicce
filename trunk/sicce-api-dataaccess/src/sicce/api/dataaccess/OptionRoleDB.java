/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import org.apache.cayenne.query.Query;
import sicce.api.info.OptionRole;
import sicce.api.info.interfaces.IOptionRole;

/**
 *
 * @author gish@c
 */
public class OptionRoleDB {

    public static void Save(List<IOptionRole> permissions) throws Exception {
        for (IOptionRole permission : permissions) {
            //Connection.getDataContext().rollbackChanges();            
            System.out.println("Option=>" + permission.getOption().getDescription() + " Role=>" + permission.getRole().getDescription());
            OptionRole optionRole = (OptionRole) Connection.getDataContext().newObject(OptionRole.class);
            optionRole.setRole(permission.getRole());
            optionRole.setOption(permission.getOption());
            Connection.getDataContext().commitChanges();
            
        }
    }

    public static void Delete(IOptionRole permission) throws Exception {
        Connection.getDataContext().deleteObject((OptionRole) permission);
        Connection.getDataContext().commitChanges();
    }
    
    public static List GetOptionRoles(Query query)
    {
        return Connection.getDataContext().performQuery(query);
    }
}
