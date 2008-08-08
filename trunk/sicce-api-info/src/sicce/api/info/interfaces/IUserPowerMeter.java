/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

/**
 *
 * @author gish@c
 */
public interface IUserPowerMeter {

    public Integer getIdUserPowerMeter();

    public void setIdUserPowerMeter(Integer idUserPowerMeter);

    public IPowerMeter getPowerMeter();

    public void setPowerMeter(IPowerMeter powerMeter);

    public IUserSicce getUserSicce();

    public void setUserSicce(IUserSicce userSicce);

    public Byte getAssigned();

    public void setAssigned(Byte assigned);

    public Byte getMonitor();

    public void setMonitor(Byte monitor);
}
