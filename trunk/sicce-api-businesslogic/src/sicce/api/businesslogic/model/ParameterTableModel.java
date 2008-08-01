/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.model;

import java.util.ArrayList;
import java.util.List;
import sicce.api.businesslogic.ParameterBizObject;
import sicce.api.info.interfaces.IParameter;

/**
 *
 * @author gish@c
 */
public class ParameterTableModel extends SicceTableModel<IParameter> {

    public ParameterTableModel(List<IParameter> dataSource) {

        List<String> parametersForMail = new ParameterBizObject().getParameterCodesForMail();
        List<IParameter> filteredParameters = new ArrayList<IParameter>();
        for (IParameter parameter : dataSource) {
            if (!parametersForMail.contains(parameter.getParameterKey())) {
                filteredParameters.add(parameter);
            }
        }
        this.dataSource = filteredParameters;
        this.columns = new String[]{"No.", "Descripción", "Valor"};
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex > 1) {
            return !isReadOnly();
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IParameter parameter = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return parameter.getDescription();
            default:
                return parameter.getValue();
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        IParameter parameter = getDataSource().get(rowIndex);
        switch (columnIndex) {
            case 2:
                parameter.setValue(value.toString());
        }
    }
}
