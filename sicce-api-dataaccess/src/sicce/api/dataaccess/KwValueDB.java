/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.util.Date;
import java.util.List;
import sicce.api.info.interfaces.IKwValue;

/**
 *
 * @author gish@c
 */
public class KwValueDB {

    public static void Save(IKwValue kwValue) throws Exception {
        try {

            DataAccessManager.getInstance().getKwValueDB().save(kwValue);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IKwValue kwValue) throws Exception {
        try {
            DataAccessManager.getInstance().getKwValueDB().update(kwValue);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IKwValue kwValue) throws Exception {
        try {
            DataAccessManager.getInstance().getKwValueDB().delete(kwValue);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllKwValues() {
        return DataAccessManager.getInstance().getKwValueDB().findAll();
    }
    
    public static List GetKWValueForDates(Date startDate, Date endDate){
        return DataAccessManager.getInstance().getKwValueDB().findByDates(startDate, endDate);
    }
    
    public static List FindDatesInRange(Date startDate, Date endDate){
        return DataAccessManager.getInstance().getKwValueDB().findDatesInRange(startDate, endDate);
    }
    
}
