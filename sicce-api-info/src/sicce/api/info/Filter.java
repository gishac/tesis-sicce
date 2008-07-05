/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import sicce.api.info.interfaces.IFilter;

/**
 *
 * @author karu
 */
public class Filter implements IFilter {
    private Field field;
    private String operator;
    private String values;

    public Filter() {
    }

    public  Field getField() {
        return field;
    }

    public  String getOperator() {
        return operator;
    }

    public  String getValues() {
        return values;
    }

    public  void setField(Field field) {
        this.field = field;
    }

    public  void setOperator(String operator) {
        this.operator = operator;
    }

    public void setValues(String values) {
        this.values = values;
    }
    
    
    
    
}
