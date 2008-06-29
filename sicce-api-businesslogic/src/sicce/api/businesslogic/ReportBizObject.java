/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.dataaccess.ReportDB;
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author gish@c
 */
public class ReportBizObject {

    public List<IReport> GetAllReport() {
        return ReportDB.GetAllReports(); 
    }
}
