/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.util.ArrayList;
import java.util.List;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.util.EncryptionProvider;

/**
 *
 * @author gish@c
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EncryptionProvider.RegisterHibernateEncryptor();
        int readInterval = 3;
        PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
        List<IPowerMeter> powerMeters = powerMeterHandler.GetAllPowerMeter();
        ArrayList<IPowerMeterWatcher> watchers = new ArrayList<IPowerMeterWatcher>();
        ProcessorObserver observer = new ProcessorObserver();
        for (IPowerMeter powerMeter : powerMeters) {
            IPowerMeterWatcher watcher = new PowerMeterWatcher(powerMeter);
            watcher.AddObserver(observer);
            watchers.add(watcher);            
        }
        TimerTaskLauncher taskLauncher = new TimerTaskLauncher(watchers, readInterval);
        taskLauncher.BeginTasks();
    }
}
