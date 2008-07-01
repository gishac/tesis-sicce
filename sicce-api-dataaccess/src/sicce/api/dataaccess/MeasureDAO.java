package sicce.api.dataaccess;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.Measure;
import sicce.api.info.interfaces.IMeasure;

/**
 * Data access object (DAO) for domain model class Measure.
 * 
 * @see sicce.api.info.Measure
 * @author MyEclipse Persistence Tools
 */

public class MeasureDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(MeasureDAO.class);
	// property constants
	public static final String INSTANTANEOUS_CURRENT_PHASE1 = "instantaneousCurrentPhase1";
	public static final String INSTANTANEOUS_CURRENT_PHASE2 = "instantaneousCurrentPhase2";
	public static final String INSTANTANEOUS_CURRENT_PHASE3 = "instantaneousCurrentPhase3";
	public static final String NEUTRAL_CURRENT = "neutralCurrent";
	public static final String PHASE_TO_PHASE_VOLTAGE_PHASE1_TO2 = "phaseToPhaseVoltagePhase1To2";
	public static final String PHASE_TO_PHASE_VOLTAGE_PHASE2_TO3 = "phaseToPhaseVoltagePhase2To3";
	public static final String PHASE_TO_PHASE_VOLTAGE_PHASE3_TO1 = "phaseToPhaseVoltagePhase3To1";
	public static final String PHASE_TO_NEUTRAL_VOLTAGE_PHASE1 = "phaseToNeutralVoltagePhase1";
	public static final String PHASE_TO_NEUTRAL_VOLTAGE_PHASE2 = "phaseToNeutralVoltagePhase2";
	public static final String PHASE_TO_NEUTRAL_VOLTAGE_PHASE3 = "phaseToNeutralVoltagePhase3";
	public static final String FREQUENCY = "frequency";
	public static final String TOTAL_ACTIVE_POWER = "totalActivePower";
	public static final String TOTAL_REACTIVE_POWER = "totalReactivePower";
	public static final String TOTAL_APPARENT_POWER = "totalApparentPower";
	public static final String ACTIVE_POWER_PHASE1 = "activePowerPhase1";
	public static final String ACTIVE_POWER_PHASE2 = "activePowerPhase2";
	public static final String ACTIVE_POWER_PHASE3 = "activePowerPhase3";
	public static final String REACTIVE_POWER_PHASE1 = "reactivePowerPhase1";
	public static final String REACTIVE_POWER_PHASE2 = "reactivePowerPhase2";
	public static final String REACTIVE_POWER_PHASE3 = "reactivePowerPhase3";
	public static final String APPARENT_POWER_PHASE1 = "apparentPowerPhase1";
	public static final String APPARENT_POWER_PHASE2 = "apparentPowerPhase2";
	public static final String APPARENT_POWER_PHASE3 = "apparentPowerPhase3";
	public static final String DEMAND_CURRENT_PHASE1 = "demandCurrentPhase1";
	public static final String DEMAND_CURRENT_PHASE2 = "demandCurrentPhase2";
	public static final String DEMAND_CURRENT_PHASE3 = "demandCurrentPhase3";
	public static final String PUISSANCE_APPARENTE_MOYENNE_TOTALE = "puissanceApparenteMoyenneTotale";
	public static final String MAXIMUM_DEMAND_CURRENT_PHASE1 = "maximumDemandCurrentPhase1";
	public static final String MAXIMUM_DEMAND_CURRENT_PHASE2 = "maximumDemandCurrentPhase2";
	public static final String MAXIMUM_DEMAND_CURRENT_PHASE3 = "maximumDemandCurrentPhase3";
	public static final String MAXIMUM_DEMAND_ACTIVE_POWER_PLUS = "maximumDemandActivePowerPlus";
	public static final String MAXIMUM_DEMAND_ACTIVE_POWER_MINUS = "maximumDemandActivePowerMinus";
	public static final String MAXIMUM_DEMAND_REACTIVE_POWER_PLUS = "maximumDemandReactivePowerPlus";
	public static final String MAXIMUM_DEMAND_REACTIVE_POWER_MINUS = "maximumDemandReactivePowerMinus";
	public static final String MAXIMUM_DEMAND_APPARENT_POWER = "maximumDemandApparentPower";
	public static final String OPERATING_TIME_COUNTER = "operatingTimeCounter";
	public static final String ACTIVE_ENERGY_IN_PLUS = "activeEnergyInPlus";
	public static final String REACTIVE_ENERGY_IN_PLUS = "reactiveEnergyInPlus";
	public static final String APPARENT_ENERGY = "apparentEnergy";
	public static final String ACTIVE_ENERGY_OUT_MINUS = "activeEnergyOutMinus";
	public static final String REACTIVE_ENERGY_OUT_MINUS = "reactiveEnergyOutMinus";
	public static final String INPUT1_PULSE_COUNTER = "input1PulseCounter";

	protected void initDao() {
		// do nothing
	}

	public void save(IMeasure transientInstance) {
		log.debug("saving Measure instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IMeasure persistentInstance) {
		log.debug("deleting Measure instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Measure findById(java.lang.Integer id) {
		log.debug("getting Measure instance with id: " + id);
		try {
			Measure instance = (Measure) getHibernateTemplate().get(
					"sicce.api.info.Measure", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Measure instance) {
		log.debug("finding Measure instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Measure instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Measure as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByInstantaneousCurrentPhase1(
			Object instantaneousCurrentPhase1) {
		return findByProperty(INSTANTANEOUS_CURRENT_PHASE1,
				instantaneousCurrentPhase1);
	}

	public List findByInstantaneousCurrentPhase2(
			Object instantaneousCurrentPhase2) {
		return findByProperty(INSTANTANEOUS_CURRENT_PHASE2,
				instantaneousCurrentPhase2);
	}

	public List findByInstantaneousCurrentPhase3(
			Object instantaneousCurrentPhase3) {
		return findByProperty(INSTANTANEOUS_CURRENT_PHASE3,
				instantaneousCurrentPhase3);
	}

	public List findByNeutralCurrent(Object neutralCurrent) {
		return findByProperty(NEUTRAL_CURRENT, neutralCurrent);
	}

	public List findByPhaseToPhaseVoltagePhase1To2(
			Object phaseToPhaseVoltagePhase1To2) {
		return findByProperty(PHASE_TO_PHASE_VOLTAGE_PHASE1_TO2,
				phaseToPhaseVoltagePhase1To2);
	}

	public List findByPhaseToPhaseVoltagePhase2To3(
			Object phaseToPhaseVoltagePhase2To3) {
		return findByProperty(PHASE_TO_PHASE_VOLTAGE_PHASE2_TO3,
				phaseToPhaseVoltagePhase2To3);
	}

	public List findByPhaseToPhaseVoltagePhase3To1(
			Object phaseToPhaseVoltagePhase3To1) {
		return findByProperty(PHASE_TO_PHASE_VOLTAGE_PHASE3_TO1,
				phaseToPhaseVoltagePhase3To1);
	}

	public List findByPhaseToNeutralVoltagePhase1(
			Object phaseToNeutralVoltagePhase1) {
		return findByProperty(PHASE_TO_NEUTRAL_VOLTAGE_PHASE1,
				phaseToNeutralVoltagePhase1);
	}

	public List findByPhaseToNeutralVoltagePhase2(
			Object phaseToNeutralVoltagePhase2) {
		return findByProperty(PHASE_TO_NEUTRAL_VOLTAGE_PHASE2,
				phaseToNeutralVoltagePhase2);
	}

	public List findByPhaseToNeutralVoltagePhase3(
			Object phaseToNeutralVoltagePhase3) {
		return findByProperty(PHASE_TO_NEUTRAL_VOLTAGE_PHASE3,
				phaseToNeutralVoltagePhase3);
	}

	public List findByFrequency(Object frequency) {
		return findByProperty(FREQUENCY, frequency);
	}

	public List findByTotalActivePower(Object totalActivePower) {
		return findByProperty(TOTAL_ACTIVE_POWER, totalActivePower);
	}

	public List findByTotalReactivePower(Object totalReactivePower) {
		return findByProperty(TOTAL_REACTIVE_POWER, totalReactivePower);
	}

	public List findByTotalApparentPower(Object totalApparentPower) {
		return findByProperty(TOTAL_APPARENT_POWER, totalApparentPower);
	}

	public List findByActivePowerPhase1(Object activePowerPhase1) {
		return findByProperty(ACTIVE_POWER_PHASE1, activePowerPhase1);
	}

	public List findByActivePowerPhase2(Object activePowerPhase2) {
		return findByProperty(ACTIVE_POWER_PHASE2, activePowerPhase2);
	}

	public List findByActivePowerPhase3(Object activePowerPhase3) {
		return findByProperty(ACTIVE_POWER_PHASE3, activePowerPhase3);
	}

	public List findByReactivePowerPhase1(Object reactivePowerPhase1) {
		return findByProperty(REACTIVE_POWER_PHASE1, reactivePowerPhase1);
	}

	public List findByReactivePowerPhase2(Object reactivePowerPhase2) {
		return findByProperty(REACTIVE_POWER_PHASE2, reactivePowerPhase2);
	}

	public List findByReactivePowerPhase3(Object reactivePowerPhase3) {
		return findByProperty(REACTIVE_POWER_PHASE3, reactivePowerPhase3);
	}

	public List findByApparentPowerPhase1(Object apparentPowerPhase1) {
		return findByProperty(APPARENT_POWER_PHASE1, apparentPowerPhase1);
	}

	public List findByApparentPowerPhase2(Object apparentPowerPhase2) {
		return findByProperty(APPARENT_POWER_PHASE2, apparentPowerPhase2);
	}

	public List findByApparentPowerPhase3(Object apparentPowerPhase3) {
		return findByProperty(APPARENT_POWER_PHASE3, apparentPowerPhase3);
	}

	public List findByDemandCurrentPhase1(Object demandCurrentPhase1) {
		return findByProperty(DEMAND_CURRENT_PHASE1, demandCurrentPhase1);
	}

	public List findByDemandCurrentPhase2(Object demandCurrentPhase2) {
		return findByProperty(DEMAND_CURRENT_PHASE2, demandCurrentPhase2);
	}

	public List findByDemandCurrentPhase3(Object demandCurrentPhase3) {
		return findByProperty(DEMAND_CURRENT_PHASE3, demandCurrentPhase3);
	}

	public List findByPuissanceApparenteMoyenneTotale(
			Object puissanceApparenteMoyenneTotale) {
		return findByProperty(PUISSANCE_APPARENTE_MOYENNE_TOTALE,
				puissanceApparenteMoyenneTotale);
	}

	public List findByMaximumDemandCurrentPhase1(
			Object maximumDemandCurrentPhase1) {
		return findByProperty(MAXIMUM_DEMAND_CURRENT_PHASE1,
				maximumDemandCurrentPhase1);
	}

	public List findByMaximumDemandCurrentPhase2(
			Object maximumDemandCurrentPhase2) {
		return findByProperty(MAXIMUM_DEMAND_CURRENT_PHASE2,
				maximumDemandCurrentPhase2);
	}

	public List findByMaximumDemandCurrentPhase3(
			Object maximumDemandCurrentPhase3) {
		return findByProperty(MAXIMUM_DEMAND_CURRENT_PHASE3,
				maximumDemandCurrentPhase3);
	}

	public List findByMaximumDemandActivePowerPlus(
			Object maximumDemandActivePowerPlus) {
		return findByProperty(MAXIMUM_DEMAND_ACTIVE_POWER_PLUS,
				maximumDemandActivePowerPlus);
	}

	public List findByMaximumDemandActivePowerMinus(
			Object maximumDemandActivePowerMinus) {
		return findByProperty(MAXIMUM_DEMAND_ACTIVE_POWER_MINUS,
				maximumDemandActivePowerMinus);
	}

	public List findByMaximumDemandReactivePowerPlus(
			Object maximumDemandReactivePowerPlus) {
		return findByProperty(MAXIMUM_DEMAND_REACTIVE_POWER_PLUS,
				maximumDemandReactivePowerPlus);
	}

	public List findByMaximumDemandReactivePowerMinus(
			Object maximumDemandReactivePowerMinus) {
		return findByProperty(MAXIMUM_DEMAND_REACTIVE_POWER_MINUS,
				maximumDemandReactivePowerMinus);
	}

	public List findByMaximumDemandApparentPower(
			Object maximumDemandApparentPower) {
		return findByProperty(MAXIMUM_DEMAND_APPARENT_POWER,
				maximumDemandApparentPower);
	}

	public List findByOperatingTimeCounter(Object operatingTimeCounter) {
		return findByProperty(OPERATING_TIME_COUNTER, operatingTimeCounter);
	}

	public List findByActiveEnergyInPlus(Object activeEnergyInPlus) {
		return findByProperty(ACTIVE_ENERGY_IN_PLUS, activeEnergyInPlus);
	}

	public List findByReactiveEnergyInPlus(Object reactiveEnergyInPlus) {
		return findByProperty(REACTIVE_ENERGY_IN_PLUS, reactiveEnergyInPlus);
	}

	public List findByApparentEnergy(Object apparentEnergy) {
		return findByProperty(APPARENT_ENERGY, apparentEnergy);
	}

	public List findByActiveEnergyOutMinus(Object activeEnergyOutMinus) {
		return findByProperty(ACTIVE_ENERGY_OUT_MINUS, activeEnergyOutMinus);
	}

	public List findByReactiveEnergyOutMinus(Object reactiveEnergyOutMinus) {
		return findByProperty(REACTIVE_ENERGY_OUT_MINUS, reactiveEnergyOutMinus);
	}

	public List findByInput1PulseCounter(Object input1PulseCounter) {
		return findByProperty(INPUT1_PULSE_COUNTER, input1PulseCounter);
	}

	public List findAll() {
		log.debug("finding all Measure instances");
		try {
			String queryString = "from Measure";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IMeasure merge(Measure detachedInstance) {
		log.debug("merging Measure instance");
		try {
			IMeasure result = (IMeasure) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IMeasure instance) {
		log.debug("attaching dirty Measure instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IMeasure instance) {
		log.debug("attaching clean Measure instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MeasureDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MeasureDAO) ctx.getBean("MeasureDAO");
	}
}