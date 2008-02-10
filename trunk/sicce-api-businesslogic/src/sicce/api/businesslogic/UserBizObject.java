/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import org.apache.cayenne.query.Query;
import org.apache.cayenne.query.SelectQuery;
import sicce.api.dataaccess.UserDB;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserBizObject {

    public List<IUserSicce> GetAllUsers() {
        Query query = new SelectQuery(sicce.api.info.UserSicce.class);
        List<IUserSicce> result = null;
        result = UserDB.GetUsers(query);
        return result;
    }
}
