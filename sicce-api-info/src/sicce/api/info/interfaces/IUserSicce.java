/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Set;

/**
 * Define los metodos a ser implementados por las clases que representen a los usuarios del sistema
 * @author gish@c
 */
public interface IUserSicce {

    /**
     * Devuelve el rol del usuario
     * @return Rol del usuario
     * @see IRole
     */
    IRole getRole();

    /**
     * Establece el rol del usuario
     * @param role Rol del usuario
     * @see IRole
     */
    void setRole(IRole role);

    /**
     * Establece el nombre del usuario
     * @param name Nombre el usuario
     */
    void setName(String name);

    /**
     * Devuelve el nombre del usuario
     * @return Nombre del usuario
     */
    String getName();

    /**
     * Establece el password del usuario
     * @param passwordSicce Password del usuario
     */
    void setPasswordSicce(String passwordSicce);

    /**
     * Devuelve el password del usuario
     * @return password del usuario
     */
    String getPasswordSicce();

    /**
     * Establece el login del usuario
     * @param usernameSicce Login del usuario
     */
    void setUsernameSicce(String usernameSicce);

    /**
     * Devuelve el login del usuario
     * @return Login del usuario
     */
    String getUsernameSicce();

    /**
     * Establece el apellido del usuario
     * @param lastnameSicce Apellido del usuario
     */
    void setLastname(String lastnameSicce);

    /**
     * Devuelve el apellido del usuario
     * @return
     */
    String getLastname();

    /**
     * Devuelve el identificador del usuario
     * @return Identificador del usuario
     */
    public Integer getIdUserSicce();

    /**
     * Establece el identificador del usuario
     * @param idUserSicce Identificador del usuario
     */
    public void setIdUserSicce(Integer idUserSicce);
    
    /**
     * Devuelve el email del usuario
     * @return Email del usuario
     */
    String getEmail();
        
    /**
     * Establece el email del usuario
     * @param email Email del usuario
     */
    public void setEmail(String email);

    /**
     * Devuelve las alarmas a las que se encuentra suscrito el usuario
     * @return Alarmas a las que se encuentra suscrito el usuario
     * @see IAlarm
     */
    public Set<IAlarm> getAlarms();

    /**
     * Establece las alarmas a las que se encuentra suscrito el usuario
     * @param alarms Alarmas a las que se encuentra suscrito el usuario
     * @see IAlarm
     */
    public void setAlarms(Set<IAlarm> alarms);

    /**
     * Devuelve los medidores asociados al usuario
     * @return Medidores asociados al usuario
     * @see IPowerMeter
     */
    public Set<IPowerMeter> getPowerMeters();

    /**
     * Establece los medidores asociados al usuario
     * @param powerMeters Medidores asociados al usuario
     */
    public void setPowerMeters(Set<IPowerMeter> powerMeters);
    
    /**
     * Agrega un medidor a ser monitoreado por el usuario
     * @param powerMeter Medidor a ser monitoreado por el usuario
     * @see IPowerMeter
     */
    public void addUserPowerMeter(IPowerMeter powerMeter);

    /**
     * Remueve un medidor de los asignados al usuario
     * @param powerMeter Medidor a ser removido
     * @see IPowerMeter
     */
    public void removeUserPowerMeter(IPowerMeter powerMeter);
}
