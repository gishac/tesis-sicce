/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.info.exceptions.InvalidModbusResponseException;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.util.EncryptionProvider;

/**
 *
 * @author gish@c
 */
public class Main {

    public static void main(String[] args) {
        try {

            EncryptionProvider.RegisterHibernateEncryptor();
            
            AlarmBizObject biz = new AlarmBizObject();
            
            IMeasure measure = ClassFactory.getMeasure();
            IPowerMeter pm = ClassFactory.getPowerMeterInstance();
            pm.setDeviceID("01");
            pm.getLocations().add(ClassFactory.getLocationInstance());
            measure.setPowerMeter(pm);
            IModbusResponse response = ClassFactory.getModbusResponse();
            response.setPowerMeter(pm);
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

            response.setHoldingRegistersResponse(buffer);

            ModbusBizObject mox = new ModbusBizObject();
            mox.ProcessModbusResponse(response, measure);
        } catch (InvalidModbusResponseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
