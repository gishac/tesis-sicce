/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.util.List;
import org.apache.cayenne.query.Query;

/**
 *
 * @author gish@c
 */
public class OptionDB {

    public static List GetOptions(Query query)
    {
        return Connection.getDataContext().performQuery(query);
    }
    
}
