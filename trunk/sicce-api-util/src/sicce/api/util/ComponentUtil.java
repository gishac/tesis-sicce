/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.util;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.TableModel;
import sicce.api.info.interfaces.ISicceTableModel;

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
            else if(IsTable(component)){
                JTable table = (JTable) component;
                if(IsSicceTableModel(table.getModel()))
                {
                    ((ISicceTableModel) table.getModel()).setDataSource(null);
                    table.repaint();
                }
            }
            else if(IsCheckBox(component)){
                ((JCheckBox) component).setSelected(false);
            }
            else if(IsDateChooser(component)){
                ((JDateChooser) component).setDate(Calendar.getInstance().getTime());
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
            if(IsTextField(component) || IsComboBox(component) || IsButton(component) || IsCheckBox(component) || IsDateChooser(component)){
                component.setEnabled(enabled);
            }
            else if(IsTable(component))
            {
                TableModel model = ((JTable) component).getModel();
                if(IsSicceTableModel(model))
                    ((ISicceTableModel) model).setReadOnly(!enabled);
            }
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
    
    /**
     * Indica si el componente es una instancia de JTable
     * @param component
     * @return
     */
    public static boolean IsTable(Component component)
    {
        return component instanceof JTable;
    }
    
    /**
     * Indica si el componente es una instancia de JButton
     * @param component
     * @return
     */
    public static boolean IsButton(Component component)
    {
        return component instanceof JButton;
    }
    
    
    /**
     * Indica si el componente es una instancia de ISicceTableModel
     * @param component
     * @return
     */
    public static boolean IsSicceTableModel(TableModel component)
    {
        return component instanceof ISicceTableModel;
    }
    
    /**
     * Indica si el componente es una instancia de JCheckbox
     * @param component
     * @return
     */
    public static boolean IsCheckBox(Component component){
        return component instanceof JCheckBox;
    }
    
    /**
     * Indica si el componente es una instancia de JDateChooser
     * @param component
     * @return
     */
    public static boolean IsDateChooser(Component component){
        return component instanceof JDateChooser;
    }
    
    /**
     * Coloca un formulario o dialogo en el centro de la pantalla
     * @param component
     * @param toolkit
     */
    public static void CenterFormInScreen(Component component, Toolkit toolkit){
        Dimension dimension = component.getSize();
        Dimension screenSize = toolkit.getScreenSize();
        component.setLocation(
                (screenSize.width - dimension.width) / 2,
                (screenSize.height - dimension.height) / 2);
    }
    
}
