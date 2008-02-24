/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.UserDB;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserBizObject {

    public List<IUserSicce> GetAllUsers() {
        return UserDB.GetAllUsers();
    }
}
