/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import java.util.ArrayList;
import java.util.List;
import sicce.api.info.ConstantsProvider.TasksProvider;

/**
 *
 * @author gish@c
 */
public class TaskInfo {
 
    private String name;;
    private String iconName;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public TaskInfo() {
    }
    
    public TaskInfo(String name, String description, String iconName) {
        this.name = name;
        this.description = description;
        this.iconName = iconName;
    }
    
    /**
     * Crea la lista de tareas para el panel 
     * @return
     */
    public static List<TaskInfo> getTasks()
    {
        List<TaskInfo> tasks = new ArrayList<TaskInfo>();
        for(TasksProvider task : TasksProvider.values())
        {
            TaskInfo taskInfo = new TaskInfo(task.toString(),"Description" + task.toString(),"Icon" + task.toString());
            tasks.add(taskInfo);
        }
        return tasks;
    }
    
}
