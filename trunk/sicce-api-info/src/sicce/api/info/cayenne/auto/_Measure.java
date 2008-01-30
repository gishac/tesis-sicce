package sicce.api.info.cayenne.auto;

/** Class _Measure was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _Measure extends org.apache.cayenne.CayenneDataObject {

    public static final String DATE_MEASURE_PROPERTY = "dateMeasure";
    public static final String VALUE_PROPERTY = "value";
    public static final String TO_LOCATION_PROPERTY = "toLocation";
    public static final String TO_POWER_METER_PROPERTY = "toPowerMeter";
    public static final String TO_UNIT_MEASURE_PROPERTY = "toUnitMeasure";

    public static final String ID_MEASURE_PK_COLUMN = "ID_MEASURE";

    public void setDateMeasure(java.util.Date dateMeasure) {
        writeProperty("dateMeasure", dateMeasure);
    }
    public java.util.Date getDateMeasure() {
        return (java.util.Date)readProperty("dateMeasure");
    }
    
    
    public void setValue(java.math.BigDecimal value) {
        writeProperty("value", value);
    }
    public java.math.BigDecimal getValue() {
        return (java.math.BigDecimal)readProperty("value");
    }
    
    
    public void setToLocation(sicce.api.info.LocationInfo toLocation) {
        setToOneTarget("toLocation", toLocation, true);
    }

    public sicce.api.info.LocationInfo getToLocation() {
        return (sicce.api.info.LocationInfo)readProperty("toLocation");
    } 
    
    
    public void setToPowerMeter(sicce.api.info.PowerMeterInfo toPowerMeter) {
        setToOneTarget("toPowerMeter", toPowerMeter, true);
    }

    public sicce.api.info.PowerMeterInfo getToPowerMeter() {
        return (sicce.api.info.PowerMeterInfo)readProperty("toPowerMeter");
    } 
    
    
    public void setToUnitMeasure(sicce.api.info.UnitMeasureInfo toUnitMeasure) {
        setToOneTarget("toUnitMeasure", toUnitMeasure, true);
    }

    public sicce.api.info.UnitMeasureInfo getToUnitMeasure() {
        return (sicce.api.info.UnitMeasureInfo)readProperty("toUnitMeasure");
    } 
    
    
}
