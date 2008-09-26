/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.server;

import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;
import sicce.api.dataaccess.UserDB;
import sicce.api.info.interfaces.IUserSicce;


/**
 * Clase que verifica los clientes que no han tenido actividad en un periodo de tiempo
 * para que el servidor pueda iniciar las lecturas de los medidores bloqueados
 * @author gish@c
 */
public class ClientVerifierThread extends Thread{

    /**
     * Objeto que realiza el procesamiento de las lecturas
     */
    private ProcessorServer processor;
    
    /**
     * Tiempo maximo de inactividad permtido a los clientes
     */
    private long maxInactivityTime;
    
    /**
     * 
     * @param processor
     */
    public ClientVerifierThread(ProcessorServer processor, long maxInactivityTime){
        this.processor = processor;
        this.maxInactivityTime = maxInactivityTime;
    }
    
    @Override
    public void run() {
        super.run();
        long currentDateInMilliseconds = Calendar.getInstance().getTimeInMillis();
        for(Entry<String,Date> entry : processor.getConnectedUsers().entrySet()){
            long lastClientNotification = entry.getValue().getTime();
            long ellapsedTime = currentDateInMilliseconds - lastClientNotification;
            if((ellapsedTime / 1000) >= maxInactivityTime){
                IUserSicce user = UserDB.FindUserByLogin(entry.getKey());
                if(user != null){
                    System.out.println("Desbloquear Medidores " + entry.getValue().toString());
                    processor.SetLockInPowerMetersFromUser(user, false);
                }
            }
            
        }
    }

}
