package sicce.ui.manager.reports;

import sicce.api.info.Field;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import java.awt.Rectangle;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.ResourceMap;
import org.netbeans.api.wizard.WizardDisplayer;
import sicce.api.businesslogic.ClassFactory;
import sicce.api.dataaccess.ConnectDAO;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.dataaccess.OptionDB;
import sicce.api.dataaccess.ReportDB;
import sicce.api.info.OptionSicce;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IReport;
import sicce.wizard.report.NewWizard;

public abstract class GenerateDjReport {

    protected JasperPrint jprint;
    protected JasperReport jreport;
    protected Map params = new HashMap();
    protected DynamicReport dreport;
    OptionSicce option = new OptionSicce();

    public abstract DynamicReport buildReport(String title, List<Field> listSelected, List<Field> listGroup, ResourceMap resourceMap) throws Exception;

    public void runReport(String titleReport, List<Field> listSelected, List<Field> listGroup, ResourceMap resourceMap) throws Exception {

        ConnectDAO con = new ConnectDAO();

        dreport = buildReport(titleReport, listSelected, listGroup, resourceMap);
        IReport currentObject = ClassFactory.getReportInstance();
        jprint = DynamicJasperHelper.generateJasperPrint(dreport, new ClassicLayoutManager(), DataAccessManager.getInstance().getConnectionDB().getConnection(), params);	//Creates the JasperPrint object, we pass as a Parameter
        JasperViewer.viewReport(jprint, false);

        jreport = DynamicJasperHelper.generateJasperReport(dreport, new ClassicLayoutManager());
      

        //DynamicJasperHelper.generateJRXML(dreport, new ClassicLayoutManager(), params, "UTF-8", out);

        String xml = DynamicJasperHelper.generateJRXML(dreport, new ClassicLayoutManager(), params, "UTF-8");

        currentObject.setReportName(titleReport);
        currentObject.setReportDescription(titleReport);
        //currentObject.setReportJrxml(out);
        currentObject.setReportJrxml(xml);
        currentObject.setOptionSicce(OptionDB.FindOptionByID(9));
        ReportDB.Save(currentObject);
        System.out.println("xmlDATA" + xml);

    }
}
