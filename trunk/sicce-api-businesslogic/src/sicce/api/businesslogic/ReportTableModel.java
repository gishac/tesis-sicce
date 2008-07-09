/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author gish@c
 */
public class ReportTableModel extends SicceTableModel<IReport> {

    private Icon imgReport;
    public ReportTableModel(List<IReport> dataSource, Icon imgReport) {
        this.dataSource = dataSource;
        this.imgReport = imgReport;
        columns = new String[]{"Nombre","Descripción", "Archivo"};
       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IReport preport = getDataSource().get(rowIndex);
        switch(columnIndex)
        {    
            case 0: 
                return preport.getReportName();
            case 1: 
                return preport.getReportDescription(); 
            case 2:
                JButton reportButton = new JButton();
                reportButton.setIcon(imgReport);
                reportButton.setPreferredSize(new Dimension(30,20));
                
                return reportButton;
            default: 
                return null;
        }
    }
    
      @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2) {
            return JButton.class;
        }
        return super.getColumnClass(columnIndex);
    }

    
    
}
