package sicce.api.info.auto;

import sicce.api.info.interfaces.IParameter;

/**
 * Representacion de los parametros del sistema
 * @author gish@c
 */
public abstract class AbstractParameter implements java.io.Serializable, IParameter {

    // Fields
    
    /**
     * Identificador del parametro
     */
    protected Integer idParameter;
    
    /**
     * Descripcion del parametro
     */
    protected String description;
    
    /**
     * Valor del parametro
     */
    protected String value;
    
    /**
     * Codigo del parametro
     */
    private String parameterKey;
    
    /**
     * Indica si el parametro es configurable o no
     */
    protected Byte configurable;

    

    // Constructors
    
    /**
     * Constructor
     */
    public AbstractParameter() {
    }

    // Property accessors
    public Integer getIdParameter() {
        return this.idParameter;
    }

    public void setIdParameter(Integer idParameter) {
        this.idParameter = idParameter;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getParameterKey() {
        return this.parameterKey;
    }
    
    public Byte getConfigurable() {
        return configurable;
    }

    public void setConfigurable(Byte configurable) {
        this.configurable = configurable;
    }
}
