/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.util;

import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class MailUtil {

    /**
     * 
     * @param properties
     * @param smtpServer
     * @param from
     * @param subject
     * @param to
     * @param body
     * @param fromPassword
     */
    public static void SendMail(Properties properties, String smtpServer, String from, 
            String subject, Set<IUserSicce> to, String body, String fromPassword){
                Session session = Session.getInstance(properties, null);
        try {
            Message mailMessage = new MimeMessage(session);
            mailMessage.setFrom(new InternetAddress(from));
            InternetAddress[] addresses = new InternetAddress[to.size()];
            for (int index = 0; index <= to.size() - 1; index++) {
                addresses[index] = new InternetAddress(((IUserSicce) to.toArray()[index]).getEmail());
            }
            mailMessage.setRecipients(Message.RecipientType.TO, addresses);
            mailMessage.setSubject(subject);
            mailMessage.setSentDate(new Date());
            mailMessage.setDataHandler(new DataHandler(new ByteArrayDataSource(body, "text/html")));
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
            transport.connect(smtpServer, from, fromPassword);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

        } catch (IOException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
}
