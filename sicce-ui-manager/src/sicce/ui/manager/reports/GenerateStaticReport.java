/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.reports;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.application.ResourceMap;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.info.ConstantsProvider.OptionsProvider;
import sicce.api.util.GetResourceDir;
import sicce.ui.manager.controls.JOptionPaneExtended;

/**
 * Clase que permite generar los reportes estáticos, en base a las plantillas
 * ubicadas en el jar Reports.jar
 * @author Karu
 */
public class GenerateStaticReport {

    private static GetResourceDir resource;

    static {
        resource = new GetResourceDir();

    }
    private static URL urlLogoUCSG = resource.getResourceDir("/small_ucsg.jpg");
    private static URL urlLogoSICCE = resource.getResourceDir("/small_sice.jpg");
    private static URL pmeterReport = resource.getResourceDir("/Medidores.jasper");
    private static URL locationReport = resource.getResourceDir("/Ubicaciones.jasper");
    private static URL userReport = resource.getResourceDir("/Usuarios.jasper");
    private static URL zoneReport = resource.getResourceDir("/Zonas.jasper");
    private static URL ConsumptionbyZoneReport = resource.getResourceDir("/ConsumptionByZone.jasper");
    private static URL ConsumptionbyLocationReport = resource.getResourceDir("/ConsumptionByLocations.jasper");
    
    private static ResourceMap resourceMap;

    public GenerateStaticReport(ResourceMap resourceMap) {
        this.resourceMap = resourceMap;

    }

    private static boolean checkResource(Component pComponentePadre) {
        if (urlLogoUCSG == null || urlLogoSICCE == null || pmeterReport == null || locationReport == null || zoneReport == null || ConsumptionbyLocationReport == null || ConsumptionbyZoneReport == null ) {
            JOptionPane.showMessageDialog(pComponentePadre, resourceMap.getString("errorReport"), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static boolean GenerateStaticReport(Component pComponentePadre, OptionsProvider option) {
        try {

            if (!checkResource(pComponentePadre)) {
                return false;
            }
            Map pCriterios = new HashMap();

            //Agrego las imagenes que lleve todo reporte
            pCriterios.put("logoUCSG", urlLogoUCSG);
            pCriterios.put("logoSICCE", urlLogoSICCE);

            JasperPrint jasperPrint = null;

            switch (option) {
                case PowerMeterReport:
                    jasperPrint = JasperFillManager.fillReport(pmeterReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
                    break;
                case LocationReport:
                    jasperPrint = JasperFillManager.fillReport(locationReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
                    break;
                case UserReport:
                    jasperPrint = JasperFillManager.fillReport(userReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
                    break;
                case ZoneReport:
                    jasperPrint = JasperFillManager.fillReport(zoneReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
                    break;
                    
            }
            validateReport(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(GenerateStaticReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateStaticReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

     public static boolean GenerateStaticConsumptionReport(Component pComponentePadre,  Boolean plocation, Boolean pzone, Date startDate, Date endDate, Double cost) {
        try {

            if (!checkResource(pComponentePadre)) {
                return false;
            }
             Map pCriterios = new HashMap();

            //Agrego las imagenes que lleve todo reporte
            pCriterios.put("logoUCSG", urlLogoUCSG);
            pCriterios.put("logoSICCE", urlLogoSICCE);
            pCriterios.put("startDate", startDate);
            pCriterios.put("endDate", endDate);
            pCriterios.put("cost", cost);
            JasperPrint jasperPrint = null;

            if (plocation){
                    jasperPrint = JasperFillManager.fillReport(ConsumptionbyLocationReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
            }
            if (pzone){
                    jasperPrint = JasperFillManager.fillReport(ConsumptionbyZoneReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
            }
             validateReport(jasperPrint);
 } catch (JRException ex) {
            Logger.getLogger(GenerateStaticReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateStaticReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }
    
   
    public static void validateReport(JasperPrint jasperPrint) {
        if (jasperPrint.getPages().size() < 0) {
            JOptionPaneExtended.showMessageDialog(null, resourceMap.getString("warningReport"));
        } else {
            JasperViewer.viewReport(jasperPrint, false);
        }

    }
}
