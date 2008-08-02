/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.wizard.reports.models;

import sicce.api.businesslogic.model.*;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author gish@c
 */
public class ReportTableModel extends SicceTableModel<ReportModel> {

    private Icon imgReport;
     
     
    public ReportTableModel(List<ReportModel> dataSource, Icon imgReport) {
        this.dataSource = dataSource;
        this.imgReport = imgReport;
        columns = new String[]{"Nombre","Descripción", "Archivo"};
       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReportModel preport = getDataSource().get(rowIndex);
        switch(columnIndex)
        {    
            case 0: 
                return preport.getReportName();
            case 1: 
                return preport.getDescription(); 
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
