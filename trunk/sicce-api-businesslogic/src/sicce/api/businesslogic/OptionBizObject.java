/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.OptionDB;
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
        Query query = new SelectQuery(sicce.api.info.OptionSicce.class);
        List<IOptionSicce> result = null;
        result = OptionDB.GetOptions(query);
        return result;
    }
}
