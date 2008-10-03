/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Date;

/**
 * Define los metodos a ser implementados por las clases que representen a los costos Kw/h en el sistema
 * @author gish@c
 */
public interface IKwValue {

    /**
     * Devuelve el identificador del item kw/h
     * @return Identificador del item kw/h
     */
    Integer getIdKwValue();

    /**
     * Establece el identificador del item kw/h
     * @param idKwValue Identificador del item kw/h
     */
    void setIdKwValue(Integer idKwValue);

    /**
     * Devuelve la fecha de inicio en la que esta activo el item kw/h
     * @return Fecha de inicio en la que esta activo el item kw/h
     */
    Date getStartDate();

    /**
     * Establece la fecha de inicio en la que esta activo el item kw/h
     * @param startDate Fecha de inicio en la que esta activo el item kw/h
     */
    void setStartDate(Date startDate);

    /**
     * Devuelve la fecha limite en la que esta activo el item kw/h
     * @return Fecha limite en la que esta activo el item kw/h
     */
    Date getEndDate();

    /**
     * Establece la fecha limite en la que esta activo el item kw/h
     * @param endDate Fecha limite en la que esta activo el item kw/h
     */
    void setEndDate(Date endDate);

    /**
     * Devuelve el valor del item kw/h de 7 a 22 horas
     * @return Valor del item kw/h de 7 a 22 horas
     */
    Double getKwValue1();

    /**
     * Establece el valor del item kw/h de 7 a 22 horas
     * @param taxValue Valor del item kw/h de 7 a 22 horas
     */
    void setKwValue1(Double kwValue1);
    
    /**
     * Devuelve el valor del item kw/h de 22 a 7 horas
     * @return Valor del item kw/h de 22 a 7 horas
     */
    Double getKwValue2();

    /**
     * Establece el valor del item kw/h de 22 a 7 horas
     * @param taxValue Valor del item kw/h de 22 a 7 horas
     */
    void setKwValue2(Double kwValue1);
    
}
