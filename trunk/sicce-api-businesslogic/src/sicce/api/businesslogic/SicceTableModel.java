/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

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

   List<T> dataSource;
    protected String[] columns = null;
    protected boolean readOnly;

    /**
     * Devuelve la fuente de datos asignada para el modelo
     * @return
     */
    public List<T> getDataSource() {
        if (dataSource == null) {
            dataSource = new ArrayList<T>();
        }
        return dataSource;
    }
    
    /**
     * Setea la fuente de datos del modelo
     * @param dataSource
     */
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
     * @param dataSource Fuente de datos para el modelo
     */
    public SicceTableModel(List<T> dataSource) {
        this.dataSource = dataSource;
    }
    
     /**
     * Constructor
     * @param dataSource Fuente de datos para el modelo
     */
    public SicceTableModel(Set<T> dataSource) {
        if(dataSource != null){
            for(T item : dataSource)
                getDataSource().add(item);
        }
    }

    /**
     * 
     * @return El numero de elementos a mostrar en la tabla
     */
    public int getRowCount() {
        return getDataSource().size();
    }

    /**
     * 
     * @return El numero de columnas a mostrar en la tabla
     */
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Devuelve el nombre de la columna en la tabla
     * @param column Indice de la columna
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
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("The method is not implemented");
    }

    /**
     * Devuelve el objeto cargado en la fila
     * @param index Fila del objeto a leer
     * @return
     */
    public T getRow(int index) {
        if (getRowCount() > index) {
            return getDataSource().get(index);
        }
        return null;
    }

    /**
     * Ajusta el Tamaño de una columna específica, dentro de un jgrid
     * @param column Columna a Ajustar
     * @param size Tamaño de la columna.
     */
    public void SetColumnSize(TableColumn column, int size) {
        column.setMinWidth(size);
        column.setPreferredWidth(size);
        column.setResizable(true);
    }

    /**
     * Indica si la tabla es de solo lectura
     * @return
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Setea el estado de lectura de la tabla
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        
    }
}
