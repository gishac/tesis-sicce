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
public interface IUserSicce {

    IRole getRole();

    void setRole(IRole role);

    void setName(String name);

    String getName();

    void setPasswordSicce(String passwordSicce);

    String getPasswordSicce();

    void setUsernameSicce(String usernameSicce);

    String getUsernameSicce();

    void setLastname(String lastnameSicce);

    String getLastname();

    Integer getID();

    public Set<IAlarm> getAlarms();

    public void setAlarms(Set<IAlarm> alarms);

    public Set<IPowerMeter> getPowerMeters();

    public void setPowerMeters(Set<IPowerMeter> powerMeters);
    
    public void addUserPowerMeter(IPowerMeter powerMeter);

    public void removeUserPowerMeter(IPowerMeter powerMeter);
}
