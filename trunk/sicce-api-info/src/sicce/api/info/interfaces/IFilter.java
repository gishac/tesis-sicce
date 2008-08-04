/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import sicce.api.info.Field;

 /**
  * Define los metodos a ser implementados por las clases
  * que representen a los filtros
  * @author gish@c
  */
public interface IFilter {

    /**
     * Devuelve el campo asignado que tiene asignado el filtro
     * @return El campo asignado que tiene asignado el filtro
     * @see Field
     */
    public Field getField();

    /**
     * Devuelve el operador de comparacion aplicado al filtro
     * @return Operador de comparacion aplicado al filtro
     */
    public String getOperator();

    /**
     * Devuelve los valores que se utilizaran en la comparacion para el filtro
     * @return Valores que se utilizaran en la comparacion para el filtro
     */
    public String getValues();

    /**
     * Establece el campo por el que se va a realizar el filtro
     * @param field Campo por el que se va a realizar el filtro
     * @see Field
     */
    public void setField(Field field);

    /**
     * Establece el operador de comparacion para el filtro
     * @param operator Operador de comparacion para el filtro
     */
    public void setOperator(String operator);

    /**
     * Establece los valores de comparacion para el filtro
     * @param values Valores de comparacion para el filtro
     */
    public void setValues(String values);
}
