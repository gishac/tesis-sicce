/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info;

import java.io.Serializable;
import sicce.api.info.ConstantsProvider.MessageType;
import sicce.api.info.interfaces.IMessage;

/**
 * Representa los mensajes enviados por el servidor a los clientes
 * @author gish@c
 */
public class Message implements IMessage, Serializable {

    /**
     * Indica si el cliente puede iniciar la lectura de los medidores
     */
    private boolean canConnect;
    
     /**
     * Identificador del usuario
     */
    private String userID;
    
    /**
     * Tipo de mensaje que se envia entre el cliente y el servidor
     */
    private ConstantsProvider.MessageType messageType;
    
    /**
     * Mensaje con informacion adicional enviado por el servidor
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean canConnect() {
        return canConnect;
    }

    public void setCanConnect(boolean canConnect) {
        this.canConnect = canConnect;
    }
    
     public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
     public MessageType getMessageType(){
         return this.messageType;
     }
    
   
    public void setMessageType(MessageType messageType){
        this.messageType = messageType;
    }
    
    /**
     * Constructor
     */
    public Message(){        
    }
    
    /**
     * Constructor
     * @param messageType Tipo de mensaje que se envia entre el cliente y el servidor
     * @param userID Identificador del usuario
     */
    public Message(MessageType messageType, String userID){
       this.messageType = messageType;
       this.userID = userID;
    }
}
