/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 *
 * @author gish@c
 */
public class PowerMeterWatcher extends Observable implements IPowerMeterWatcher {

    /**
     * Medidor a ser observado
     */
    private IPowerMeter powerMeter;
    
    public IPowerMeter getPowerMeter(){
        return this.powerMeter;
    }
    
    /**
     * Gestor de logica para el medidor
     */
    private PowerMeterBizObject powerMeterBizObject;
    
    public PowerMeterBizObject getpowerMeterBizObject(){
        return powerMeterBizObject;
    }
    
    
    /**
     * 
     * @param powerMeter
     */    
    public PowerMeterWatcher(IPowerMeter powerMeter){
        this.powerMeter = powerMeter;        
        this.powerMeterBizObject = new PowerMeterBizObject();
    }
    
    /**
     * Realiza el proceso de lectura del medidor
     */
    public void Watch()
    {
        //Lee los datos del medidor y los guarda en la base\
        int lectura = new Random().nextInt(10000);
        setChanged();
        notifyObservers(lectura);
    }

    /**
     * 
     * @param observer
     */
    public void AddObserver(Observer observer) {
        addObserver(observer);
    }
    
}
