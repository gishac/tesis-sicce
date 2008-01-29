package sicce.api.info.cayenne.auto;

import java.util.List;

/** Class _PowerMeter was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _PowerMeter extends org.apache.cayenne.CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String IP_ADDRESS_PROPERTY = "ipAddress";
    public static final String LOG_STATUS_PROPERTY = "logStatus";
    public static final String SERIAL_PROPERTY = "serial";
    public static final String STATUS2_PROPERTY = "status2";
    public static final String LOCATION_ARRAY_PROPERTY = "locationArray";
    public static final String MEASURE_ARRAY_PROPERTY = "measureArray";

    public static final String ID_POWER_METER_PK_COLUMN = "ID_POWER_METER";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }
    
    
    public void setIpAddress(Long ipAddress) {
        writeProperty("ipAddress", ipAddress);
    }
    public Long getIpAddress() {
        return (Long)readProperty("ipAddress");
    }
    
    
    public void setLogStatus(Boolean logStatus) {
        writeProperty("logStatus", logStatus);
    }
    public Boolean getLogStatus() {
        return (Boolean)readProperty("logStatus");
    }
    
    
    public void setSerial(String serial) {
        writeProperty("serial", serial);
    }
    public String getSerial() {
        return (String)readProperty("serial");
    }
    
    
    public void setStatus2(Boolean status2) {
        writeProperty("status2", status2);
    }
    public Boolean getStatus2() {
        return (Boolean)readProperty("status2");
    }
    
    
    public void addToLocationArray(sicce.api.info.cayenne.Location obj) {
        addToManyTarget("locationArray", obj, true);
    }
    public void removeFromLocationArray(sicce.api.info.cayenne.Location obj) {
        removeToManyTarget("locationArray", obj, true);
    }
    public List getLocationArray() {
        return (List)readProperty("locationArray");
    }
    
    
    public void addToMeasureArray(sicce.api.info.cayenne.Measure obj) {
        addToManyTarget("measureArray", obj, true);
    }
    public void removeFromMeasureArray(sicce.api.info.cayenne.Measure obj) {
        removeToManyTarget("measureArray", obj, true);
    }
    public List getMeasureArray() {
        return (List)readProperty("measureArray");
    }
    
    
}
