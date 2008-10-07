/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.server;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.dataaccess.UserDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IMessage;
import sicce.api.info.interfaces.IUserSicce;

/**
 * 
 * @author gish@c
 */
public class ClientListener extends Thread {

    /**
     * Socket de comunicacion con los clientes de la aplicacion
     */
    private Socket socket = null;
    /**
     * Procesador de lecturas
     */
    private ProcessorServer processor;
    
    /**
     * Log de conexiones
     */
    private String logFileName = "Log.txt";
    
    /**
     * Objeto para manejar la logica de los medidores
     */
    PowerMeterBizObject powerMeterBizObject = null;

    public ClientListener(Socket socket, ProcessorServer processor) {
        this.socket = socket;
        this.processor = processor;
    }

    @Override
    public void run() {
        ObjectOutputStream outputStream = null;
        try {
            super.run();
            synchronized(this){
                String log = System.getProperty("user.dir") + File.separator + logFileName;
                File logFile = new File(log);
                FileWriter fileWriter = new FileWriter(logFile,true);
                fileWriter.write("Cliente conectado desde >> " + socket.getInetAddress().getHostAddress() + " " + Calendar.getInstance().getTime().toString() + "\n\r");
                fileWriter.flush();
                fileWriter.close();
            }            
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            boolean logout = false;
            while (!logout) {
                IMessage message = (IMessage) inputStream.readObject();
                if (message != null) {
                    HandleMessage(message, outputStream);
                    if (message.getMessageType() == ConstantsProvider.MessageType.Logout) {
                        logout = true;
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(outputStream != null)
                    outputStream.close();
                if(socket != null)
                    socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Procesa el mensaje recibido desde el cliente
     * @param message Mensaje a procesar
     * @param outputStream Flujo de salida de datos
     */
    private void HandleMessage(IMessage message, ObjectOutputStream outputStream) {
        IUserSicce user = UserDB.FindUserByLogin(message.getUserID());
        switch (message.getMessageType()) {
            case Authentication:
                Authenticate(message, outputStream, user);
                break;
            case Notification:
                NotifyReadFromClient(message, outputStream, user);
                break;
            case Logout:
                Logout(message, outputStream);
                break;
        }
    }

    /**
     * Realiza el proceso de autenticacion de un cliente hacia el server
     * @param message Mensaje a procesar
     * @param outputStream Flujo de salida de datos
     * @param user Usuario que desea autenticarse como cliente
     */
    private void Authenticate(IMessage message, ObjectOutputStream outputStream, IUserSicce user) {

        if (user != null) {
            try {
                boolean readyToConnect = processor.SetLockInPowerMetersFromUser(user, true);
                IMessage serverMessage = ClassFactory.getMessage();
                serverMessage.setCanConnect(readyToConnect);
                outputStream.writeObject(serverMessage);
                outputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Notifica al proceso servidor que se realizo una lectura de datos desde el cliente
     * @param message Mensaje a procesar
     * @param outputStream Flujo de salida de datos
     * @param user Usuario que envia la notificacion de lectura
     */
    private void NotifyReadFromClient(IMessage message, ObjectOutputStream outputStream, IUserSicce user) {
        if (user != null) {
            try {
                processor.UpdateClientNotification(user);
                IMessage serverMessage = ClassFactory.getMessage();
                serverMessage.setMessage("ACK");
                outputStream.writeObject(serverMessage);
                outputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Indica que un cliente termino su proceso de lecturas
     * @param message Mensaje a procesar
     * @param outputStream Flujo de salida de datos
     */
    private void Logout(IMessage message, ObjectOutputStream outputStream) {

    }
}
