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
        InstantaneousCurrentPhase1(6,10),
        InstantaneousCurrentPhase2(10,14),
        InstantaneousCurrentPhase3(14,18),
        NeutralCurrent(18,22),
        PhaseToPhaseVoltagePhase1To2(22,26),
        PhaseToPhaseVoltagePhase2To3(26,30),
        PhaseToPhaseVoltagePhase3To1(30,34),
        PhaseToNeutralVoltagePhase1(34,38),
        PhaseToNeutralVoltagePhase2(38,42),
        PhaseToNeutralVoltagePhase3(42,46),
        Frequency(46,50),
        TotalActivePower(50,54),
        TotalReactivePower(54,58),
        TotalApparentPower(58,62),
        ActivePowerPhase1(62,66),
        ActivePowerPhase2(66,70),
        ActivePowerPhase3(70,74),
        ReactivePowerPhase1(74,78),
        ReactivePowerPhase2(78,82),
        ReactivePowerPhase3(82,86),
        ApparentPowerPhase1(90,94),
        ApparentPowerPhase2(94,98),
        ApparentPowerPhase3(102,106),
        DemandCurrentPhase1(106,110),
        DemandCurrentPhase2(110,114),
        DemandCurrentPhase3(114,118),
        Puissance_Apparente_Moyenne_Totale(122,126),
        MaximumDemandCurrentPhase1(126,130),
        MaximumDemandCurrentPhase2(130,134),
        MaximumDemandCurrentPhase3(134,138),
        MaximumDemandActivePowerPlus(138,142),
        MaximumDemandActivePowerMinus(142,146),
        MaximumDemandReactivePowerPlus(146,150),
        MaximumDemandReactivePowerMinus(150,154),
        MaximumDemandApparentPower(154,158),
        OperatingTimeCounter(158,162),
        ActiveEnergyInPlus(162,166),
        ReactiveEnergyInPlus(166,170),
        ApparentEnergy(170,174),
        ActiveEnergyOutMinus(174,178),
        ReactiveEnergyOutMinus(178,182),
        Input1PulseCounter(182,186);
        
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
