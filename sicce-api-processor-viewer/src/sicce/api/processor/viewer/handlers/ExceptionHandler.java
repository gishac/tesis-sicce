/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.processor.viewer.handlers;

import java.awt.TrayIcon;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.processor.viewer.observers.ExceptionObserver;

/**
 *
 * @author gish@c
 */
public class ExceptionHandler {

    private TrayIcon trayIcon;
    private DefaultListModel exceptionList;
    
    /**
     * 
     * @param trayIcon
     */
    public ExceptionHandler(TrayIcon trayIcon, DefaultListModel exceptionList){
        this.trayIcon = trayIcon;
        this.exceptionList = exceptionList;
    }
    
    /**
     * 
     * @param trayIcon
     * @return
     */
    public ExceptionObserver getExceptionObserver() {
        return new ExceptionObserver(this);
        
    }
    
    /**
     * 
     * @param ex
     */
    public void HandleException(Exception ex, IPowerMeter powerMeter){
         String error = Calendar.getInstance().getTime() + " - Ocurrio un error en el medidor: " + powerMeter.getDescription() + ". ERROR => " +
                ex.getMessage();
        if(this.trayIcon != null){
            trayIcon.displayMessage("Error procesando lectura", 
           "Ocurrio un error en el medidor: " + powerMeter.getDescription() + 
                    ". \n Comuníquese con el Administrador del sistema." ,
           TrayIcon.MessageType.ERROR);
        }
        this.exceptionList.addElement(error);
    }
}
