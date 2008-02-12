/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;
import java.util.List;
import org.apache.cayenne.query.Query;
import sicce.api.info.Measure;
import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author karu
 */
public class MeasureDB {

    public static IMeasure Save(IMeasure measure) throws Exception
    {
        try
        {
            
        Measure measureToSave = (Measure)Connection.getDataContext().newObject(Measure.class);
        measureToSave.setDateMeasure(measure.getDateMeasure());        
        Connection.getDataContext().commitChanges();
        return measureToSave;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public static void Update(IMeasure measure) throws Exception
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
    
    public static void Delete(IMeasure measure) throws Exception
    {
      try
        {
            Connection.getDataContext().deleteObject((Measure) measure);
            Connection.getDataContext().commitChanges();
        }
        catch(Exception ex)
        {
            throw ex;
        }  
    }
    
    public static List GetMeasures(Query query)
    {
        return Connection.getDataContext().performQuery(query);
    }
  
    
}
