package sicce.api.info;

import sicce.api.info.auto._LocationType;
import sicce.api.info.interfaces.ILocationType;

public class LocationType extends _LocationType implements ILocationType{
 
    public static final String LOCATION_TYPE_ID = "ID_LOCATION_TYPE";
    
    public int getID()
    {
        return (getObjectId() != null)? Integer.parseInt(getObjectId().getIdSnapshot().get(LOCATION_TYPE_ID).toString()) : 0;
    }
    
}



