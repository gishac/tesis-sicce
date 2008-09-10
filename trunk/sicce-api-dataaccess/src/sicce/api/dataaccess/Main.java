/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import sicce.api.info.UserPowerMeter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.util.EncryptionProvider;

/**
 *
 * @author gish@c
 */
public class Main {

    public static void main(String[] args) {
        try {




            //DataAccessManager.getInstance().getUserPowerMeterDB().save(upm);
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Database", "gisbert", "gisbert");
            
            String query = "begin ?:=demo.sendsms(); end;";
            CallableStatement stmt = conn.prepareCall(query);

            //stmt.setInt(1,1);
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
