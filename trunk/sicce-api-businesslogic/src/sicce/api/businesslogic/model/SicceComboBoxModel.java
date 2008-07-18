/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.model;

import sicce.api.businesslogic.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import sicce.api.businesslogic.renderer.SicceComboBoxRenderer;
/**
 *
 * @author gish@c
 */
public class SicceComboBoxModel <T> extends AbstractListModel implements ComboBoxModel {

    T current = null;
    List<T> dataSource = null;

    public SicceComboBoxModel(List<T> dataSource) {
        this.dataSource = dataSource;
    }

    public List<T> getDataSource() {
        if(dataSource == null)
            dataSource = new ArrayList<T>();
        return dataSource;
    }

    public void setDataSource(List<T> dataSource) {
        this.dataSource = dataSource;
    }
    
    public void setSelectedItem(Object item) {        
        current = (T) item;
        fireContentsChanged(item, -1, -1);
    }
    
    /**
     * 
     * @param item
     * @param renderer
     */
    public void setSelectedItem(Object item, SicceComboBoxRenderer renderer) {
        if(item == null)
            return;
        for(Object value : getDataSource()){
            try {
                if (renderer.GetValueMemberValue(value).toString().equals(renderer.GetValueMemberValue(item))) {
                    setSelectedItem(item);
                }
            } catch (Exception ex) {
                Logger.getLogger(SicceComboBoxModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public Object getSelectedItem() {
        return current;
    }

    public int getSize() {
       return getDataSource().size();             
    }

    public Object getElementAt(int index) {
        if(getDataSource().size() > index)
            return getDataSource().get(index);
        return null;
    }

}
