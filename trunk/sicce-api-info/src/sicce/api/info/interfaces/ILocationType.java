/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import org.apache.cayenne.PersistenceState;

/**
 *
 * @author gish@c
 */
public interface ILocationType {

    void setDescription(String description);
    String getDescription();
    int getID();
}
