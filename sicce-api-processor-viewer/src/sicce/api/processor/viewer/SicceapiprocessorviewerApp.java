/*
 * SicceapiprocessorviewerApp.java
 */

package sicce.api.processor.viewer;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.util.EncryptionProvider;

/**
 * Clase principal de la aplicacion
 * @author gish@c
 */
public class SicceapiprocessorviewerApp extends SingleFrameApplication {

    /**
     * Crea y muestra el formulario principal de la aplicacion
     */
    @Override protected void startup() {
        
        SicceapiprocessorviewerView sicceMonitor = new SicceapiprocessorviewerView(this);
        LoginForm loginForm = new LoginForm(sicceMonitor.getResourceMap());
        loginForm.setVisible(true);
        if(loginForm.getDialogResult() == DialogResult.Ok){
            sicceMonitor.setCurrentUser(loginForm.getCurrentUser());
            sicceMonitor.Init();
            show(sicceMonitor);
        }
        else{
            getApplication().exit(null);
        }
    }

    /**
     * Inicializa la ventana inyectando los recursos
     * @param root
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * Devuelve la instancia de la aplicacion
     * @return Instancia de la aplicacion
     */
    public static SicceapiprocessorviewerApp getApplication() {
        return Application.getInstance(SicceapiprocessorviewerApp.class);
    }

    /*
     * Metodo principal de inicio de la aplicacion
     * @param args
     */
    public static void main(String[] args) {
        EncryptionProvider.RegisterHibernateEncryptor();
        launch(SicceapiprocessorviewerApp.class, args);
    }
}
