/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que representen a las repuestas Modbus
 * @author gish@c
 */
public interface IModbusResponse {

    /**
     * Devuelve la respuesta para la consulta de HoldingRegisters
     * @return
     */
    String[] getHoldingRegistersResponse();
    
    /**
     * Establece la respuesta para la consulta de HoldingRegisters
     * @param holdingRegistersResponse Respuesta para la consulta de HoldingRegisters
     */
    void setHoldingRegistersResponse(String[] holdingRegistersResponse);
    
    /**
     * Devuelve la respuesta para la consulta de TDHValues
     * @return Respuesta para la consulta de TDHValues
     */
    String getTHDValuesResponse();
    
    /**
     * Establece la respuesta para la consulta de TDHValues
     * @param thdValuesResponse Respuesta para la consulta de TDHValues
     */
    void setTHDValuesResponse(String thdValuesResponse);
    
    /**
     * Devuelve el medidor asociado a la respuesta Modbus
     * @return Medidor asociado a la respuesta Modbus
     */
    IPowerMeter getPowerMeter();
    
    /**
     * Establece el medidor asociado a la respuesta Modbus
     * @param powerMeter Medidor asociado a la respuesta Modbus
     */
    void setPowerMeter(IPowerMeter powerMeter);
    
}
