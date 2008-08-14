/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que representen a las asociacion usuario/medidor
 * @author gish@c
 */
public interface IUserPowerMeter {

    /**
     * Devuelve el identificador de la asociacion
     * @return Identificador de la asociacion
     */
    public Integer getIdUserPowerMeter();

    /**
     * Establece el identificador de la asociacion
     * @param idUserPowerMeter Identificador de la asociacion
     */
    public void setIdUserPowerMeter(Integer idUserPowerMeter);

    /**
     * Devuelve el medidor asignado
     * @return Medidor asignado
     * @see IPowerMeter
     */
    public IPowerMeter getPowerMeter();

    /**
     * Establece el medidor asignado
     * @param powerMeter Medidor asignado
     * @see IPowerMeter
     */
    public void setPowerMeter(IPowerMeter powerMeter);

    /**
     * Devuelve el usuario asignado
     * @return Usuario asignado
     * @see IUserSicce
     */
    public IUserSicce getUserSicce();

    /**
     * Establece el usuario asignado
     * @param userSicce Usuario asignado
     * @see IUserSicce
     */
    public void setUserSicce(IUserSicce userSicce);

    
    /**
     * Devuelve el valor indicando si el medidor esta asignado al usuario
     * @return <strong> 1 </strong>, si esta asignado; <strong> 0 </strong>, si no esta asignado
     */
    public Byte getAssigned();

    /**
     * Establece el valor indicando si el medidor esta asignado al usuario
     * @param assigned <strong> 1 </strong>, si esta asignado; <strong> 0 </strong>, si no esta asignado
     */
    public void setAssigned(Byte assigned);

    /**
     * Devuelve el valor indicando si el medidor es monitoreado por el usuario
     * @return <strong> 1 </strong>, si es monitoreado; <strong> 0 </strong>, si no es monitoreado
     */
    public Byte getMonitor();

    /**
     * Establece el valor indicando si el medidor sera monitoreado por el usuario
     * @param monitor <strong> 1 </strong>, si es monitoreado; <strong> 0 </strong>, si no es monitoreado
     */
    public void setMonitor(Byte monitor);
}
