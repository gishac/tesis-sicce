package sicce.api.dataaccess;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.UnitMeasure;

/**
 * Data access object (DAO) for domain model class UnitMeasure.
 * 
 * @see sicce.api.info.UnitMeasure
 * @author MyEclipse Persistence Tools
 */

public class UnitMeasureDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UnitMeasureDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(UnitMeasure transientInstance) {
		log.debug("saving UnitMeasure instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UnitMeasure persistentInstance) {
		log.debug("deleting UnitMeasure instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UnitMeasure findById(java.lang.Integer id) {
		log.debug("getting UnitMeasure instance with id: " + id);
		try {
			UnitMeasure instance = (UnitMeasure) getHibernateTemplate().get(
					"sicce.api.info.UnitMeasure", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UnitMeasure instance) {
		log.debug("finding UnitMeasure instance by example");
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
		log.debug("finding UnitMeasure instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UnitMeasure as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all UnitMeasure instances");
		try {
			String queryString = "from UnitMeasure";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UnitMeasure merge(UnitMeasure detachedInstance) {
		log.debug("merging UnitMeasure instance");
		try {
			UnitMeasure result = (UnitMeasure) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UnitMeasure instance) {
		log.debug("attaching dirty UnitMeasure instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UnitMeasure instance) {
		log.debug("attaching clean UnitMeasure instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UnitMeasureDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UnitMeasureDAO) ctx.getBean("UnitMeasureDAO");
	}
}