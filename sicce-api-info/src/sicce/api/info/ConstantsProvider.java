/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info;

/**
 *
 * @author gish@c
 */
public class ConstantsProvider {

    /**
     * Opciones
     */
    public enum OptionsProvider {

        Role(1),
        User(2),
        PowerMeter(3),
        LocationType(4),
        Location(5),
        Zone(6),
        Parameter(7),
        Alarm(8),
        Wizard(9),
        PowerMeterReport(10),
        LocationTypeReport(11),
        LocationReport(12),
        UserReport(13),
        ZoneReport(14),
        SavedReport(15);
        private int taskID;

        OptionsProvider(int taskID) {
            this.taskID = taskID;
        }

        public int getTaskID() {
            return taskID;
        }
    }

    /**
     * Acciones del toolbar
     */
    public enum ToolBarAction {

        None,
        RegistryLoaded,
        Edit,
        New,
        Save,
        Delete,
        Search,
        Back
    }

    /**
     * Tipo de render
     */
    public enum DisplayMemberRenderType {

        Method,
        Field
    }

    /**
     * Tipo de dialogo
     */
    public enum DialogResult {

        Ok,
        Cancel
    }

    /**
     * Tipo de Alarma
     */
    public enum AlarmType {

        Mail(1),
        SMS(2);
        private int alarmType;

        AlarmType(int alarmType) {
            this.alarmType = alarmType;
        }

        public int getAlarmType() {
            return alarmType;
        }
    }

    /**
     * Tipo de Agenda
     */
    public enum ScheduleType {

        Daily(1),
        Planned(2);
        private int scheduleType;

        ScheduleType(int scheduleType) {
            this.scheduleType = scheduleType;
        }

        public int getScheduleType() {
            return scheduleType;
        }
    }
    
    public enum RequestType{
        HoldingRegisters(98),
        THD(10);
        
        private int totalRegisters;
        
        RequestType(int totalRegisters) {
            this.totalRegisters = totalRegisters;
        }

        public int getTotalRegisters() {
            return totalRegisters;
        }
    }
    
    public enum ModbusRegister{
        InstantaneousCurrentPhase1(6,9),
        InstantaneousCurrentPhase2(10,13),
        InstantaneousCurrentPhase3,
        NeutralCurrent,
        PhaseToPhaseVoltagePhase1To2,
        PhaseToPhaseVoltagePhase2To3,
        PhaseToPhaseVoltagePhase3To1,
        PhaseToNeutralVoltagePhase1,
        PhaseToNeutralVoltagePhase2,
        PhaseToNeutralVoltagePhase3,
        Frequency,
        TotalActivePower,
        TotalReactivePower,
        TotalApparentPower,
        ActivePowerPhase1,
        ActivePowerPhase2,
        ActivePowerPhase3,
        ReactivePowerPhase1,
        ReactivePowerPhase2,
        ReactivePowerPhase3,
        ApparentPowerPhase1,
        ApparentPowerPhase2,
        ApparentPowerPhase3,
        DemandCurrentPhase1,
        DemandCurrentPhase2,
        DemandCurrentPhase3,
        Puissance_Apparente_Moyenne_Totale,
        MaximumDemandCurrentPhase1,
        MaximumDemandCurrentPhase2,
        MaximumDemandCurrentPhase3,
        MaximumDemandActivePowerPlus,
        MaximumDemandActivePowerMinus,
        MaximumDemandReactivePowerPlus,
        MaximumDemandReactivePowerMinus,
        MaximumDemandApparentPower,
        OperatingTimeCounter,
        ActiveEnergyInPlus(11,22),
        ReactiveEnergyInPlus,
        ApparentEnergy,
        ActiveEnergyOutMinus,
        ReactiveEnergyOutMinus,
        Input1PulseCounter;
        
        private int frameStartIndex;
        private int frameEndIndex;
        
        public int getFrameStartIndex(){
            return frameStartIndex;
        }
        
        public int getFrameEndIndex(){
            return frameEndIndex;
        }
        
        ModbusRegister(int startIndex, int endIndex){
            this.frameStartIndex = startIndex;
            this.frameEndIndex = endIndex;
        }
        
        ModbusRegister(){
            
        }
    }
}
