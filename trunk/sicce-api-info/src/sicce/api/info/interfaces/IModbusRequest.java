/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.io.OutputStream;
import java.net.InetAddress;

/**
 *
 * @author gish@c
 */
public interface IModbusRequest {

    void setIpAddress(InetAddress address);
    InetAddress getIpAddress();
    void setRequest(String request[]);
    String[] getRequest();
    
}
