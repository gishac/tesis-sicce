/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import java.net.InetAddress;
import sicce.api.info.interfaces.IModbusRequest;

/**
 *
 * @author gish@c
 */
public class ModbusRequest implements IModbusRequest {

   
    private String request[];
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
