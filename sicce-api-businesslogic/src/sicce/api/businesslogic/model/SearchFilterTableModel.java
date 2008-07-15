/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.List;
import javax.swing.JButton;
import sicce.api.info.interfaces.ISimpleQueryResult;

/**
 *
 * @author karu
 */
public class SearchFilterTableModel extends SicceTableModel<ISimpleQueryResult> {

  

    public SearchFilterTableModel(List<ISimpleQueryResult> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"Descripción"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ISimpleQueryResult pdescription = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pdescription.getResult();
          
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3)
            return JButton.class;
        return super.getColumnClass(columnIndex);
    }
    
    


   
}
