/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

/**
 * Clase que representa un objeto a ser mostrado en un Combo
 * @author gish@c
 */
public class ComboBoxItem <T> {

    /**
     * Valor a ser mostrado en el combo
     */
    private T value;
    
    /**
     * Codigo del objeto
     */
    private String id;

    /**
     * Devuelve el valor mostrado en el combo
     * @return Valor mostrado en el combo
     */
    public T getValue() {
        return value;
    }

    /**
     * Establece el valor a ser mostrado en el combo
     * @param value Valor a ser mostrado en el combo
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Devuelve el codigo del objeto
     * @return Codigo del objeto
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el codigo del objeto
     * @param id Codigo del objeto
     */
    public void setId(String id) {
        this.id = id;
    }
    
}
