/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

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
    void setLocationType (ILocationType locationType);
    ILocationType getLocationType(); 
    void setPowerMeter (IPowerMeter powerMeter);
    IPowerMeter getPowerMeter();
}
