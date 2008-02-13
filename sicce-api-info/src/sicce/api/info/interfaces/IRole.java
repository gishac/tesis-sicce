/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.List;

/**
 *
 * @author gish@c
 */
public interface IRole extends Cloneable {

    void setDescription(String description);
    String getDescription();
    int getID();
    void setID(Object id);
    void generateID();
    List<IOptionRole> getPermissions();
    Object clone();
}
