/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.JComboBox;
import sicce.api.businesslogic.comparator.ComboBoxItemComparator;
import sicce.api.businesslogic.model.SicceComboBoxModel;
import sicce.api.businesslogic.renderer.SicceComboBoxRenderer;
import sicce.api.info.ComboBoxItem;
import sicce.api.info.ConstantsProvider.DisplayMemberRenderType;
import sicce.api.info.ConstantsProvider.ModbusRegister;

/**
 * Administrador de datos del combo de parametros disponibles
 * @author gish@c
 */
public class MeasureVisibilityHandler {

    /**
     * Registros disponibles a seleccionar para monitorear
     */
    private HashMap<ModbusRegister, String> registersInViews;

    /**
     * Devuelve los registros disponibles a seleccionar para monitorear
     * @return
     */
    public HashMap<ModbusRegister, String> getRegistersInViews() {
        if (registersInViews == null) {
            registersInViews = new HashMap<ModbusRegister, String>();
        }
        return registersInViews;
    }
    /**
     * Instancia del objeto MeasureVisibilityHandler
     */
    private static MeasureVisibilityHandler instance;

    /**
     * Devuelve la instancia del objeto MeasureVisibilityHandler
     * @return Instancia del objeto MeasureVisibilityHandler
     */
    public static MeasureVisibilityHandler getInstance() {
        if (instance == null) {
            instance = new MeasureVisibilityHandler();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private MeasureVisibilityHandler() {
        //getRegistersInViews().put(ModbusRegister.InstantaneousCurrentPhase1, "Intensidad Instantánea, Fase 1");
        //getRegistersInViews().put(ModbusRegister.InstantaneousCurrentPhase2, "Intensidad Instantánea, Fase 2");
        //getRegistersInViews().put(ModbusRegister.InstantaneousCurrentPhase3, "Intensidad Instantánea, Fase 3");
        //getRegistersInViews().put(ModbusRegister.NeutralCurrent, "Intensidad del Neutro");
        getRegistersInViews().put(ModbusRegister.ActiveEnergyIn, "Total Energía Consumida");
        getRegistersInViews().put(ModbusRegister.PhaseToPhaseVoltagePhase1To2, "Tensión Fase a Fase, Fase 1 a 2");
        getRegistersInViews().put(ModbusRegister.PhaseToPhaseVoltagePhase2To3, "Tensión Fase a Fase, Fase 2 a 3");
        getRegistersInViews().put(ModbusRegister.PhaseToPhaseVoltagePhase3To1, "Tensión Fase a Fase, Fase 3 a 1");
        getRegistersInViews().put(ModbusRegister.PhaseToNeutralVoltagePhase1, "Tensión Fase a Neutro, Fase 1");
        getRegistersInViews().put(ModbusRegister.PhaseToNeutralVoltagePhase2, "Tensión Fase a Neutro, Fase 2");
        getRegistersInViews().put(ModbusRegister.PhaseToNeutralVoltagePhase3, "Tensión Fase a Neutro, Fase 3");
        getRegistersInViews().put(ModbusRegister.Frequency, "Frecuencia");
        getRegistersInViews().put(ModbusRegister.TotalActivePower, "Potencia Activa Total");
        getRegistersInViews().put(ModbusRegister.TotalReactivePower, "Potencia Reactiva Total");
        getRegistersInViews().put(ModbusRegister.TotalApparentPower, "Potencia Aparente Total");
        getRegistersInViews().put(ModbusRegister.ActivePowerPhase1, "Potencia Activa, Fase 1");
        getRegistersInViews().put(ModbusRegister.ActivePowerPhase2, "Potencia Activa, Fase 2");
        getRegistersInViews().put(ModbusRegister.ActivePowerPhase3, "Potencia Activa, Fase 3");
        getRegistersInViews().put(ModbusRegister.ReactivePowerPhase1, "Potencia Reactiva, Fase 1");
        getRegistersInViews().put(ModbusRegister.ReactivePowerPhase2, "Potencia Reactiva, Fase 2");
        getRegistersInViews().put(ModbusRegister.ReactivePowerPhase3, "Potencia Reactiva, Fase 3");
        getRegistersInViews().put(ModbusRegister.ApparentPowerPhase1, "Potencia Aparente, Fase 1");
        getRegistersInViews().put(ModbusRegister.ApparentPowerPhase2, "Potencia Aparente, Fase 2");
    }

    /**
     * Llena el combo con los posibles registros del medidor a visualizar
     * @param cmbMeasures Combo a ser cargado con los registros disponibles del medidor
     */
    public void FillMeasures(JComboBox cmbMeasures) {
        List<ComboBoxItem<ModbusRegister>> registers = new ArrayList<ComboBoxItem<ModbusRegister>>();
        for (Entry<ModbusRegister, String> entry : getRegistersInViews().entrySet()) {
            ComboBoxItem<ModbusRegister> item = new ComboBoxItem<ModbusRegister>();
            item.setId(entry.getValue());
            item.setValue(entry.getKey());
            registers.add(item);
        }
        Collections.sort(registers, new ComboBoxItemComparator());
        SicceComboBoxRenderer comboRenderer = new SicceComboBoxRenderer("getId", DisplayMemberRenderType.Method, "getValue", DisplayMemberRenderType.Method);
        cmbMeasures.setRenderer(comboRenderer);
        cmbMeasures.setModel(new SicceComboBoxModel(registers));
        cmbMeasures.setSelectedIndex(1);

    }
}
