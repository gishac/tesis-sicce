/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a los medidores
 * @author karu
 */
public interface IPowerMeter {

    /**
     * Devuelve el identificador del medidor
     * @return Identificador del medidor
     */
    public Integer getIdPowerMeter();

    /**
     * Establece el identificador del medidor
     * @param idPowerMeter Identificador del medidor
     */
    public void setIdPowerMeter(Integer idPowerMeter);
    
    /**
     * Establece el codigo serial del medidor
     * @param serial Codigo serial del medidor
     */
    void setSerial(String serial);

    /**
     * Devuelve el codigo serial del medidor
     * @return Codigo serial del medidor
     */
    String getSerial();

    /**
     * Establece el identificador del medidor
     * @param deviceID Identificador del medidor
     */
    void setDeviceID(String deviceID);

    /**
     * Devuelve el identificador del medidor
     * @return Identificador del medidor
     */
    String getDeviceID();

    /**
     * Establece la descripcion del medidor
     * @param description Descripcion del medidor
     */
    void setDescription(String description);

    /**
     * Devuelve la descripcion del medidor
     * @return Descripcion del medidor
     */
    String getDescription();

    /**
     * Establece la direccion ip del medidor
     * @param ipAddress Direccion ip del medidor
     */
    void setIpAddress(String ipAddress);

    /**
     * Devuelve la direccion ip del medidor
     * @return Direccion ip del medidor
     */
    String getIpAddress();

    /**
     * Devuelve las alarmas asignadas al medidor
     * @return Alarmas asignadas al medidor
     * @see IAlarm
     */
    Set<IAlarm> getAlarms();

    /**
     * Establece las alarmas asignadas al medidor
     * @param alarms Alarmas asignadas al medidor
     * @see IAlarm
     */
    void setAlarms(Set<IAlarm> alarms);
    
    /**
     * Devuelve la ubicacion que tiene asignada el medidor
     * @return Ubicacion que tiene asignada el medidor
     * @see ILocation
     */
    public Set<ILocation> getLocations();

    /**
     * Establece la ubicacion que tiene asignada el medidor
     * @param locations Ubicacion que tiene asignada el medidor
     * @see ILocation
     */
    public void setLocations(Set<ILocation> locations);

    /**
     * Establece el usuario que tiene asignado el medidor
     * @return Usuario que tiene asignado el medidor
     * @see IUserSicce
     */
    public Set<IUserSicce> getUsers();

    /**
     * Establece el usuario que tiene asignado el medidor
     * @return Usuario que tiene asignado el medidor
     * @see IUserSicce
     */
    public void setUsers(Set<IUserSicce> users);
    
    /**
     * Devuelve las mediciones realizadas en el medidor
     * @return Mediciones realizadas en el medidor
     * @see IMeasure
     */
    public Set<IMeasure> getMeasures();

    /**
     * Establece las mediciones realizadas en el medidor
     * @param measures Mediciones realizadas en el medidor
     * @see IMeasure
     */
    public void setMeasures(Set<IMeasure> measures);
}
