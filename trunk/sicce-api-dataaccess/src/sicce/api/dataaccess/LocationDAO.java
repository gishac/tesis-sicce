package sicce.api.dataaccess;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.Location;
import sicce.api.info.interfaces.ILocation;

/**
 * Data access object (DAO) for domain model class Location.
 * 
 * @see sicce.api.info.Location
 * @author MyEclipse Persistence Tools
 */

public class LocationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LocationDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String LOCATION_1 = "location_1";
	public static final String CODE = "code";

	protected void initDao() {
		// do nothing
	}

	public void save(ILocation transientInstance) {
		log.debug("saving Location instance");
		try {
			
			getHibernateTemplate().save(transientInstance);
			log.debug("save successfull");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
        
        public void update(ILocation location) {
		log.debug("updating Location instance");
		try {
			
			getHibernateTemplate().update(location);
			log.debug("updated successful");
		} catch (RuntimeException re) {
			log.error("updated failed", re);
			throw re;
		}
	}

	public void delete(ILocation persistentInstance) {
		log.debug("deleting Location instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ILocation findById(java.lang.Integer id) {
		log.debug("getting Location instance with id: " + id);
		try {
			ILocation instance = (ILocation) getHibernateTemplate().get(
					"sicce.api.info.Location", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Location instance) {
		log.debug("finding Location instance by example");
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
		log.debug("finding Location instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Location as model where model."
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

	public List findByLocation_1(Object location_1) {
		return findByProperty(LOCATION_1, location_1);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findAll() {
		log.debug("finding all Location instances");
		try {
			String queryString = "from Location";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Location merge(Location detachedInstance) {
		log.debug("merging Location instance");
		try {
			Location result = (Location) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Location instance) {
		log.debug("attaching dirty Location instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Location instance) {
		log.debug("attaching clean Location instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LocationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LocationDAO) ctx.getBean("LocationDAO");
	}

	
}