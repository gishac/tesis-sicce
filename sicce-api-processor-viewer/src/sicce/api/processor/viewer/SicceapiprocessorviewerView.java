/*
 * SicceapiprocessorviewerView.java
 */
package sicce.api.processor.viewer;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JTabbedPane;
import sicce.api.businesslogic.AlarmBizObject;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.processor.Processor;
import sicce.api.processor.client.ProcessorClient;
import sicce.api.processor.server.ProcessorServer;
import sicce.api.processor.viewer.controls.ChartPane;
import sicce.api.processor.viewer.controls.ErrorsPane;
import sicce.api.processor.viewer.controls.LogPane;
import sicce.api.processor.viewer.controls.MeasuresPane;

/**
 * Formulario principal del visor
 * @author gish@c
 */
public class SicceapiprocessorviewerView extends FrameView {

    /**
     * Usuario actual de la aplicacion
     */
    private IUserSicce currentUser;
    /**
     * Objeto que realiza el proceso de lecturas de los medidores
     */
    private Processor processor;

    /**
     * Establece el usuario actual de la aplicacion
     * @param currentUser Usuario actual de la aplicacion
     */
    public void setCurrentUser(IUserSicce currentUser) {
        this.currentUser = currentUser;
    }
    /**
     * Medidores asignados al usuario actual de la aplicacion
     */
    private Set<IPowerMeter> powerMetersForCurrentUser;

    /**
     * Devuelve los medidores asignados al usuario actual de la aplicacion
     * @return Medidores asignados al usuario actual de la aplicacion
     */
    private Set<IPowerMeter> getPowerMetersForCurrentUser() {
        if (powerMetersForCurrentUser == null) {
            powerMetersForCurrentUser = new HashSet<IPowerMeter>();
            for (IUserPowerMeter userPowerMeter : currentUser.getUserPowerMeters()) {
                if (userPowerMeter.getPowerMeter().getLocations().size() > 0) {
                    if (userPowerMeter.getMonitor() != null && userPowerMeter.getMonitor().equals(Byte.valueOf("1"))) {
                        powerMetersForCurrentUser.add(userPowerMeter.getPowerMeter());
                    }
                }
            }
        }
        return powerMetersForCurrentUser;
    }

    /**
     * Constructor
     * @param app Punto de entrada de la aplicacion
     */
    public SicceapiprocessorviewerView(SingleFrameApplication app) {
        super(app);
    }

    /**
     * Inicializa todos los componentes del formulario
     */
    public void Init() {

        if (currentUser.getUsernameSicce().toLowerCase().equals("adminsicce")) {
            processor = new ProcessorServer();
            PowerMeterBizObject powerMeterHandler = new PowerMeterBizObject();
            getPowerMetersForCurrentUser().clear();
            Set<IPowerMeter> validPowerMeters = powerMeterHandler.GetValidPowerMetersForMonitor();
            getPowerMetersForCurrentUser().addAll(validPowerMeters);
        } else {
            processor = new ProcessorClient(currentUser);
        }

        initComponents();
        OrganizeUIElements();
        SetTrayIcon();
        BuildPanes();
        RunProcessor();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.BorderLayout());

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
    /**
     * Control que administra los tabs
     */
    private JTabbedPane tabManager;
    /**
     * Panel para mostrar el grafico
     */
    private ChartPane chartPane;
    /**
     * Panel para mostrar las lecturas en forma de log
     */
    private LogPane logPane;
    /**
     * Panel para mostrar registros especificos del medidor
     */
    private MeasuresPane measuresPane;
    /**
     * Panel para mostrar los errores ocurridos
     */
    private ErrorsPane errorsPane;
    /**
     * Manejador del Tray del sistema
     */
    private TrayIcon trayIcon;

    /**
     * Devuelve el control que administra los tabs
     * @return Control que administra los tabs
     */
    public JTabbedPane getTabManager() {
        if (tabManager == null) {
            tabManager = new JTabbedPane();
        }
        return tabManager;
    }

    /**
     * Establece la ubicacion de los objetos en el panel
     */
    private void OrganizeUIElements() {
        this.getFrame().getContentPane().add(getTabManager(), BorderLayout.CENTER);
    }

    /**
     * Crea los paneles que van a ser mostrados en el visor
     */
    private void BuildPanes() {
        chartPane = new ChartPane(getResourceMap().getString("ChartTitle"), getResourceMap(), getPowerMetersForCurrentUser(), processor);
        logPane = new LogPane(getResourceMap(), getPowerMetersForCurrentUser(), processor);
        measuresPane = new MeasuresPane(getPowerMetersForCurrentUser(), processor);
        errorsPane = new ErrorsPane(trayIcon, processor);
        getTabManager().addTab("Gráficos del Consumo Eléctrico", chartPane);
        getTabManager().addTab("Log de Lecturas del Consumo Eléctrico", logPane);
        getTabManager().addTab("Detalle de Registros Por Medidor", measuresPane);
        getTabManager().addTab("Detalle de Errores", errorsPane);
    }

    /**
     * Inicia el proceso de lecturas de los medidores
     */
    private void RunProcessor() {

        AlarmBizObject alarmBizObject = new AlarmBizObject();
        for (IPowerMeter powerMeter : getPowerMetersForCurrentUser()) {
            for (IAlarm alarm : powerMeter.getAlarms()) {
                alarm.RegisterAlarmListener(alarmBizObject);
                processor.AddObserver((Observer) alarm);
            }
        }

        if (processor instanceof ProcessorClient) {
            ((ProcessorClient) processor).Run(powerMetersForCurrentUser);
        } else {
            /*List<IAlarm> allAlarms = alarmBizObject.GetAllAlarms();
            for(IAlarm alarm : allAlarms){
                processor.AddObserver((Observer) alarm);
            }*/
            processor.RunProcessor();
        }

    }

    /**
     * Establece el icono en el Tray del sistema
     */
    private void SetTrayIcon() {
        if (SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                trayIcon = new TrayIcon(getResourceMap().getImageIcon("TrayIcon").getImage(), getResourceMap().getString("ApplicationName"));
                trayIcon.setImageAutoSize(true);
                tray.add(trayIcon);
            } catch (AWTException ex) {
                Logger.getLogger(SicceapiprocessorviewerView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
