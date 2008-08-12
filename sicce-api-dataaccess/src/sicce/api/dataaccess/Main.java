/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.dataaccess;

import sicce.api.info.UserPowerMeter;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.util.EncryptionProvider;

/**
 *
 * @author gish@c
 */
public class Main {
 public static void main(String[] args) {
    
     EncryptionProvider.RegisterHibernateEncryptor();
     
     IUserSicce user = DataAccessManager.getInstance().getUserDB().findById(2);
     
     for(IUserPowerMeter powerMetersForUser : user.getUserPowerMeters()){
         System.out.println(powerMetersForUser.getPowerMeter().getDescription());
     }
     
     /*IPowerMeter powerMeter = DataAccessManager.getInstance().getPowerMeterDB().findById(1);
     
     IUserPowerMeter upm = new UserPowerMeter();
     upm.setUserSicce(user);
     upm.setPowerMeter(powerMeter);
     upm.setMonitor(Byte.valueOf("1"));
     upm.setAssigned(Byte.valueOf("0"));*/
     
     //DataAccessManager.getInstance().getUserPowerMeterDB().save(upm);
     
    }
}
