/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

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
import sicce.api.util.EncryptionProvider;

/**
 * Clase que administra el proceso de lecturas
 * @author gish@c
 */
public class Processor {

    /**
     * Observadores registrados
     */
    private static List<Observer> observers;

    /**
     * Devuelve los observadores registrados
     * @return
     */
    public static List<Observer> getObservers() {
        if (observers == null) {
            observers = new ArrayList<Observer>();
        }
        return observers;
    }

    /**
     * Registra un observador para los monitorear las lecturas de los medidores
     * @param observer Observador para los monitorear las lecturas de los medidores
     */
    public static void AddObserver(Observer observer) {
        getObservers().add(observer);
    }

    /**
     * Setea los observadores para monitorear las lecturas de los medidores
     * @param watcher Observadores para monitorear las lecturas de los medidores
     */
    private static void SetObservers(IPowerMeterWatcher watcher) {
        for (Observer observer : getObservers()) {
            watcher.AddObserver(observer);
        }
    }
    
    /**
     * Medidores a ser monitoreados
     */
    private static Set<IPowerMeter> powerMeters;

    /**
     * Devuelve los medidores a ser monitoreados
     * @return Medidores a ser monitoreados
     */
    private static Set<IPowerMeter> getPowerMeters() {
        if(powerMeters == null){
            powerMeters = new HashSet<IPowerMeter>();
        }
        return powerMeters;
    }
    
    /**
     * Establece los medidores a ser monitoreados
     * @param powerMetersParam
     */
    private static void setPowerMeters(Set<IPowerMeter> powerMetersParam){
        powerMeters = powerMetersParam;
    }
    
   /**
    * Inicia el proceso de monitoreo de los medidores
    * @param powerMetersParam Medidores a monitorear
    */
    public static void DoProcess(Set<IPowerMeter> powerMetersParam) {
        try {
            Processor.setPowerMeters(powerMetersParam);
            Processor.DoProcess();
        } catch (Exception ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicia el proceso de monitoreo de los medidores
     */
    public static void DoProcess() {
        try {

            if(Processor.getPowerMeters().size() == 0 ){
                System.out.println("No existen medidores disponibles para realizar el proceso");
                return;
            }
            
            EncryptionProvider.RegisterHibernateEncryptor();
            IParameter readInterval = ParameterDB.GetParameterByKey(ConstantsProvider.READ_INTERVAL);
            //PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
            //List<IPowerMeter> powerMeters = powerMeterHandler.GetActivePowerMeter();
            ArrayList<IPowerMeterWatcher> watchers = new ArrayList<IPowerMeterWatcher>();
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
}
