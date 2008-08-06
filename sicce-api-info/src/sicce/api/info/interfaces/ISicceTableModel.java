/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.List;
import javax.swing.table.TableColumn;

/**
 * Define los metodos a ser implementados por las clases que representen a los modelos de tablas 
 * @author gish@c
 */
public interface ISicceTableModel<T> {
    
    /**
     * Devuelve la fuente de datos de la lista 
     * @return
     */
    List<T> getDataSource();
    
    /**
     * Establece la fuente de datos de la lista
     * @param dataSource
     */
    void setDataSource(List<T> dataSource);
    
    /**
     * Devuelve el objeto asociado a la fila correspondiente
     * @param index Indice del objeto que se desea obtener
     * @return Objeto asociado a la fila correspondiente
     */
    T getRow(int index);
    
    /**
     * Indica si la tabla es de solo lectura
     * @return El valor indicando si la tabla es de solo lectura
     */
    boolean isReadOnly();
    
    /**
     * Establece el estado de escritura de la tabla
     * @param readOnly Estado de escritura de la tabla
     */
    void setReadOnly(boolean readOnly);
    
    /**
     * Establece el tamaño de una columna de la tabla
     * @param column Columna a ser modificada
     * @param size Tamaño de la columna
     */
    void SetColumnSize(TableColumn column, int size);
}
