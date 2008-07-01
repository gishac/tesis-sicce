/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import sicce.api.businesslogic.ClassFactory;
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
    /**
     * Devuelve los observadores registrados
     * @return
     */
    public static List<Observer> getObservers()
    {
        if(observers == null){
            observers = new ArrayList<Observer>();
        }
        return observers;
    }
    
    /**
     * Registra un observador para los controladores de medidores
     * @param observer
     */
    public static void AddObserver(Observer observer){
        getObservers().add(observer);
    }
    
    /**
     * Setea los observadores a los controladores de medidor
     * @param watcher
     */
    private static void SetObservers(IPowerMeterWatcher watcher){
        for(Observer observer : getObservers()){
            watcher.AddObserver(observer);
        }
    }
    
    /**
     * Realiza el proceso de lectura de los medidores
     */
    public static void DoProcess(){
        EncryptionProvider.RegisterHibernateEncryptor();
        int readInterval = 5;
        PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
        List<IPowerMeter> powerMeters = powerMeterHandler.GetAllPowerMeter();
        
        IPowerMeter meter = ClassFactory.getPowerMeterInstance();
        meter.setIpAddress("192.168.8.42");
        meter.setDescription("home test");
        meter.setSerial("01");
        meter.setDeviceID("01");
        
        powerMeters.add(meter);
        
        ArrayList<IPowerMeterWatcher> watchers = new ArrayList<IPowerMeterWatcher>();
        for (IPowerMeter powerMeter : powerMeters) {
            IPowerMeterWatcher watcher = new PowerMeterWatcher(powerMeter);
            SetObservers(watcher);        
            watchers.add(watcher);            
        }
        TimerTaskLauncher taskLauncher = new TimerTaskLauncher(watchers, readInterval);
        taskLauncher.BeginTasks();
    }    
}
