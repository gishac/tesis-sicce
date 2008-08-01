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
     * 
     */
    public static final String READ_INTERVAL = "READ_INTERVAL";
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
    public static final String SMTP_SERVER = "SMTP_SERVER";
    public static final String SMTP_PORT = "SMTP_PORT";
    public static final String MAIL_SENDER = "MAIL_SENDER";
    public static final String MAIL_SENDER_PASSWORD = "MAIL_SENDER_PASSWORD";
    public static final String MAIL_USE_SSL = "MAIL_USE_SSL";

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

    public enum RequestType {

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

    public enum ModbusRegister {

        InstantaneousCurrentPhase1(10, 14),
        InstantaneousCurrentPhase2(18, 22),
        InstantaneousCurrentPhase3(26, 30),
        NeutralCurrent(34, 38),
        PhaseToPhaseVoltagePhase1To2(42, 46),
        PhaseToPhaseVoltagePhase2To3(50, 54),
        PhaseToPhaseVoltagePhase3To1(58, 62),
        PhaseToNeutralVoltagePhase1(66, 70),
        PhaseToNeutralVoltagePhase2(74, 78),
        PhaseToNeutralVoltagePhase3(82, 86),
        Frequency(90, 94),
        TotalActivePower(98, 102),
        TotalReactivePower(106, 110),
        TotalApparentPower(114, 118),
        ActivePowerPhase1(130, 134),
        ActivePowerPhase2(138, 142),
        ActivePowerPhase3(146, 150),
        ReactivePowerPhase1(154, 158),
        ReactivePowerPhase2(162, 166),
        ReactivePowerPhase3(170, 174),
        ApparentPowerPhase1(178, 182),
        ApparentPowerPhase2(190, 194),
        ApparentPowerPhase3(102, 106),
        DemandCurrentPhase1(106, 110),
        DemandCurrentPhase2(110, 114),
        DemandCurrentPhase3(114, 118),
        Puissance_Apparente_Moyenne_Totale(122, 126),
        MaximumDemandCurrentPhase1(126, 130),
        MaximumDemandCurrentPhase2(130, 134),
        MaximumDemandCurrentPhase3(134, 138),
        MaximumDemandActivePowerPlus(138, 142),
        MaximumDemandActivePowerMinus(142, 146),
        MaximumDemandReactivePowerPlus(146, 150),
        MaximumDemandReactivePowerMinus(150, 154),
        MaximumDemandApparentPower(154, 158),
        OperatingTimeCounter(158, 162),
        ActiveEnergyInPlus(162, 166),
        ReactiveEnergyInPlus(166, 170),
        ApparentEnergy(170, 174),
        ActiveEnergyOutMinus(174, 178),
        ReactiveEnergyOutMinus(178, 182),
        Input1PulseCounter(182, 186);
        private int frameStartIndex;
        private int frameEndIndex;

        public int getFrameStartIndex() {
            return frameStartIndex;
        }

        public int getFrameEndIndex() {
            return frameEndIndex;
        }

        ModbusRegister(int startIndex, int endIndex) {
            this.frameStartIndex = startIndex;
            this.frameEndIndex = endIndex;
        }

        ModbusRegister() {

        }
    }
}
