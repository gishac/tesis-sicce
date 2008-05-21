/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

/**
 *
 * @author gish@c
 */
public interface IGroup {

    Integer getIdGroup();
    void setIdGroup(Integer idGroup);
    String getDescription();
    void setDescription(String description);
    Set<IOptionSicce> getOptions();
    void setOptions(Set<IOptionSicce> options);
    
}
