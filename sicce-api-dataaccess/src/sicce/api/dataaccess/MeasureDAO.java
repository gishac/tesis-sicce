package sicce.api.dataaccess;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.Measure;

/**
 * Data access object (DAO) for domain model class Measure.
 * 
 * @see sicce.api.info.Measure
 * @author MyEclipse Persistence Tools
 */

public class MeasureDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(MeasureDAO.class);
	// property constants
	public static final String VALUE = "value";

	protected void initDao() {
		// do nothing
	}

	public void save(Measure transientInstance) {
		log.debug("saving Measure instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Measure persistentInstance) {
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

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
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

	public Measure merge(Measure detachedInstance) {
		log.debug("merging Measure instance");
		try {
			Measure result = (Measure) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Measure instance) {
		log.debug("attaching dirty Measure instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Measure instance) {
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