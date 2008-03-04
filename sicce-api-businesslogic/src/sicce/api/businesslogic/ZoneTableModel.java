/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import sicce.api.info.interfaces.IZone;

/**
 *
 * @author gish@c
 */
public class ZoneTableModel extends SicceTableModel<IZone> {

    public ZoneTableModel(List<IZone> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Descripción"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IZone zone = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1:
                return zone.getDescription();
            default: 
                return null;
        }
    }
    
    
}
