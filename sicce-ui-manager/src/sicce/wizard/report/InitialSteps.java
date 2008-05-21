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

import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardException;
import org.netbeans.spi.wizard.WizardPanelProvider;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import sicce.api.businesslogic.OptionBizObject;
import sicce.api.businesslogic.SicceComboBoxModel;
import sicce.api.businesslogic.SicceComboBoxRenderer;
import sicce.api.info.ConstantsProvider.DisplayMemberRenderType;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.wizard.report.panels.ReportForm1;
import sicce.wizard.report.panels.ReportForm2;
import sicce.wizard.report.panels.ReportForm3;
import sicce.wizard.report.panels.SpeciesPanel;

/**
 * Defines the first two panes of the wizard.  The second one is where the
 * user decides what comes next.
 *
 * @author Timothy Boudreau
 */
class InitialSteps extends WizardPanelProvider {

    private static final String GENERAL_INFORMATION = "generalInformation";
    private static final String SELECTED_FIELDS = "selectedField";
    private static final String GROUP_FIELDS = "groupField";
    

    /**
     * Creates a new instance of InitialSteps
     */
    InitialSteps() {
        super("UCSG Report Wizard", new String[]{GENERAL_INFORMATION, SELECTED_FIELDS,GROUP_FIELDS},
                new String[]{"Tipo de reporte", "Selección de Columnas", "Definición de Criterios"});
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
          


            default:
                throw new IllegalArgumentException(id);
        }
    }
}
