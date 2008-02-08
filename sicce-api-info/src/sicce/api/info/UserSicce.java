package sicce.api.info;

import sicce.api.info.auto._UserSicce;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IUserSicce;

public class UserSicce extends _UserSicce implements IUserSicce {

    public IRole getRole() {
        return getToRole();
    }

    public void setRole(IRole role) {
        setToRole((Role)role);
    }

}



