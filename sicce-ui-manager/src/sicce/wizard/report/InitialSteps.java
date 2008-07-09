/*  The contents of this file are subject to the terms of the Common Development
and Distribution License (the License). You may not use this file except in
compliance with the License.
You can obtain a copy of the License at http://www.netbeans.org/cddl.html
or http://www.netbeans.org/cddl.txt.
When distributing Covered Code, include this CDDL Header Notice in each file
and include the License file at http://www.netbeans.org/cddl.txt.
If applicable, add the following below the CDDL Header, with the fields
enclosed by brackets [] replaced by your own identifying information:
"Portions Copyrighted [year] [name of copyright owner]" */
package sicce.wizard.report;

import java.util.Date;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPanelProvider;


import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import org.jdesktop.application.ResourceMap;
import sicce.ui.manager.reports.ReportTemplate;
import sicce.wizard.report.panels.ReportForm1;
import sicce.wizard.report.panels.ReportForm2;
import sicce.wizard.report.panels.ReportForm3;
import sicce.wizard.report.panels.ReportForm4;


/**
 * Defines the first two panes of the wizard.  The second one is where the
 * user decides what comes next.
 *
 * @author karu
 */
class InitialSteps extends WizardPanelProvider {

    private static final String GENERAL_INFORMATION = "generalInformation";
    private static final String SELECTED_FIELDS = "selectedField";
    private static final String GROUP_FIELDS = "groupField";
    private static final String WHERE_FIELDS = "whereField";
    private ReportTemplate template = new ReportTemplate();

    public static final String KEY_SELECTED = "selectedFields";
    public static final String KEY_GROUP = "groupFields";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_NAME = "name";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    private ResourceMap resourceMap;
    List selectedField = null;
    List groupField  = null;
    String title = null;
    Date beginDate = null;
    Date finishDate = null;
    

    /**
     * Creates a new instance of InitialSteps
     */
    InitialSteps(ResourceMap resourceMap) {
        super("UCSG Report Wizard", new String[]{GENERAL_INFORMATION, SELECTED_FIELDS,GROUP_FIELDS, WHERE_FIELDS},
                new String[]{"Tipo de reporte", "Selección de Columnas", "Definición de Criterios", "Definición de Filtros"});
        this.resourceMap = resourceMap;
    }

    protected JComponent createPanel(final WizardController controller,
            final String id, final Map data) {

        switch (indexOfStep(id)) {
    
            case 0:
                return new ReportForm1(controller, data);
            
            case 1:
                return new ReportForm2(controller, data);
            
            case 2:
                return new ReportForm3(controller, data);
            case 3:
                return new ReportForm4(controller, data,resourceMap);


            default:
                throw new IllegalArgumentException(id);
        }
    }
    @Override
    /*
     * 
     */
    
    public Object finish(Map wizardData)  {
        try {
            
           template.runReport(wizardData);
        
        } catch (Exception ex) {
            Logger.getLogger(InitialSteps.class.getName()).log(Level.SEVERE, null, ex);
        }
               return true;
    }

    
}
