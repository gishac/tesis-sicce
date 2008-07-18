/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic.model;

import sicce.api.info.interfaces.IMeasure;

/**
 *
 * @author gish@c
 */
public class PhaseValuesTableModel extends SicceTableModel<IMeasure> {

    private IMeasure measure;
    
    public IMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(IMeasure measure) {
        this.measure = measure;
        this.fireTableDataChanged();
    }
    
    public PhaseValuesTableModel(IMeasure dataSource) {
        columns = new String[]{"", "Fase 1","Fase 2", "Fase 3"};
        this.measure = dataSource;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if(rowIndex == 0 && columnIndex == 0)
            value = "Intensidad Instantánea";
        if(rowIndex == 1 && columnIndex == 0)
            value = "Tension Fase a Neutro";
        if(rowIndex == 2 && columnIndex == 0)
            value = "Potencia Activa";
        if(rowIndex == 3 && columnIndex == 0)
            value = "Potencia Reactiva";
        if(this.measure != null){
            
        }
        return value;
    }

    
    
    @Override
    public int getRowCount() {
        return 4;
    }

    
    
    
    
}
