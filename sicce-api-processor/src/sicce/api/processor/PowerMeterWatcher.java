/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
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
 * Representacion de un observador de medidor
 * @author gish@c
 */
public class PowerMeterWatcher extends Observable implements IPowerMeterWatcher {

    /**
     * Medidor a ser observado
     * @see IPowerMeter
     */
    private IPowerMeter powerMeter;
    /**
     * Gestor de logica para el medidor
     * @see PowerMeterBizObject
     */
    private PowerMeterBizObject powerMeterBizObject;

    /**
     * Devuelve el medidor a ser observado
     * @return Medidor a ser observado
     * @see IPowerMeter
     */
    public IPowerMeter getPowerMeter() {
        return this.powerMeter;
    }
    /**
     * Indica si el medidor esta bloqueado y no se pueden realizar lecturas sobre el
     */
    private boolean locked;

    /**
     * Indica si el medidor esta bloqueado y no se pueden realizar lecturas sobre el
     * @return
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Establece si se bloquean las lecturas sobre el medidor
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Establece el gestor de logica para el medidor
     * @return Gestor de logica para el medidor
     * @see PowerMeterBizObject
     */
    public PowerMeterBizObject getpowerMeterBizObject() {
        return powerMeterBizObject;
    }

    /**
     * Constructor
     * @param powerMeter Medidor a ser observado
     * @see IPowerMeter
     */
    public PowerMeterWatcher(IPowerMeter powerMeter) {
        this.powerMeter = powerMeter;
        this.powerMeterBizObject = new PowerMeterBizObject();
    }

    /**
     * Realiza el proceso de monitoreo sobre el medidor
     */
    public void Watch() {
        try {

            if (isLocked()) {
                System.out.println("Medidor: " + this.getPowerMeter().getDescription() + " Bloqueado");
                return;
            }
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
     * Agrega un observador para monitorear las actividades del medidor
     * @param observer Observador para monitorear las actividades del medidor
     */
    public void AddObserver(Observer observer) {
        addObserver(observer);
    }

    /**
     * Notifica a los observadores cuando ocurre una excepcion en el proceso
     * @param ex Excepcion registrada
     */
    public void NotifyException(Exception ex) {
        setChanged();
        notifyObservers(ex);
    }
}
