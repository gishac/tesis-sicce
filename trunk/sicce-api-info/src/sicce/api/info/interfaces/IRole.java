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
public interface IRole extends Cloneable {

    void setDescription(String description);
    String getDescription();
    int getID();
    void setID(int id);
    Set<IOptionSicce> getPermissions();
    Set<IUserSicce> getUsersInRole();
    void addPermission(IOptionSicce permission);
    void removePermission(IOptionSicce permission);
    Object clone();
}
