/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que representen a los parametros del sistema
 * @author gish@c
 */
public interface IParameter {
    
    /**
     * Devuelve el identificador del parametro
     * @return Identificador del parametro
     */
    public Integer getIdParameter();
    
    /**
     * Establece el identificador del parametro
     * @param idParameter Identificador del parametro
     */
    public void setIdParameter(Integer idParameter);
    
    /**
     * Establece la descripcion del parametro
     * @param description Descripcion del parametro
     */
    void setDescription(String description);
    
    /**
     * Devuelve la descripcion del parametro
     * @return Descripcion del parametro
     */
    String getDescription();
    
    /**
     * Establece el valor del parametro
     * @param value Valor del parametro
     */
    void setValue(String value);
    
    /**
     * Devuelve el valor del parametro
     * @return Valor del parametro
     */
    String getValue();
    
    /**
     * Establece el codigo del parametro
     * @param parameterKey Codigo del parametro
     */
    void setParameterKey(String parameterKey);
    
    /**
     * Devuelve el codigo del parametro
     * @return Codigo del parametro
     */
    String getParameterKey();
    
    /**
     * Indica si el parametro es configurable
     * @return Si el parametro es configurable
     */
    public Byte getConfigurable();

    /**
     * Establece si el parametro es configurable
     * @param configurable <strong> 1 </strong>, si el parametro es configurable; <strong> 0 </strong>, si el parametro no es configurable
     */
    public void setConfigurable(Byte configurable);
}
