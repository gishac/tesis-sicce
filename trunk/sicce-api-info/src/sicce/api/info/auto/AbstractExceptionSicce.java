package sicce.api.info.auto;
// default package

import java.util.Date;
import sicce.api.info.interfaces.IExceptionSicce;


/**
 * 
 * @author gish@c
 */
public abstract class AbstractExceptionSicce  implements java.io.Serializable, IExceptionSicce {


    // Fields    

     private Integer idException;
     private Integer idPowerMeter;
     private Date dateException;
     private String stackTrace;
     private String message;


    // Constructors

    /** default constructor */
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