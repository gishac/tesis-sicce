package sicce.api.info;

import sicce.api.info.auto._Role;
import sicce.api.info.interfaces.IRole;

public class Role extends _Role implements IRole {

    public static final String ROLE_ID = "ID_ROLE";
    
    public int getID()
    {
        return (getObjectId() != null)? Integer.parseInt(getObjectId().getIdSnapshot().get(ROLE_ID).toString()) : 0;
    }
    
}



