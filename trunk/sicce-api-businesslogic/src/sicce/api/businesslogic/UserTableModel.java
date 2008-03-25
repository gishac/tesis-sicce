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
        columns = new String[]{"No.", "Usuario", "Rol", "Nombre"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IUserSicce user = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1:
                return user.getUsernameSicce();
            case 2: 
                return user.getRole().getDescription();
            case 3:
                return user.getName() + " " + user.getLastname();
            default: 
                return null;
        }
    }
    
    
}
