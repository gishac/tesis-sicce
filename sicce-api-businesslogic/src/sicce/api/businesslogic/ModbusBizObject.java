/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
        frame[2] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_COMMAND).getValue();
        frame[3] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES).getValue();
        frame[4] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES).getValue();
        frame[5] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES).getValue();
        frame[6] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES).getValue();
        frame[7] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_CRC_HI_BYTES).getValue();
        frame[8] = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_CRC_LO_BYTES).getValue();

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
        measure.setActivePowerPhase1(GetRegisterValue(ModbusRegister.ActivePowerPhase1, holdingRegistersFrame));
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
        
        //Descomentar
        measure.setLocation((ILocation)response.getPowerMeter().getLocations().toArray()[0]);
        
        measure.setMaximumDemandActivePowerMinus(GetRegisterValue(ModbusRegister.MaximumDemandActivePowerMinus, holdingRegistersFrame));
        measure.setMaximumDemandActivePowerPlus(GetRegisterValue(ModbusRegister.MaximumDemandActivePowerPlus, holdingRegistersFrame));
        measure.setMaximumDemandApparentPower(GetRegisterValue(ModbusRegister.MaximumDemandApparentPower, holdingRegistersFrame));
        measure.setMaximumDemandCurrentPhase1(GetRegisterValue(ModbusRegister.MaximumDemandCurrentPhase1, holdingRegistersFrame));
        measure.setMaximumDemandCurrentPhase2(GetRegisterValue(ModbusRegister.MaximumDemandCurrentPhase2, holdingRegistersFrame));
        measure.setMaximumDemandCurrentPhase3(GetRegisterValue(ModbusRegister.MaximumDemandCurrentPhase3, holdingRegistersFrame));
        measure.setMaximumDemandReactivePowerMinus(GetRegisterValue(ModbusRegister.MaximumDemandReactivePowerMinus, holdingRegistersFrame));
        measure.setMaximumDemandReactivePowerPlus(GetRegisterValue(ModbusRegister.MaximumDemandReactivePowerPlus, holdingRegistersFrame));
        measure.setNeutralCurrent(GetRegisterValue(ModbusRegister.NeutralCurrent, holdingRegistersFrame));
        measure.setOperatingTimeCounter(GetRegisterValue(ModbusRegister.OperatingTimeCounter, holdingRegistersFrame));
        measure.setPhaseToNeutralVoltagePhase1(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase1, holdingRegistersFrame));
        measure.setPhaseToNeutralVoltagePhase2(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase2, holdingRegistersFrame));
        measure.setPhaseToNeutralVoltagePhase3(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase3, holdingRegistersFrame));        
        measure.setPhaseToPhaseVoltagePhase1To2(GetRegisterValue(ModbusRegister.PhaseToPhaseVoltagePhase1To2, holdingRegistersFrame));
        measure.setPhaseToPhaseVoltagePhase2To3(GetRegisterValue(ModbusRegister.PhaseToPhaseVoltagePhase2To3, holdingRegistersFrame));
        measure.setPhaseToPhaseVoltagePhase3To1(GetRegisterValue(ModbusRegister.PhaseToPhaseVoltagePhase3To1, holdingRegistersFrame));
        measure.setPowerMeter(response.getPowerMeter());
        measure.setPuissanceApparenteMoyenneTotale(GetRegisterValue(ModbusRegister.Puissance_Apparente_Moyenne_Totale, holdingRegistersFrame));
        measure.setReactiveEnergyInPlus(GetRegisterValue(ModbusRegister.ReactiveEnergyInPlus, holdingRegistersFrame));
        measure.setReactiveEnergyOutMinus(GetRegisterValue(ModbusRegister.ReactiveEnergyOutMinus, holdingRegistersFrame));
        measure.setReactivePowerPhase1(GetRegisterValue(ModbusRegister.ReactivePowerPhase1, holdingRegistersFrame));
        measure.setReactivePowerPhase2(GetRegisterValue(ModbusRegister.ReactivePowerPhase2, holdingRegistersFrame));
        measure.setReactivePowerPhase3(GetRegisterValue(ModbusRegister.ReactivePowerPhase3, holdingRegistersFrame));
        measure.setTotalActivePower(GetRegisterValue(ModbusRegister.TotalActivePower, holdingRegistersFrame));
        measure.setTotalApparentPower(GetRegisterValue(ModbusRegister.TotalApparentPower, holdingRegistersFrame));
        measure.setTotalReactivePower(GetRegisterValue(ModbusRegister.TotalReactivePower, holdingRegistersFrame));
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
        String registerStringValue = String.valueOf(Integer.parseInt(registerHexValue, 16)); 
        value = Double.parseDouble(ApplyFormatToRegisterValue(registerStringValue,register));
        return value;
    }
    
    
    /**
     * 
     * @param registerValue
     * @param register
     * @return
     */
    private String ApplyFormatToRegisterValue(String registerValue, ModbusRegister register){
        //TODO: Aplicar logica especial ya sea de separacion de decimales, o algo en especial al resultado
        return registerValue;
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
        String deviceID = frame.substring(0, 2);
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
        String requestID = frame.substring(2,4);
        return requestID.equals(ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_COMMAND).getValue());
    }
    
    /**
     * 
     * @param frame
     * @return
     */
    private boolean ValidateResponseLength(String frame){
        String responseLengthHex = frame.substring(4,6);
        int frameLength = GetIntValueFromHex(responseLengthHex);
        if(frame.length() != frameLength)
            return false;
        return true;
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
            //Socket socket = new Socket(request.getIpAddress(), Integer.parseInt(ModbusBizObject.getRequestFields().get(ConstantsProvider.PORT).getValue()));
            //powerMeter.setSocket(socket);
            //outStream = new BufferedOutputStream(socket.getOutputStream());
            //inStream = new BufferedInputStream(socket.getInputStream());
        }
        else{
            //powerMeter.getSocket().shutdownInput();
        }
        
        Socket socket = new Socket(request.getIpAddress(), Integer.parseInt(ModbusBizObject.getRequestFields().get(ConstantsProvider.PORT).getValue()));
        socket.setReuseAddress(true);
        
        //WriteRequest(powerMeter.getSocket().getOutputStream(), request);
        //response = ReadResult(new BufferedInputStream(powerMeter.getSocket().getInputStream()), charsToRead);
        //socket.close();
        
        WriteRequest( socket.getOutputStream(), request);
        response = ReadResult(socket.getInputStream(), charsToRead);
        
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
        
        return response;
    }

    /**
     * 
     * @param outputStream
     * @param request
     */
    private void WriteRequest(OutputStream outputStream, IModbusRequest request) throws IOException {
        //outputStream.flush();
        String frame[] = request.getRequest();
        for (int index = 0; index < frame.length; index++) {
            outputStream.write(GetIntValueFromHex(frame[index]));
        }
        outputStream.flush();
        //
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
        return Integer.parseInt(value, 16);
    }
    /**
     * 
     * @return
     */
    public static synchronized HashMap<String, IParameter> getRequestFields() {
        if (requestFields == null) {
            try {
                requestFields = new HashMap<String, IParameter>();
                IParameter readHoldingRegistersCommand = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_COMMAND);
                IParameter startAddressHI = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES);
                IParameter startAddressLO = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES);
                IParameter registersToReadHI = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES);
                IParameter registersToReadLO = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES);
                IParameter crcHI = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_CRC_HI_BYTES);
                IParameter crcLO = ParameterDB.GetParameterByKey(ConstantsProvider.READ_HOLDING_REGISTERS_CRC_LO_BYTES);
                IParameter port = ParameterDB.GetParameterByKey(ConstantsProvider.PORT);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_COMMAND, readHoldingRegistersCommand);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES, startAddressHI);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES, startAddressLO);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES, registersToReadHI);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES, registersToReadLO);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_CRC_HI_BYTES, crcHI);
                requestFields.put(ConstantsProvider.READ_HOLDING_REGISTERS_CRC_LO_BYTES, crcLO);
                requestFields.put(ConstantsProvider.PORT, port);
            } catch (Exception ex) {
                Logger.getLogger(ModbusBizObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return requestFields;
    }
}
