/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info;

/**
 * Clase que contiene todas las constantes utilizadas en la aplicacion
 * @author gish@c
 */
public class ConstantsProvider {

    /**
     * Codigo del parametro READ_INTERVAL
     */
    public static final String READ_INTERVAL = "READ_INTERVAL";
    /**
     * Codigo del parametro DIGI_PORT
     */
    public static final String PORT = "DIGI_PORT";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_COMMAND 
     */
    public static final String READ_HOLDING_REGISTERS_COMMAND = "READ_HOLDING_REGISTERS_COMMAND";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES
     */
    public static final String READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES = "READ_HOLDING_REGISTERS_START_ADDRESS_HI_BYTES";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES
     */
    public static final String READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES = "READ_HOLDING_REGISTERS_START_ADDRESS_LO_BYTES";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES
     */
    public static final String READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES = "READ_HOLDING_REGISTERS_REGISTERS_TO_READ_HI_BYTES";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES
     */
    public static final String READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES = "READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_REGISTERS_TO_READ_LO_BYTES
     */
    public static final String READ_HOLDING_REGISTERS_CRC_HI_BYTES = "READ_HOLDING_REGISTERS_CRC_HI_BYTES";
    /**
     * Codigo del parametro READ_HOLDING_REGISTERS_CRC_LO_BYTES
     */
    public static final String READ_HOLDING_REGISTERS_CRC_LO_BYTES = "READ_HOLDING_REGISTERS_CRC_LO_BYTES";
    /**
     * Codigo del parametro SMTP_SERVER
     */
    public static final String SMTP_SERVER = "SMTP_SERVER";
    /**
     * Codigo del parametro SMTP_PORT
     */
    public static final String SMTP_PORT = "SMTP_PORT";
    /**
     * Codigo del parametro MAIL_SENDER
     */
    public static final String MAIL_SENDER = "MAIL_SENDER";
    /**
     * Codigo del parametro MAIL_SENDER_PASSWORD
     */
    public static final String MAIL_SENDER_PASSWORD = "MAIL_SENDER_PASSWORD";
    /**
     * Codigo del parametro MAIL_USE_SSL
     */
    public static final String MAIL_USE_SSL = "MAIL_USE_SSL";
    /**
     * Codigo del parametro KWH_VALUE_1
     */
    public static final String KWH_VALUE_1 = "KWH_VALUE_1";
    /**
     * Codigo del parametro KWH_VALUE_2
     */
    public static final String KWH_VALUE_2 = "KWH_VALUE_2";
    /**
     * Codigo del parametro SERVER_READ_TIMEOUT_INTERVAL
     */
    public static final String SERVER_READ_TIMEOUT_INTERVAL = "SERVER_READ_TIMEOUT_INTERVAL";
    /**
     * Codigo del parametro SERVER_MAX_INACTIVITY_ALLOWED
     */
    public static final String SERVER_MAX_INACTIVITY_ALLOWED = "SERVER_MAX_INACTIVITY_ALLOWED";
    /**
     * Codigo del parametro FEE_STREET_LIGHTNING
     */
    public static final String FEE_STREET_LIGHTNING = "FEE_STREET_LIGHTNING";
    /**
     * Codigo del parametro FEE_GARBAGE_COLLECT
     */
    public static final String FEE_GARBAGE_COLLECT = "FEE_GARBAGE_COLLECT";
    /**
     * Codigo del parametro FEE_FIRE_DEPARTMENT
     */
    public static final String FEE_FIRE_DEPARTMENT = "FEE_FIRE_DEPARTMENT";
    /**
     * Codigo del parametro SERVER_IP
     */
    public static final String SERVER_IP = "SERVER_IP";
    /**
     * Codigo del parametro SERVER_PORT
     */
    public static final String SERVER_PORT = "SERVER_PORT";

    /**
     * Opciones del menu
     */
    public enum OptionsProvider {

        Role(1),
        User(2),
        PowerMeter(3),
        LocationType(4),
        Location(5),
        Zone(6),
        Parameter(7),
        Taxes(8),
        Alarm(9),
        Wizard(10),
        UserPowerMeterReport(11),
        UserPowerMeterException(12),
        UserPowerMeterAlarm(13),
        ZoneReport(14),
        SavedReport(15),
        ConsumptionReport(16);
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
     * Tipo de render para obtener los valores en los renderers personalizados
     */
    public enum DisplayMemberRenderType {

        Method,
        Field
    }

    /**
     * Tipo de retorno dialogo
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
     * Tipo de consulta Modbus
     */
    public enum RequestType {

        HoldingRegisters(198),
        THD(10);
        private int totalRegisters;

        RequestType(int totalRegisters) {
            this.totalRegisters = totalRegisters;
        }

        public int getTotalRegisters() {
            return totalRegisters;

        }
    }

    /**
     * Registros disponibles en el medidor PM-500
     */
    public enum ModbusRegister {

        PhaseToPhaseVoltagePhase1To2(21, 22),
        PhaseToPhaseVoltagePhase2To3(25, 26),
        PhaseToPhaseVoltagePhase3To1(29, 30),
        PhaseToNeutralVoltagePhase1(33, 34),
        PhaseToNeutralVoltagePhase2(37, 38),
        PhaseToNeutralVoltagePhase3(41, 42),
        Frequency(45, 46),
        TotalActivePower(49, 50),
        TotalReactivePower(53, 54),
        TotalApparentPower(57, 58),
        ActivePowerPhase1(65, 66),
        ActivePowerPhase2(69, 70),
        ActivePowerPhase3(73, 74),
        ReactivePowerPhase1(77, 78),
        ReactivePowerPhase2(81, 82),
        ReactivePowerPhase3(85, 86),
        ApparentPowerPhase1(89, 90),
        ApparentPowerPhase2(93, 94),
        ApparentPowerPhase3(97, 98),
        ActiveEnergyIn(181, 182);
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

    /**
     * Tipo de mensajes entre el cliente y el servidor de lecturas
     */
    public enum MessageType {

        Authentication,
        Notification,
        Logout
    }
}
