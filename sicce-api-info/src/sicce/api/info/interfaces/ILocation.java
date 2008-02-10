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
public interface ILocation {

    void setDescription(String description);
    String getDescription();
    int getID();
    void setIdLocation (Integer idLocation);
    Integer getIdLocation(); 
    void setIdLocationType (Integer idLocationType);
    Integer getIdLocationType(); 
    void setIdPowerMeter (Integer idPowerMeter);
    Integer getIdPowerMeter();
}
