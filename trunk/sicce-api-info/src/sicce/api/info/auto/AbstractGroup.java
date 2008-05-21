/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import sicce.api.info.interfaces.IOptionSicce;

/**
 *
 * @author gish@c
 */
public class AbstractGroup implements Serializable {

    private Integer idGroup;
    private String description;
    private Set<IOptionSicce> options = new HashSet<IOptionSicce>(0);

    // Constructors
    /** default constructor */
    public AbstractGroup() {
    }

    /** minimal constructor */
    public AbstractGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    /** full constructor */
    public AbstractGroup(Integer idGroup, String description, Set<IOptionSicce> options) {
        this.idGroup = idGroup;
        this.description = description;
        this.options = options;
    }

    // Property accessors
    public Integer getIdGroup() {
        return this.idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<IOptionSicce> getOptions() {
        return this.options;
    }

    public void setOptions(Set<IOptionSicce> options) {
        this.options = options;
    }
}
