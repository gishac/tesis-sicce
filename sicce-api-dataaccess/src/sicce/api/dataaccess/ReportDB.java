/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author gish@c
 */
public class ReportDB {

    public static void Save(IReport report) throws Exception {
        try {
            DataAccessManager.getInstance().getReportDB().save(report);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Update(IReport report) throws Exception {
        try {
           DataAccessManager.getInstance().getReportDB().update(report);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void Delete(IReport report) throws Exception {
        try {
            DataAccessManager.getInstance().getReportDB().delete(report);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List GetAllReports() {
        return DataAccessManager.getInstance().getReportDB().findAll();
    }

    public static IReport FindReportByID(int id) {
        return DataAccessManager.getInstance().getReportDB().findById(id);
    }
}
