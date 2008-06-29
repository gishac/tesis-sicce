/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author gish@c
 */
public class ReportTableModel extends SicceTableModel<IReport> {

    public ReportTableModel(List<IReport> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Nombre","Descripción", "Archivo"};
       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IReport preport = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1: 
                return preport.getReportName();
            case 2: 
                return preport.getReportDescription(); 
            default: 
                return null;
        }
    }
    
    
    
}
