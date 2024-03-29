/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.List;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class RoleTableModel extends SicceTableModel<IRole> {

    public RoleTableModel(List<IRole> dataSource) {
        super(dataSource);
        columns = new String[]{"No.","Código", "Descripción"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IRole role = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return role.getIdRole();
            case 2:
                return role.getDescription();
            default:
                return null;
        }
    }
    
}
