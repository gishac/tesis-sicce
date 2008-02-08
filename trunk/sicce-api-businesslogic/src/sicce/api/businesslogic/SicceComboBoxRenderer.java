/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import sicce.api.info.ConstantsProvider.DisplayMemberRenderType;

/**
 *
 * @author gish@c
 */
public class SicceComboBoxRenderer extends JLabel implements ListCellRenderer {
    
    private String displayMember;
    private DisplayMemberRenderType displayMemberType;

    /**
     * 
     * @return
     */
    public String getDisplayMember() {
        return displayMember;
    }

    /**
     * 
     * @param displayMember
     */
    public void setDisplayMember(String displayMember) {
        this.displayMember = displayMember;
    }

    /**
     * 
     * @param displayMember Metodo o campo que define de donde se va a tomar el valor a mostrar en el combo
     * @param displayMemberType Tipo de displayMember: Metodo o campo
     */
    public SicceComboBoxRenderer(String displayMember, DisplayMemberRenderType displayMemberType) {
        setDisplayMember(displayMember);
        this.displayMemberType = displayMemberType;
        this.setOpaque(true);
    }
    
    /**
     * 
     * @param item
     * @return
     */
    private String GetDisplayMemberValue(Object item) throws Exception
    {
        Object result = null;
        if(displayMemberType == DisplayMemberRenderType.Method)
            result = GetByMethod(item);
        else
            result = GetByField(item);
        return (result != null)? result.toString() : "null";
    }
    
    /**
     * 
     * @param item
     * @return
     */
    private Object GetByMethod(Object item) throws Exception
    {
        Method method = item.getClass().getMethod(displayMember, null);
        return method.invoke(item,new Object[0]);
    }
    
    /**
     * 
     * @param item
     * @return
     * @throws java.lang.Exception
     */
    private Object GetByField(Object item) throws Exception
    {
        Field field = item.getClass().getField(displayMember);
        return field.get(item);
    }
    
    /**
     * 
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            if (value != null) {                
                if(isSelected)
                {
                    this.setBackground(list.getSelectionBackground());
                    this.setForeground(list.getSelectionForeground());
                }
                else
                {
                    this.setBackground(list.getBackground());
                    this.setForeground(list.getSelectionForeground());
                }                
                this.setText(GetDisplayMemberValue(value));
            }
        } 
        catch (Exception ex) {
            Logger.getLogger(SicceComboBoxRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
}
