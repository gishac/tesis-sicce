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
import java.util.HashMap;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPanelProvider;


import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import org.jdesktop.application.ResourceMap;
import sicce.api.info.interfaces.IUserSicce;
import sicce.ui.manager.reports.ReportTemplate;
import sicce.wizard.report.panels.ReportWizardStep1;
import sicce.wizard.report.panels.ReportWizardStep2;
import sicce.wizard.report.panels.ReportWizardStep3;
import sicce.wizard.report.panels.ReportWizardStep4;
import sicce.wizard.reports.models.ReportModel;

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
    public static final String KEY_SELECTED = "selectedFields";
    public static final String KEY_GROUP = "groupFields";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_NAME = "name";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ORT_VERTICAL = "ortVertical";
    public static final String KEY_ORT_HORIZONTAL = "ortHorizontal";
    public static final String KEY_FIELD = "KeyField";
    public static final String KEY_FIELD_CHART = "FieldChart";
    public static final String KEY_BL_CHART = "blChart";
     public static final String KEY_BL_IS_LOADED = "isloaded";
    private ReportTemplate template = new ReportTemplate();
    private ResourceMap resourceMap;
    private ReportModel report;
    private IUserSicce currentUser;
    List selectedField = null;
    List groupField = null;
    String title = null;
    Date beginDate = null;
    Date finishDate = null;

    /**
     * Creates a new instance of InitialSteps
     */
    InitialSteps(ResourceMap resourceMap, ReportModel report, IUserSicce currentUser) {
        super("SICCE - Asistente de Reportes", new String[]{GENERAL_INFORMATION, SELECTED_FIELDS, GROUP_FIELDS, WHERE_FIELDS},
                new String[]{"Tipo de reporte", "Selección de Columnas", "Definición de Criterios", "Definición de Filtros"});
        this.resourceMap = resourceMap;
         this.report = report;
         this.currentUser = currentUser;
    }

    protected JComponent createPanel(final WizardController controller,
            final String id, final Map wizardData) {

        if (report != null) {
           
            wizardData.put(KEY_NAME, report.getReportName());
            wizardData.put(KEY_DESCRIPTION, report.getDescription());
            wizardData.put(KEY_FIELD, report.getField());  
            wizardData.put(KEY_ORT_VERTICAL, report.getOrtVertical());
            wizardData.put(KEY_ORT_HORIZONTAL, report.getOrtHorizontal());
            wizardData.put(KEY_WHERE, report.getFilters());
            wizardData.put(KEY_BEGIN_DATE, report.getStartDate());
            wizardData.put(KEY_FINISH_DATE, report.getEndDate());
            wizardData.put(KEY_BL_CHART, report.getChart());
            wizardData.put(KEY_FIELD_CHART, report.getCharSelected());
            wizardData.put(KEY_BL_IS_LOADED, report.getIsLoaded());
        }
        switch (indexOfStep(id)) {

            case 0:
                return new ReportWizardStep1(controller, wizardData);

            case 1:
                return new ReportWizardStep2(controller, wizardData);

            case 2:
                return new ReportWizardStep3(controller, wizardData);
            case 3:
                return new ReportWizardStep4(controller, wizardData, resourceMap);


            default:
                throw new IllegalArgumentException(id);
        }
    }

    @Override
    /*
     * 
     */
    public Object finish(Map wizardData) {
        try {

            template.runReport(wizardData, currentUser);

        } catch (Exception ex) {
            Logger.getLogger(InitialSteps.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
