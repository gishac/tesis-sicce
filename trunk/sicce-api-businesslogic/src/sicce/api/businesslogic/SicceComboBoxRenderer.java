/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

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
    private String valueMember;   
    private DisplayMemberRenderType displayMemberType;
    private DisplayMemberRenderType valueMemberType;

    public String getValueMember() {
        return valueMember;
    }

    public void setValueMember(String valueMember) {
        this.valueMember = valueMember;
    }
    

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
    * @param displayMember Metodo o campo que define de donde se va a tomar el valor a mostrar en el combo
     *@param displayMemberType Tipo de displayMember: Metodo o campo
    * @param valueMember Metodo o campo que define de donde se va a tomar el valor que identifica al objeto
    * @param valueMemberType Tipo de valueMember: Metodo o campo
    */
    public SicceComboBoxRenderer(String displayMember, DisplayMemberRenderType displayMemberType, String valueMember, DisplayMemberRenderType valueMemberType) {
        this(displayMember,displayMemberType);
        setValueMember(valueMember);
        this.valueMemberType = valueMemberType;
    }
    
    /**
     * 
     * @param item
     * @return
     */
    public String GetDisplayMemberValue(Object item) throws Exception
    {
       return GetMemberValue(item, displayMember).toString();
    }
    
     /**
     * 
     * @param item
     * @return
     */
    public String GetValueMemberValue(Object item) throws Exception
    {
       return GetMemberValue(item, valueMember).toString();
    }
    
     /**
     * 
     * @param item
     * @return
     */
    private String GetMemberValue(Object item, String member) throws Exception
    {
        Object result = null;
        if(displayMemberType == DisplayMemberRenderType.Method)
            result = GetByMethod(item, member);
        else
            result = GetByField(item, member);
        return (result != null)? result.toString() : "null";
    }
    
    /**
     * 
     * @param item
     * @return
     */
    private Object GetByMethod(Object item, String methodToCall) throws Exception
    {
        Method method = item.getClass().getMethod(methodToCall, null);
        return method.invoke(item,new Object[0]);
    }
    
    /**
     * 
     * @param item
     * @return
     * @throws java.lang.Exception
     */
    private Object GetByField(Object item, String fieldToGet) throws Exception
    {
        Field field = item.getClass().getField(fieldToGet);
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
