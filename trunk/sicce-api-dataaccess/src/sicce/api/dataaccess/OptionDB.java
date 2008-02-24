/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IOptionSicce;

/**
 *
 * @author gish@c
 */
public class OptionDB {

    public static List GetAllOptions() {
        return DataAccessManager.getInstance().getOptionDB().findAll();
    }

    public static IOptionSicce FindOptionByID(int id) {
        return DataAccessManager.getInstance().getOptionDB().findById(id);
    }
}
