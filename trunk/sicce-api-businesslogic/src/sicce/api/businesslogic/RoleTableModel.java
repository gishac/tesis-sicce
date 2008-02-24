/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class RoleTableModel extends SicceTableModel<IRole> {

    public RoleTableModel(List<IRole> dataSource) {
        super(dataSource);
        columns = new String[]{"Código", "Descripcion"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IRole role = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return role.getID();
            case 1:
                return role.getDescription();
            default:
                return null;
        }
    }
    
}
