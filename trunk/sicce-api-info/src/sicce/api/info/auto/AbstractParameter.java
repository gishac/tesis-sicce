package sicce.api.info.auto;

/**
 * AbstractParameter generated by MyEclipse Persistence Tools
 */

public abstract class AbstractParameter implements java.io.Serializable {

	// Fields

	protected Integer idParameter;
	protected String description;
	protected Long value;

	// Constructors

	/** default constructor */
	public AbstractParameter() {
	}

	/** minimal constructor */
	public AbstractParameter(Integer idParameter) {
		this.idParameter = idParameter;
	}

	/** full constructor */
	public AbstractParameter(Integer idParameter, String description, Long value) {
		this.idParameter = idParameter;
		this.description = description;
		this.value = value;
	}

	// Property accessors

	public Integer getIdParameter() {
		return this.idParameter;
	}

	public void setIdParameter(Integer idParameter) {
		this.idParameter = idParameter;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getValue() {
		return this.value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}