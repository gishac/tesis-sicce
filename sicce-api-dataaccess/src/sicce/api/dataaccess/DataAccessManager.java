
package sicce.api.dataaccess;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class DataAccessManager {

    private RoleDAO roleDB;
    private UserSicceDAO userDB;
    private LocationDAO locationDB;
    private LocationTypeDAO locationTypeDB;
    private MeasureDAO measureDB;
    private OptionSicceDAO optionDB;
    private ParameterDAO parameterDB;
    private PowerMeterDAO powerMeterDB;
    private ZoneDAO zoneDB;
    private AlarmDAO alarmDB;
    private ScheduleDayDAO scheduleDayDB;
    private GroupDAO groupDB;
    private static DataAccessManager instance;
    private ConnectDAO connectionDB;
    private UserPowerMeterDAO userPowerMeterDB;
    private ExceptionSicceDAO exceptionDB;
    private TaxDAO taxDB;
    private KwValueDAO kwValueDB;
    
    
    public static void setInstance(DataAccessManager instance) {
        DataAccessManager.instance = instance;
    }

    public AlarmDAO getAlarmDB() {
        return alarmDB;
    }

    public void setAlarmDB(AlarmDAO alarmDB) {
        this.alarmDB = alarmDB;
    }

    public ScheduleDayDAO getScheduleDayDB() {
        return scheduleDayDB;
    }

    public void setScheduleDayDB(ScheduleDayDAO scheduleDayDB) {
        this.scheduleDayDB = scheduleDayDB;
    }

    public RoleDAO getRoleDB() {
        return roleDB;
    }

    public void setRoleDB(RoleDAO roleDB) {
        this.roleDB = roleDB;
    }

    public UserSicceDAO getUserDB() {
        return userDB;
    }

    public void setUserDB(UserSicceDAO userDB) {
        this.userDB = userDB;
    }

    public LocationDAO getLocationDB() {
        return locationDB;
    }

    public void setLocationDB(LocationDAO locationDB) {
        this.locationDB = locationDB;
    }

    public LocationTypeDAO getLocationTypeDB() {
        return locationTypeDB;
    }

    public void setLocationTypeDB(LocationTypeDAO locationTypeDB) {
        this.locationTypeDB = locationTypeDB;
    }

    public MeasureDAO getMeasureDB() {
        return measureDB;
    }

    public void setMeasureDB(MeasureDAO measureDB) {
        this.measureDB = measureDB;
    }

    public OptionSicceDAO getOptionDB() {
        return optionDB;
    }

    public void setOptionDB(OptionSicceDAO optionDB) {
        this.optionDB = optionDB;
    }

    public ParameterDAO getParameterDB() {
        return parameterDB;
    }

    public void setParameterDB(ParameterDAO parameterDB) {
        this.parameterDB = parameterDB;
    }

    public PowerMeterDAO getPowerMeterDB() {
        return powerMeterDB;
    }

    public void setPowerMeterDB(PowerMeterDAO powerMeterDB) {
        this.powerMeterDB = powerMeterDB;
    }

    public ZoneDAO getZoneDB() {
        return zoneDB;
    }

    public void setZoneDB(ZoneDAO zoneDB) {
        this.zoneDB = zoneDB;
    }

    public GroupDAO getGroupDB() {
        return groupDB;
    }

    public void setGroupDB(GroupDAO groupDB) {
        this.groupDB = groupDB;
    }
  
    public ConnectDAO getConnectionDB() {
        return connectionDB;
    }

    public void setConnectionDB(ConnectDAO connectionDB) {
        this.connectionDB = connectionDB;
    }
    
    public UserPowerMeterDAO getUserPowerMeterDB() {
        return userPowerMeterDB;
    }

    public void setUserPowerMeterDB(UserPowerMeterDAO userPowerMeterDB) {
        this.userPowerMeterDB = userPowerMeterDB;
    }
    
    public ExceptionSicceDAO getExceptionDB() {
        return exceptionDB;
    }

    public void setExceptionDB(ExceptionSicceDAO exceptionDB) {
        this.exceptionDB = exceptionDB;
    }
    
    public TaxDAO getTaxDB() {
        return taxDB;
    }

    public void setTaxDB(TaxDAO taxDB) {
        this.taxDB = taxDB;
    }
    
    public KwValueDAO getKwValueDB() {
        return kwValueDB;
    }

    public void setKwValueDB(KwValueDAO kwValueDB) {
        this.kwValueDB = kwValueDB;
    }
    
    public static DataAccessManager getInstance() {
        if (instance == null) {
            BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
            instance = (DataAccessManager) beanFactory.getBean("DataAccessManager");
        }
        return instance;
    }

    public static DataAccessManager getFromApplicationContext(ApplicationContext ctx) {
        return (DataAccessManager) ctx.getBean("DataAccessManager");
    }
}