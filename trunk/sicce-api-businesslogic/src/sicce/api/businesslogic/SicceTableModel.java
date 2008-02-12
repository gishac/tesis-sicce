/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author gish@c
 */
public abstract class SicceTableModel<T> extends AbstractTableModel {

    List<T> dataSource;
    protected String[] columns = null;

    protected List<T> getDataSource() {
        if (dataSource == null) {
            dataSource = new ArrayList<T>();
        }
        return dataSource;
    }

    public SicceTableModel() {
    }

    public SicceTableModel(List<T> dataSource) {
        this.dataSource = dataSource;
    }

    public int getRowCount() {
        return getDataSource().size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("The method is not implemented");
    }

    public T getRow(int index) {
        if (getRowCount() > index) {
            return getDataSource().get(index);
        }
        return null;
    }
     /**
     * Ajusta el Tamaño de una columna específica, dentro de un jgrid
     * @param pColumna Posición de la Columna a Ajustar
     * @param pTamanio Tamaño de la columna.
     */
    public static void ColumnSize(TableColumn pColumna, int pTamanio)
    {
        pColumna.setMinWidth(pTamanio);
        pColumna.setPreferredWidth(pTamanio);
        pColumna.setResizable(true);
    };
    
}
