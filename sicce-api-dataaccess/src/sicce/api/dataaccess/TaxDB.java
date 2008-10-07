/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.util.Date;
import java.util.List;
import sicce.api.info.interfaces.ITax;

/**
 *
 * @author gish@c
 */
public class TaxDB {

    public static void Save(ITax tax) throws Exception {
        try {

            DataAccessManager.getInstance().getTaxDB().save(tax);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(ITax tax) throws Exception {
        try {
            DataAccessManager.getInstance().getTaxDB().update(tax);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(ITax tax) throws Exception {
        try {
            DataAccessManager.getInstance().getTaxDB().delete(tax);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllTaxes() {
        return DataAccessManager.getInstance().getTaxDB().findAll();
    }
    
    public static List GetTaxValueForDates(Date startDate, Date endDate){
        return DataAccessManager.getInstance().getTaxDB().findByDates(startDate, endDate);
    }
    
}
