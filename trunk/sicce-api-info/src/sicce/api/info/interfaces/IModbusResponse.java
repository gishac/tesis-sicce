/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 *
 * @author gish@c
 */
public interface IModbusResponse {

    String getHoldingRegistersResponse();
    void setHoldingRegistersResponse(String holdingRegistersResponse);
    String getTHDValuesResponse();
    void setTHDValuesResponse(String thdValuesResponse);
    IPowerMeter getPowerMeter();
    void setPowerMeter(IPowerMeter powerMeter);
    
}
