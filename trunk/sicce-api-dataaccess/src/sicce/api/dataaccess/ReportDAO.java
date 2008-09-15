/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gish@c
 */
public class ReportDAO {

    public void callSpConsumption(Date startDate, Date endDate){
        try {
            Connection con = DataAccessManager.getInstance().getConnectionDB().getConnection();
            String ConsumptionProc = "{ call getConsumptionPerday(?,?) }";            
            CallableStatement cs = con.prepareCall(ConsumptionProc);
            cs.setDate("startDate",new java.sql.Date(startDate.getTime()));
            cs.setDate("endDate",new java.sql.Date(endDate.getTime()));
            cs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
}
