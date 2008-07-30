/*
 * SicceapiprocessorviewerView.java
 */
package sicce.api.processor.viewer;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.processor.Processor;
import sicce.api.processor.viewer.controls.ChartPane;
import sicce.api.processor.viewer.controls.ErrorsPane;
import sicce.api.processor.viewer.controls.LogPane;
import sicce.api.processor.viewer.controls.MeasuresPane;

/**
 * The application's main frame.
 */
public class SicceapiprocessorviewerView extends FrameView {

    private IUserSicce currentUser;

    public void setCurrentUser(IUserSicce currentUser) {
        this.currentUser = currentUser;
    }
    
    public SicceapiprocessorviewerView(SingleFrameApplication app) {
        super(app);
    }
    
    public void Init(){
        initComponents();        
        OrganizeUIElements();
        SetTrayIcon();
        BuildPanes();
        RunProcessor();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = SicceapiprocessorviewerApp.getApplication().getMainFrame();
            aboutBox = new SicceapiprocessorviewerAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        SicceapiprocessorviewerApp.getApplication().show(aboutBox);
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
    private JDialog aboutBox;
    private JTabbedPane tabManager;
    private ChartPane chartPane;
    private LogPane logPane;
    private MeasuresPane measuresPane;
    private ErrorsPane errorsPane;
    private TrayIcon trayIcon;

  
    /**
     * gish@c
     * Retorna la instancia del control que administra los tabs
     */
    public JTabbedPane getTabManager() {
        if (tabManager == null) {
            tabManager = new JTabbedPane();
        }
        return tabManager;
    }

    /**
     * 
     */
    private void OrganizeUIElements() {
        this.getFrame().getContentPane().add(getTabManager(), BorderLayout.CENTER);
    }

    /**
     * 
     */
    private void BuildPanes() {
        chartPane = new ChartPane(getResourceMap().getString("ChartTitle"), getResourceMap());
        logPane = new LogPane(getResourceMap());
        measuresPane = new MeasuresPane();
        errorsPane = new ErrorsPane(trayIcon);
        getTabManager().addTab("Gráficos del Consumo Eléctrico", chartPane);
        getTabManager().addTab("Log de Lecturas del Consumo Eléctrico", logPane);
        getTabManager().addTab("Detalle de Registros Por Medidor", measuresPane);
        getTabManager().addTab("Detalle de Errores", errorsPane);
    }


    /**
     * 
     */
    private void RunProcessor() {
      Processor.DoProcess(currentUser.getPowerMeters());
    }
    
    /**
     * 
     */
    private void SetTrayIcon(){
        if(SystemTray.isSupported()){
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
