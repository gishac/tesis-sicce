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

import java.util.Map;
import org.jdesktop.application.ResourceMap;
import org.netbeans.spi.wizard.WizardBranchController;
import org.netbeans.spi.wizard.WizardPanelProvider;
import sicce.api.info.interfaces.IUserSicce;
import sicce.wizard.reports.models.ReportModel;



/**
 * This is the main entry point, from which the wizard is created.
 *
 * @author Timothy Boudreau
 */
public class NewWizard extends WizardBranchController {

    protected ResourceMap resourceMap;
    
  
   public NewWizard (ResourceMap resourceMap, ReportModel reportSelected, IUserSicce currentUser){
     super( new InitialSteps( resourceMap, reportSelected, currentUser) );
     this.resourceMap = resourceMap;
   }
   
   public NewWizard (ResourceMap resourceMap, IUserSicce currentUser)
   {
     this(resourceMap, null, currentUser);
   }
   
   
    protected WizardPanelProvider getPanelProviderForStep(String step, Map collectedData) {
          return null;
        
    }

  

    
}
