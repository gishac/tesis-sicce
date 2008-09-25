/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Date;

/**
 * Define los metodos a ser implementados por las clases que representen a las lecturas del medidor
 * @author gish@c
 */
public interface IMeasure {

    /**
     * Devuelve el codigo de la medicion
     * @return
     */
    Integer getIdMeasure();

    /**
     * Establece el codigo de la medicion
     * @param idMeasure Codigo de la medicion
     */
    void setIdMeasure(Integer idMeasure);

    /**
     * Devuelve el medidor sobre el que se realizo la lectura
     * @return Medidor sobre el que se realizo la lectura
     */
    IPowerMeter getPowerMeter();

    /**
     * Establece el medidor sobre el que se realiza la lectura
     * @param powerMeter Medidor sobre el que se realiza la lectura
     * @see IPowerMeter
     */
    void setPowerMeter(IPowerMeter powerMeter);

    /**
     * Devuelve la dependencia que tiene asignado el medidor sobre el que se realizan las lecturas
     * @return Dependencia que tiene asignado el medidor sobre el que se realizan las lecturas
     * @see ILocation
     */
    ILocation getLocation();

    /**
     * Establece la dependencia que tiene asignado el medidor sobre el que se realizan las lecturas
     * @param location Dependencia que tiene asignado el medidor sobre el que se realizan las lecturas
     * @see ILocation
     */
    void setLocation(ILocation location);

    /**
     * Devuelve la fecha en la que se realiza la lectura
     * @return Fecha en la que se realiza la lectura
     */
    Date getDateMeasure();

    /**
     * Establece la fecha en la que se realiza la lectura
     * @param dateMeasure Fecha en la que se realiza la lectura
     */
    void setDateMeasure(Date dateMeasure);

  
    /**
     * Devuelve el valor del registro PhaseToPhaseVoltagePhase1To2
     * @return Valor del registro PhaseToPhaseVoltagePhase1To2
     */
    Double getPhaseToPhaseVoltagePhase1To2();

    /**
     * Establece el valor del registro PhaseToPhaseVoltagePhase1To2
     * @param phaseToPhaseVoltagePhase1To2 Valor del registro PhaseToPhaseVoltagePhase1To2
     */
    void setPhaseToPhaseVoltagePhase1To2(
            Double phaseToPhaseVoltagePhase1To2);

    /**
     * Devuelve el valor del registro PhaseToPhaseVoltagePhase2To3
     * @return Valor del registro PhaseToPhaseVoltagePhase2To3
     */
    Double getPhaseToPhaseVoltagePhase2To3();

    /**
     * Establece el valor del registro PhaseToPhaseVoltagePhase2To3
     * @param phaseToPhaseVoltagePhase2To3 Valor del registro PhaseToPhaseVoltagePhase2To3
     */
    void setPhaseToPhaseVoltagePhase2To3(
            Double phaseToPhaseVoltagePhase2To3);

    /**
     * Devuelve el valor del registro PhaseToPhaseVoltagePhase3To1
     * @return Valor del registro PhaseToPhaseVoltagePhase3To1
     */
    Double getPhaseToPhaseVoltagePhase3To1();

    /**
     * Establece el valor del registro PhaseToPhaseVoltagePhase3To1
     * @param phaseToPhaseVoltagePhase3To1 Valor del registro PhaseToPhaseVoltagePhase3To1
     */
    void setPhaseToPhaseVoltagePhase3To1(
            Double phaseToPhaseVoltagePhase3To1);

    /**
     * Devuelve el valor del registro PhaseToNeutralVoltagePhase1
     * @return Valor del registro PhaseToNeutralVoltagePhase1
     */
    Double getPhaseToNeutralVoltagePhase1();

    /**
     * Establece el valor del registro PhaseToNeutralVoltagePhase1
     * @param phaseToNeutralVoltagePhase1 Valor del registro PhaseToNeutralVoltagePhase1
     */
    void setPhaseToNeutralVoltagePhase1(
            Double phaseToNeutralVoltagePhase1);

    /**
     * Devuelve el valor del registro PhaseToNeutralVoltagePhase2
     * @return Valor del registro PhaseToNeutralVoltagePhase2
     */
    Double getPhaseToNeutralVoltagePhase2();

    /**
     * Establece el valor del registro PhaseToNeutralVoltagePhase2
     * @param phaseToNeutralVoltagePhase2 Valor del registro PhaseToNeutralVoltagePhase2
     */
    void setPhaseToNeutralVoltagePhase2(
            Double phaseToNeutralVoltagePhase2);

    /**
     * Devuelve el valor del registro PhaseToNeutralVoltagePhase3
     * @return Valor del registro PhaseToNeutralVoltagePhase3
     */
    Double getPhaseToNeutralVoltagePhase3();

    /**
     * Establece el valor del registro PhaseToNeutralVoltagePhase3
     * @param phaseToNeutralVoltagePhase3 Valor del registro PhaseToNeutralVoltagePhase3
     */
    void setPhaseToNeutralVoltagePhase3(
            Double phaseToNeutralVoltagePhase3);

    /**
     * Devuelve el valor del registro Frequency
     * @return Valor del registro Frequency
     */
    Double getFrequency();

