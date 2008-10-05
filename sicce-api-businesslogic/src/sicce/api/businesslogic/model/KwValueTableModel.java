/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import sicce.api.info.interfaces.IKwValue;

/**
 *
 * @author gish@c
 */
public class KwValueTableModel extends SicceTableModel<IKwValue> {

    SimpleDateFormat formatter;
    
    public KwValueTableModel(List<IKwValue> dataSource) {
        super(dataSource);
        columns = new String[]{"No.", "Fecha Inicio", "Fecha Fin", "Valor 7-22","Valor 22-7"};
        formatter = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IKwValue kwValue = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:                
                return formatter.format(kwValue.getStartDate());
            case 2:
                return formatter.format(kwValue.getEndDate());
            case 3:
                return kwValue.getKwValue1();
            case 4:
                return kwValue.getKwValue2();
            default:
                return null;
        }
    }
}
