/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import sicce.api.info.ConstantsProvider.MessageType;

/**
 *
 * @author gish@c
 */
public interface IMessage {

     /**
     * Devuelve el mensaje con informacinon adicional enviado por el servidor
     * @return Mensaje con informacion adicional enviado por el servidor
     */
    public String getMessage() ;

    /**
     * Establece el mensaje con informacion adicional enviado por el servidor
     * @param message Mensaje con informacion adicional enviado por el servidor
     */
    public void setMessage(String message);

    /**
     * Indica si el cliente puede iniciar la lectura de los medidores
     * @return <strong>True</strong>, si el cliente puede iniciar las lecturas; <strong>False</strong>, si el cliente no puede iniciar las lecturas
     */
    public boolean canConnect();

    /**
     * Establece si el cliente puede iniciar la lectura de los medidores
     * @param canConnect <strong>True</strong>, si el cliente puede iniciar las lecturas; <strong>False</strong>, si el cliente no puede iniciar las lecturas
     */
    public void setCanConnect(boolean canConnect);
    
     /**
     * Devuelve el identificador del usuario
     * @return Identificador del usuario
     */
    public String getUserID();

    /**
     * Establece el identificador del usuario
     * @param userID Identificador del usuario
     */
    public void setUserID(String userID);
    
    /**
     * Devuelve el tipo de mensaje que se envia entre el cliente y el servidor
     * @return Tipo de mensaje que se envia entre el cliente y el servidor
     * @see MessageType
     */
    public MessageType getMessageType();
    
    /**
     * Establece el tipo de mensaje que se envia entre el cliente y el servidor
     * @param messageType Tipo de mensaje que se envia entre el cliente y el servidor
     * @see MessageType
     */
    public void setMessageType(MessageType messageType);
    
}
