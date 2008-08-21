/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

import java.awt.TrayIcon;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import sicce.api.businesslogic.ParameterBizObject;
import sicce.api.businesslogic.factory.ClassFactory;
import sicce.api.dataaccess.ExceptionSicceDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IExceptionSicce;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.processor.viewer.observers.ExceptionObserver;
import sicce.api.util.MailUtil;

/**
 * Manejador de excepciones
 * @author gish@c
 */
public class ExceptionHandler {

    /**
     * Manejador del Tray del sistema
     */
    private TrayIcon trayIcon;
    
    /**
     * Modelo de lista para mostrar las excepciones
     */
    private DefaultListModel exceptionList;
    
    /**
     * Mapa de objetos con la configuracion para el envio de correos electronicos
     */
    private HashMap<String, IParameter> parametersForMail;

    /**
     * Devuelve el mapa de objetos con la configuracion para el envio de correos electronicos
     * @return
     */
    private HashMap<String, IParameter> getparametersForMail() {
        if (parametersForMail == null) {
            parametersForMail = new HashMap<String, IParameter>();
            ParameterBizObject parameterBizObject = new ParameterBizObject();
            List<IParameter> parameters = parameterBizObject.GetAllParameters();
            for (IParameter parameter : parameters) {
                if (parameterBizObject.getParameterCodesForMail().contains(parameter.getParameterKey())) {
                    parametersForMail.put(parameter.getParameterKey(), parameter);
                }
            }
        }
        return parametersForMail;
    }

    /**
     * Constructor
     * @param trayIcon Manejador del Tray del sistema
     * @param exceptionList Modelo de lista para mostrar las excepciones
     */
    public ExceptionHandler(TrayIcon trayIcon, DefaultListModel exceptionList) {
        this.trayIcon = trayIcon;
        this.exceptionList = exceptionList;
    }

    /**
     * Devuelve el objeto observador para monitorear el proceso de lecturas
     * @return Objeto observador para monitorear el proceso de lecturas
     * @see ExceptionObserver
     */
    public ExceptionObserver getExceptionObserver() {
        return new ExceptionObserver(this);

    }

    /**
     * Maneja la excepcion ocurrida durante las lecturas
     * @param ex Excepcion generada
     * @param powerMeter Medidor que origina la excepcion
     */
    public void HandleException(Exception ex, IPowerMeter powerMeter) {
        String errorOrigin = Calendar.getInstance().getTime() + " - Ocurrio un error en el medidor: " + powerMeter.getDescription() + ". ERROR => " +
                ex.getMessage();
        String errorMessage = "Ha ocurrido un error de procesamiento de datos en el medidor : " +
                powerMeter.getDescription() + " asignado a la dependencia: " + ((ILocation) powerMeter.getLocations().toArray()[0]).getDescription() + ".<br /><br /> <strong>SICCE Monitor <br /> UCSG</strong> ";
        if (this.trayIcon != null) {
            trayIcon.displayMessage("Error procesando lectura",
                    "Ocurrio un error en el medidor: " + powerMeter.getDescription() +
                    "\n" + "Comuníquese con el Administrador del sistema.",
                    TrayIcon.MessageType.ERROR);
        }
        LogException(powerMeter, ex);
        SendMail(powerMeter, errorMessage);
        this.exceptionList.addElement(errorOrigin);
    }

    /**
     * Envia el correo electronico de la excepcion a todos los usuarios que tienen asignado el medidor
     * @param powerMeter Medidor que origina la excepcion
     * @param errorMessage Mensaje de error
     */
    private void SendMail(IPowerMeter powerMeter, String errorMessage) {
        Properties props = new Properties();
        props.put("mail.smtps.auth", getparametersForMail().get(ConstantsProvider.MAIL_USE_SSL).getValue());
        props.put("mail.smtp.host", getparametersForMail().get(ConstantsProvider.SMTP_SERVER).getValue());
        props.put("mail.smtp.port", getparametersForMail().get(ConstantsProvider.SMTP_PORT).getValue());

        Set<IUserSicce> to = new HashSet<IUserSicce>();
        for (IUserPowerMeter userPowerMeter : powerMeter.getUserPowerMeters()) {
            if(userPowerMeter.getUserSicce().getEmail() != null && userPowerMeter.getUserSicce().getEmail().length() > 0)
                to.add(userPowerMeter.getUserSicce());
        }
        MailUtil.SendMail(props, getparametersForMail().get(ConstantsProvider.SMTP_SERVER).getValue(),
                getparametersForMail().get(ConstantsProvider.MAIL_SENDER).getValue(), "SICCE Error " + Calendar.getInstance().getTime().toString(),
                to, errorMessage, getparametersForMail().get(ConstantsProvider.MAIL_SENDER_PASSWORD).getValue());
    }
    
    /**
     * Almacena la informacion de los errores ocurridso en la base de datos
     * @param powerMeter Medidor que origina la excepcion
     * @param ex Excepcion generada
     */
    private void LogException(IPowerMeter powerMeter, Exception ex){
        try {
            IExceptionSicce exception = ClassFactory.getExceptionSicceInstance();
            exception.setDateException(Calendar.getInstance().getTime());
            exception.setIdPowerMeter(powerMeter.getIdPowerMeter());
            exception.setMessage(ex.getMessage());
            StringBuilder stackBuilder = new StringBuilder(60000);
            for(StackTraceElement stackElement : ex.getStackTrace()){
                stackBuilder.append("at " + stackElement.getClassName() + "." + stackElement.getMethodName() + "(" + stackElement.getFileName() + ":" + stackElement.getLineNumber() + ") - ");
            }
            stackBuilder.trimToSize();
            exception.setStackTrace(stackBuilder.toString());
            ExceptionSicceDB.Save(exception);
        } catch (Exception ex1) {
            Logger.getLogger(ExceptionHandler.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
}
