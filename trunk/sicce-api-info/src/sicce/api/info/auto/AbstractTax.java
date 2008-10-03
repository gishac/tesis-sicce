/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.auto;

import java.io.Serializable;
import java.util.Date;
import sicce.api.info.interfaces.ITax;

/**
 * Representacion de los rubros en el sistema
 * @author gish@c
 */
public abstract class AbstractTax implements Serializable, ITax{

    /**
     * Identificador del rubro
     */
    private Integer idTax;
    
    /**
     * Fecha de inicio en la que esta activo el rubro
     */
    private Date startDate;
    
    /**
     * Fecha limite en la que esta activo el rubro
     */
    private Date endDate;
    
    /**
     * Valor del rubro
     */
    private Double taxValue;
    
    /**
     * Descripcion del rubro
     */
    private String description;

    /**
     * Constructor
     */
    public AbstractTax() {
    }

    public Integer getIdTax() {
        return this.idTax;
    }

    public void setIdTax(Integer idTax) {
        this.idTax = idTax;
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

    public Double getTaxValue() {
        return this.taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
