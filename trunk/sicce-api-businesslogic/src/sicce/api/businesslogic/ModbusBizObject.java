/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import sicce.api.businesslogic.factory.ClassFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashMap;
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
import sicce.api.util.UtilMath;

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
            byteIpAddress[index] = (byte) value;
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
                response.setHoldingRegistersResponse(GetResponseBuffer(request, requestType.getTotalRegisters(), powerMeter));
                break;
//            case THD:
//                response.setTHDValuesResponse(GetResponse(request, requestType.getTotalRegisters(), powerMeter));
//                break;
        }
        return response;
    }

    /**
     * 
     * @param response
     * @param measure
     */
    public void ProcessModbusResponse(IModbusResponse response, IMeasure measure) throws InvalidModbusResponseException {
        if (!ValidateHoldingRegistersResponse(response)) {
            throw new InvalidModbusResponseException();
        }
        String[] holdingRegistersFrame = response.getHoldingRegistersResponse();
        //String holdingRegistersFrame = "";
        measure.setDateMeasure(Calendar.getInstance().getTime());
        measure.setLocation((ILocation) response.getPowerMeter().getLocations().toArray()[0]);
        measure.setPowerMeter(response.getPowerMeter());

        measure.setPhaseToNeutralVoltagePhase1(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase1, holdingRegistersFrame));
        measure.setPhaseToNeutralVoltagePhase2(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase2, holdingRegistersFrame));
        measure.setPhaseToNeutralVoltagePhase3(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase3, holdingRegistersFrame));
        measure.setPhaseToPhaseVoltagePhase1To2(GetRegisterValue(ModbusRegister.PhaseToPhaseVoltagePhase1To2, holdingRegistersFrame));
        measure.setPhaseToPhaseVoltagePhase2To3(GetRegisterValue(ModbusRegister.PhaseToPhaseVoltagePhase2To3, holdingRegistersFrame));
        measure.setPhaseToPhaseVoltagePhase3To1(GetRegisterValue(ModbusRegister.PhaseToPhaseVoltagePhase3To1, holdingRegistersFrame));
        measure.setReactivePowerPhase1(GetRegisterValue(ModbusRegister.ReactivePowerPhase1, holdingRegistersFrame));
        measure.setFrequency(GetRegisterValue(ModbusRegister.Frequency, holdingRegistersFrame));
        measure.setPhaseToNeutralVoltagePhase1(GetRegisterValue(ModbusRegister.PhaseToNeutralVoltagePhase1, holdingRegistersFrame));
        measure.setTotalActivePower(GetRegisterValue(ModbusRegister.TotalActivePower, holdingRegistersFrame));
        measure.setTotalApparentPower(GetRegisterValue(ModbusRegister.TotalApparentPower, holdingRegistersFrame));
        measure.setTotalReactivePower(GetRegisterValue(ModbusRegister.TotalReactivePower, holdingRegistersFrame));
        measure.setActivePowerPhase1(GetRegisterValue(ModbusRegister.ActivePowerPhase1, holdingRegistersFrame));
        measure.setActivePowerPhase2(GetRegisterValue(ModbusRegister.ActivePowerPhase2, holdingRegistersFrame));
        measure.setActivePowerPhase3(GetRegisterValue(ModbusRegister.ActivePowerPhase3, holdingRegistersFrame));
        measure.setReactivePowerPhase1(GetRegisterValue(ModbusRegister.ReactivePowerPhase1, holdingRegistersFrame));
        measure.setReactivePowerPhase2(GetRegisterValue(ModbusRegister.ReactivePowerPhase2, holdingRegistersFrame));
        measure.setReactivePowerPhase3(GetRegisterValue(ModbusRegister.ReactivePowerPhase3, holdingRegistersFrame));
        measure.setApparentPowerPhase1(GetRegisterValue(ModbusRegister.ApparentPowerPhase1, holdingRegistersFrame));
        measure.setApparentPowerPhase2(GetRegisterValue(ModbusRegister.ApparentPowerPhase2, holdingRegistersFrame));
        measure.setApparentPowerPhase3(GetRegisterValue(ModbusRegister.ApparentPowerPhase3, holdingRegistersFrame));
        measure.setActiveEnergyIn(GetRegisterValue(ModbusRegister.ActiveEnergyIn, holdingRegistersFrame));
    }

    /**
     * 
     * @param register
     * @param frame
     * @return
     */
    private Double GetRegisterValue(ModbusRegister register, String[] frame) {
        Double value = null;
        String registerHexValue = frame[register.getFrameStartIndex()] + frame[register.getFrameEndIndex()];
        String registerStringValue = String.valueOf(Integer.parseInt(registerHexValue, 16));
        value = Double.parseDouble(ApplyFormatToRegisterValue(registerStringValue, register));
        return value;
    }

    /**
     * 
     * @param registerValue
     * @param register
     * @return
     */
    private String ApplyFormatToRegisterValue(String registerValue, ModbusRegister register) {
        if (register == ModbusRegister.ActiveEnergyIn) {
            return registerValue;
        }
        registerValue = UtilMath.Parse(registerValue, 2);
        return registerValue;
    }

    /**
     * 
     * @param response
     * @return
     */
    private boolean ValidateHoldingRegistersResponse(IModbusResponse response) {
        boolean valid = true;
        String[] frame = response.getHoldingRegistersResponse();
        if (frame.length <= 4) {
            return false;
        }
        if (!ValidateDeviceID(response.getPowerMeter(), frame[0])) {
            return false;
        }
        if (!ValidateHoldingRegistersID(frame[1])) {
            return false;
        }
        if (!ValidateResponseLength(frame)) {
            return false;
        }
        return valid;
    }

    /**
     * 
     * @param powerMeter
     * @param frame
     * @return
     */
    private boolean ValidateDeviceID(IPowerMeter powerMeter, String deviceID) {
        if (Integer.parseInt(deviceID) != Integer.parseInt(powerMeter.getDeviceID())) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param frame
     * @return
     */
    private boolean ValidateHoldingRegistersID(String requestID) {
        return Integer.parseInt(requestID) == Integer.parseInt(ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_COMMAND).getValue());
    }

    /**
     * 
     * @param frame
     * @return
     */
    private boolean ValidateResponseLength(String[] frame) {
        String responseLengthHex = frame[2];
        int frameLength = GetIntValueFromHex(responseLengthHex);
        String requestedRegisters = ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES).getValue() + ModbusBizObject.getRequestFields().get(ConstantsProvider.READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES).getValue();
        int requestLength = Integer.parseInt(requestedRegisters, 16) * 2;
        if (requestLength != frameLength) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param request
     * @param charsToRead
     * @return
     * @throws java.io.IOException
     */
    private String[] GetResponseBuffer(IModbusRequest request, int charsToRead, IPowerMeter powerMeter) throws IOException {

        /*int port = Integer.parseInt(ModbusBizObject.getRequestFields().get(ConstantsProvider.PORT).getValue());
        Socket socket = new Socket(request.getIpAddress(), port);
        socket.setReuseAddress(true);
        WriteRequest(socket.getOutputStream(), request);
        String[] response = ReadResultBuffer(socket.getInputStream(), charsToRead);
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
        return response;*/
        
        String[] buffer = new String[200];

            buffer[0] = "1";
            buffer[1] = "3";
            buffer[2] = "F0";
            buffer[3] = "0";
            buffer[4] = "2";
            buffer[5] = "81";
            buffer[6] = "68";
            buffer[7] = "0";
            buffer[8] = "2";
            buffer[9] = "37";
            buffer[10] = "30";
            buffer[11] = "0";
            buffer[12] = "2";
            buffer[13] = "1B";
            buffer[14] = "D8";
            buffer[15] = "0";
            buffer[16] = "0";
            buffer[17] = "0";
            buffer[18] = "0";
            buffer[19] = "0";
            buffer[20] = "0";
            buffer[21] = "55";
            buffer[22] = "54";
            buffer[23] = "0";
            buffer[24] = "0";
            buffer[25] = "55";
            buffer[26] = "72";
            buffer[27] = "0";
            buffer[28] = "0";
            buffer[29] = "55";
            buffer[30] = "98";
            buffer[31] = "0";
            buffer[32] = "0";
            buffer[33] = "0";
            buffer[34] = "0";
            buffer[35] = "0";
            buffer[36] = "0";
            buffer[37] = "0";
            buffer[38] = "0";
            buffer[39] = "0";
            buffer[40] = "0";
            buffer[41] = "0";
            buffer[42] = "0";
            buffer[43] = "0";
            buffer[44] = "0";
            buffer[45] = "17";
            buffer[46] = "6B";
            buffer[47] = "0";
            buffer[48] = "0";
            buffer[49] = "15";
            buffer[50] = "B7";
            buffer[51] = "0";
            buffer[52] = "0";
            buffer[53] = "4";
            buffer[54] = "0E";
            buffer[55] = "0";
            buffer[56] = "0";
            buffer[57] = "16";
            buffer[58] = "18";
            buffer[59] = "0";
            buffer[60] = "0";
            buffer[61] = "3";
            buffer[62] = "D6";
            buffer[63] = "0";
            buffer[64] = "0";
            buffer[65] = "7";
            buffer[66] = "FF";
            buffer[67] = "0";
            buffer[68] = "0";
            buffer[69] = "6";
            buffer[70] = "DD";
            buffer[71] = "0";
            buffer[72] = "0";
            buffer[73] = "6";
            buffer[74] = "DB";
            buffer[75] = "0";
            buffer[76] = "0";
            buffer[77] = "1";
            buffer[78] = "97";
            buffer[79] = "0";
            buffer[80] = "0";
            buffer[81] = "1";
            buffer[82] = "46";
            buffer[83] = "0";
            buffer[84] = "0";
            buffer[85] = "1";
            buffer[86] = "31";
            buffer[87] = "0";
            buffer[88] = "0";
            buffer[89] = "8";
            buffer[90] = "27";
            buffer[91] = "0";
            buffer[92] = "0";
            buffer[93] = "6";
            buffer[94] = "FB";
            buffer[95] = "0";
            buffer[96] = "0";
            buffer[97] = "6";
            buffer[98] = "F5";
            buffer[99] = "0";
            buffer[100] = "0";
            buffer[101] = "3";
            buffer[102] = "D4";
            buffer[103] = "0";
            buffer[104] = "0";
            buffer[105] = "3";
            buffer[106] = "D7";
            buffer[107] = "0";
            buffer[108] = "0";
            buffer[109] = "3";
            buffer[110] = "D9";
            buffer[111] = "0";
            buffer[112] = "2";
            buffer[113] = "55";
            buffer[114] = "58";
            buffer[115] = "0";
            buffer[116] = "2";
            buffer[117] = "24";
            buffer[118] = "8A";
            buffer[119] = "0";
            buffer[120] = "1";
            buffer[121] = "F2";
            buffer[122] = "98";
            buffer[123] = "0";
            buffer[124] = "0";
            buffer[125] = "14";
            buffer[126] = "E8";
            buffer[127] = "0";
            buffer[128] = "0";
            buffer[129] = "0";
            buffer[130] = "0";
            buffer[131] = "0";
            buffer[132] = "0";
            buffer[133] = "3";
            buffer[134] = "E5";
            buffer[135] = "0";
            buffer[136] = "0";
            buffer[137] = "0";
            buffer[138] = "0";
            buffer[139] = "0";
            buffer[140] = "0";
            buffer[141] = "15";
            buffer[142] = "46";
            buffer[143] = "0";
            buffer[144] = "5";
            buffer[145] = "2A";
            buffer[146] = "80";
            buffer[147] = "0";
            buffer[148] = "3";
            buffer[149] = "84";
            buffer[150] = "78";
            buffer[151] = "0";
            buffer[152] = "5";
            buffer[153] = "6";
            buffer[154] = "82";
            buffer[155] = "0";
            buffer[156] = "0";
            buffer[157] = "2B";
            buffer[158] = "50";
            buffer[159] = "0";
            buffer[160] = "0";
            buffer[161] = "0";
            buffer[162] = "0";
            buffer[163] = "0";
            buffer[164] = "0";
            buffer[165] = "7";
            buffer[166] = "C7";
            buffer[167] = "0";
            buffer[168] = "0";
            buffer[169] = "0";
            buffer[170] = "30";
            buffer[171] = "0";
            buffer[172] = "0";
            buffer[173] = "2B";
            buffer[174] = "ED";
            buffer[175] = "0";
            buffer[176] = "0";
            buffer[177] = "92";
            buffer[178] = "1A";
            buffer[179] = "0";
            buffer[180] = "0";
            buffer[181] = "E";
            buffer[182] = "68";
            buffer[183] = "0";
            buffer[184] = "0";
            buffer[185] = "2";
            buffer[186] = "4E";
            buffer[187] = "0";
            buffer[188] = "0";
            buffer[189] = "E";
            buffer[190] = "9E";
            buffer[191] = "0";
            buffer[192] = "0";
            buffer[193] = "0";
            buffer[194] = "0";
            buffer[195] = "0";
            buffer[196] = "0";
            buffer[197] = "0";
            
            return buffer;

    }

    /**
     * 
     * @param outputStream
     * @param request
     */
    private void WriteRequest(OutputStream outputStream, IModbusRequest request) throws IOException {
        //outputStream.flush();
        String frame[] = request.getRequest();
        String fullFrame = "";
        for (int index = 0; index < frame.length; index++) {
            int value = GetIntValueFromHex(frame[index]);
            fullFrame += String.valueOf((byte) value);
            outputStream.write(value);
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
    private String[] ReadResultBuffer(InputStream inputStream, int charsToRead) throws IOException {
        String[] buffer = new String[charsToRead];
        for (int index = 0; index < charsToRead; index++) {
            String valueHex = Integer.toHexString(inputStream.read()).toUpperCase();
            System.out.println(valueHex);
            buffer[index] = valueHex.length() <= 1 ? "0" + valueHex : valueHex;
        }
        return buffer;
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
