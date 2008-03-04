/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import java.util.List;
import javax.swing.table.TableColumn;
import sicce.api.info.interfaces.ILocation;

/**
 *
 * @author gish@c
 */
public class LocationTableModel extends SicceTableModel<ILocation> {

    public LocationTableModel(List<ILocation> dataSource) {
        this.dataSource = dataSource;
        columns = new String[]{"No.", "Ubicación", "Tipo de Dependencia", "Medidor Asignado" , "Descripción" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ILocation location = getDataSource().get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return rowIndex + 1;
            case 1: 
                if (location.getLocation()!=null)
                    return location.getLocation().getDescription();
                else
                    return null;
            case 2:
                return location.getLocationType().getDescription();
            case 3:
                return location.getPowerMeter().getDescription();
            case 4:
                return location.getDescription();
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
