package sicce.ui.manager.reports;

import java.util.HashMap;
import java.util.Map;


import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Calendar;
import net.sf.jasperreports.view.JasperViewer;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.dataaccess.OptionDB;
import sicce.api.dataaccess.ReportDB;
import sicce.api.info.OptionSicce;
import sicce.api.info.interfaces.IReport;

public abstract class GenerateDjReport {

    protected JasperPrint jprint;
    protected JasperReport jreport;
    protected Map params = new HashMap();
    protected DynamicReport dreport;
    OptionSicce option = new OptionSicce();
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public abstract DynamicReport buildReport(Map wizardData) throws Exception;

    public void runReport(Map wizardData) throws Exception {

        dreport = buildReport(wizardData);
        jprint = DynamicJasperHelper.generateJasperPrint(dreport, new ClassicLayoutManager(), DataAccessManager.getInstance().getConnectionDB().getConnection(), params);	//Creates the JasperPrint object, we pass as a Parameter
        JasperViewer.viewReport(jprint, false);
        jreport = DynamicJasperHelper.generateJasperReport(dreport, new ClassicLayoutManager());  
        String xml = DynamicJasperHelper.generateJRXML(dreport, new ClassicLayoutManager(), params, "UTF-8");
        saveReport(wizardData, xml);

    }
    
    public void saveReport(Map wizardData, String xml){
        try {
            IReport currentObject = ClassFactory.getReportInstance();
            String directory = System.getProperty("user.dir") + File.separator + "rptSicce";
            
            if(!new File(directory).exists())
                new File(directory).mkdir();
            
            String reportName = "Report" + String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".jrxml" ;
            String fullPath = directory + File.separator + reportName;
            File reportFile = new File(fullPath);
            FileOutputStream fos = new FileOutputStream(reportFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            /*FileWriter writer = new FileWriter(reportFile);
            writer.write(xml);
            writer.close();*/
            osw.write(xml);
            osw.flush();
            osw.close();
            
            String titleReport = (String) wizardData.get(KEY_NAME);
            String descriptionReport = (String) wizardData.get(KEY_DESCRIPTION);
            currentObject.setReportName(titleReport);
            currentObject.setReportDescription(descriptionReport);
            currentObject.setReportJrxml(fullPath);
            currentObject.setOptionSicce(OptionDB.FindOptionByID(9));
            ReportDB.Save(currentObject);
        } catch (Exception ex) {
            Logger.getLogger(GenerateDjReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
