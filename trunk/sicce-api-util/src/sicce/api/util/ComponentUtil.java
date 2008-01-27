/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.util;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author gish@c
 */
public class ComponentUtil {

    /**
     * Limpia la lista de components
     * @param components
     */
    public static void Clear(Component[] components)
    {
        for(Component component : components)
        {
            if(IsTextField(component))
            {
                ((JTextField) component).setText("");
            }
            else if(IsComboBox(component))
            {
                JComboBox comboBox = (JComboBox) component;
                if(comboBox.getItemCount() > 0)
                    comboBox.setSelectedIndex(0);
            }
        }
    }
    
    /**
     * Setea el estado de los componentes
     * @param components
     * @param enabled
     */
    public static void SetState(Component[] components, boolean enabled)
    {
        for(Component component : components)
        {
            if(IsTextField(component) || IsComboBox(component))
                component.setEnabled(enabled);
        }
    }
    
    /**
     * Indica si el componente es una instancia de JTextField
     * @param component
     * @return
     */
    public static boolean IsTextField(Component component)
    {
        return component instanceof JTextField;
    }
    
    /**
     * Indica si el componente es una instancia de JComboBox
     * @param component
     * @return
     */
    public static boolean IsComboBox(Component component)
    {
        return component instanceof JComboBox;
    }
    
}
