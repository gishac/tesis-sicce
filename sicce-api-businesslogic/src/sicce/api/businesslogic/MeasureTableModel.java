/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import javax.swing.table.TableColumn;
import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author gish@c
 */
public class MeasureTableModel extends SicceTableModel<IMeasure> {

    public MeasureTableModel(List<IMeasure> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Ubicación" , "Medidor" , "Fecha" , "Valor"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IMeasure measure = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex;
            case 1: 
                return  measure.getLocation().getDescription();
            case 2: 
                return  measure.getPowerMeter().getDescription();
            case 3: 
                return  measure.getDateMeasure();
            case 4: 
                return  measure.getValueMeasure();
            default: 
                return null;
        }
    }
    
}
