/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author gish@c
 */
public class PlainQueryExecutor {

    /**
     * 
     * @param query
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static Vector ExecuteSimpleQuery(String query, Connection connection) throws SQLException{
        Vector queryResult = new Vector();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            queryResult.add(result.getObject(0));
        }
        return queryResult;
    }
    
}
