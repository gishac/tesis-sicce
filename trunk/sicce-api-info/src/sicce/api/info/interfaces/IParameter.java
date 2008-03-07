/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 *
 * @author gish@c
 */
public interface IParameter {
    void setID(int parameterID);
    int getID();
    void setDescription(String description);
    String getDescription();
    void setValue(String value);
    String getValue();
}
