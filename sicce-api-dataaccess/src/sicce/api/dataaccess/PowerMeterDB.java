/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;
import sicce.api.info.PowerMeter;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterDB {

    public static IPowerMeter Save(IPowerMeter pmeter) throws Exception
    {
        try
        {
            
        PowerMeter pmeterToSave = (PowerMeter)Connection.getDataContext().newObject(PowerMeter.class);
        pmeterToSave.setSerial(pmeter.getSerial());
        pmeterToSave.setIpAddress(pmeter.getIpAddress());
        pmeterToSave.setDescription(pmeter.getDescription());        
        Connection.getDataContext().commitChanges();
        return pmeterToSave;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public static void Update(IPowerMeter pmeter) throws Exception
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
    
     public static void Delete(IPowerMeter pmeter) throws Exception
    {
      try
        {
            Connection.getDataContext().deleteObject((PowerMeter) pmeter);
            Connection.getDataContext().commitChanges();
        }
        catch(Exception ex)
        {
            throw ex;
        }  
    }
    
    
}
