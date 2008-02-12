/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.PowerMeterDB;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterBizObject {

    public List<IPowerMeter> GetAllPowerMeter() {
        Query query = new SelectQuery(sicce.api.info.PowerMeter.class);
        List<IPowerMeter> result = null;
        result = PowerMeterDB.GetPowerMeter(query);
        return result;
    }
}
