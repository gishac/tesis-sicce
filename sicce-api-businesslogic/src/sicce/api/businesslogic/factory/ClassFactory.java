/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.factory;

import sicce.api.info.Alarm;
import sicce.api.info.ExceptionSicce;
import sicce.api.info.Filter;
import sicce.api.info.KwValue;
import sicce.api.info.Location;
import sicce.api.info.LocationType;
import sicce.api.info.Measure;
import sicce.api.info.ModbusRequest;
import sicce.api.info.ModbusResponse;
import sicce.api.info.OptionSicce;
import sicce.api.info.PowerMeter;
import sicce.api.info.Role;
import sicce.api.info.ScheduleDay;
import sicce.api.info.Message;
import sicce.api.info.Tax;
import sicce.api.info.UserPowerMeter;
import sicce.api.info.UserSicce;
import sicce.api.info.Zone;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IExceptionSicce;
import sicce.api.info.interfaces.IFilter;
import sicce.api.info.interfaces.IKwValue;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.ILocationType;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IModbusRequest;
import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IScheduleDay;
import sicce.api.info.interfaces.IMessage;
import sicce.api.info.interfaces.ITax;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.info.interfaces.IZone;

/**
 * Crea las instancias de los objetos de negocio
 * @author gish@c
 */
public class ClassFactory {

    public static IRole getRoleInstance() {
        return new Role();
    }

    public static IOptionSicce getOptionInstance() {
        return new OptionSicce();
    }

    public static IUserSicce getUserInstance() {
        return new UserSicce();
    }

    public static IPowerMeter getPowerMeterInstance() {
        return new PowerMeter();
    }

    public static ILocationType getLocationTypeInstance() {
        return new LocationType();
    }

    public static ILocation getLocationInstance() {
        return new Location();
    }

    public static IZone getZoneInstance() {
        return new Zone();
    }

    public static IAlarm getAlarmInstance() {
        return new Alarm();
    }

    public static IScheduleDay getScheduleInstance() {
        return new ScheduleDay();
    }

    public static IModbusRequest getModbusRequest() {
        return new ModbusRequest();
    }

    public static IModbusResponse getModbusResponse() {
        return new ModbusResponse();
    }

    public static IMeasure getMeasure() {
        return new Measure();
    }

    public static IFilter getFilterInstance() {
        return new Filter();
    }
    
    public static IUserPowerMeter getUserPowerMeterInstance(){
        return new UserPowerMeter();
    }
    
    public static IExceptionSicce getExceptionSicceInstance(){
        return new ExceptionSicce();
    }
        
    public static IMessage getMessage(){
        return new Message();
    }
    
    public static ITax getTaxInstance(){
        return new Tax();
    }
    
    public static IKwValue getkwValueInstance(){
        return new KwValue();
    }
}
