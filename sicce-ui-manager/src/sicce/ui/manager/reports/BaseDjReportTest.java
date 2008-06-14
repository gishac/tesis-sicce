package sicce.ui.manager.reports;

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
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.view.JasperViewer;
import sicce.api.dataaccess.ConnectDAO;
import sicce.api.dataaccess.DataAccessManager;



public abstract class BaseDjReportTest {

    protected JasperPrint jprint;
    protected JasperReport jreport;
    protected Map params = new HashMap();
    protected DynamicReport dreport;

    public abstract DynamicReport buildReport(String title, List<Field> listSelected,  List<Field> listGroup, Date begin, Date finish) throws Exception;

    public void runReport(String titleReport, List<Field> listSelected,  List<Field> listGroup, Date begin, Date finish) throws Exception {

    ConnectDAO con = new ConnectDAO();

        dreport = buildReport(titleReport, listSelected,listGroup, begin, finish);

       jprint = DynamicJasperHelper.generateJasperPrint(dreport, new ClassicLayoutManager() , DataAccessManager.getInstance().getConnectionDB().getConnection() , params);	//Creates the JasperPrint object, we pass as a Parameter
       JasperViewer.viewReport(jprint, true);
       jreport = DynamicJasperHelper.generateJasperReport(dreport, new ClassicLayoutManager());
       
       String xmlData = DynamicJasperHelper.generateJRXML(dreport, new ClassicLayoutManager(), params, "UTF-8");
       System.out.println("xmlDATA" + xmlData);
       
    }
}
