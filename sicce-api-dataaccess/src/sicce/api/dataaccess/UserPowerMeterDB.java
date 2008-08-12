/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.Set;
import sicce.api.info.interfaces.IUserPowerMeter;

/**
 *
 * @author gish@c
 */
public class UserPowerMeterDB {

    /**
     * 
     * @param userPowerMeters
     * @throws java.lang.Exception
     */
    public static void Save(Set<IUserPowerMeter> userPowerMeters) throws Exception {
        try {
            for (IUserPowerMeter userPowerMeter : userPowerMeters) {
                Save(userPowerMeter);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 
     * @param userPoweMeter
     * @throws java.lang.Exception
     */
    public static void Save(IUserPowerMeter userPoweMeter) throws Exception {
        try {
            DataAccessManager.getInstance().getUserPowerMeterDB().save(userPoweMeter);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    /**
     * 
     * @param userPowerMeters
     * @throws java.lang.Exception
     */
    public static void Update(Set<IUserPowerMeter> userPowerMeters) throws Exception{
        try {
            for (IUserPowerMeter userPowerMeter : userPowerMeters) {
                DataAccessManager.getInstance().getUserPowerMeterDB().attachDirty(userPowerMeter);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    /**
     * 
     * @param userPowerMeter
     * @throws java.lang.Exception
     */
    public static void Update(IUserPowerMeter userPowerMeter) throws Exception{
        try{
            DataAccessManager.getInstance().getUserPowerMeterDB().update(userPowerMeter);
        }
        catch(Exception ex){
            throw ex;
        }
    }
}
