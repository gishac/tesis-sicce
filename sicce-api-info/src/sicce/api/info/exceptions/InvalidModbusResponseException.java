/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.exceptions;

/**
 * Representacion de una excepcion manejada causada por una trama de datos incorrecta
 * @author gish@c
 */
public class InvalidModbusResponseException extends Exception {

    /**
     * Constructor
     */
    public InvalidModbusResponseException(){
        super("La respuesta enviada por el medidor no tiene el formato correcto");
    }
}
