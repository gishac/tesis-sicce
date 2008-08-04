/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info;

import java.net.InetAddress;
import sicce.api.info.interfaces.IModbusRequest;

/**
 * Representacion de las consultas Modbus
 * @author gish@c
 */
public class ModbusRequest implements IModbusRequest {

    /**
     * Trama de consulta Modbus
     */
    private String request[];
    
    /**
     * Direccion ip del medidor al que se enviara la consulta Modbus
     */
    private InetAddress ipAddress;

    public void setRequest(String request[]) {
        this.request = request;
    }

    public String[] getRequest() {
        return this.request;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public InetAddress getIpAddress() {
        return this.ipAddress;
    }
}
