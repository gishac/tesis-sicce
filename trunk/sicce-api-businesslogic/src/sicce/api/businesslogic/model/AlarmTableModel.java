/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.model;

import java.util.List;
import sicce.api.info.interfaces.IAlarm;

/**
 *
 * @author gish@c
 */
public class AlarmTableModel extends SicceTableModel<IAlarm> {

    public AlarmTableModel(List<IAlarm> dataSource){
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Descripción", "Tipo"};
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IAlarm alarm = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1:
                return alarm.getDescription();
            case 2: 
                return alarm.getAlarmTypeEnum().toString();
            default: 
                return null;
        }
    }
}
