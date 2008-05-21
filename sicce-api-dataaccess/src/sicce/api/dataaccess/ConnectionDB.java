/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.ILocation;

/**
 *
 * @author karu
 */
public class ConnectionDB {

    public static void getConnection() throws Exception {
        try {
            ConnectDAO dao = DataAccessManager.getInstance().getConnectionDB();
            dao.getConnection();
        } catch (Exception ex) {
            throw ex;
        }
    }
}


