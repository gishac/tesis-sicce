package sicce.api.info.auto;

import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IUserPowerMeter;
import sicce.api.info.interfaces.IUserSicce;

/**
 * 
 * @author gish@c
 */
public abstract class AbstractUserPowerMeter implements java.io.Serializable, IUserPowerMeter {

	// Fields

	private Integer idUserPowerMeter;
	private IPowerMeter powerMeter;
	private IUserSicce userSicce;
	private Byte assigned;
	private Byte monitor;

	// Constructors

	/**
         * Constructor
         */
	public AbstractUserPowerMeter() {
	}

	// Property accessors

	public Integer getIdUserPowerMeter() {
		return this.idUserPowerMeter;
	}

	public void setIdUserPowerMeter(Integer idUserPowerMeter) {
		this.idUserPowerMeter = idUserPowerMeter;
	}

	public IPowerMeter getPowerMeter() {
		return this.powerMeter;
	}

	public void setPowerMeter(IPowerMeter powerMeter) {
		this.powerMeter = powerMeter;
	}

	public IUserSicce getUserSicce() {
		return this.userSicce;
	}

	public void setUserSicce(IUserSicce userSicce) {
		this.userSicce = userSicce;
	}

	public Byte getAssigned() {
		return this.assigned;
	}

	public void setAssigned(Byte assigned) {
		this.assigned = assigned;
	}

	public Byte getMonitor() {
		return this.monitor;
	}

	public void setMonitor(Byte monitor) {
		this.monitor = monitor;
	}

}