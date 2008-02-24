/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserDB {

    public static void Save(IUserSicce user) throws Exception {
        try {

            DataAccessManager.getInstance().getUserDB().save(user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IUserSicce user) throws Exception {
        try {
            DataAccessManager.getInstance().getUserDB().update(user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IUserSicce user) throws Exception {
        try {
            DataAccessManager.getInstance().getUserDB().delete(user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllUsers() {
        return DataAccessManager.getInstance().getUserDB().findAll();
    }
}
