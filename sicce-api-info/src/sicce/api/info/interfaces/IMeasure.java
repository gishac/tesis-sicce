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
     * Devuelve el valor del registro InstantaneousCurrentPhase1
     * @return Valor del registro InstantaneousCurrentPhase1
     */
    Double getInstantaneousCurrentPhase1();

    /**
     * Establece el valor del registro InstantaneousCurrentPhase1
     * @param instantaneousCurrentPhase1 Valor del registro InstantaneousCurrentPhase1
     */
    void setInstantaneousCurrentPhase1(Double instantaneousCurrentPhase1);

    /**
     * Devuelve el valor del registro InstantaneousCurrentPhase2
     * @return Valor del registro InstantaneousCurrentPhase2
     */
    Double getInstantaneousCurrentPhase2();

    /**
     * Establece el valor del registro InstantaneousCurrentPhase2
     * @param instantaneousCurrentPhase2 Valor del registro InstantaneousCurrentPhase2
     */
    void setInstantaneousCurrentPhase2(Double instantaneousCurrentPhase2);

    /**
     * Devuelve el valor del registro InstantaneousCurrentPhase3
     * @return Valor del registro InstantaneousCurrentPhase3
     */
    Double getInstantaneousCurrentPhase3();

    /**
     * Establece el valor del registro InstantaneousCurrentPhase3
     * @param instantaneousCurrentPhase3 Valor del registro InstantaneousCurrentPhase3
     */
    void setInstantaneousCurrentPhase3(Double instantaneousCurrentPhase3);

    /**
     * Devuelve el valor del registro NeutralCurrent
     * @return Valor del registro NeutralCurrent
     */
    Double getNeutralCurrent();

    /**
     * Establece el valor del registro NeutralCurrent
     * @param neutralCurrent Valor del registro NeutralCurrent
     */
    void setNeutralCurrent(Double neutralCurrent);

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
     * Devuelve el valor del registro DemandCurrentPhase1
     * @return Valor del registro DemandCurrentPhase1
     */
    Double getDemandCurrentPhase1();

    /**
     * Establece el valor del registro DemandCurrentPhase1
     * @param demandCurrentPhase1 Valor del registro DemandCurrentPhase1
     */
    void setDemandCurrentPhase1(Double demandCurrentPhase1);

    /**
     * Devuelve el valor del registro DemandCurrentPhase2
     * @return Valor del registro DemandCurrentPhase2
     */
    Double getDemandCurrentPhase2();

    /**
     * Establece el valor del registro DemandCurrentPhase2
     * @param demandCurrentPhase2 Valor del registro DemandCurrentPhase2
     */
    void setDemandCurrentPhase2(Double demandCurrentPhase2);

    /**
     * Devuelve el valor del registro DemandCurrentPhase3
     * @return Valor del registro DemandCurrentPhase3
     */
    Double getDemandCurrentPhase3();

    /**
     * Establece el valor del registro DemandCurrentPhase3
     * @param demandCurrentPhase3 Valor del registro DemandCurrentPhase3
     */
    void setDemandCurrentPhase3(Double demandCurrentPhase3);

    /**
     * Devuelve el valor del registro PuissanceApparenteMoyenneTotale
     * @return Valor del registro PuissanceApparenteMoyenneTotale
     */
    Double getPuissanceApparenteMoyenneTotale();

    /**
     * Establece el valor del registro PuissanceApparenteMoyenneTotale
     * @param puissanceApparenteMoyenneTotale Valor del registro PuissanceApparenteMoyenneTotale
     */
    void setPuissanceApparenteMoyenneTotale(
            Double puissanceApparenteMoyenneTotale);

    /**
     * Devuelve el valor del registro MaximumDemandCurrentPhase1
     * @return Valor del registro MaximumDemandCurrentPhase1
     */
    Double getMaximumDemandCurrentPhase1();

    /**
     * Establece el valor del registro MaximumDemandCurrentPhase1
     * @param maximumDemandCurrentPhase1 Valor del registro MaximumDemandCurrentPhase1
     */
    void setMaximumDemandCurrentPhase1(Double maximumDemandCurrentPhase1);

    /**
     * Devuelve el valor del registro MaximumDemandCurrentPhase2
     * @return Valor del registro MaximumDemandCurrentPhase2
     */
    Double getMaximumDemandCurrentPhase2();

    /**
     * Establece el valor del registro MaximumDemandCurrentPhase2
     * @param maximumDemandCurrentPhase2 Valor del registro MaximumDemandCurrentPhase2
     */
    void setMaximumDemandCurrentPhase2(Double maximumDemandCurrentPhase2);

    /**
     * Devuelve el valor del registro MaximumDemandCurrentPhase3
     * @return Valor del registro MaximumDemandCurrentPhase3
     */
    Double getMaximumDemandCurrentPhase3();

    /**
     * Establece el valor del registro MaximumDemandCurrentPhase3
     * @param maximumDemandCurrentPhase3 Valor del registro MaximumDemandCurrentPhase3
     */
    void setMaximumDemandCurrentPhase3(Double maximumDemandCurrentPhase3);

    /**
     * Devuelve el valor del registro MaximumDemandActivePowerPlus
     * @return Valor del registro MaximumDemandActivePowerPlus
     */
    Double getMaximumDemandActivePowerPlus();

    /**
     * Establece el valor del registro MaximumDemandActivePowerPlus
     * @param maximumDemandActivePowerPlus Valor del registro MaximumDemandActivePowerPlus
     */
    void setMaximumDemandActivePowerPlus(
            Double maximumDemandActivePowerPlus);

    /**
     * Devuelve el valor del registro MaximumDemandActivePowerMinus
     * @return Valor del registro MaximumDemandActivePowerMinus
     */
    Double getMaximumDemandActivePowerMinus();

    /**
     * Establece el valor del registro MaximumDemandActivePowerMinus
     * @param maximumDemandActivePowerMinus Valor del registro MaximumDemandActivePowerMinus
     */
    void setMaximumDemandActivePowerMinus(
            Double maximumDemandActivePowerMinus);

    /**
     * Devuelve el valor del registro MaximumDemandReactivePowerPlus
     * @return Valor del registro MaximumDemandReactivePowerPlus
     */
    Double getMaximumDemandReactivePowerPlus();

    /**
     * Establece el valor del registro MaximumDemandReactivePowerPlus
     * @param maximumDemandReactivePowerPlus Valor del registro MaximumDemandReactivePowerPlus
     */
    void setMaximumDemandReactivePowerPlus(
            Double maximumDemandReactivePowerPlus);

    /**
     * Devuelve el valor del registro MaximumDemandReactivePowerMinus
     * @return Valor del registro MaximumDemandReactivePowerMinus
     */
    Double getMaximumDemandReactivePowerMinus();

    /**
     * Establece el valor del registro MaximumDemandReactivePowerMinus
     * @param maximumDemandReactivePowerMinus Valor del registro MaximumDemandReactivePowerMinus
     */
    void setMaximumDemandReactivePowerMinus(
            Double maximumDemandReactivePowerMinus);

    /**
     * Devuelve el valor del registro MaximumDemandApparentPower
     * @return Valor del registro MaximumDemandApparentPower
     */
    Double getMaximumDemandApparentPower();

    /**
     * Establece el valor del registro MaximumDemandApparentPower
     * @param maximumDemandApparentPower Valor del registro MaximumDemandApparentPower
     */
    void setMaximumDemandApparentPower(Double maximumDemandApparentPower);

    /**
     * Devuelve el valor del registro OperatingTimeCounter
     * @return Valor del registro OperatingTimeCounter
     */
    Double getOperatingTimeCounter();

    /**
     * Establece el valor del registro OperatingTimeCounter
     * @param operatingTimeCounter Valor del registro OperatingTimeCounter
     */
    void setOperatingTimeCounter(Double operatingTimeCounter);

    /**
     * Devuelve el valor del registro ActiveEnergyInPlus
     * @return Valor del registro ActiveEnergyInPlus
     */
    Double getActiveEnergyInPlus();

    /**
     * Establece el valor del registro ActiveEnergyInPlus
     * @param activeEnergyInPlus Valor del registro ActiveEnergyInPlus
     */
    void setActiveEnergyInPlus(Double activeEnergyInPlus);

    /**
     * Devuelve el valor del registro ReactiveEnergyInPlus
     * @return Valor del registro ReactiveEnergyInPlus
     */
    Double getReactiveEnergyInPlus();

    /**
     * Establece el valor del registro ReactiveEnergyInPlus
     * @param reactiveEnergyInPlus Valor del registro ReactiveEnergyInPlus
     */
    void setReactiveEnergyInPlus(Double reactiveEnergyInPlus);

    /**
     * Devuelve el valor del registro ApparentEnergy
     * @return Valor del registro ApparentEnergy
     */
    Double getApparentEnergy();

    /**
     * Establece el valor del registro ApparentEnergy
     * @param apparentEnergy Valor del registro ApparentEnergy
     */
    void setApparentEnergy(Double apparentEnergy);

    /**
     * Devuelve el valor del registro ActiveEnergyOutMinus
     * @return Valor del registro ActiveEnergyOutMinus
     */
    Double getActiveEnergyOutMinus();

    /**
     * Establece el valor del registro ActiveEnergyOutMinus
     * @param activeEnergyOutMinus Valor del registro ActiveEnergyOutMinus
     */
    void setActiveEnergyOutMinus(Double activeEnergyOutMinus);

    /**
     * Devuelve el valor del registro ReactiveEnergyOutMinus
     * @return Valor del registro ReactiveEnergyOutMinus
     */
    Double getReactiveEnergyOutMinus();

    /**
     * Establece el valor del registro ReactiveEnergyOutMinus
     * @param reactiveEnergyOutMinus valor del registro ReactiveEnergyOutMinus
     */
    void setReactiveEnergyOutMinus(Double reactiveEnergyOutMinus);

    /**
     * Devuelve el valor del registro Input1PulseCounter
     * @return Valor del registro Input1PulseCounter
     */
    Double getInput1PulseCounter();

    /**
     * Establece el valor del registro Input1PulseCounter
     * @param input1PulseCounter Valor del registro Input1PulseCounter
     */
    void setInput1PulseCounter(Double input1PulseCounter);
}
