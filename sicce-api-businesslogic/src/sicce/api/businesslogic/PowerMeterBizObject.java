/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.PowerMeterDB;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterBizObject {

    public List<IPowerMeter> GetAllPowerMeter() {
        return PowerMeterDB.GetAllPowerMeter();        
    }
}
