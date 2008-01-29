package sicce.api.dataaccess.auto;

import java.util.List;

/** Class _LocationType was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _LocationType extends org.apache.cayenne.CayenneDataObject {

    public static final String CODE_PROPERTY = "code";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String LOCATION_ARRAY_PROPERTY = "locationArray";

    public static final String ID_LOCATION_TYPE_PK_COLUMN = "ID_LOCATION_TYPE";

    public void setCode(String code) {
        writeProperty("code", code);
    }
    public String getCode() {
        return (String)readProperty("code");
    }
    
    
    public void setDescription(Long description) {
        writeProperty("description", description);
    }
    public Long getDescription() {
        return (Long)readProperty("description");
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
    
    
}
