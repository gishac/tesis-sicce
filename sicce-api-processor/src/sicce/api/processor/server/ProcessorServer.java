/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.server;

import java.net.ServerSocket;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.processor.*;
import sicce.api.util.EncryptionProvider;

/**
 * Clase que administra el proceso de lecturas en modo servidor
 * @author gish@c
 */
public class ProcessorServer extends Processor {

    /**
     * Usuarios conectados a la aplicacion
     */
    private Map<String, Date> connectedUsers;

    /**
     * Devuelve los usuarios conectados a la aplicacion
     * @return Usuarios conectados a la aplicacion
     */
    public Map<String, Date> getConnectedUsers() {
        if (connectedUsers == null) {
            connectedUsers = new HashMap<String, Date>();

        }
        return connectedUsers;
    }
    
    /**
     * Constructor
     */
    public ProcessorServer(){
        super();
    }

    /**
     * @param args Argumentos
     */
    public static void main(String[] args) {

        EncryptionProvider.RegisterHibernateEncryptor();
        ProcessorServer processor = new ProcessorServer();
        processor.Run();
    }

    /**
     * Bloquea o desbloquea todos los medidores pertenecientes al usuario
     * @param user Usuario de la aplicacion
     * @param lock <strong>True</strong>, si se van a bloquear los medidores; <strong>False</strong>, para desbloquear 
     * los medidores
     * @return <strong>True</strong>, si el proceso se realizo correctamente; <strong>False</strong>, si no se pudo realizar el proceso
     */
    public boolean SetLockInPowerMetersFromUser(IUserSicce user, boolean lock) {
        if (lock) {
            if (IsUserConnected(user)) {
                return false;
            }
            getConnectedUsers().put(user.getUsernameSicce(), Calendar.getInstance().getTime());
        } else {
            getConnectedUsers().remove(user.getUsernameSicce());
        }
        for (IPowerMeterWatcher watcher : watchers) {
            for (IUserPowerMeter userPowerMeter : user.getUserPowerMeters()) {
                if (userPowerMeter.getPowerMeter().getIdPowerMeter().equals(watcher.getPowerMeter().getIdPowerMeter()) &&
                        userPowerMeter.getMonitor().equals(Byte.parseByte("1"))) {
                    watcher.setLocked(lock);
                }
            }
        }
        return true;
    }

    /**
     * Carga todos los medidores configurados para iniciar el proceso de lectura
     */
    private void LoadAvailablePowerMetersForServer() {
        PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
        List<IPowerMeter> activePowerMeters = powerMeterHandler.GetAllPowerMeter();
        Set<IPowerMeter> validPowerMeters = new HashSet<IPowerMeter>();
        for (IPowerMeter powerMeter : activePowerMeters) {
            if (powerMeter.getLocations() != null && powerMeter.getLocations().size() > 0) {
                validPowerMeters.add(powerMeter);
            }
        }
        setPowerMeters(validPowerMeters);
    }

    /**
     * Actualiza la hora en la que el cliente notifico que realizo una lectura
     * @param user Usuario que esta conectado remotamente
     */
    public void UpdateClientNotification(IUserSicce user) {
        if (IsUserConnected(user)) {
            System.out.println("Notificacion de lectura recibida de " + user.getUsernameSicce());
            getConnectedUsers().put(user.getUsernameSicce(), Calendar.getInstance().getTime());
        }
    }

    /**
     * Indica si el usuario ya esta conectado remotamente
     * @param user Usuario que se va a verificar
     * @return <strong>True</strong>, si el usuario esta conectado; <strong>False</strong>, si no esta conectado
     */
    private boolean IsUserConnected(IUserSicce user) {
        return getConnectedUsers().containsKey(user.getUsernameSicce());
    }

    @Override
    public boolean Run() {
        try {
            
            LoadAvailablePowerMetersForServer();
            if(getPowerMeters().size() <= 0)
                return false;
            LaunchReads();
            IParameter readTimeoutInterval = ParameterDB.GetParameterByKey(ConstantsProvider.SERVER_READ_TIMEOUT_INTERVAL);
            IParameter maxInactivityAllowed = ParameterDB.GetParameterByKey(ConstantsProvider.SERVER_MAX_INACTIVITY_ALLOWED);
            TimerTimeoutLauncher timeOutReader = new TimerTimeoutLauncher(this, Integer.parseInt(readTimeoutInterval.getValue()), Long.parseLong(maxInactivityAllowed.getValue()));
            timeOutReader.BeginTasks();
            serverSocket = new ServerSocket(Integer.parseInt(serverPort.getValue()));
            while (true) {
                new ClientListener(serverSocket.accept(), this).start();
            }

        } catch (Exception ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
