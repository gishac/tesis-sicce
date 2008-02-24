/*
 * SicceuimanagerApp.java
 */

package sicce.ui.manager.forms;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import sicce.api.util.EncryptionProvider;

/**
 * The main class of the application.
 */
public class SicceuimanagerApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new SicceuimanagerView(this));
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
     * @return the instance of SicceuimanagerApp
     */
    public static SicceuimanagerApp getApplication() {
        return Application.getInstance(SicceuimanagerApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        EncryptionProvider.RegisterHibernateEncryptor();
        launch(SicceuimanagerApp.class, args);
    }
}
