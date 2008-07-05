/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import java.util.List;
import javax.swing.JButton;
import javax.swing.table.TableColumn;
import sicce.api.info.interfaces.IFilter;

/**
 *
 * @author gish@c
 */
public class ReportFilterTableModel extends SicceTableModel<IFilter> {

    private TableColumn operatorColumn;

    public ReportFilterTableModel(List<IFilter> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"Descripción", "Operador", "Valor", "buscar"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IFilter pFilter = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pFilter.getField().getAliasField();
            case 1:
                return pFilter.getOperator();
            case 2:
                return pFilter.getValues();
            case 3:
                return new JButton("...");
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3)
            return JButton.class;
        return super.getColumnClass(columnIndex);
    }
    
    


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex >= 1) {
            //return !isReadOnly();
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        IFilter pfilter = getDataSource().get(rowIndex);
        if (pfilter!=null){
        switch (columnIndex) {
            case 1:
                pfilter.setOperator(value.toString());
                break;
            case 2:
                pfilter.setValues(value.toString());
                break;
        }
        }
    }

    public void addFilter(IFilter filter) {
        this.getDataSource().add(filter);
        this.fireTableDataChanged();
    }

   
}
