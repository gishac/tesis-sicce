/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.dataaccess.ParameterDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IParameter;
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
    public static List<Observer> getObservers() {
        if (observers == null) {
            observers = new ArrayList<Observer>();
        }
        return observers;
    }

    /**
     * Registra un observador para los controladores de medidores
     * @param observer
     */
    public static void AddObserver(Observer observer) {
        getObservers().add(observer);
    }

    /**
     * Setea los observadores a los controladores de medidor
     * @param watcher
     */
    private static void SetObservers(IPowerMeterWatcher watcher) {
        for (Observer observer : getObservers()) {
            watcher.AddObserver(observer);
        }
    }

    /**
     * Realiza el proceso de lectura de los medidores
     */
    public static void DoProcess() {
        try {

            EncryptionProvider.RegisterHibernateEncryptor();
            IParameter readInterval = ParameterDB.GetParameterByKey(ConstantsProvider.READ_INTERVAL);
            PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
            List<IPowerMeter> powerMeters = powerMeterHandler.GetActivePowerMeter();
            ArrayList<IPowerMeterWatcher> watchers = new ArrayList<IPowerMeterWatcher>();
            for (IPowerMeter powerMeter : powerMeters) {
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
