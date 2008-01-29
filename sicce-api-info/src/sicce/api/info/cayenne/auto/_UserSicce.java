package sicce.api.info.cayenne.auto;

/** Class _UserSicce was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _UserSicce extends org.apache.cayenne.CayenneDataObject {

    public static final String CODE_UCSG_PROPERTY = "codeUcsg";
    public static final String NAME_PROPERTY = "name";
    public static final String PASSWORD_SICCE_PROPERTY = "passwordSicce";
    public static final String SURNAME_PROPERTY = "surname";
    public static final String USERNAME_SICCE_PROPERTY = "usernameSicce";
    public static final String TO_ROLE_PROPERTY = "toRole";

    public static final String ID_USER_SICCE_PK_COLUMN = "ID_USER_SICCE";

    public void setCodeUcsg(Long codeUcsg) {
        writeProperty("codeUcsg", codeUcsg);
    }
    public Long getCodeUcsg() {
        return (Long)readProperty("codeUcsg");
    }
    
    
    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }
    
    
    public void setPasswordSicce(String passwordSicce) {
        writeProperty("passwordSicce", passwordSicce);
    }
    public String getPasswordSicce() {
        return (String)readProperty("passwordSicce");
    }
    
    
    public void setSurname(String surname) {
        writeProperty("surname", surname);
    }
    public String getSurname() {
        return (String)readProperty("surname");
    }
    
    
    public void setUsernameSicce(String usernameSicce) {
        writeProperty("usernameSicce", usernameSicce);
    }
    public String getUsernameSicce() {
        return (String)readProperty("usernameSicce");
    }
    
    
    public void setToRole(sicce.api.info.cayenne.Role toRole) {
        setToOneTarget("toRole", toRole, true);
    }

    public sicce.api.info.cayenne.Role getToRole() {
        return (sicce.api.info.cayenne.Role)readProperty("toRole");
    } 
    
    
}
