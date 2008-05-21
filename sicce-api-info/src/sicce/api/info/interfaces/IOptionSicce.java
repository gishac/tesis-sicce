/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 *
 * @author gish@c
 */
public interface IOptionSicce {

    void setDescription(String description);
    String getDescription();
    String getActionCommand();
    int getID();
    IGroup getGroup();
    void setGroup(IGroup group);
}
