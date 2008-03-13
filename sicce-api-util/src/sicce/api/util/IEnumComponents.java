
package sicce.api.util;

/**
 * Define la Interface para Manejar la Clases Enum que definen campos.
 *
 */
public interface IEnumComponents {
    /**
     * Define si el campo es Requerido.
     * @return True, si el campo es requerido.
     */

    public boolean isRequired();
    /**
     * Define si el campo deberia Habilitarse.
     * @return True, si el campo deber�a Habilitarse.
     */

    public boolean isEnabled();
    /**
     * Obtiene el M�nimo de Carateres Requerido para el campo.
     * @return Retorna el M�nimo de Carateres Requerido para el campo.
     */

    public int getMinRequired();
    /**
     * Obtiene el M�ximo de Carateres Requerido para el campo.
     * @return Retorna el M�ximo de Carateres Requerido para el campo.
     */

    public int getMaxRequired();
    /**
     * Indica si el campo es de Tipo String.
     * @return True, si el campo es de Tipo String.
     */

    public boolean isString();
   
    
    /**
     * Indica si el campo es de Tipo Numerico
     * @return True, Indica si el campo es de Tipo Numerico.
     */
    public boolean isNumeric();
    
     /**
     * Indica si el campo es de Tipo Numerico mayor que 0.
     * @return True, Indica si el campo es de Tipo Numerico mayor que 0.
     */
    public boolean isNumericMoreThan0();
  
     /**
     * Obtiene la descripcion del campo
     * @return True, Retorna la descripcion del campo.
     */
    public String getFieldDescription();
}
