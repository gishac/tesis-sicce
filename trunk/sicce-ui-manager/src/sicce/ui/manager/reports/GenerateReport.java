/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.reports;

import java.awt.Component;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sicce.api.dataaccess.DataAccessManager;
import sicce.api.util.GetResourceDir;

/**
 * Clase que permite generar los reportes estáticos, en base a las plantillas
 * ubicadas en el jar Reports.jar
 * @author Karu
 */
public class GenerateReport {

    private static GetResourceDir resource;
    
       static{
        resource = new GetResourceDir();  
 
    }
                    
    private static URL  urlLogoUCSG = resource.getResourceDir("/small_ucsg.jpg");
    private static URL urlLogoSICCE = resource.getResourceDir("/small_sice.jpg");
    private static URL pmeterReport = resource.getResourceDir("/Medidores.jasper");
    private static URL lTypeReport = resource.getResourceDir("/TiposDependencia.jasper");
    private static URL locationReport = resource.getResourceDir("/Ubicaciones.jasper");
    private static URL userReport = resource.getResourceDir("/Usuarios.jasper");
    private static URL zoneReport = resource.getResourceDir("/Zonas.jasper");            
    
    
    private static boolean checkResource(Component pComponentePadre)
    {
       if (urlLogoUCSG == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar logoUCSG en el Reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       
       if (urlLogoSICCE == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar logoUCSG en el Reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       if (pmeterReport == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar el reporte de Medidores.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (locationReport == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar el reporte de Dependencias.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (lTypeReport == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar el reporte de Tipo de Dependencias.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (userReport == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar el reporte de Usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
        if (zoneReport == null)
        {   
           JOptionPane.showMessageDialog(pComponentePadre, "Error al cargar el reporte de Zonas.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       return true; 
    }
    
        public static boolean GeneratePowerMeterReport(Component pComponentePadre) {
    
        if (!checkResource(pComponentePadre))
                return false;                       
        Map pCriterios = new HashMap();

        //Agrego las imagenes que lleve todo reporte
        pCriterios.put("logoUCSG", urlLogoUCSG);
        pCriterios.put("logoSICCE", urlLogoSICCE);
        
        JasperPrint jasperPrint = null;
        
        try {
            
            jasperPrint = JasperFillManager.fillReport(pmeterReport.openStream(), pCriterios,  DataAccessManager.getInstance().getConnectionDB().getConnection());   
            JasperViewer.viewReport(jasperPrint, false);
            return true;

        } catch (Exception e) {
                                    
            e.printStackTrace();                              
            return false;
        }

    }

        
        public static boolean GenerateLocationReport(Component pComponentePadre) {
    
        if (!checkResource(pComponentePadre))
                return false;                       
        Map pCriterios = new HashMap();

        //Agrego las imagenes que lleve todo reporte
        pCriterios.put("logoUCSG", urlLogoUCSG);
        pCriterios.put("logoSICCE", urlLogoSICCE);
        
        JasperPrint jasperPrint = null;
        
        try {
            
            jasperPrint = JasperFillManager.fillReport(locationReport.openStream(), pCriterios,  DataAccessManager.getInstance().getConnectionDB().getConnection());   
            JasperViewer.viewReport(jasperPrint, false);
            return true;

        } catch (Exception e) {
                                    
            e.printStackTrace();                              
            return false;
        }

    }
        
        
        
        public static boolean GenerateLTypeReport(Component pComponentePadre) {
    
        if (!checkResource(pComponentePadre))
                return false;                       
        Map pCriterios = new HashMap();

        //Agrego las imagenes que lleve todo reporte
        pCriterios.put("logoUCSG", urlLogoUCSG);
        pCriterios.put("logoSICCE", urlLogoSICCE);
        
        JasperPrint jasperPrint = null;
        
        try {
            
            jasperPrint = JasperFillManager.fillReport(lTypeReport.openStream(), pCriterios,  DataAccessManager.getInstance().getConnectionDB().getConnection());   
            JasperViewer.viewReport(jasperPrint, false);
            return true;

        } catch (Exception e) {
                                    
            e.printStackTrace();                              
            return false;
        }

    }

        
        
       public static boolean GenerateUserReport(Component pComponentePadre) {
    
        if (!checkResource(pComponentePadre))
                return false;                       
        Map pCriterios = new HashMap();

        //Agrego las imagenes que lleve todo reporte
        pCriterios.put("logoUCSG", urlLogoUCSG);
        pCriterios.put("logoSICCE", urlLogoSICCE);
        
        JasperPrint jasperPrint = null;
        
        try {
            
            jasperPrint = JasperFillManager.fillReport(userReport.openStream(), pCriterios,  DataAccessManager.getInstance().getConnectionDB().getConnection());   
            JasperViewer.viewReport(jasperPrint, false);
            return true;

        } catch (Exception e) {
                                    
            e.printStackTrace();                              
            return false;
        }

    }

       
        public static boolean GenerateZoneReport(Component pComponentePadre) {
    
        if (!checkResource(pComponentePadre))
                return false;                       
        Map pCriterios = new HashMap();

        //Agrego las imagenes que lleve todo reporte
        pCriterios.put("logoUCSG", urlLogoUCSG);
        pCriterios.put("logoSICCE", urlLogoSICCE);
        
        JasperPrint jasperPrint = null;
        
        try {
            
            jasperPrint = JasperFillManager.fillReport(zoneReport.openStream(), pCriterios,  DataAccessManager.getInstance().getConnectionDB().getConnection());   
            JasperViewer.viewReport(jasperPrint, false);
            return true;

        } catch (Exception e) {
                                    
            e.printStackTrace();                              
            return false;
        }

    }


    
}
