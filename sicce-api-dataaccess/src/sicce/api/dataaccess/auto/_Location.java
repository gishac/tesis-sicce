package sicce.api.dataaccess.auto;

import java.util.List;

/** Class _Location was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _Location extends org.apache.cayenne.CayenneDataObject {

    public static final String CODE_PROPERTY = "code";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String LOCATION_PROPERTY = "location";
    public static final String LOCATION_ARRAY_PROPERTY = "locationArray";
    public static final String LOCATION_ZONE_ARRAY_PROPERTY = "locationZoneArray";
    public static final String MEASURE_ARRAY_PROPERTY = "measureArray";
    public static final String TO_LOCATION_PROPERTY = "toLocation";
    public static final String TO_LOCATION_TYPE_PROPERTY = "toLocationType";
    public static final String TO_POWER_METER_PROPERTY = "toPowerMeter";

    public static final String ID_LOCATION_PK_COLUMN = "ID_LOCATION";

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
    
    
    public void setLocation(Long location) {
        writeProperty("location", location);
    }
    public Long getLocation() {
        return (Long)readProperty("location");
    }
    
    
    public void addToLocationArray(sicce.api.dataaccess.Location obj) {
        addToManyTarget("locationArray", obj, true);
    }
    public void removeFromLocationArray(sicce.api.dataaccess.Location obj) {
        removeToManyTarget("locationArray", obj, true);
    }
    public List getLocationArray() {
        return (List)readProperty("locationArray");
    }
    
    
    public void addToLocationZoneArray(sicce.api.dataaccess.LocationZone obj) {
        addToManyTarget("locationZoneArray", obj, true);
    }
    public void removeFromLocationZoneArray(sicce.api.dataaccess.LocationZone obj) {
        removeToManyTarget("locationZoneArray", obj, true);
    }
    public List getLocationZoneArray() {
        return (List)readProperty("locationZoneArray");
    }
    
    
    public void addToMeasureArray(sicce.api.dataaccess.Measure obj) {
        addToManyTarget("measureArray", obj, true);
    }
    public void removeFromMeasureArray(sicce.api.dataaccess.Measure obj) {
        removeToManyTarget("measureArray", obj, true);
    }
    public List getMeasureArray() {
        return (List)readProperty("measureArray");
    }
    
    
    public void setToLocation(sicce.api.dataaccess.Location toLocation) {
        setToOneTarget("toLocation", toLocation, true);
    }

    public sicce.api.dataaccess.Location getToLocation() {
        return (sicce.api.dataaccess.Location)readProperty("toLocation");
    } 
    
    
    public void setToLocationType(sicce.api.dataaccess.LocationType toLocationType) {
        setToOneTarget("toLocationType", toLocationType, true);
    }

    public sicce.api.dataaccess.LocationType getToLocationType() {
        return (sicce.api.dataaccess.LocationType)readProperty("toLocationType");
    } 
    
    
    public void setToPowerMeter(sicce.api.dataaccess.PowerMeter toPowerMeter) {
        setToOneTarget("toPowerMeter", toPowerMeter, true);
    }

    public sicce.api.dataaccess.PowerMeter getToPowerMeter() {
        return (sicce.api.dataaccess.PowerMeter)readProperty("toPowerMeter");
    } 
    
    
}
