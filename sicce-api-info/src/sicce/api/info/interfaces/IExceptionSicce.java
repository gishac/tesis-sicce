/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.Date;

/**
 *
 * @author gish@c
 */
public interface IExceptionSicce {

    public Integer getIdException();
    
    public void setIdException(Integer idException);

    public Integer getIdPowerMeter();
    
    public void setIdPowerMeter(Integer idPowerMeter);

    public Date getDateException();
    
    public void setDateException(Date dateException);
    
    public String getStackTrace();
    
    public void setStackTrace(String stackTrace);

    public String getMessage();
    
    public void setMessage(String message);
    
}
