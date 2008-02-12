/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.cayenne.PersistenceState;

/**
 *
 * @author gish@c
 */
public interface IMeasure {

    void setIdLocation(Integer idLocation);

    Integer getIdLocation();

    void setIdPowerMeter(Integer idPowerMeter);

    Integer getIdPowerMeter();

    void setValueMeasure(BigDecimal valueMeasure);

    BigDecimal getValueMeasure();

    void setDateMeasure(Date dateMeasure);

    Date getDateMeasure();

    int getID();
    
    ILocation getLocation();
    
    IPowerMeter getPowerMeter();
}
