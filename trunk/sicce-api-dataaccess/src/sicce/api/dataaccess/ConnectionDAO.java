/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.sql.Connection;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author gish@c
 */
public class ConnectionDAO extends HibernateDaoSupport {

    protected void initDao() {
    // do nothing
    }
    
    public Connection getConnection(){
        Session s = getSession();
        return s.connection();
    }
}
