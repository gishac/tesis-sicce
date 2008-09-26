/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import javax.swing.JTable;
import javax.swing.JTextField;
import sicce.api.businesslogic.model.PhaseValuesTableModel;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.processor.viewer.observers.MeasureObserver;

/**
 * Manejador de datos para el panel de Detalles de registros
 * @author gish@c
 */
public class MeasureViewHandler {

    /**
     * Medidor del cual se muestran los registros
     */
    private IPowerMeter activePowerMeter;
    
    /**
     * Tabla para mostrar los valores de los registros
     */
    private JTable measuresTable;
    
    /**
     * Caja de texto para mostrar el parametro tension fase a fase, fase 1 a 2
     */
    private JTextField txtPhaseToPhaseVoltage1To2;
    
    /**
     * Caja de texto para mostrar el parametro tension fase a fase, fase 2 a 3
     */
    private JTextField txtPhaseToPhaseVoltage2To3;
    
    /**
     * Caja de texto para mostrar el parametro tension fase a fase, fase 3 a 1
     */
    private JTextField txtPhaseToPhaseVoltage3To1;
    
    /**
     * Caja de texto para mostrar el parametro Potencia Total Activa
     */
    private JTextField txtTotalActivePower;
    
    /**
     * Caja de texto para mostrar el parametro Potencia Total Reactiva
     */
    private JTextField txtTotalReactivePower;
    
    /**
     * Caja de texto para mostrar el parametro Potencia Total Aparente
     */
    private JTextField txtTotalApparentPower;
    
    /**
     * Caja de texto para mostrar el parametro Energia Activa Consumida
     */
    private JTextField txtTotalActiveEnergyIn;
    
    /**
     * Modelo de tabla para mostrar los valores de los registros
     */
    private PhaseValuesTableModel measurestableModel;

    /**
     * Devuelve el medidor del cual se muestran los registros
     * @return Medidor del cual se muestran los registros
     */
    public IPowerMeter getActivePowerMeter() {
        return activePowerMeter;
    }

    /**
     * Establece el medidor del cual se muestran los registros
     * @param activePowerMeter Medidor del cual se muestran los registros
     */
    public void setActivePowerMeter(IPowerMeter activePowerMeter) {
        this.activePowerMeter = activePowerMeter;
    }

    /**
     * Devuelve la tabla para mostrar los valores de los registros
     * @return Tabla para mostrar los valores de los registros
     */
    public JTable getMeasuresTable() {
        return measuresTable;
    }

    /**
     * Establece la tabla para mostrar los valores de los registros
     * @param measuresTable Tabla para mostrar los valores de los registros
     */
    public void setMeasuresTable(JTable measuresTable) {
        this.measuresTable = measuresTable;
    }

    /**
     * Constructor
     * @param measuresTable Tabla donde se muestran los valores de los registros del medidor
     * @param txtPhaseToPhaseVoltage1To2 Caja de texto para mostrar el parametro tension fase a fase, fase 1 a 2
     * @param txtPhaseToPhaseVoltage2To3 Caja de texto para mostrar el parametro tension fase a fase, fase 2 a 3
     * @param txtPhaseToPhaseVoltage3To1 Caja de texto para mostrar el parametro tension fase a fase, fase 3 a 1
     * @param txtTotalActivePower Caja de texto para mostrar el parametro Potencia Total Activa
     * @param txtTotalReactivePower Caja de texto para mostrar el parametro Potencia Total Reactiva
     * @param txtTotalApparentPower Caja de texto para mostrar el parametro Potencia Total Aparente
     * @param txtTotalActiveEnergyIn Caja de texto para mostrar el parametro Energia Activa Consumida
     */
    public MeasureViewHandler(JTable measuresTable, JTextField txtPhaseToPhaseVoltage1To2,
            JTextField txtPhaseToPhaseVoltage2To3, JTextField txtPhaseToPhaseVoltage3To1, JTextField txtTotalActivePower,
            JTextField txtTotalReactivePower, JTextField txtTotalApparentPower, JTextField txtTotalActiveEnergyIn) {
        this.measuresTable = measuresTable;
        measurestableModel = new PhaseValuesTableModel(null);
        this.measuresTable.setModel(measurestableModel);
        this.txtPhaseToPhaseVoltage1To2 = txtPhaseToPhaseVoltage1To2;
        this.txtPhaseToPhaseVoltage2To3 = txtPhaseToPhaseVoltage2To3;
        this.txtPhaseToPhaseVoltage3To1 = txtPhaseToPhaseVoltage3To1;
        this.txtTotalActivePower = txtTotalActivePower;
        this.txtTotalReactivePower = txtTotalReactivePower;
        this.txtTotalApparentPower = txtTotalApparentPower;
        this.txtTotalActiveEnergyIn = txtTotalActiveEnergyIn;
    }

    /**
     * Muestra los valores de la nueva lectura realizada
     * @param powerMeter Medidor del que proviene la lectura
     * @param measure Lectura a procesar
     */
    public void ProcessMeasure(IPowerMeter powerMeter, IMeasure measure) {
        if (activePowerMeter == null) {
            return;
        }
        if (activePowerMeter.getSerial().equals(powerMeter.getSerial())) {
            measurestableModel.setMeasure(measure);
            this.txtPhaseToPhaseVoltage1To2.setText(measure.getPhaseToPhaseVoltagePhase1To2().toString());
            this.txtPhaseToPhaseVoltage2To3.setText(measure.getPhaseToPhaseVoltagePhase2To3().toString());
            this.txtPhaseToPhaseVoltage3To1.setText(measure.getPhaseToPhaseVoltagePhase3To1().toString());
            this.txtTotalActivePower.setText(measure.getTotalActivePower().toString());
            this.txtTotalApparentPower.setText(measure.getTotalApparentPower().toString());
            this.txtTotalReactivePower.setText(measure.getTotalReactivePower().toString());
            this.txtTotalActiveEnergyIn.setText(measure.getActiveEnergyIn().toString());
        }
    }

    /**
     * Devuelve ol objeto observador para monitorear el proceso de lecturas
     * @return Objeto observador para monitorear el proceso de lecturas
     */
    public MeasureObserver getMeasureObserver() {
        return new MeasureObserver(this);
    }
}
