/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.ConstantsProvider.ModbusRegister;
import sicce.api.info.ConstantsProvider.RequestType;
import sicce.api.info.exceptions.InvalidModbusResponseException;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IModbusRequest;
import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;

/**
 *
 * @author gish@c
 */
public class ModbusBizObject {

    /**
     * 
     */
    public static final String PORT = "DIGI_PORT";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_COMMAND = "READ_HOLDING_REGISTERS_COMMAND";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES = "READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES = "READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES = "READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES = "READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_CRC_HI_BYTES = "READ_HOLDING_REGISTERS_CRC_HI_BYTES";
    /**
     * 
     */
    public static final String READ_HOLDING_REGISTERS_CRC_LO_BYTES = "READ_HOLDING_REGISTERS_CRC_LO_BYTES";
    /**
     * 
     */
    private static HashMap<String, IParameter> requestFields;

    /**
     * 
     * @param powerMeter
     * @return
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     */
    public IModbusRequest BuildModbusRequestToReadHoldingRegisters(IPowerMeter powerMeter) throws UnknownHostException, IOException, Exception {
        IModbusRequest modbusRequest = PrepareRequest(powerMeter);
        modbusRequest.setRequest(BuildModbusCommandForHoldingRegisters(powerMeter));
        return modbusRequest;
    }

    /**
     * 
     * @param powerMeter
     * @return
     * @throws java.net.UnknownHostException
     */
    public IModbusRequest PrepareRequest(IPowerMeter powerMeter) throws UnknownHostException {

        InetAddress ipAddress = InetAddress.getByAddress(powerMeter.getIpAddress(), GetIPAddressAsByteArray(powerMeter));
        IModbusRequest modbusRequest = ClassFactory.getModbusRequest();
        modbusRequest.setIpAddress(ipAddress);
        return modbusRequest;
    }

    /**
     * 
     * @param powerMeter
     * @return
     */
    private byte[] GetIPAddressAsByteArray(IPowerMeter powerMeter) {
        byte byteIpAddress[] = new byte[4];
        String octets[] = powerMeter.getIpAddress().split("\\.");
        for (int index = 0; index <= 3; index++) {
            int value = Integer.parseInt(octets[index]);
            byteIpAddress[index] = (byte)value;
        }
        return byteIpAddress;
    }

