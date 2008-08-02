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
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.view.JasperViewer;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.dataaccess.OptionDB;
import sicce.api.dataaccess.ReportDB;
import sicce.api.info.Field;
import sicce.api.info.OptionSicce;
import sicce.api.util.SerializableUtil;
import sicce.wizard.reports.models.FieldHandler;
import sicce.wizard.reports.models.ReportModel;

public abstract class GenerateDjReport {

    protected JasperPrint jprint;
    protected JasperReport jreport;
    protected Map params = new HashMap();
    protected DynamicReport dreport;
    OptionSicce option = new OptionSicce();
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    public static final String KEY_ORT_VERTICAL = "ortVertical";
    public static final String KEY_ORT_HORIZONTAL = "ortHorizontal";
    public static final String KEY_FIELD = "KeyField";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_FIELD_CHART = "FieldChart";
    public static final String KEY_BL_CHART = "blChart";
    public static final String KEY_BL_IS_LOADED = "isloaded";
    
    public abstract DynamicReport buildReport(Map wizardData) throws Exception;

    public void runReport(Map wizardData) throws Exception {
        wizardData.put(KEY_BL_IS_LOADED, new Boolean(true));
        dreport = buildReport(wizardData);
        jprint = DynamicJasperHelper.generateJasperPrint(dreport, new ClassicLayoutManager(), DataAccessManager.getInstance().getConnectionDB().getConnection(), params);	//Creates the JasperPrint object, we pass as a Parameter
        JasperViewer.viewReport(jprint, false);
        jreport = DynamicJasperHelper.generateJasperReport(dreport, new ClassicLayoutManager());
        String xml = DynamicJasperHelper.generateJRXML(dreport, new ClassicLayoutManager(), params, "UTF-8");
        saveReport(wizardData, xml);

    }

    public void saveReport(Map wizardData, String xml) {
        try {
            ReportModel currentObject = new ReportModel();
            String directory = System.getProperty("user.dir") + File.separator + "rptSicce";

            if (!new File(directory).exists()) {
                new File(directory).mkdir();
            }
            String titleReport = (String) wizardData.get(KEY_NAME);
            String reportName =  titleReport + ".jrxml";
            String fullPath = directory + File.separator + reportName;
            if(new File(fullPath).exists())
                new File(fullPath).delete();
            
            String descriptionReport = (String) wizardData.get(KEY_DESCRIPTION);
            FieldHandler pFieldHandler = (FieldHandler) wizardData.get(KEY_FIELD);
            Boolean pvertical = (Boolean) wizardData.get(KEY_ORT_VERTICAL);
            Boolean phorizontal = (Boolean) wizardData.get(KEY_ORT_HORIZONTAL);
            List listFilters = (List) wizardData.get(KEY_WHERE);
            Date start = (Date) wizardData.get(KEY_BEGIN_DATE);
            Date end = (Date) wizardData.get(KEY_FINISH_DATE);
            Boolean chart = (Boolean) wizardData.get(KEY_BL_CHART);
            Field chartSelected = (Field) wizardData.get(KEY_FIELD_CHART);
            Boolean isloaded = (Boolean)wizardData.get(KEY_BL_IS_LOADED);
            currentObject.setReportName(titleReport);
            currentObject.setDescription(descriptionReport);
            currentObject.setField(pFieldHandler);
            currentObject.setOrtVertical(pvertical);
            currentObject.setOrtHorizontal(phorizontal);
            currentObject.setFilters(listFilters);
            currentObject.setStartDate(start);
            currentObject.setEndDate(end);
            currentObject.setChart(chart);
            currentObject.setCharSelected(chartSelected);
            currentObject.setIsLoaded(isloaded);
            SerializableUtil.Serialize(currentObject, fullPath);
            
        } catch (Exception ex) {
            Logger.getLogger(GenerateDjReport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
