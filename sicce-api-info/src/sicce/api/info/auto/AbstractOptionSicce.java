package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.interfaces.IGroup;
import sicce.api.info.interfaces.IRole;

/**
 * AbstractOptionSicce generated by MyEclipse Persistence Tools
 */
public abstract class AbstractOptionSicce implements java.io.Serializable {

    // Fields
    protected Integer idOptionSicce;
    protected String description;
    protected String actionCommand;
    protected String icon;
    protected Set<IRole> roles = new HashSet<IRole>(0);
    private IGroup group;

    

    // Constructors
    /** default constructor */
    public AbstractOptionSicce() {
    }

    /** minimal constructor */
    public AbstractOptionSicce(Integer idOptionSicce) {
        this.idOptionSicce = idOptionSicce;
    }

    /** full constructor */
    public AbstractOptionSicce(Integer idOptionSicce, String description,
            String icon, Set<IRole> roles) {
        this.idOptionSicce = idOptionSicce;
        this.description = description;
        this.icon = icon;
        this.roles = roles;
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
