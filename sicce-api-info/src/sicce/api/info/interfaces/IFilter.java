/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import sicce.api.info.Field;

/**
 *
 * @author gish@c
 */
public interface IFilter {

    public Field getField();

    public String getOperator();

    public String getValues();

    public void setField(Field field);

    public void setOperator(String operator);

    public void setValues(String values);
}
