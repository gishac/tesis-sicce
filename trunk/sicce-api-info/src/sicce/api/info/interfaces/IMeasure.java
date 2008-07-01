/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Date;
import sicce.api.info.Location;
import sicce.api.info.PowerMeter;

/**
 *
 * @author gish@c
 */
public interface IMeasure {

    Integer getIdMeasure();

    void setIdMeasure(Integer idMeasure);

    IPowerMeter getPowerMeter();

    void setPowerMeter(IPowerMeter powerMeter);

    ILocation getLocation();

    void setLocation(ILocation location);

    Date getDateMeasure();

    void setDateMeasure(Date dateMeasure);

    Double getInstantaneousCurrentPhase1();

    void setInstantaneousCurrentPhase1(Double instantaneousCurrentPhase1);

    Double getInstantaneousCurrentPhase2();

    void setInstantaneousCurrentPhase2(Double instantaneousCurrentPhase2);

    Double getInstantaneousCurrentPhase3();

    void setInstantaneousCurrentPhase3(Double instantaneousCurrentPhase3);

    Double getNeutralCurrent();

    void setNeutralCurrent(Double neutralCurrent);

    Double getPhaseToPhaseVoltagePhase1To2();

    void setPhaseToPhaseVoltagePhase1To2(
            Double phaseToPhaseVoltagePhase1To2);

    Double getPhaseToPhaseVoltagePhase2To3();

    void setPhaseToPhaseVoltagePhase2To3(
            Double phaseToPhaseVoltagePhase2To3);

    Double getPhaseToPhaseVoltagePhase3To1();

    void setPhaseToPhaseVoltagePhase3To1(
            Double phaseToPhaseVoltagePhase3To1);

    Double getPhaseToNeutralVoltagePhase1();

    void setPhaseToNeutralVoltagePhase1(
            Double phaseToNeutralVoltagePhase1);

    Double getPhaseToNeutralVoltagePhase2();

    void setPhaseToNeutralVoltagePhase2(
            Double phaseToNeutralVoltagePhase2);

    Double getPhaseToNeutralVoltagePhase3();

    void setPhaseToNeutralVoltagePhase3(
            Double phaseToNeutralVoltagePhase3);

    Double getFrequency();

    void setFrequency(Double frequency);

    Double getTotalActivePower();

    void setTotalActivePower(Double totalActivePower);

    Double getTotalReactivePower();

    void setTotalReactivePower(Double totalReactivePower);

    Double getTotalApparentPower();

    void setTotalApparentPower(Double totalApparentPower);

    Double getActivePowerPhase1();

    void setActivePowerPhase1(Double activePowerPhase1);

    Double getActivePowerPhase2();

    void setActivePowerPhase2(Double activePowerPhase2);

    Double getActivePowerPhase3();

    void setActivePowerPhase3(Double activePowerPhase3);

    Double getReactivePowerPhase1();

    void setReactivePowerPhase1(Double reactivePowerPhase1);

    Double getReactivePowerPhase2();

    void setReactivePowerPhase2(Double reactivePowerPhase2);

    Double getReactivePowerPhase3();

    void setReactivePowerPhase3(Double reactivePowerPhase3);

    Double getApparentPowerPhase1();

    void setApparentPowerPhase1(Double apparentPowerPhase1);

    Double getApparentPowerPhase2();

    void setApparentPowerPhase2(Double apparentPowerPhase2);

    Double getApparentPowerPhase3();

    void setApparentPowerPhase3(Double apparentPowerPhase3);

    Double getDemandCurrentPhase1();

    void setDemandCurrentPhase1(Double demandCurrentPhase1);

    Double getDemandCurrentPhase2();

    void setDemandCurrentPhase2(Double demandCurrentPhase2);

    Double getDemandCurrentPhase3();

    void setDemandCurrentPhase3(Double demandCurrentPhase3);

    Double getPuissanceApparenteMoyenneTotale();

    void setPuissanceApparenteMoyenneTotale(
            Double puissanceApparenteMoyenneTotale);

    Double getMaximumDemandCurrentPhase1();

    void setMaximumDemandCurrentPhase1(Double maximumDemandCurrentPhase1);

    Double getMaximumDemandCurrentPhase2();

    void setMaximumDemandCurrentPhase2(Double maximumDemandCurrentPhase2);

    Double getMaximumDemandCurrentPhase3();

    void setMaximumDemandCurrentPhase3(Double maximumDemandCurrentPhase3);

    Double getMaximumDemandActivePowerPlus();

    void setMaximumDemandActivePowerPlus(
            Double maximumDemandActivePowerPlus);

    Double getMaximumDemandActivePowerMinus();

    void setMaximumDemandActivePowerMinus(
            Double maximumDemandActivePowerMinus);

    Double getMaximumDemandReactivePowerPlus();

    void setMaximumDemandReactivePowerPlus(
            Double maximumDemandReactivePowerPlus);

    Double getMaximumDemandReactivePowerMinus();

    void setMaximumDemandReactivePowerMinus(
            Double maximumDemandReactivePowerMinus);

    Double getMaximumDemandApparentPower();

    void setMaximumDemandApparentPower(Double maximumDemandApparentPower);

    Double getOperatingTimeCounter();

    void setOperatingTimeCounter(Double operatingTimeCounter);

    Double getActiveEnergyInPlus();

    void setActiveEnergyInPlus(Double activeEnergyInPlus);

    Double getReactiveEnergyInPlus();

    void setReactiveEnergyInPlus(Double reactiveEnergyInPlus);

    Double getApparentEnergy();

    void setApparentEnergy(Double apparentEnergy);

    Double getActiveEnergyOutMinus();

    void setActiveEnergyOutMinus(Double activeEnergyOutMinus);

    Double getReactiveEnergyOutMinus();

    void setReactiveEnergyOutMinus(Double reactiveEnergyOutMinus);

    Double getInput1PulseCounter();

    void setInput1PulseCounter(Double input1PulseCounter);
}
