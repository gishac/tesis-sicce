/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author gish@c
 */
public class ConstantsProvider {

    public enum TasksProvider
    {
        User,
        PowerMeter,
        Location
    }
    
    public enum ToolBarAction
    {
        None,
        RegistryLoaded,
        Edit,
        New,
        Save,
        Delete,
        Search,
        Back
                
    }
}
