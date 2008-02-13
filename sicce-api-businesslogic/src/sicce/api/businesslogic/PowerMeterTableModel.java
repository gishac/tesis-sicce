/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterTableModel extends SicceTableModel<IPowerMeter> {

    public PowerMeterTableModel(List<IPowerMeter> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Serial","Dirección IP", "Descripción"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IPowerMeter pmeter = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex;
            case 1: 
                return pmeter.getSerial();
            case 2: 
                return pmeter.getIpAddress(); 
            case 3: 
                return pmeter.getDescription(); 
            default: 
                return null;
        }
    }
    
}
