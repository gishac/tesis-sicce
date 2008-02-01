/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import org.apache.cayenne.access.DataContext;

/**
 *
 * @author gish@c
 */
public class Connection {

    private static DataContext instance;
    
    /**
     * Devuelve la instancia del DataContext
     * @return
     */
    public static DataContext getDataContext()
    {
        if(instance == null)
            instance = DataContext.createDataContext();
        return instance;
    }
            
    
}
