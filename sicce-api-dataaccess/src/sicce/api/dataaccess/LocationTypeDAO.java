package sicce.api.dataaccess;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.LocationType;
import sicce.api.info.interfaces.ILocationType;

/**
 * Data access object (DAO) for domain model class LocationType.
 * 
 * @see sicce.api.info.LocationType
 * @author MyEclipse Persistence Tools
 */

public class LocationTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LocationTypeDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(ILocationType transientInstance) {
		log.debug("saving LocationType instance");
		try {
			getHibernateTemplate().save(transientInstance);
                        log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
        
        public void update(ILocationType locationType) {
		log.debug("updating LocationType instance");
		try {
			getHibernateTemplate().update(locationType);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(ILocationType persistentInstance) {
		log.debug("deleting LocationType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LocationType findById(java.lang.Integer id) {
		log.debug("getting LocationType instance with id: " + id);
		try {
			LocationType instance = (LocationType) getHibernateTemplate().get(
					"sicce.api.info.LocationType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LocationType instance) {
		log.debug("finding LocationType instance by example");
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
		log.debug("finding LocationType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LocationType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all LocationType instances");
		try {
			String queryString = "from LocationType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LocationType merge(LocationType detachedInstance) {
		log.debug("merging LocationType instance");
		try {
			LocationType result = (LocationType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LocationType instance) {
		log.debug("attaching dirty LocationType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LocationType instance) {
		log.debug("attaching clean LocationType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LocationTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LocationTypeDAO) ctx.getBean("LocationTypeDAO");
	}
}