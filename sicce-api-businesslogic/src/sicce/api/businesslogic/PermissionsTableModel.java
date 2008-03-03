/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import java.util.Set;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;

/**
 *
 * @author gish@c
 */
public class PermissionsTableModel extends SicceTableModel<IOptionSicce> {

    RoleBizObject roleBizObject;
    private IRole role;

    /**
     * Constructor
     * @param optionsList Lista de opciones disponibles
     * @param role Rol al cual van a ser asignados los permisos
     */
    public PermissionsTableModel(List<IOptionSicce> dataSource, IRole role) {

        if (role == null) {
            this.role = ClassFactory.getRoleInstance();
        } else {
            this.role = role;
        }
        this.columns = new String[]{"Opción", "Estado"};
        this.dataSource = dataSource;
        this.roleBizObject = new RoleBizObject();
    }

    @Override
    public int getRowCount() {
        return getDataSource().size();
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
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        synchronized (this) {
            if(getDataSource().size() <= rowIndex)
                return;
            boolean checked = Boolean.parseBoolean(value.toString());
            IOptionSicce option = getDataSource().get(rowIndex);
            if (checked) {                
                AddPermissionToRole(option, role);
            }
            if(!checked){
                RemovePermission(option, role);
            }
        }
    }

    @Override
    public void setDataSource(List dataSource) {
        if(dataSource == null)
            this.role = ClassFactory.getRoleInstance();
    }
    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return !isReadOnly();
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IOptionSicce option = getDataSource().get(rowIndex);
        boolean state = false;
        for (IOptionSicce activeOption : role.getPermissions()) {
            if (option.getID() == activeOption.getID()) {
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
     * Retorna la coleccion de permisos asignado
     * @return
     */
    public Set<IOptionSicce> getPermissions() {
        return this.role.getPermissions();
    }
    
    /**
     * Agrega un permiso a un rol
     * @param permission
     * @param role
     */
    private void AddPermissionToRole(IOptionSicce permission, IRole role) {
        if (!roleBizObject.PermissionExists(permission.getID(), role)) {
            role.addPermission(permission);
        }
    }
    
    /**
     * Elimina un permiso de un rol
     * @param permission
     * @param role
     */
    private void RemovePermission(IOptionSicce permission, IRole role){
        for(IOptionSicce permissionInRole : role.getPermissions())
        {
            if(permissionInRole.getID() == permission.getID()){
                permission = permissionInRole;
                break;
            } 
        }
        role.removePermission(permission);
    }

}
