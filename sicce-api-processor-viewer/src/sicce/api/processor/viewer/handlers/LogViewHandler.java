/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import sicce.api.businesslogic.MeasureBizObject;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.processor.viewer.observers.LogObserver;

/**
 * Manejador de datos para el panel de lecturas en formato texto
 * @author gish@c
 */
public class LogViewHandler {

    /**
     * Modelo de lista para mostrar las lecturas
     */
    private DefaultListModel logListModel;
    
    /**
     * Medidores que no van a ser tomados en cuenta para mostrar las lecturas
     */
    private List<String> nonAllowedPowerMeters;
    
    /**
     * Codigo del registro del medidor que se esta visualizando en el grafico
     */
    private int currentRegisterID;
    
    /**
     * Descripcion del registro del medidor que esta visualizando en el grafico
     */
    private String currentRegisterName;

    /**
     * Devuelve los medidores que no van a ser tomados en cuenta para mostrar las lecturas
     * @return
     */
    private List<String> getNonAllowedPowerMeters() {
        if (nonAllowedPowerMeters == null) {
            nonAllowedPowerMeters = new ArrayList<String>();
        }
        return nonAllowedPowerMeters;
    }

    /**
     * Constructor
     * @param logListModel Modelo de lista para mostrar las lecturas
     */
    public LogViewHandler(DefaultListModel logListModel) {
        this.logListModel = logListModel;
    }

    /**
     * Agrega el valor de la nueva lectura al log
     * @param measure Lectura realizada
     */
    public void ProcessMeasure(IMeasure measure) {
        if (getNonAllowedPowerMeters().contains(measure.getPowerMeter().getSerial())) {
            return;
        }
        String log = "Lectura del medidor " + measure.getPowerMeter().getDescription() + " realizada el " +
                measure.getDateMeasure().toString() + ". " + currentRegisterName + " - Valor: " + GetMeasure(measure);
        this.logListModel.addElement(log);
    }

    /**
     * Procesa la lectura para obtener el valor del registro que se esta monitoreando actualmente
     * @param measure Lectura a procesar
     * @return Valor del registro
     */
    private double GetMeasure(IMeasure measure) {
        return new MeasureBizObject().GetMeasure(measure, currentRegisterID);
    }

    /**
     * Devuelve ol objeto observador para monitorear el proceso de lecturas
     * @return Objeto observador para monitorear el proceso de lecturas
     * @see LogObserver
     */
    public LogObserver getLogObserver() {
        return new LogObserver(this);
    }

    /**
     * Agrega o remueve una seria para ser mostrada en el panel
     * @param powerMeterSerial Serial del medidor
     * @param state <strong>True</strong>, si va a ser agregado; <strong>False</strong>, si va ser removido
     */
    public void HandlePowerMeterVisibility(String powerMeterSerial, boolean state) {
        if (!state) {
            getNonAllowedPowerMeters().add(powerMeterSerial);
        } else {
            getNonAllowedPowerMeters().remove(powerMeterSerial);
        }
    }

    /**
     * Cambia el parametro del medidor
     * @param registerID Codigo del registro a monitorear
     * @param registerName Descripcion del registro a monitorear
     */
    public void HandleMeasureChanged(int registerID, String registerName) {
        this.currentRegisterID = registerID;
        this.currentRegisterName = registerName;
    }
}
