/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import javax.swing.table.TableColumn;
import sicce.api.info.interfaces.ILocationType;

/**
 *
 * @author gish@c
 */
public class LocationTypeTableModel extends SicceTableModel<ILocationType> {

    public LocationTypeTableModel(List<ILocationType> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Descripción"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ILocationType ltype = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex;
            case 1: 
                return ltype.getDescription();         
            default: 
                return null;
        }
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
