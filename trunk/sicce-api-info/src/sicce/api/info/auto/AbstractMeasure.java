package sicce.api.info.auto;

import java.util.Date;

import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IPowerMeter;

/**
 * Representacion de las lecturas realizadas en los medidores
 * @author gish@c
 */
public abstract class AbstractMeasure implements java.io.Serializable, IMeasure {

    /**
     *  Codigo de la medicion
     */
    protected Integer idMeasure;
    
    /**
     * Medidor sobre el que se realiza la lectura
     * @see IPowerMeter
     */
    protected IPowerMeter powerMeter;
    
    /**
     * Dependencia que tiene asignado el medidor sobre el que se realizan las lecturas
     */
    protected ILocation location;
    
    /**
     * Fecha en la que se realiza la lectura
     */
    protected Date dateMeasure;
   
    /**
     * Valor del registro PhaseToPhaseVoltagePhase1To2
     */
    protected Double phaseToPhaseVoltagePhase1To2;
    
    /**
     * Valor del registro PhaseToPhaseVoltagePhase2To3
     */ 
    protected Double phaseToPhaseVoltagePhase2To3;
    
    /**
     * Valor del registro PhaseToPhaseVoltagePhase3To1
     */
    protected Double phaseToPhaseVoltagePhase3To1;
    
    /**
     * Valor del registro PhaseToNeutralVoltagePhase1
     */
    protected Double phaseToNeutralVoltagePhase1;
    
    /**
     * Valor del registro PhaseToNeutralVoltagePhase2
     */
    protected Double phaseToNeutralVoltagePhase2;
    
    /**
     * Valor del registro PhaseToNeutralVoltagePhase3
     */
    protected Double phaseToNeutralVoltagePhase3;
    
    /**
     * Valor del registro Frequency
     */
    protected Double frequency;
    
    /**
     * Valor del registro TotalActivePower
     */
    protected Double totalActivePower;
    
    /**
     * Valor del registro TotalReactivePower
     */
    protected Double totalReactivePower;
    
    /**
     * Valor del registro TotalApparentPower
     */
    protected Double totalApparentPower;
    
    /**
     * Valor del registro ActivePowerPhase1
     */
    protected Double activePowerPhase1;
    
    /**
     * Valor del registro ActivePowerPhase2
     */
    protected Double activePowerPhase2;
    
    /**
     * Valor del registro ActivePowerPhase3
     */
    protected Double activePowerPhase3;
    
    /**
     * Valor del registro ReactivePowerPhase1
     */
    protected Double reactivePowerPhase1;
    
    /**
     * Valor del registro ReactivePowerPhase2
     */
    protected Double reactivePowerPhase2;
    
    /**
     * Valor del registro ReactivePowerPhase3
     */
    protected Double reactivePowerPhase3;
    
    /**
     * Valor del registro ApparentPowerPhase1
     */
    protected Double apparentPowerPhase1;
    
    /**
     * Valor del registro ApparentPowerPhase2
     */
    protected Double apparentPowerPhase2;
    
    /**
     * Valor del registro ApparentPowerPhase3
     */
    protected Double apparentPowerPhase3;
    
    /**
     * Valor del registro ActiveEnergyInPlus
     */
    private Double activeEnergyIn;
    
     /**
     * Constructor
     */
    public AbstractMeasure() {
    }

    // Property accessors
    public Integer getIdMeasure() {
        return this.idMeasure;
    }

    public void setIdMeasure(Integer idMeasure) {
        this.idMeasure = idMeasure;
    }

    public IPowerMeter getPowerMeter() {
        return this.powerMeter;
    }

    public void setPowerMeter(IPowerMeter powerMeter) {
        this.powerMeter = powerMeter;
    }

    public ILocation getLocation() {
        return this.location;
    }

    public void setLocation(ILocation location) {
        this.location = location;
    }

    public Date getDateMeasure() {
        return this.dateMeasure;
    }

    public void setDateMeasure(Date dateMeasure) {
        this.dateMeasure = dateMeasure;
    }

    public Double getPhaseToPhaseVoltagePhase1To2() {
        return this.phaseToPhaseVoltagePhase1To2;
    }

    public void setPhaseToPhaseVoltagePhase1To2(
            Double phaseToPhaseVoltagePhase1To2) {
        this.phaseToPhaseVoltagePhase1To2 = phaseToPhaseVoltagePhase1To2;
    }

    public Double getPhaseToPhaseVoltagePhase2To3() {
        return this.phaseToPhaseVoltagePhase2To3;
    }

