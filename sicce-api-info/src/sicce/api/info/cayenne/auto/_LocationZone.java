package sicce.api.info.cayenne.auto;

/** Class _LocationZone was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _LocationZone extends org.apache.cayenne.CayenneDataObject {

    public static final String TO_LOCATION_PROPERTY = "toLocation";
    public static final String TO_ZONE_PROPERTY = "toZone";


    public void setToLocation(sicce.api.info.LocationInfo toLocation) {
        setToOneTarget("toLocation", toLocation, true);
    }

    public sicce.api.info.LocationInfo getToLocation() {
        return (sicce.api.info.LocationInfo)readProperty("toLocation");
    } 
    
    
    public void setToZone(sicce.api.info.ZoneInfo toZone) {
        setToOneTarget("toZone", toZone, true);
    }

    public sicce.api.info.ZoneInfo getToZone() {
        return (sicce.api.info.ZoneInfo)readProperty("toZone");
    } 
    
    
}
