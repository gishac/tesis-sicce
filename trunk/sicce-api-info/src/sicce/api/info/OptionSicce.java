package sicce.api.info;

import sicce.api.info.auto._OptionSicce;
import sicce.api.info.interfaces.IOptionSicce;

public class OptionSicce extends _OptionSicce implements IOptionSicce {

    public static final String OPTION_ID = "ID_OPTION_SICCE";
    
    public int getID() {
        return (getObjectId() != null)? Integer.parseInt(getObjectId().getIdSnapshot().get(OPTION_ID).toString()) : 0;
    }

    
    
}



