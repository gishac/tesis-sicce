/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IMessage;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.processor.Processor;

/**
 * Clase que administra el proceso de lecturas en modo cliente
 * @author gish@c
 */
public class ProcessorClient extends Processor {

    /**
     * Socket para comunicarse con el servidor de lecturas
     */
    private Socket clientSocket;
    /**
     * Flujo de datos para enviar datos al servidor de lecturas
     */
    private ObjectOutputStream clientOutputStream;
    /**
     * Flujo de datos para recibir datos del servidor de lecturas
     */
    private ObjectInputStream clientInputStream;
    /**
     * Usuario cliente
     */
    private IUserSicce currentUser;
    /**
     * Direccion del servidor de lecturas
     */
    private IParameter serverAddress;

    /**
     * Constructor
     * @param clientUser Usuario cliente connectado
     */
    public ProcessorClient(IUserSicce clientUser) {
        super();
        try {
            serverAddress = ParameterDB.GetParameterByKey(ConstantsProvider.SERVER_IP);
            this.currentUser = clientUser;
        } catch (Exception ex) {
            Logger.getLogger(ProcessorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (clientOutputStream != null) {
            clientOutputStream.close();
        }
        if (clientInputStream != null) {
            clientInputStream.close();
        }
        if (clientSocket != null) {
            clientSocket.close();
        }
    }

    /**
     * Inicia el proceso de monitoreo de los medidores en modo cliente
     * @param powerMetersParam Medidores a monitorear
     */
    public void Run(Set<IPowerMeter> powerMetersParam) {
        try {
            setPowerMeters(powerMetersParam);
            RunProcessor();
        } catch (Exception ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean RunProcessor() {
        boolean authenticated = true;
        try {
            clientSocket = new Socket(serverAddress.getValue(), Integer.parseInt(serverPort.getValue()));
            clientSocket.setKeepAlive(true);
            clientOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            clientInputStream = new ObjectInputStream(clientSocket.getInputStream());
            IMessage authenticationMessage = ClassFactory.getMessage();
            authenticationMessage.setUserID(currentUser.getUsernameSicce());
            authenticationMessage.setMessageType(ConstantsProvider.MessageType.Authentication);
            clientOutputStream.writeObject(authenticationMessage);
            clientOutputStream.flush();
            IMessage serverResponse = (IMessage) clientInputStream.readObject();
            if (!serverResponse.canConnect()) {
                authenticated = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProcessorClient.class.getName()).log(Level.SEVERE, null, ex);
            DisplayServerConnectionError();
            authenticated = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessorClient.class.getName()).log(Level.SEVERE, null, ex);
            DisplayServerConnectionError();
            authenticated = false;
        }
        if (authenticated) {
            this.AddObserver(new ReadObserver(this));
            LaunchReads();
        }
        return true;

    }

    /**
     * Muestra un mensaje de error indicando que no se pudo conectar al servidor de lecturas
     */
    private void DisplayServerConnectionError() {
        JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar conectarse al servidor procesador de lecturas en la dirección " + serverAddress.getValue() +
                " en el puerto " + serverPort.getValue() + ". \n" + "Comuníquese con el administrador del sistema.", "SICCE", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Notifica al servidor que se realizo una electura
     */
    public void KeepAliveInServer() {
        try {
            IMessage notificationMessage = ClassFactory.getMessage();
            notificationMessage.setUserID(currentUser.getUsernameSicce());
            notificationMessage.setMessage("Keep alive from " + currentUser.getUsernameSicce());
            notificationMessage.setMessageType(ConstantsProvider.MessageType.Notification);
            if (clientSocket != null) {
                clientOutputStream.writeObject(notificationMessage);
                clientOutputStream.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ProcessorClient.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
