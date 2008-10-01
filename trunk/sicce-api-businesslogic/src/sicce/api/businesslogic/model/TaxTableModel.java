/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.List;
import sicce.api.info.interfaces.ITax;

/**
 *
 * @author gish@c
 */
public class TaxTableModel extends SicceTableModel<ITax> {

    public TaxTableModel(List<ITax> dataSource) {
        super(dataSource);
        columns = new String[]{"No.", "Descripción", "Fecha Inicio", "Fecha Fin", "Valor"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ITax tax = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return tax.getDescription();
            case 2:
                return tax.getStartDate().toString();
            case 3:
                return tax.getEndDate().toString();
            case 4:
                return tax.getTaxValue();
            default:
                return null;
        }
    }
}
