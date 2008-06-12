/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.wizard.report;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.spi.wizard.WizardBranchController;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardException;
import org.netbeans.spi.wizard.WizardPage.WizardResultProducer;
import sicce.ui.manager.controls.JOptionPaneExtended;
import sicce.ui.manager.reports.FieldHandler;
import sicce.ui.manager.reports.ReportTemplate;

/**
 *
 * @author Administrador
 */
public class ReportResult implements WizardResultProducer {
    private ReportTemplate template = new ReportTemplate();

    public static final String KEY_SELECTED = "selectedFields";
    public static final String KEY_GROUP = "groupFields";
    List selectedField = null;
    List groupField  = null;
    public Object finish(Map wizardData) throws WizardException {
        try {
            
           selectedField = (List) wizardData.get(KEY_SELECTED);
           groupField = (List) wizardData.get(KEY_GROUP);
           template.runReport("Reporte",selectedField ,groupField); 
            
        
        } catch (Exception ex) {
            Logger.getLogger(ReportResult.class.getName()).log(Level.SEVERE, null, ex);
        }
                 throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean cancel(Map arg0) {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