    public void setPhaseToPhaseVoltagePhase2To3(
            Double phaseToPhaseVoltagePhase2To3) {
        this.phaseToPhaseVoltagePhase2To3 = phaseToPhaseVoltagePhase2To3;
    }

    public Double getPhaseToPhaseVoltagePhase3To1() {
        return this.phaseToPhaseVoltagePhase3To1;
    }

    public void setPhaseToPhaseVoltagePhase3To1(
            Double phaseToPhaseVoltagePhase3To1) {
        this.phaseToPhaseVoltagePhase3To1 = phaseToPhaseVoltagePhase3To1;
    }

    public Double getPhaseToNeutralVoltagePhase1() {
        return this.phaseToNeutralVoltagePhase1;
    }

    public void setPhaseToNeutralVoltagePhase1(
            Double phaseToNeutralVoltagePhase1) {
        this.phaseToNeutralVoltagePhase1 = phaseToNeutralVoltagePhase1;
    }

    public Double getPhaseToNeutralVoltagePhase2() {
        return this.phaseToNeutralVoltagePhase2;
    }

    public void setPhaseToNeutralVoltagePhase2(
            Double phaseToNeutralVoltagePhase2) {
        this.phaseToNeutralVoltagePhase2 = phaseToNeutralVoltagePhase2;
    }

    public Double getPhaseToNeutralVoltagePhase3() {
        return this.phaseToNeutralVoltagePhase3;
    }

    public void setPhaseToNeutralVoltagePhase3(
            Double phaseToNeutralVoltagePhase3) {
        this.phaseToNeutralVoltagePhase3 = phaseToNeutralVoltagePhase3;
    }

    public Double getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public Double getTotalActivePower() {
        return this.totalActivePower;
    }

    public void setTotalActivePower(Double totalActivePower) {
        this.totalActivePower = totalActivePower;
    }

    public Double getTotalReactivePower() {
        return this.totalReactivePower;
    }

    public void setTotalReactivePower(Double totalReactivePower) {
        this.totalReactivePower = totalReactivePower;
    }

    public Double getTotalApparentPower() {
        return this.totalApparentPower;
    }

    public void setTotalApparentPower(Double totalApparentPower) {
        this.totalApparentPower = totalApparentPower;
    }

    public Double getActivePowerPhase1() {
        return this.activePowerPhase1;
    }

    public void setActivePowerPhase1(Double activePowerPhase1) {
        this.activePowerPhase1 = activePowerPhase1;
    }

    public Double getActivePowerPhase2() {
        return this.activePowerPhase2;
    }

    public void setActivePowerPhase2(Double activePowerPhase2) {
        this.activePowerPhase2 = activePowerPhase2;
    }

    public Double getActivePowerPhase3() {
        return this.activePowerPhase3;
    }

    public void setActivePowerPhase3(Double activePowerPhase3) {
        this.activePowerPhase3 = activePowerPhase3;
    }

    public Double getReactivePowerPhase1() {
        return this.reactivePowerPhase1;
    }

    public void setReactivePowerPhase1(Double reactivePowerPhase1) {
        this.reactivePowerPhase1 = reactivePowerPhase1;
    }

    public Double getReactivePowerPhase2() {
        return this.reactivePowerPhase2;
    }

    public void setReactivePowerPhase2(Double reactivePowerPhase2) {
        this.reactivePowerPhase2 = reactivePowerPhase2;
    }

    public Double getReactivePowerPhase3() {
        return this.reactivePowerPhase3;
    }

    public void setReactivePowerPhase3(Double reactivePowerPhase3) {
        this.reactivePowerPhase3 = reactivePowerPhase3;
    }

    public Double getApparentPowerPhase1() {
        return this.apparentPowerPhase1;
    }

    public void setApparentPowerPhase1(Double apparentPowerPhase1) {
        this.apparentPowerPhase1 = apparentPowerPhase1;
    }

    public Double getApparentPowerPhase2() {
        return this.apparentPowerPhase2;
    }

    public void setApparentPowerPhase2(Double apparentPowerPhase2) {
        this.apparentPowerPhase2 = apparentPowerPhase2;
    }

    public Double getApparentPowerPhase3() {
        return this.apparentPowerPhase3;
    }

    public void setApparentPowerPhase3(Double apparentPowerPhase3) {
        this.apparentPowerPhase3 = apparentPowerPhase3;
    }

    public Double getActiveEnergyIn() {
        return activeEnergyIn;
    }

    public void setActiveEnergyIn(Double activeEnergyIn) {
        this.activeEnergyIn = activeEnergyIn;
    }
}