    /**
     * Establece el valor del registro Frequency
     * @param frequency Valor del registro Frequency
     */
    void setFrequency(Double frequency);

    /**
     * Devuelve el valor del registro TotalActivePower
     * @return Valor del registro TotalActivePower
     */
    Double getTotalActivePower();

    /**
     * Establece el valor del registro TotalActivePower
     * @param totalActivePower Valor del registro TotalActivePower
     */
    void setTotalActivePower(Double totalActivePower);

    /**
     * Devuelve el valor del registro TotalReactivePower
     * @return Valor del registro TotalReactivePower
     */
    Double getTotalReactivePower();

    /**
     * Establece el valor del registro TotalReactivePower
     * @param totalReactivePower Valor del registro TotalReactivePower
     */
    void setTotalReactivePower(Double totalReactivePower);

    /**
     * Devuelve el valor del registro TotalApparentPower
     * @return Valor del registro TotalApparentPower
     */
    Double getTotalApparentPower();

    /**
     * Establece el valor del registro TotalApparentPower
     * @param totalApparentPower Valor del registro TotalApparentPower
     */
    void setTotalApparentPower(Double totalApparentPower);

    /**
     * Devuelve el valor del registro ActivePowerPhase1
     * @return Valor del registro ActivePowerPhase1
     */
    Double getActivePowerPhase1();

    /**
     * Establece el valor del registro ActivePowerPhase1
     * @param activePowerPhase1 Valor del registro ActivePowerPhase1
     */
    void setActivePowerPhase1(Double activePowerPhase1);

    /**
     * Devuelve el valor del registro ActivePowerPhase2
     * @return Valor del registro ActivePowerPhase2
     */
    Double getActivePowerPhase2();

    /**
     * Establece el valor del registro ActivePowerPhase2
     * @param activePowerPhase2 Valor del registro ActivePowerPhase2
     */
    void setActivePowerPhase2(Double activePowerPhase2);

    /**
     * Devuelve el valor del registro ActivePowerPhase3
     * @return Valor del registro ActivePowerPhase3
     */
    Double getActivePowerPhase3();

    /**
     * Establece el valor del registro ActivePowerPhase3
     * @param activePowerPhase3 Valor del registro ActivePowerPhase3
     */
    void setActivePowerPhase3(Double activePowerPhase3);

    /**
     * Devuelve el valor del registro ReactivePowerPhase1
     * @return Valor del registro ReactivePowerPhase1
     */
    Double getReactivePowerPhase1();

    /**
     * Establece el valor del registro ReactivePowerPhase1
     * @param reactivePowerPhase1 Valor del registro ReactivePowerPhase1
     */
    void setReactivePowerPhase1(Double reactivePowerPhase1);

    /**
     * Devuelve el valor del registro ReactivePowerPhase2
     * @return Valor del registro ReactivePowerPhase2
     */
    Double getReactivePowerPhase2();

    /**
     * Establece el valor del registro ReactivePowerPhase2
     * @param reactivePowerPhase2 Valor del registro ReactivePowerPhase2
     */
    void setReactivePowerPhase2(Double reactivePowerPhase2);

    /**
     * Devuelve el valor del registro ReactivePowerPhase3
     * @return Valor del registro ReactivePowerPhase3
     */
    Double getReactivePowerPhase3();

    /**
     * Establece el valor del registro ReactivePowerPhase3
     * @param reactivePowerPhase3 Valor del registro ReactivePowerPhase3
     */
    void setReactivePowerPhase3(Double reactivePowerPhase3);

    /**
     * Devuelve el valor del registro ApparentPowerPhase1
     * @return Valor del registro ApparentPowerPhase1
     */
    Double getApparentPowerPhase1();

    /**
     * Establece el valor del registro ApparentPowerPhase1
     * @param apparentPowerPhase1 Valor del registro ApparentPowerPhase1
     */
    void setApparentPowerPhase1(Double apparentPowerPhase1);

    /**
     * Devuelve el valor del registro ApparentPowerPhase2
     * @return Valor del registro ApparentPowerPhase2
     */
    Double getApparentPowerPhase2();

    /**
     * Establece el valor del registro ApparentPowerPhase2
     * @param apparentPowerPhase2 Valor del registro ApparentPowerPhase2
     */
    void setApparentPowerPhase2(Double apparentPowerPhase2);

    /**
     * Devuelve el valor del registro ApparentPowerPhase3
     * @return Valor del registro ApparentPowerPhase3
     */
    Double getApparentPowerPhase3();

    /**
     * Establece el valor del registro ApparentPowerPhase3
     * @param apparentPowerPhase3 Valor del registro ApparentPowerPhase3
     */
    void setApparentPowerPhase3(Double apparentPowerPhase3);

    /**
     * Establece el valor del registro ActiveEnergyIn
     * @param activeEnergyInPlus Valor del registro ActiveEnergyIn
     */
    void setActiveEnergyIn(Double activeEnergyInPlus);
    
    /**
     * Devuelve el valor del registro ActiveEnergyIn
     * @return Valor del registro ActiveEnergyIn
     */
    Double getActiveEnergyIn();
}
