/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.List;
import javax.swing.table.TableColumn;

/**
 *
 * @author gish@c
 */
public interface ISicceTableModel<T> {
    List<T> getDataSource();
    void setDataSource(List<T> dataSource);
    T getRow(int index);
    boolean isReadOnly();
    void setReadOnly(boolean readOnly);
    void SetColumnSize(TableColumn column, int size);
}
