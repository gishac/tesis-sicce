package sicce.api.info.auto;

import java.util.List;

/** Class _LocationType was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _LocationType extends org.apache.cayenne.CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String LOCATION_ARRAY_PROPERTY = "locationArray";

    public static final String ID_LOCATION_TYPE_PK_COLUMN = "ID_LOCATION_TYPE";

    public void setDescription(Integer description) {
        writeProperty("description", description);
    }
    public Integer getDescription() {
        return (Integer)readProperty("description");
    }
    
    
    public void addToLocationArray(sicce.api.info.Location obj) {
        addToManyTarget("locationArray", obj, true);
    }
    public void removeFromLocationArray(sicce.api.info.Location obj) {
        removeToManyTarget("locationArray", obj, true);
    }
    public List getLocationArray() {
        return (List)readProperty("locationArray");
    }
    
    
}
