package sicce.api.info.auto;

import java.util.HashSet;
import java.util.Set;

import sicce.api.info.Measure;

/**
 * AbstractUnitMeasure generated by MyEclipse Persistence Tools
 */

public abstract class AbstractUnitMeasure implements java.io.Serializable {

	// Fields

	protected Integer idUnitMeasure;
	protected String description;
	protected Set<Measure> measures = new HashSet<Measure>(0);

	// Constructors

	/** default constructor */
	public AbstractUnitMeasure() {
	}

	/** minimal constructor */
	public AbstractUnitMeasure(Integer idUnitMeasure) {
		this.idUnitMeasure = idUnitMeasure;
	}

	/** full constructor */
	public AbstractUnitMeasure(Integer idUnitMeasure, String description,
			Set<Measure> measures) {
		this.idUnitMeasure = idUnitMeasure;
		this.description = description;
		this.measures = measures;
	}

	// Property accessors

	public Integer getIdUnitMeasure() {
		return this.idUnitMeasure;
	}

	public void setIdUnitMeasure(Integer idUnitMeasure) {
		this.idUnitMeasure = idUnitMeasure;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Measure> getMeasures() {
		return this.measures;
	}

	public void setMeasures(Set<Measure> measures) {
		this.measures = measures;
	}

}