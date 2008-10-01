/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Date;

/**
 * Define los metodos a ser implementados por las clases que representen a los rubros en el sistema
 * @author gish@c
 */
public interface ITax {

    /**
     * Devuelve la descripcion del rubro
     * @return Descripcion del rubro
     */
    String getDescription();

    /**
     * Establece la descripcion del rubro
     * @param idTax Descripcion del rubro
     */
    void setDescription(String idTax);
    
    /**
     * Devuelve el identificador del rubro
     * @return Identificador del rubro
     */
    Integer getIdTax();

    /**
     * Establece el identificador del rubro
     * @param idTax Identificador del rubro
     */
    void setIdTax(Integer idTax);

    /**
     * Devuelve la fecha de inicio en la que esta activo el rubro
     * @return Fecha de inicio en la que esta activo el rubro
     */
    Date getStartDate();

    /**
     * Establece la fecha de inicio en la que esta activo el rubro
     * @param startDate Fecha de inicio en la que esta activo el rubro
     */
    void setStartDate(Date startDate);

    /**
     * Devuelve la fecha limite en la que esta activo el rubro
     * @return Fecha limite en la que esta activo el rubro
     */
    Date getEndDate();

    /**
     * Establece la fecha limite en la que esta activo el rubro
     * @param endDate Fecha limite en la que esta activo el rubro
     */
    void setEndDate(Date endDate);

    /**
     * Devuelve el valor del rubro
     * @returnValor del rubro
     */
    Double getTaxValue();

    /**
     * Establece el valor del rubro
     * @param taxValue Valor del rubro
     */
    void setTaxValue(Double taxValue);
}
