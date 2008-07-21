/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.dataaccess.MeasureDB;
import sicce.api.info.ConstantsProvider.RequestType;
import sicce.api.info.exceptions.InvalidModbusResponseException;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IModbusResponse;
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

    public IPowerMeter getPowerMeter() {
        return this.powerMeter;
    }
    /**
     * Gestor de logica para el medidor
     */
    private PowerMeterBizObject powerMeterBizObject;

    public PowerMeterBizObject getpowerMeterBizObject() {
        return powerMeterBizObject;
    }

    /**
     * 
     * @param powerMeter
     */
    public PowerMeterWatcher(IPowerMeter powerMeter) {
        this.powerMeter = powerMeter;
        this.powerMeterBizObject = new PowerMeterBizObject();
    }

    /**
     * Realiza el proceso de lectura del medidor
     */
    public void Watch() {
        try {
            
            HashMap<RequestType, IModbusResponse> powerMeterData = powerMeterBizObject.ReadPowerMeterData(this.powerMeter);
            IMeasure measure = powerMeterBizObject.ProcessPowerMeterData(powerMeterData);
            MeasureDB.Save(measure);
            setChanged();
            notifyObservers(measure);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
            NotifyException(ex);
        } catch (IOException ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
            NotifyException(ex);
        } catch (InvalidModbusResponseException ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
            NotifyException(ex);
        } catch (Exception ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
            NotifyException(ex);
        }
    }

    /**
     * 
     * @param observer
     */
    public void AddObserver(Observer observer) {
        addObserver(observer);
    }

    /**
     * 
     * @param ex
     */
    private void NotifyException(Exception ex) {
        setChanged();
        notifyObservers(ex);
    }
}
