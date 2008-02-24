/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sicce.api.dataaccess.OptionDB;
import sicce.api.info.OptionSicce;
import sicce.api.info.interfaces.IOptionSicce;

/**
 *
 * @author gish@c
 */
public class OptionBizObject {

    /**
     * 
     * @return Devuelve todas las opciones
     */
    public List<IOptionSicce> GetAllOptions() {
        return OptionDB.GetAllOptions();
    }

    /**
     * Devuelve una opcion
     * @param optionID
     * @return
     */
    public IOptionSicce GetOptionByID(int optionID) {
       return OptionDB.FindOptionByID(optionID);
    }
}
