/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.util;

import java.awt.Component;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author gish@c
 */
public class ComponentUtil {

    /**
     * Limpia la lista de componentes
     * @param components
     */
    public static void Clear(Component[] components)
    {
        for(Component component : components)
        {
            if(IsTextField(component))
            {
                if(component instanceof JTextField)
                    ((JTextField) component).setText("");
                if(component instanceof JTextArea)
                    ((JTextArea) component).setText("");
                
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
     * Limpia la lista de componentes
     * @param components
     */
    public static void Clear(List<Component> components)
    {
        Component[] toClear = new Component[components.size()];
        for(int i=0; i<= components.size()-1; i++)
        {
            toClear[i] = components.get(i);
        }
        Clear(toClear);            
    }
    
    /**
     * Setea el estado de los componentes
     * @param components
     * @param enabled
     */
    public static void SetState(boolean enabled,Component[] components)
    {
        for(Component component : components)
        {
            if(IsTextField(component) || IsComboBox(component))
                component.setEnabled(enabled);
        }
    }
    
    /**
     * Setea el estado de los componentes
     * @param components
     * @param enabled
     */
    public static void SetState(boolean enabled,List<Component> components)
    {
        Component[] toSetState = new Component[components.size()];
        for(int i=0; i<= components.size()-1; i++)
        {
            toSetState[i] = components.get(i);
        }
        SetState(enabled, toSetState);
    }
    
    /**
     * Indica si el componente es una instancia de JTextField
     * @param component
     * @return
     */
    public static boolean IsTextField(Component component)
    {
        boolean result = component instanceof JTextField || component instanceof JTextArea;
        return result;
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
