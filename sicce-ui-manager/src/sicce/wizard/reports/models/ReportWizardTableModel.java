/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.wizard.reports.models;

import sicce.api.businesslogic.model.SicceTableModel;
import sicce.api.info.Field;
import sicce.api.businesslogic.*;
import java.util.List;

/**
 *
 * @author gish@c
 */
public class ReportWizardTableModel extends SicceTableModel<Field> {
    
    
    private static List datasource;

    public ReportWizardTableModel(List<Field> dataSource) {
        this.datasource = dataSource;
        columns = new String[]{"Campo", "Operador","Valor"};
       
    }

    
        @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field fieldSelected = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return fieldSelected.getDescriptionField();
            default: 
                return null;
        }
    }
    
    
    

}
