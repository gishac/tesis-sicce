/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.handlers;

import javax.swing.JOptionPane;
import sicce.ui.manager.controls.JOptionPaneExtended;

/**
 *
 * @author gish@c
 */
public class ExceptionHandler {
    private static String productName;
    private static String errorMessage;
    
    public static void Initialize(String applicationName, String errorText){
        productName = applicationName;
        errorMessage = errorText;
    }
    
    /**
     * Muestra un mensaje y la excepcion ocurrida
     * @param ex
     */
    public static void DisplayException(Exception ex){
        JOptionPaneExtended.showMessageDialog(null, errorMessage + ex.getMessage(),productName,JOptionPane.ERROR_MESSAGE);
    }
}
