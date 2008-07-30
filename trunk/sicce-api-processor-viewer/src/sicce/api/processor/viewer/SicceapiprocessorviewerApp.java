/*
 * SicceapiprocessorviewerApp.java
 */

package sicce.api.processor.viewer;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.util.EncryptionProvider;

/**
 * The main class of the application.
 */
public class SicceapiprocessorviewerApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
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
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of SicceapiprocessorviewerApp
     */
    public static SicceapiprocessorviewerApp getApplication() {
        return Application.getInstance(SicceapiprocessorviewerApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        EncryptionProvider.RegisterHibernateEncryptor();
        launch(SicceapiprocessorviewerApp.class, args);
    }
}
