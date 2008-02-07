/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;
import sicce.api.info.Role;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class RoleDB {

    public static IRole Save(IRole role) throws Exception
    {
        try
        {
            
        Role roleToSave = (Role)Connection.getDataContext().newObject(Role.class);
        roleToSave.setDescription(role.getDescription());        
        Connection.getDataContext().commitChanges();
        return roleToSave;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public static void Update(IRole role) throws Exception
    {
        try
        {
            Connection.getDataContext().modifiedObjects();        
            Connection.getDataContext().commitChanges();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public static void Delete(IRole role) throws Exception
    {
      try
        {
            Connection.getDataContext().deleteObject((Role) role);
            Connection.getDataContext().commitChanges();
        }
        catch(Exception ex)
        {
            throw ex;
        }  
    }
    
}
