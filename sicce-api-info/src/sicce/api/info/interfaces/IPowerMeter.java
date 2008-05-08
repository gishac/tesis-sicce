/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Set;

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
    
    Set<IAlarm> getAlarms();
    void setAlarms(Set<IAlarm> alarms);
}
