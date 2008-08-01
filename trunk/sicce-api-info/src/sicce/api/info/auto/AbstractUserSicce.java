package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;
import sicce.api.info.interfaces.IAlarm;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IRole;
import sicce.api.info.interfaces.IUserSicce;

/**
 * AbstractUserSicce generated by MyEclipse Persistence Tools
 */
public abstract class AbstractUserSicce implements IUserSicce, java.io.Serializable {

    // Fields
    protected Integer idUserSicce;
    protected IRole role;
    protected String name;
    protected String lastname;
    protected Integer codeUcsg;
    protected String usernameSicce;
    protected String passwordSicce;
    protected Set<IAlarm> alarms = new HashSet<IAlarm>(0);
    protected Set<IPowerMeter> powerMeters = new HashSet(0);
    protected String email;

    

    // Constructors
    /** default constructor */
    public AbstractUserSicce() {
    }

    /** minimal constructor */
    public AbstractUserSicce(Integer idUserSicce, IRole role) {
        this.idUserSicce = idUserSicce;
        this.role = role;
    }

    /** full constructor */
    public AbstractUserSicce(Integer idUserSicce, IRole role, String name,
            String lastname, Integer codeUcsg, String usernameSicce,
            String passwordSicce) {
        this.idUserSicce = idUserSicce;
        this.role = role;
        this.name = name;
        this.lastname = lastname;
        this.codeUcsg = codeUcsg;
        this.usernameSicce = usernameSicce;
        this.passwordSicce = passwordSicce;
    }

    // Property accessors
    public Integer getIdUserSicce() {
        return this.idUserSicce;
    }

    public void setIdUserSicce(Integer idUserSicce) {
        this.idUserSicce = idUserSicce;
    }
    
    public Integer getID() {
        return getIdUserSicce();
    }

    public IRole getRole() {
        return this.role;
    }

    public void setRole(IRole role) {
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getCodeUcsg() {
        return this.codeUcsg;
    }

    public void setCodeUcsg(Integer codeUcsg) {
        this.codeUcsg = codeUcsg;
    }

    public String getUsernameSicce() {
        return this.usernameSicce;
    }

    public void setUsernameSicce(String usernameSicce) {
        this.usernameSicce = usernameSicce;
    }

    public String getPasswordSicce() {
        return this.passwordSicce;
    }

    public void setPasswordSicce(String passwordSicce) {
        this.passwordSicce = passwordSicce;
    }

    public Set<IAlarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(Set<IAlarm> alarms) {
        this.alarms = alarms;
    }
    
    public Set<IPowerMeter> getPowerMeters() {
        return powerMeters;
    }

    public void setPowerMeters(Set<IPowerMeter> powerMeters) {
        this.powerMeters = powerMeters;
    }
    
    public void addUserPowerMeter(IPowerMeter powerMeter) {
        this.powerMeters.add(powerMeter);
    }

    public void removeUserPowerMeter(IPowerMeter powerMeter) {
        this.powerMeters.remove(powerMeter);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
