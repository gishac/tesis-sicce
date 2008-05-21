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

import java.awt.Rectangle;
import java.util.Map;
import javax.swing.UIManager;
import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.WizardBranchController;
import org.netbeans.spi.wizard.WizardPanelProvider;
import sicce.wizard.report.panels.SpeciesPanel;


/**
 * This is the main entry point, from which the wizard is created.
 *
 * @author Timothy Boudreau
 */
public class NewWizard extends WizardBranchController {

    public NewWizard(  ) {
        super( new InitialSteps(  ) );
    }
   
    protected WizardPanelProvider getPanelProviderForStep(String step, Map collectedData) {
        //There's only one branch point, so we don't need to test the
        //value of step
        Object species = collectedData.get(SpeciesPanel.KEY_SPECIES);
        if (SpeciesPanel.VALUE_CAT.equals(species)) {
            // return getCatLoversSteps();
            return null;
        } else if (SpeciesPanel.VALUE_DOG.equals(species)) {
            // return getDogLoversSteps();
            return null;
        } else if (SpeciesPanel.VALUE_GERBIL.equals(species)) {
            return null;//new GerbilSteps();
        } else {
            return null;
        }
    }

    
}
