package sicce.api.info.cayenne.auto;

/** Class _Parameter was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _Parameter extends org.apache.cayenne.CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String VALUE_PROPERTY = "value";

    public static final String ID_PARAMETER_PK_COLUMN = "ID_PARAMETER";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }
    
    
    public void setValue(java.math.BigDecimal value) {
        writeProperty("value", value);
    }
    public java.math.BigDecimal getValue() {
        return (java.math.BigDecimal)readProperty("value");
    }
    
    
}
