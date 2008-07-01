/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.exceptions;

/**
 *
 * @author gish@c
 */
public class InvalidModbusResponseException extends Exception {

    public InvalidModbusResponseException(){
        super("La respuesta enviada por el medidor no tiene el formato correcto");
    }
}
