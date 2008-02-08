/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 *
 * @author karu
 */
public interface IPowerMeter {
    
    void setSerial(String serial);
    String getSerial();
    
    void setDescription(String description);
    String getDescription();
    
    void setIpAddress(String ipAddress);
    String getIpAddress();
}
