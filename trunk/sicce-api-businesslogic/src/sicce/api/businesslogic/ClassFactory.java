/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.businesslogic;

import sicce.api.info.Location;
import sicce.api.info.LocationType;
import sicce.api.info.Measure;
import sicce.api.info.OptionSicce;
import sicce.api.info.PowerMeter;
import sicce.api.info.Role;
import sicce.api.info.UserSicce;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.ILocationType;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IUserSicce;

/**
 *
 * @author gish@c
 */
public class ClassFactory {

    public static IRole getRoleInstance()
    {
        return new Role();
    }
    
    public static IOptionSicce getOptionInstance()
    {
        return new OptionSicce();
    }
    
    public static IUserSicce getUserInstance()
    {
        return new UserSicce();
    }
    
    public static IPowerMeter getPowerMeterInstance()
    {
        return new PowerMeter();
    }
    
    public static ILocationType getLocationTypeInstance()
    {
        return new LocationType();
    }
    
      public static ILocation getLocationInstance()
    {
        return new Location();
    }
      
        public static IMeasure getMeasureInstance()
    {
        return new Measure();
    }
}
