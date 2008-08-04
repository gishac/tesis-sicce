/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.net.InetAddress;

/**
 * Define los metodos a ser implementados por las clases que representen a las consultas Modbus
 * @author gish@c
 */
public interface IModbusRequest {

    /**
     * Establece la direccion ip del medidor al que se enviara la consulta Modbus
     * @param address Direccion ip del medidor al que se enviara la consulta Modbus
     */
    void setIpAddress(InetAddress address);
    
    /**
     * Devuelve la direccion ip del medidor al que se enviara la consulta Modbus
     * @return Direccion ip del medidor al que se enviara la consulta Modbus
     */
    InetAddress getIpAddress();
    
    /**
     * Establece la trama de consulta Modbus
     * @param request La trama de consulta Modbus
     */
    void setRequest(String request[]);
    
    /**
     * Devuelve la trama de consulta Modbus
     * @return La trama de consulta Modbus
     */
    String[] getRequest();
    
}
