package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.interfaces.IGroup;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IRole;

/**
 * Representacion de las opciones del sistema
 * @author gish@c
 */
public abstract class AbstractOptionSicce implements java.io.Serializable, IOptionSicce {

    // Fields
    
    /**
     * Identificador de la opcion
     */
    protected Integer idOptionSicce;
    
    /**
     * Descripcion de la opcion
     */
    protected String description;
    
    /**
     * Accion a ser ejecutada por la opcion
     */
    protected String actionCommand;
    
    /**
     * Icono asignado la opcion
     */
    protected String icon;
    
    /**
     * Roles que tienen asignada la opcion
     * @see IRole
     */
    protected Set<IRole> roles = new HashSet<IRole>(0);
    
    /**
     * Grupo al que pertenece la opcion
     * @see IGroup
     */
    protected IGroup group;
    
    
    // Constructors
    
    /**
     * Constructor
     */
    public AbstractOptionSicce() {
    }

    
    // Property accessors
    public Integer getIdOptionSicce() {
        return this.idOptionSicce;
    }

    public void setIdOptionSicce(Integer idOptionSicce) {
        this.idOptionSicce = idOptionSicce;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionCommand() {
        return actionCommand;
    }
    
    public void setActionCommand(String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<IRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<IRole> roles) {
        this.roles = roles;
    }

    public IGroup getGroup() {
        return group;
    }

    public void setGroup(IGroup group) {
        this.group = group;
    }
}
