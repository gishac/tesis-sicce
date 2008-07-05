/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import sicce.api.info.SimpleQueryResult;
import sicce.api.info.interfaces.ISimpleQueryResult;

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
    public static List<ISimpleQueryResult> ExecuteSimpleQuery(String query, Connection connection) throws SQLException{
        List<ISimpleQueryResult> queryResult = new ArrayList<ISimpleQueryResult>();
        
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            ISimpleQueryResult resultQuery = new SimpleQueryResult();
            resultQuery.setResult(result.getObject(1).toString());
            queryResult.add(resultQuery);
        }
        return queryResult;
    }
    
}
