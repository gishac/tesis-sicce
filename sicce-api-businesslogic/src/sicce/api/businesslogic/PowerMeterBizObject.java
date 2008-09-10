/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import sicce.api.businesslogic.factory.ClassFactory;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import sicce.api.dataaccess.PowerMeterDB;
import sicce.api.info.ConstantsProvider.RequestType;
import sicce.api.info.exceptions.InvalidModbusResponseException;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IModbusRequest;
import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class PowerMeterBizObject {

    /**
     * 
     * @return
     */
    public List<IPowerMeter> GetAllPowerMeter() {
        return PowerMeterDB.GetAllPowerMeter();
    }
    
    
    /**
     * 
     * @param powerMeter
     * @return
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public HashMap<RequestType,IModbusResponse> ReadPowerMeterData(IPowerMeter powerMeter) throws UnknownHostException, IOException, Exception {
        ModbusBizObject modbusHandler = new ModbusBizObject();
        IModbusRequest readHoldingRegistersRequest = modbusHandler.BuildModbusRequestToReadHoldingRegisters(powerMeter);
        IModbusResponse response = modbusHandler.SendModbusRequest(readHoldingRegistersRequest, RequestType.HoldingRegisters,powerMeter);
        HashMap<RequestType,IModbusResponse> result = new HashMap<RequestType, IModbusResponse>();
        result.put(RequestType.HoldingRegisters, response);
        return result;
    }
    
    /**
     * 
     * @param powerMeterData
     */
    public IMeasure ProcessPowerMeterData(HashMap<RequestType,IModbusResponse> powerMeterData) throws InvalidModbusResponseException{
        IMeasure measure = ClassFactory.getMeasure();
        IModbusResponse response = powerMeterData.get(RequestType.HoldingRegisters);
        System.out.println(response.getHoldingRegistersResponse());
        new ModbusBizObject().ProcessModbusResponse(response, measure);
        return measure;
    }
    
}
