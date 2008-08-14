/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import sicce.api.info.interfaces.IExceptionSicce;

/**
 *
 * @author gish@c
 */
public class ExceptionSicceDB {

    public static void Save(IExceptionSicce exception) throws Exception {
        try {
            DataAccessManager.getInstance().getExceptionDB().save(exception);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