    /**
     * 
     * @param powerMeter
     * @return
     */
    private String[] BuildModbusCommandForHoldingRegisters(IPowerMeter powerMeter) {
        String[] frame = {"70", "XX", "XX", "XX", "XX", "XX", "XX", "XX", "XX"};

        frame[1] = powerMeter.getDeviceID();
        frame[2] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_COMMAND).getValue();
        frame[3] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES).getValue();
        frame[4] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES).getValue();
        frame[5] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES).getValue();
        frame[6] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES).getValue();
        frame[7] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_CRC_HI_BYTES).getValue();
        frame[8] = ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_CRC_LO_BYTES).getValue();

        return frame;
    }

    /**
     * 
     * @param request
     * @param requestType
     * @return
     * @throws java.io.IOException
     */
    public IModbusResponse SendModbusRequest(IModbusRequest request, RequestType requestType, IPowerMeter powerMeter) throws IOException {
        IModbusResponse response = ClassFactory.getModbusResponse();
        response.setPowerMeter(powerMeter);     
        switch (requestType) {
            case HoldingRegisters:
                response.setHoldingRegistersResponse(GetResponse(request, requestType.getTotalRegisters(),powerMeter));
                break;
            case THD:
                response.setTHDValuesResponse(GetResponse(request, requestType.getTotalRegisters(),powerMeter));
                break;
        }
        return response;
    }
    
    /**
     * 
     * @param response
     * @param measure
     */
    public void ProcessModbusResponse(IModbusResponse response, IMeasure measure) throws InvalidModbusResponseException{        
        if(!ValidateHoldingRegistersResponse(response)){
            throw new InvalidModbusResponseException();
        }
        String holdingRegistersFrame = response.getHoldingRegistersResponse();                
        measure.setActiveEnergyInPlus(GetRegisterValue(ModbusRegister.ActiveEnergyInPlus, holdingRegistersFrame));
        measure.setActiveEnergyOutMinus(GetRegisterValue(ModbusRegister.ActiveEnergyOutMinus, holdingRegistersFrame));
        measure.setActivePowerPhase1(GetRegisterValue(ModbusRegister.ActiveEnergyOutMinus, holdingRegistersFrame));
        measure.setActivePowerPhase2(GetRegisterValue(ModbusRegister.ActivePowerPhase2, holdingRegistersFrame));
        measure.setActivePowerPhase3(GetRegisterValue(ModbusRegister.ActivePowerPhase3, holdingRegistersFrame));
        measure.setApparentEnergy(GetRegisterValue(ModbusRegister.ApparentEnergy, holdingRegistersFrame));
        measure.setApparentPowerPhase1(GetRegisterValue(ModbusRegister.ApparentPowerPhase1, holdingRegistersFrame));
        measure.setApparentPowerPhase2(GetRegisterValue(ModbusRegister.ApparentPowerPhase2, holdingRegistersFrame));
        measure.setApparentPowerPhase3(GetRegisterValue(ModbusRegister.ApparentPowerPhase3, holdingRegistersFrame));
        measure.setDateMeasure(Calendar.getInstance().getTime());
        measure.setDemandCurrentPhase1(GetRegisterValue(ModbusRegister.DemandCurrentPhase1, holdingRegistersFrame));
        measure.setDemandCurrentPhase2(GetRegisterValue(ModbusRegister.DemandCurrentPhase2, holdingRegistersFrame));
        measure.setDemandCurrentPhase3(GetRegisterValue(ModbusRegister.DemandCurrentPhase3, holdingRegistersFrame));
        measure.setFrequency(GetRegisterValue(ModbusRegister.Frequency, holdingRegistersFrame));
        measure.setInput1PulseCounter(GetRegisterValue(ModbusRegister.Input1PulseCounter, holdingRegistersFrame));
        measure.setInstantaneousCurrentPhase1(GetRegisterValue(ModbusRegister.InstantaneousCurrentPhase1, holdingRegistersFrame));
        measure.setInstantaneousCurrentPhase2(GetRegisterValue(ModbusRegister.InstantaneousCurrentPhase2, holdingRegistersFrame));
        measure.setInstantaneousCurrentPhase3(GetRegisterValue(ModbusRegister.InstantaneousCurrentPhase3, holdingRegistersFrame));
        measure.setLocation((ILocation)response.getPowerMeter().getLocations().toArray()[0]);
    }
    
    /**
     * 
     * @param register
     * @param frame
     * @return
     */
    private Double GetRegisterValue(ModbusRegister register, String frame){
        Double value = null;
        String registerHexValue = frame.substring(register.getFrameStartIndex(), register.getFrameEndIndex());
        value = Double.parseDouble(String.valueOf(Integer.parseInt(registerHexValue, 16)));
        return value;
    }
    
    /**
     * 
     * @param response
     * @return
     */
    private boolean ValidateHoldingRegistersResponse(IModbusResponse response){
        boolean valid = true;
        String frame = response.getHoldingRegistersResponse();
        if(frame.length() <= 4)
            return false;
        if(!ValidateDeviceID(response.getPowerMeter(), frame))
            return false;
        if(!ValidateHoldingRegistersID(frame))
            return false;
        if(!ValidateResponseLength(frame))
            return false;
        return valid;
    }
    
    /**
     * 
     * @param powerMeter
     * @param frame
     * @return
     */
    private boolean ValidateDeviceID(IPowerMeter powerMeter, String frame){
        String deviceID = frame.substring(0, 1);
        if(!deviceID.equals(powerMeter.getDeviceID()))
            return false;
        return true;
    }
    
    /**
     * 
     * @param frame
     * @return
     */
    private boolean ValidateHoldingRegistersID(String frame){
        String requestID = frame.substring(2,3);
        return requestID.equals(ModbusBizObject.getRequestFields().get(READ_HOLDING_REGISTERS_COMMAND).getValue());
    }
    
    /**
     * 
     * @param frame
     * @return
     */
    private boolean ValidateResponseLength(String frame){
        String responseLengthHex = frame.substring(4,5);
        int frameLength = GetIntValueFromHex(responseLengthHex);
        frameLength = frameLength + 6;
        if(frame.length() != frameLength)
            return false;
        return false;
    }

    /**
     * 
     * @param request
     * @param charsToRead
     * @return
     * @throws java.io.IOException
     */
    private String GetResponse(IModbusRequest request, int charsToRead, IPowerMeter powerMeter) throws IOException {
        String response = "";
        if(powerMeter.getSocket() == null)
        {
            Socket xocket = new Socket(request.getIpAddress(), Integer.parseInt(ModbusBizObject.getRequestFields().get(PORT).getValue()));
            powerMeter.setSocket(xocket);
        } 
        Socket socket = powerMeter.getSocket();
        WriteRequest(socket.getOutputStream(), request);
        response = ReadResult(socket.getInputStream(), charsToRead);
        return response;
    }

    /**
     * 
     * @param outputStream
     * @param request
     */
    private void WriteRequest(OutputStream outputStream, IModbusRequest request) throws IOException {
        String frame[] = request.getRequest();
        for (int index = 0; index < frame.length; index++) {
            outputStream.write(GetIntValueFromHex(frame[index]));
        }
        outputStream.flush();
    }

    /**
     * 
     * @param inputStream
     * @param charsToRead
     * @return
     * @throws java.io.IOException
     */
    private String ReadResult(InputStream inputStream, int charsToRead) throws IOException {
        StringBuffer buffer = new StringBuffer();
        for (int index = 0; index < charsToRead; index++) {
            String valueHex = Integer.toHexString(inputStream.read()).toUpperCase();
            valueHex = (valueHex.length() == 1 ? "0" + valueHex : valueHex);
            buffer.append(valueHex);
        }
        return buffer.toString();
    }

    /**
     * 
     * @param value
     * @return
     */
    private int GetIntValueFromHex(String value) {
//        int result;
//        result = GetHexValueFromChar(value.charAt(0)) * 16 + GetHexValueFromChar(value.charAt(1));
//        return result;
        return Integer.parseInt(value, 16);
    }

    /**
     * 
     * @param value
     * @return
     */
    public int GetHexValueFromChar(char value) {
        int result = 0;
        switch (value) {
            case '0':
                result = 0;
                break;
            case '1':
                result = 1;
                break;
            case '2':
                result = 2;
                break;
            case '3':
                result = 3;
                break;
            case '4':
                result = 4;
                break;
            case '5':
                result = 5;
                break;
            case '6':
                result = 6;
                break;
            case '7':
                result = 7;
                break;
            case '8':
                result = 8;
                break;
            case '9':
                result = 9;
                break;
            case 'A':
                result = 10;
                break;
            case 'B':
                result = 11;
                break;
            case 'C':
                result = 12;
                break;
            case 'D':
                result = 13;
                break;
            case 'E':
                result = 14;
                break;
            case 'F':
                result = 15;
                break;
        }
        return result;
    }

    /**
     * 
     * @return
     */
    public static synchronized HashMap<String, IParameter> getRequestFields() {
        if (requestFields == null) {
            try {
                requestFields = new HashMap<String, IParameter>();
                IParameter readHoldingRegistersCommand = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_COMMAND);
                IParameter startAddressHI = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES);
                IParameter startAddressLO = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES);
                IParameter registersToReadHI = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES);
                IParameter registersToReadLO = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES);
                IParameter crcHI = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_CRC_HI_BYTES);
                IParameter crcLO = ParameterDB.GetParameterByKey(READ_HOLDING_REGISTERS_CRC_LO_BYTES);
                IParameter port = ParameterDB.GetParameterByKey(ModbusBizObject.PORT);
                requestFields.put(READ_HOLDING_REGISTERS_COMMAND, readHoldingRegistersCommand);
                requestFields.put(READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES, startAddressHI);
                requestFields.put(READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES, startAddressLO);
                requestFields.put(READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES, registersToReadHI);
                requestFields.put(READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES, registersToReadLO);
                requestFields.put(READ_HOLDING_REGISTERS_CRC_HI_BYTES, crcHI);
                requestFields.put(READ_HOLDING_REGISTERS_CRC_LO_BYTES, crcLO);
                requestFields.put(PORT, port);
            } catch (Exception ex) {
                Logger.getLogger(ModbusBizObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requestFields;
    }
}
