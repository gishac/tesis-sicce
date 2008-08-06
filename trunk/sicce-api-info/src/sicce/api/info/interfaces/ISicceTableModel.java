/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.List;
import javax.swing.table.TableColumn;

/**
 * Define los metodos a ser implementados por las clases que representen 
 * @author gish@c
 */
public interface ISicceTableModel<T> {
    
    /**
     * 
     * @return
     */
    List<T> getDataSource();
    
    /**
     * 
     * @param dataSource
     */
    void setDataSource(List<T> dataSource);
    
    /**
     * 
     * @param index
     * @return
     */
    T getRow(int index);
    
    /**
     * 
     * @return
     */
    boolean isReadOnly();
    
    /**
     * 
     * @param readOnly
     */
    void setReadOnly(boolean readOnly);
    
    /**
     * 
     * @param column
     * @param size
     */
    void SetColumnSize(TableColumn column, int size);
}
