/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.MeasureDB;
import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author gish@c
 */
public class MeasureBizObject {

    public List<IMeasure> GetAllMeasures() {
        Query query = new SelectQuery(sicce.api.info.Measure.class);
        List<IMeasure> result = null;
        result = MeasureDB.GetMeasures(query);
        return result;
    }
}
