/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IPowerMeter;

/**
 * Representacion de las respuestas Modbus
 * @author gish@c
 */
public class ModbusResponse implements IModbusResponse {

    /**
     * Respuesta para la consulta de HoldingRegisters
     */
    private String holdingRegistersResponse;
    
    /**
     * Respuesta para la consulta de TDHValues
     */
    private String thdValuesResponse;
    
    /**
     * Medidor asociado a la respuesta Modbus
     */
    private IPowerMeter powerMeter;

    public String getHoldingRegistersResponse() {
        return this.holdingRegistersResponse;
    }

    public void setHoldingRegistersResponse(String holdingRegistersResponse) {
        this.holdingRegistersResponse = holdingRegistersResponse;
    }

    public String getTHDValuesResponse() {
        return this.thdValuesResponse;
    }

    public void setTHDValuesResponse(String thdValuesResponse) {
        this.thdValuesResponse = thdValuesResponse;
    }

    public IPowerMeter getPowerMeter() {
        return powerMeter;
    }

    public void setPowerMeter(IPowerMeter powerMeter) {
        this.powerMeter = powerMeter;
    }

}
