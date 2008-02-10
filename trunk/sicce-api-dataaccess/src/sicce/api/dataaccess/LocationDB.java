/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;
import java.util.List;
import org.apache.cayenne.query.Query;
import sicce.api.info.Location;
import sicce.api.info.interfaces.ILocation;

/**
 *
 * @author karu
 */
public class LocationDB {

    public static ILocation Save(ILocation location) throws Exception
    {
        try
        {
            
        Location locationToSave = (Location)Connection.getDataContext().newObject(Location.class);
        locationToSave.setDescription(location.getDescription());        
        Connection.getDataContext().commitChanges();
        return locationToSave;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public static void Update(ILocation location) throws Exception
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
    
    public static void Delete(ILocation location) throws Exception
    {
      try
        {
            Connection.getDataContext().deleteObject((Location) location);
            Connection.getDataContext().commitChanges();
        }
        catch(Exception ex)
        {
            throw ex;
        }  
    }
    
     
  
    
}
