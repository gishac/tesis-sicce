/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 * Clase que administra el proceso de lecturas
 * @author gish@c
 */
public class Processor {

    /**
     * Observadores registrados
     */
    protected List<Observer> observers;
    /**
     * Socket para comunicarse con los clientes
     */
    protected ServerSocket serverSocket = null;
    /**
     * Observadores de los medidores
     */
    protected ArrayList<IPowerMeterWatcher> watchers;
    /**
     * Puerto en el que se inicia el servidor
     */
    protected IParameter serverPort;
    
    /**
     * Medidores a ser monitoreados
     */
    protected Set<IPowerMeter> powerMeters;
   
    
    /**
     * Constructor 
     */
    public Processor(){
        try {
            serverPort = ParameterDB.GetParameterByKey(ConstantsProvider.SERVER_PORT);            
        } catch (Exception ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve los observadores registrados
     * @return
     */
    public List<Observer> getObservers() {
        if (observers == null) {
            observers = new ArrayList<Observer>();
        }
        return observers;
    }

    /**
     * Registra un observador para los monitorear las lecturas de los medidores
     * @param observer Observador para los monitorear las lecturas de los medidores
     */
    public void AddObserver(Observer observer) {
        getObservers().add(observer);
    }

    /**
     * Setea los observadores para monitorear las lecturas de los medidores
     * @param watcher Observadores para monitorear las lecturas de los medidores
     */
    protected void SetObservers(IPowerMeterWatcher watcher) {
        for (Observer observer : getObservers()) {
            watcher.AddObserver(observer);
        }
    }
    

    /**
     * Devuelve los medidores a ser monitoreados
     * @return Medidores a ser monitoreados
     */
    protected Set<IPowerMeter> getPowerMeters() {
        if (powerMeters == null) {
            powerMeters = new HashSet<IPowerMeter>();
        }
        return powerMeters;
    }

    /**
     * Establece los medidores a ser monitoreados
     * @param powerMetersParam
     */
    protected void setPowerMeters(Set<IPowerMeter> powerMetersParam) {
        powerMeters = powerMetersParam;
    }

    

    /**
     * Inicia el proceso de lecturas
     */
    protected void LaunchReads(){
        try {
            if (getPowerMeters().size() == 0) {
                System.out.println("No existen medidores disponibles para realizar el proceso");
                return;
            }

            IParameter readInterval = ParameterDB.GetParameterByKey(ConstantsProvider.READ_INTERVAL);
            watchers = new ArrayList<IPowerMeterWatcher>();
            for (IPowerMeter powerMeter : getPowerMeters()) {
                IPowerMeterWatcher watcher = new PowerMeterWatcher(powerMeter);
                SetObservers(watcher);
                watchers.add(watcher);
            }
            TimerTaskLauncher taskLauncher = new TimerTaskLauncher(watchers, Integer.parseInt(readInterval.getValue()));
            taskLauncher.BeginTasks();
        } catch (Exception ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Inicia el proceso de lecturas
     * @return <strong>True</strong>, si se inicia el proceso de lecturas; <strong>False</strong>, si no inicia el proceso de lecturas
     */
    public boolean Run(){
        return false;
    }
        
}






