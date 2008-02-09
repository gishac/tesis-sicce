/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import org.apache.cayenne.query.Query;
import sicce.api.info.Role;
import sicce.api.info.UserSicce;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserDB {

    public static IUserSicce Save(IUserSicce user) throws Exception {
        try {

            //Connection.getDataContext().rollbackChanges();
            UserSicce userToSave = new UserSicce(); //(UserSicce) Connection.getDataContext().newObject(UserSicce.class);
            Connection.getDataContext().registerNewObject(userToSave);
            userToSave.setName(user.getName());
            userToSave.setToRole((Role) user.getRole());
            userToSave.setPasswordSicce(user.getPasswordSicce());
            userToSave.setUsernameSicce(user.getUsernameSicce());
            userToSave.setLastname(user.getLastname());
            Connection.getDataContext().commitChanges();
            return userToSave;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IUserSicce user) throws Exception {
        try {
            Connection.getDataContext().modifiedObjects();
            Connection.getDataContext().commitChanges();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IUserSicce user) throws Exception {
        try {
            Connection.getDataContext().deleteObject((UserSicce) user);
            Connection.getDataContext().commitChanges();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetUsers(Query query) {
        return Connection.getDataContext().performQuery(query);
    }
}
