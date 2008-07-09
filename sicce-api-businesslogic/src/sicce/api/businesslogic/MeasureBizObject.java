/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import sicce.api.info.ConstantsProvider.ModbusRegister;
import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author gish@c
 */
public class MeasureBizObject {

    public MeasureBizObject(){
        
    }
    
    /**
     * 
     * @param measure
     * @param registerID
     * @return
     */
    public double GetMeasure(IMeasure measure, int registerID){
        double value = 0;
        if(registerID == ModbusRegister.InstantaneousCurrentPhase1.ordinal()){
            value = measure.getInstantaneousCurrentPhase1();
        }
        if(registerID == ModbusRegister.InstantaneousCurrentPhase2.ordinal()){
            value = measure.getInstantaneousCurrentPhase2();
        }
        if(registerID == ModbusRegister.InstantaneousCurrentPhase3.ordinal()){
            value = measure.getInstantaneousCurrentPhase3();
        }
        if(registerID == ModbusRegister.NeutralCurrent.ordinal()){
            value = measure.getNeutralCurrent();
        }
        if(registerID == ModbusRegister.PhaseToPhaseVoltagePhase1To2.ordinal()){
            value = measure.getPhaseToPhaseVoltagePhase1To2();
        }
        if(registerID == ModbusRegister.PhaseToPhaseVoltagePhase2To3.ordinal()){
            value = measure.getPhaseToPhaseVoltagePhase2To3();
        }
        if(registerID == ModbusRegister.PhaseToPhaseVoltagePhase3To1.ordinal()){
            value = measure.getPhaseToPhaseVoltagePhase3To1();
        }
        if(registerID == ModbusRegister.PhaseToNeutralVoltagePhase1.ordinal()){
            value = measure.getPhaseToNeutralVoltagePhase1();
        }
        if(registerID == ModbusRegister.PhaseToNeutralVoltagePhase2.ordinal()){
            value = measure.getPhaseToNeutralVoltagePhase2();
        }
        if(registerID == ModbusRegister.PhaseToNeutralVoltagePhase3.ordinal()){
            value = measure.getPhaseToNeutralVoltagePhase3();
        }
        if(registerID == ModbusRegister.Frequency.ordinal()){
            value = measure.getFrequency();
        }
        if(registerID == ModbusRegister.TotalActivePower.ordinal()){
            value = measure.getTotalActivePower();
        }
        if(registerID == ModbusRegister.TotalReactivePower.ordinal()){
            value = measure.getTotalReactivePower();
        }
        if(registerID == ModbusRegister.TotalApparentPower.ordinal()){
            value = measure.getTotalApparentPower();
        }
        if(registerID == ModbusRegister.ActivePowerPhase1.ordinal()){
            value = measure.getActivePowerPhase1();
        }
        if(registerID == ModbusRegister.ActivePowerPhase2.ordinal()){
            value = measure.getActivePowerPhase2();
        }
        if(registerID == ModbusRegister.ActivePowerPhase3.ordinal()){
            value = measure.getActivePowerPhase3();
        }
        if(registerID == ModbusRegister.ReactivePowerPhase1.ordinal()){
            value = measure.getReactivePowerPhase1();
        }
        if(registerID == ModbusRegister.ReactivePowerPhase2.ordinal()){
            value = measure.getReactivePowerPhase2();
        }
        if(registerID == ModbusRegister.ReactivePowerPhase3.ordinal()){
            value = measure.getReactivePowerPhase3();
        }
        if(registerID == ModbusRegister.ApparentPowerPhase1.ordinal()){
            value = measure.getApparentPowerPhase1();
        }
        if(registerID == ModbusRegister.ApparentPowerPhase2.ordinal()){
            value = measure.getApparentPowerPhase2();
        }
        return value;
    }
    
}
