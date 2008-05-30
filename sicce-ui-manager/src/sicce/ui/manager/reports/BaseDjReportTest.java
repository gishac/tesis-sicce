package sicce.ui.manager.reports;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import java.util.List;
import net.sf.jasperreports.view.JasperViewer;
import sicce.api.dataaccess.ConnectDAO;
import sicce.api.dataaccess.DataAccessManager;



public abstract class BaseDjReportTest {

    protected JasperPrint jp;
    protected JasperReport jr;
    protected Map params = new HashMap();
    protected DynamicReport dr;

    public abstract DynamicReport buildReport(String title, List<Field> listSelected,  List<Field> listGroup) throws Exception;

    public void runReport(String titleReport, List<Field> listSelected,  List<Field> listGroup) throws Exception {

    ConnectDAO con = new ConnectDAO();

        dr = buildReport(titleReport, listSelected,listGroup);

       jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), DataAccessManager.getInstance().getConnectionDB().getConnection() , params);	//Creates the JasperPrint object, we pass as a Parameter
       JasperViewer.viewReport(jp, false);
       jr = DynamicJasperHelper.generateJasperReport(dr, new ClassicLayoutManager());
    }
}
