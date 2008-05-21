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
        Alarm(7);
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
}
