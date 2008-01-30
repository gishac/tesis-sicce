package sicce.api.info.cayenne.auto;

import java.util.List;

/** Class _Zone was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _Zone extends org.apache.cayenne.CayenneDataObject {

    public static final String CODE_PROPERTY = "code";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String LOCATION_ZONE_ARRAY_PROPERTY = "locationZoneArray";

    public static final String ID_ZONE_PK_COLUMN = "ID_ZONE";

    public void setCode(String code) {
        writeProperty("code", code);
    }
    public String getCode() {
        return (String)readProperty("code");
    }
    
    
    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }
    
    
    public void addToLocationZoneArray(sicce.api.info.LocationZoneInfo obj) {
        addToManyTarget("locationZoneArray", obj, true);
    }
    public void removeFromLocationZoneArray(sicce.api.info.LocationZoneInfo obj) {
        removeToManyTarget("locationZoneArray", obj, true);
    }
    public List getLocationZoneArray() {
        return (List)readProperty("locationZoneArray");
    }
    
    
}
