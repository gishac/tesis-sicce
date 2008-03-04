package sicce.api.info.auto;

import java.util.Date;

import sicce.api.info.Location;
import sicce.api.info.PowerMeter;
import sicce.api.info.UnitMeasure;

/**
 * AbstractMeasure generated by MyEclipse Persistence Tools
 */

public abstract class AbstractMeasure implements java.io.Serializable {

	// Fields

	protected Integer idMeasure;
	protected PowerMeter powerMeter;
	protected Location location;
	protected UnitMeasure unitMeasure;
	protected Long value;
	protected Date dateMeasure;

	// Constructors

	/** default constructor */
	public AbstractMeasure() {
	}

	/** minimal constructor */
	public AbstractMeasure(Integer idMeasure, PowerMeter powerMeter,
			Location location, UnitMeasure unitMeasure) {
		this.idMeasure = idMeasure;
		this.powerMeter = powerMeter;
		this.location = location;
		this.unitMeasure = unitMeasure;
	}

	/** full constructor */
	public AbstractMeasure(Integer idMeasure, PowerMeter powerMeter,
			Location location, UnitMeasure unitMeasure, Long value,
			Date dateMeasure) {
		this.idMeasure = idMeasure;
		this.powerMeter = powerMeter;
		this.location = location;
		this.unitMeasure = unitMeasure;
		this.value = value;
		this.dateMeasure = dateMeasure;
	}

	// Property accessors

	public Integer getIdMeasure() {
		return this.idMeasure;
	}

	public void setIdMeasure(Integer idMeasure) {
		this.idMeasure = idMeasure;
	}

	public PowerMeter getPowerMeter() {
		return this.powerMeter;
	}

	public void setPowerMeter(PowerMeter powerMeter) {
		this.powerMeter = powerMeter;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public UnitMeasure getUnitMeasure() {
		return this.unitMeasure;
	}

	public void setUnitMeasure(UnitMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	public Long getValue() {
		return this.value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Date getDateMeasure() {
		return this.dateMeasure;
	}

	public void setDateMeasure(Date dateMeasure) {
		this.dateMeasure = dateMeasure;
	}

}