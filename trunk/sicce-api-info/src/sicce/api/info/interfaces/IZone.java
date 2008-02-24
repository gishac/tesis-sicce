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
public interface IZone {

    void setID(int id);
    int getID();
    void setDescription(String description);
    String getDescription();
    String getCode();
    void setCode(String code);
    Set<ILocation> getLocationsInZone();
    void setLocationsInZone(Set<ILocation> locationsInZone);
}
