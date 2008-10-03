/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.io.Serializable;
import java.util.Date;
import sicce.api.info.interfaces.IKwValue;

/**
 * Representacion de los valores kw/h en el sistema
 * @author gish@c
 */
public abstract class AbstractKwValue implements Serializable, IKwValue {

    /**
     * Identificador del item kw/h
     */
    private Integer idKwValue;
    
    /**
     * Fecha de inicio en la que esta activo el item kw/h
     */
    private Date startDate;
    
    /**
     * Fecha limite en la que esta activo el item kw/h
     */
    private Date endDate;
    
    /**
     * Valor del item kw/h de 7 a 22 horas
     */
    private Double kwValue1;
    
    /**
     * Valor del item kw/h de 22 a 7 horas
     */
    private Double kwValue2;
    
    public Integer getIdKwValue() {
        return this.idKwValue;
    }

    public void setIdKwValue(Integer idKwValue) {
        this.idKwValue = idKwValue;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getKwValue1() {
        return this.kwValue1;
    }

    public void setKwValue1(Double kwValue1) {
        this.kwValue1 = kwValue1;
    }

    public Double getKwValue2() {
        return this.kwValue2;
    }

    public void setKwValue2(Double kwValue2) {
        this.kwValue2 = kwValue2;
    }
}
