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

    public enum OptionsProvider {

        Role(1),
        User(2),
        PowerMeter(3),
        LocationType(4),
        Location(5),
        Zone(6),
        Parameter(7);
        private int taskID;

        OptionsProvider(int taskID) {
            this.taskID = taskID;
        }

        public int getTaskID() {
            return taskID;
        }
    }

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

    public enum DisplayMemberRenderType {

        Method,
        Field
    }

    public enum DialogResult {
        
        Ok,
        Cancel
    }

}
