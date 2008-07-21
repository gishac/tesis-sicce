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
        columns = new String[]{"", "Fase 1", "Fase 2", "Fase 3"};
        this.measure = dataSource;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if (rowIndex == 0 && columnIndex == 0) {
            value = "Intensidad Instantánea";
        }
        if (rowIndex == 1 && columnIndex == 0) {
            value = "Tension Fase a Neutro";
        }
        if (rowIndex == 2 && columnIndex == 0) {
            value = "Potencia Activa";
        }
        if (rowIndex == 3 && columnIndex == 0) {
            value = "Potencia Reactiva";
        }
        if (this.measure != null) {
            if (rowIndex == 0) {
                if (columnIndex == 1) {
                    value = measure.getInstantaneousCurrentPhase1();
                }
                if (columnIndex == 2) {
                    value = measure.getInstantaneousCurrentPhase2();
                }
                if (columnIndex == 3) {
                    value = measure.getInstantaneousCurrentPhase2();
                }
            }
            if (rowIndex == 1) {
                if (columnIndex == 1) {
                    value = measure.getPhaseToNeutralVoltagePhase1();
                }
                if (columnIndex == 2) {
                    value = measure.getPhaseToNeutralVoltagePhase2();
                }
                if (columnIndex == 3) {
                    value = measure.getPhaseToNeutralVoltagePhase3();
                }
            }
            if (rowIndex == 2) {
                if (columnIndex == 1) {
                    value = measure.getActivePowerPhase1();
                }
                if (columnIndex == 2) {
                    value = measure.getActivePowerPhase2();
                }
                if (columnIndex == 3) {
                    value = measure.getActivePowerPhase3();
                }
            }
            if (rowIndex == 3) {
                if (columnIndex == 1) {
                    value = measure.getReactivePowerPhase1();
                }
                if (columnIndex == 2) {
                    value = measure.getReactivePowerPhase2();
                }
                if (columnIndex == 3) {
                    value = measure.getReactivePowerPhase3();
                }
            }
        }
        return value;
    }

    @Override
    public int getRowCount() {
        return 4;
    }
}
