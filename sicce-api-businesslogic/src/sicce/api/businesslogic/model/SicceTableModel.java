/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import sicce.api.info.interfaces.ISicceTableModel;

/**
 * Modelo generico para el manejo de JTables
 * @author gish@c
 */
public abstract class SicceTableModel<T> extends AbstractTableModel implements ISicceTableModel {

    protected List<T> dataSource;
    protected String[] columns = null;
    protected boolean readOnly;

    public List<T> getDataSource() {
        if (dataSource == null) {
            dataSource = new ArrayList<T>();
        }
        return dataSource;
    }
    
    public void setDataSource(List dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Constructor
     */
    public SicceTableModel() {
    }

    /**
     * Constructor
     * @param dataSource Fuente de datos para la tabla
     */
    public SicceTableModel(List<T> dataSource) {
        this.dataSource = dataSource;
    }
    
     /**
     * Constructor
     * @param dataSource Fuente de datos para la tabla
     */
    public SicceTableModel(Set<T> dataSource) {
        if(dataSource != null){
            for(T item : dataSource)
                getDataSource().add(item);
        }
    }

    /**
     * Devuelve la cantidad de filas en la tabla
     * @return Cantidad de filas en la tabla
     */
    public int getRowCount() {
        return getDataSource().size();
    }

    /**
     * Devuelve la cantidad de columnas en la tabla
     * @return cantidad de columnas en la tabla
     */
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Devuelve el nombre de la columna en la tabla
     * @param column Indice de la columna en la tabla
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    /**
     * Devuelve el valor en la celda en la fila y columna 
     * @param rowIndex Numero de fila
     * @param columnIndex Numero de columna
     * @return Valor en la celda en la fila y columna 
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("The method is not implemented");
    }

    public T getRow(int index) {
        if (getRowCount() > index) {
            return getDataSource().get(index);
        }
        return null;
    }

    public void SetColumnSize(TableColumn column, int size) {
        column.setMinWidth(size);
        column.setPreferredWidth(size);
        column.setResizable(true);
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        
    }
}
