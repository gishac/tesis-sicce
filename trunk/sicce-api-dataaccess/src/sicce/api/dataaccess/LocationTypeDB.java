/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;
import sicce.api.info.LocationType;
import sicce.api.info.interfaces.ILocationType;

/**
 *
 * @author karu
 */
public class LocationTypeDB {

    public static ILocationType Save(ILocationType locationType) throws Exception
    {
        try
        {
            
        LocationType locationTypeToSave = (LocationType)Connection.getDataContext().newObject(LocationType.class);
        locationTypeToSave.setDescription(locationType.getDescription());        
        Connection.getDataContext().commitChanges();
        return locationTypeToSave;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public static void Update(ILocationType locationType) throws Exception
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
    
    public static void Delete(ILocationType locationType) throws Exception
    {
      try
        {
            Connection.getDataContext().deleteObject((LocationType) locationType);
            Connection.getDataContext().commitChanges();
        }
        catch(Exception ex)
        {
            throw ex;
        }  
    }
    
}
