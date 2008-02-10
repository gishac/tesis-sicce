package sicce.api.info;

import sicce.api.info.auto._Location;
import sicce.api.info.interfaces.ILocation;

public class Location extends _Location implements ILocation{
  
    public static final String LOCATION_ID = "ID_LOCATION";
    
    public int getID()
    {
        return (getObjectId() != null)? Integer.parseInt(getObjectId().getIdSnapshot().get(LOCATION_ID).toString()) : 0;
    }

    public void setIdLocation(Integer idLocation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getIdLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setIdLocationType(Integer idLocationType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getIdLocationType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setIdPowerMeter(Integer idPowerMeter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getIdPowerMeter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}



