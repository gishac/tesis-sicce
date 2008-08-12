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
import javax.swing.DefaultListModel;
import sicce.api.businesslogic.ParameterBizObject;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.processor.viewer.observers.ExceptionObserver;
import sicce.api.util.MailUtil;

/**
 *
 * @author gish@c
 */
public class ExceptionHandler {

    private TrayIcon trayIcon;
    private DefaultListModel exceptionList;
    private HashMap<String, IParameter> parametersForMail;

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
     * 
     * @param trayIcon
     */
    public ExceptionHandler(TrayIcon trayIcon, DefaultListModel exceptionList) {
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
        SendMail(powerMeter, errorMessage);
        this.exceptionList.addElement(errorOrigin);
    }

    /**
     * 
     * @param powerMeter
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
}
