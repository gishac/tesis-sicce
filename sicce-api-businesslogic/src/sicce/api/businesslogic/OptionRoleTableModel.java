/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sicce.api.info.interfaces.IOptionRole;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class OptionRoleTableModel extends SicceTableModel<IOptionRole> {

    private List<IOptionSicce> optionsList;
    private IRole role;
    private Map<Integer,Integer> permissions;

    /**
     * Devuelve la lista de opciones
     * @return
     */
    public List<IOptionSicce> getOptionsList() {
        if (optionsList == null) {
            optionsList = new ArrayList<IOptionSicce>();
        }
        return optionsList;
    }

    /**
     * Constructor
     * @param optionsList Lista de opciones disponibles
     * @param role Rol al cual van a ser asignados los permisos
     */
    public OptionRoleTableModel(List<IOptionSicce> optionsList, IRole role) {
        
        if (role == null) {
            role = ClassFactory.getRoleInstance();
        }
        else{
            this.role = (IRole)role.clone();
            copyOptionsToDataSource(this.role);
        }
        columns = new String[]{"Opción", "Estado"};
        permissions = new HashMap<Integer, Integer>();
        this.optionsList = optionsList;
        
    }

    @Override
    public int getRowCount() {
        return getOptionsList().size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        synchronized (this) {
           
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IOptionSicce option = getOptionsList().get(rowIndex);
        boolean state = false;
        for (IOptionRole activeOption : getDataSource()) {
            if (option.getID() == activeOption.getOption().getID()) {
                state = true;
                break;
            }
        }
        switch (columnIndex) {
            case 0:
                return option.getDescription();
            case 1:
                return state;
            default:
                return null;
        }
    }

    /**
     * Copia los permisos del rol a la coleccion de permisos designado como dataSource
     * @param role
     */
    private void copyOptionsToDataSource(IRole role) {
        dataSource = null;
        if(role.getPermissions() == null)
            return;
        
    }
    
    public Map<Integer,Integer> getPermissions()
    {
        return permissions;
    }
}
