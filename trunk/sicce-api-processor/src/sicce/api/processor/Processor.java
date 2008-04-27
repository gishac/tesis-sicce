/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IPowerMeterWatcher;
import sicce.api.util.EncryptionProvider;

/**
 *
 * @author gish@c
 */
public class Processor {

    private static List<Observer> observers;
    public static List<Observer> getObservers()
    {
        if(observers == null){
            observers = new ArrayList<Observer>();
        }
        return observers;
    }
    
    /**
     * Realiza el proceso de lectura de los medidores
     */
    public static void DoProcess(){
        EncryptionProvider.RegisterHibernateEncryptor();
        int readInterval = 3;
        PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
        List<IPowerMeter> powerMeters = powerMeterHandler.GetAllPowerMeter();
        ArrayList<IPowerMeterWatcher> watchers = new ArrayList<IPowerMeterWatcher>();
        for (IPowerMeter powerMeter : powerMeters) {
            IPowerMeterWatcher watcher = new PowerMeterWatcher(powerMeter);
            SetObservers(watcher);        
            watchers.add(watcher);            
        }
        TimerTaskLauncher taskLauncher = new TimerTaskLauncher(watchers, readInterval);
        taskLauncher.BeginTasks();
    }
    
    /**
     * 
     * @param observer
     */
    public static void AddObserver(Observer observer){
        getObservers().add(observer);
    }
    
    /**
     * 
     * @param watcher
     */
    private static void SetObservers(IPowerMeterWatcher watcher){
        for(Observer observer : getObservers()){
            watcher.AddObserver(observer);
        }
    }
    
}
