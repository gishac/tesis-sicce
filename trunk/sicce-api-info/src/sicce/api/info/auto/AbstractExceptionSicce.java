package sicce.api.info.auto;
// default package
import java.util.Date;
import sicce.api.info.interfaces.IExceptionSicce;

/**
 * Representacion de las excepciones ocurridas en el sistema
 * @author gish@c
 */
public abstract class AbstractExceptionSicce implements java.io.Serializable, IExceptionSicce {


    // Fields    
    /**
     * Identificador de la excepcion
     */
    private Integer idException;
    
    /**
     * Codigo del medidor donde ocurrio la excepcion
     */
    private Integer idPowerMeter;
    
    /**
     * Fecha cuando ocurre la excepcion
     */
    private Date dateException;
    
    /**
     * Volcado de pila de la excepcion
     */
    private String stackTrace;
    
    /**
     * Mensaje de la excepcion
     */
    private String message;


    // Constructors
    
    /**
     * Constructor
     */
    public AbstractExceptionSicce() {
    }

    // Property accessors
    public Integer getIdException() {
        return this.idException;
    }

    public void setIdException(Integer idException) {
        this.idException = idException;
    }

    public Integer getIdPowerMeter() {
        return this.idPowerMeter;
    }

    public void setIdPowerMeter(Integer idPowerMeter) {
        this.idPowerMeter = idPowerMeter;
    }

    public Date getDateException() {
        return this.dateException;
    }

    public void setDateException(Date dateException) {
        this.dateException = dateException;
    }

    public String getStackTrace() {
        return this.stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
