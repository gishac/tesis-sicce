package sicce.api.info;

import sicce.api.info.auto._OptionRole;
import sicce.api.info.interfaces.IOptionRole;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;

public class OptionRole extends _OptionRole implements IOptionRole {

    public IRole getRole() {
        return getToRole();
    }

    public IOptionSicce getOption() {
        return getToOptionSicce();
    }

    public void setRole(IRole role) {
        this.setToRole((Role) role);
    }

    public void setOption(IOptionSicce option) {
        this.setToOptionSicce((OptionSicce) option);
    }

}



