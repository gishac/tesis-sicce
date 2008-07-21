/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.net.Socket;
import java.util.Set;

/**
 *
 * @author karu
 */
public interface IPowerMeter {

    void setSerial(String serial);

    String getSerial();

    void setDeviceID(String deviceID);

    String getDeviceID();

    void setDescription(String description);

    String getDescription();

    void setIpAddress(String ipAddress);

    String getIpAddress();

    Set<IAlarm> getAlarms();

    void setAlarms(Set<IAlarm> alarms);

    Socket getSocket();

    void setSocket(Socket socket);

    public Set<ILocation> getLocations();

    public void setLocations(Set<ILocation> locations);

    public Set<IUserSicce> getUsers();

    public void setUsers(Set<IUserSicce> users);
}
