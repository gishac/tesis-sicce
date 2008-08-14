/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

import java.util.Date;

/**
 * Define los metodos a ser implementados por las clases que representen a las excepciones del sistema
 * @author gish@c
 */
public interface IExceptionSicce {

    /**
     * Devuelve el identificador de la excepcion
     * @return Identificador de la excepcion
     */
    public Integer getIdException();

    /**
     * Establece el identificador de la excepcion
     * @param idException Identificador de la excepcion
     */
    public void setIdException(Integer idException);

    /**
     * Devuelve el codigo del medidor donde ocurrio la excepcion
     * @return Codigo del medidor donde ocurrio la excepcion
     */
    public Integer getIdPowerMeter();

    /**
     * Establece el codigo del medidor donde ocurrio la excepcion
     * @param idPowerMeter Codigo del medidor donde ocurrio la excepcion
     */
    public void setIdPowerMeter(Integer idPowerMeter);

    /**
     * Devuelve la fecha en que ocurrio la excepcion
     * @return Fecha en que ocurrio la excepcion
     */
    public Date getDateException();

    /**
     * Establece la fecha cuando ocurre la excepcion
     * @param dateException Fecha cuando ocurre la excepcion
     */
    public void setDateException(Date dateException);

    /**
     * Devuelve el volcado de pila de la excepcion
     * @return Volcado de pila de la excepcion
     */
    public String getStackTrace();

    /**
     * Establece el volcado de pila de la excepcion
     * @param stackTrace Volcado de pila de la excepcion
     */
    public void setStackTrace(String stackTrace);

    /**
     * Devuelve el mensaje de la excepcion
     * @return Mensaje de la excepcion
     */
    public String getMessage();

    /**
     * Establece el mensaje de la excepcion
     * @param message Mensaje de la excepcion
     */
    public void setMessage(String message);
}
