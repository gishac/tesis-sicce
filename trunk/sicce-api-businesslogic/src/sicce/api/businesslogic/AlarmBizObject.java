/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic;

import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import sicce.api.dataaccess.AlarmDB;
import sicce.api.info.ConstantsProvider;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IAlarmListener;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.IParameter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class AlarmBizObject implements IAlarmListener {

    private HashMap<String,IParameter> parametersForMail; 
    private HashMap<String,IParameter> getparametersForMail(){
        if(parametersForMail == null){
            parametersForMail = new HashMap<String, IParameter>();
            ParameterBizObject parameterBizObject = new ParameterBizObject();
            List<IParameter> parameters = parameterBizObject.GetAllParameters();
            for(IParameter parameter : parameters){
                if(parameterBizObject.getParameterCodesForMail().contains(parameter.getParameterKey()))
                    parametersForMail.put(parameter.getParameterKey(), parameter);
            }
        }
        return parametersForMail;
    }
    
    public AlarmBizObject(){
        
    }
    
    /**
     * Indica si la alarma ya tiene asignado el medidor
     * @param powerMeterID
     * @param alarm
     * @return
     */
    public boolean PowerMeterExists(String powerMeterID, IAlarm alarm) {
        for (IPowerMeter powerMeter : alarm.getAlarmPowerMeters()) {
            if (powerMeter.getSerial().equals(powerMeterID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si la alarma ya tiene asignado el usuario
     * @param userID
     * @param alarm
     * @return
     */
    public boolean UserExists(int userID, IAlarm alarm) {
        for (IUserSicce user : alarm.getAlarmUsers()) {
            if (user.getID() == userID) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return
     */
    public List<IAlarm> GetAllAlarms() {
        return AlarmDB.GetAllAlarms();
    }

    /**
     * 
     * @param alarm
     */
    public void actionPerformed(IAlarm alarm, IPowerMeter powerMeter) {
        if(alarm.getAlarmTypeEnum() == ConstantsProvider.AlarmType.Mail)
            SendMail(alarm, powerMeter);        
    }

    public void SendMail(IAlarm alarm, IPowerMeter powerMeter) {
        
        if(alarm.getAlarmUsers().size() <= 0)
            return;
        
        Properties props = new Properties();
        props.put("mail.smtps.auth", getparametersForMail().get(ConstantsProvider.MAIL_USE_SSL).getValue());
        props.put("mail.smtp.host", getparametersForMail().get(ConstantsProvider.SMTP_SERVER).getValue());
        props.put("mail.smtp.port", getparametersForMail().get(ConstantsProvider.SMTP_PORT).getValue());
        Session session = Session.getInstance(props, null);
        try {
            Message mailMessage = new MimeMessage(session);
            mailMessage.setFrom(new InternetAddress(getparametersForMail().get(ConstantsProvider.MAIL_SENDER).getValue()));
            InternetAddress[] addresses = new InternetAddress[alarm.getAlarmUsers().size()];
            for(int index = 0; index <= alarm.getAlarmUsers().size()-1; index++){
                addresses[index] = new InternetAddress(((IUserSicce) alarm.getAlarmUsers().toArray()[index]).getEmail());
            }
            mailMessage.setRecipients(Message.RecipientType.TO, addresses);
            mailMessage.setSubject("SICCE Alerta " + Calendar.getInstance().getTime().toString());
            mailMessage.setSentDate(new Date());
            mailMessage.setDataHandler(new DataHandler(new ByteArrayDataSource("Ha ocurrido un error al tratar de procesar las lecturas del medidor : " + 
                    powerMeter.getDescription() + " asignado a la dependencia: " + ((ILocation) powerMeter.getLocations().toArray()[0]).getDescription() +".<br /><br /> <strong>SICCE Monitor <br /> UCSG</strong> ","text/html")));
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
            transport.connect(getparametersForMail().get(ConstantsProvider.SMTP_SERVER).getValue(), getparametersForMail().get(ConstantsProvider.MAIL_SENDER).getValue(),getparametersForMail().get(ConstantsProvider.MAIL_SENDER_PASSWORD).getValue());
            transport.sendMessage(mailMessage,mailMessage.getAllRecipients());
            
        } catch (IOException ex) {
            Logger.getLogger(AlarmBizObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }

    }
}