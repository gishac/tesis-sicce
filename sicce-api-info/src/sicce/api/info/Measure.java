package sicce.api.info;

import java.math.BigDecimal;
import sicce.api.info.auto._Measure;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;

public class Measure extends _Measure implements IMeasure {
    
    public static final String MEASURE_ID = "ID_MEASURE";
    
    public int getID()
    {
        return (getObjectId() != null)? Integer.parseInt(getObjectId().getIdSnapshot().get(MEASURE_ID).toString()) : 0;
    }

    public void setIdLocation(Integer idLocation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getIdLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setIdPowerMeter(Integer idPowerMeter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getIdPowerMeter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setValueMeasure(BigDecimal valueMeasure) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getValueMeasure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ILocation getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IPowerMeter getPowerMeter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}



