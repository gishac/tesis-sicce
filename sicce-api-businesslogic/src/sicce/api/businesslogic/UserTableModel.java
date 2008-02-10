/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class UserTableModel extends SicceTableModel<IUserSicce> {

    public UserTableModel(List<IUserSicce> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"Usuario", "Rol", "Nombre"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IUserSicce user = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return user.getName();
            case 1: 
                return user.getRole().getDescription();
            case 2:
                return user.getUsernameSicce() + " " + user.getLastname();
            default: 
                return null;
        }
    }
    
    
}
