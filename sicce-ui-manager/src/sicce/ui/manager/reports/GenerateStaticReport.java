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
import sicce.api.info.interfaces.IUserSicce;
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
    private static URL zoneReport = resource.getResourceDir("/Zonas.jasper");
    private static URL ConsumptionbyZoneReport = resource.getResourceDir("/ConsumptionByZone.jasper");
    private static URL ConsumptionbyLocationReport = resource.getResourceDir("/ConsumptionByLocations.jasper");
    private static URL UserPowerMeter = resource.getResourceDir("UserPowerMeter.jasper");
    private static URL UserPowerMeterException = resource.getResourceDir("UserPowerMeterException.jasper");
    private static URL UserPowerMeterAlarm = resource.getResourceDir("UserPowerMeterAlarm.jasper");
    
    private static ResourceMap resourceMap;

    public GenerateStaticReport(ResourceMap resourceMap) {
        this.resourceMap = resourceMap;

    }

    private boolean checkResource(Component pComponentePadre) {
        if (urlLogoUCSG == null || urlLogoSICCE == null || zoneReport == null || ConsumptionbyLocationReport == null 
                || ConsumptionbyZoneReport == null || UserPowerMeter == null || UserPowerMeterException == null || UserPowerMeterAlarm == null ) {
            JOptionPane.showMessageDialog(pComponentePadre, resourceMap.getString("errorReport"), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public  boolean GenerateStaticReport(Component pComponentePadre,OptionsProvider option, IUserSicce currentUser, ResourceMap resourceMap) {
        try {

            if (!checkResource(pComponentePadre)) {
                return false;
            }
            Map pCriterios = new HashMap();

            //Agrego las imagenes que lleve todo reporte
            pCriterios.put("logoUCSG", urlLogoUCSG);
            pCriterios.put("logoSICCE", urlLogoSICCE);
            pCriterios.put("iduser", currentUser.getIdUserSicce());

            JasperPrint jasperPrint = null;
             switch (option) {
            case UserPowerMeterReport:
            jasperPrint = JasperFillManager.fillReport(UserPowerMeter.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
            break;
            case UserPowerMeterException:
            jasperPrint = JasperFillManager.fillReport(UserPowerMeterException.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
            break;
            case UserPowerMeterAlarm:
            jasperPrint = JasperFillManager.fillReport(UserPowerMeterAlarm.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
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

     public  boolean GenerateStaticTotalConsumptionReport(Component pComponentePadre, Boolean plocation, Boolean pzone, Map parameters) {
        try {
           if (!checkResource(pComponentePadre)) {
                return false;
            }
             Map pCriterios = new HashMap();

            //Agrego las imagenes que lleve todo reporte
            pCriterios.put("logoUCSG", urlLogoUCSG);
            pCriterios.put("logoSICCE", urlLogoSICCE);
            pCriterios.putAll(parameters);
            
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
    
     
      public  boolean GenerateStaticIndividualConsumptionReport(Component pComponentePadre, Map parameters) {
        try {
           if (!checkResource(pComponentePadre)) {
                return false;
            }
             Map pCriterios = new HashMap();

            //Agrego las imagenes que lleve todo reporte
            pCriterios.put("logoUCSG", urlLogoUCSG);
            pCriterios.put("logoSICCE", urlLogoSICCE);
            pCriterios.putAll(parameters);
            
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport(ConsumptionbyLocationReport.openStream(), pCriterios, DataAccessManager.getInstance().getConnectionDB().getConnection());
             validateReport(jasperPrint);
 } catch (JRException ex) {
            Logger.getLogger(GenerateStaticReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateStaticReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    } 
     
     
     
   
    public  void validateReport(JasperPrint jasperPrint) {
        if (jasperPrint.getPages().size() < 0) {
            JOptionPaneExtended.showMessageDialog(null, resourceMap.getString("warningReport"));
        } else {
            JasperViewer.viewReport(jasperPrint, false);
        }

    }
}
