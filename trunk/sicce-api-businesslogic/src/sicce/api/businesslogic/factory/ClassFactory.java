/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.businesslogic.factory;

import sicce.api.info.Alarm;
import sicce.api.info.Filter;
import sicce.api.info.Location;
import sicce.api.info.LocationType;
import sicce.api.info.Measure;
import sicce.api.info.ModbusRequest;
import sicce.api.info.ModbusResponse;
import sicce.api.info.OptionSicce;
import sicce.api.info.PowerMeter;
import sicce.api.info.Report;
import sicce.api.info.Role;
import sicce.api.info.ScheduleDay;
import sicce.api.info.UserSicce;
import sicce.api.info.Zone;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IFilter;
import sicce.api.info.interfaces.ILocation;
import sicce.api.info.interfaces.ILocationType;
import sicce.api.info.interfaces.IMeasure;
import sicce.api.info.interfaces.IModbusRequest;
import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IReport;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IScheduleDay;
import sicce.api.info.interfaces.IUserSicce;
import sicce.api.info.interfaces.IZone;

/**
 *
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
    
    public static IAlarm getAlarmInstance(){
        return new Alarm();
    }
    
    public static IScheduleDay getScheduleInstance(){
        return new ScheduleDay();
    }
    
    public static IModbusRequest getModbusRequest(){
        return new ModbusRequest();
    }
    
    public static IModbusResponse getModbusResponse(){
        return new ModbusResponse();
    }
    
    public static IMeasure getMeasure(){
        return new Measure();
    }
    
    public static IReport getReportInstance(){
        return new Report();
    }
    
    public static IFilter getFilterInstance(){
        return new Filter();
    }
}
