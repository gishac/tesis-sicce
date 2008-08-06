package sicce.api.info;

import sicce.api.info.auto.AbstractOptionSicce;
import sicce.api.info.interfaces.IOptionSicce;

/**
 * Representacion de las opciones del sistema
 * @author gish@c
 */
public class OptionSicce extends AbstractOptionSicce implements
        java.io.Serializable, Comparable {

    // Constructors
    
    /**
     * Constructor 
     */
    public OptionSicce() {
    }
   
    /**
     * Implementacion de Comparable para poder ordenar objetos de tipo OptionSicce
     * @param option Objeto de comparacion
     * @return Resultado de la comparacion
     */
    public int compareTo(Object option) {
        IOptionSicce optionSicce = (IOptionSicce) option;
        return this.getDescription().compareTo(optionSicce.getDescription());
    }
}
