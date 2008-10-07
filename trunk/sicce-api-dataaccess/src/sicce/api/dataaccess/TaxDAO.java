package sicce.api.dataaccess;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import sicce.api.info.interfaces.ITax;

/**
 * Data access object (DAO) for domain model class Tax.
 * 
 * @see sicce.api.info.Tax
 * @author MyEclipse Persistence Tools
 */

public class TaxDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TaxDAO.class);
	// property constants
	public static final String TAX_VALUE = "taxValue";

	protected void initDao() {
		// do nothing
	}

	public void save(ITax transientInstance) {
		log.debug("saving Tax instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
        
        public void update(ITax tax) {
		log.debug("updating Tax instance");
		try {
			getHibernateTemplate().update(tax);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(ITax persistentInstance) {
		log.debug("deleting Tax instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ITax findById(java.lang.Integer id) {
		log.debug("getting Tax instance with id: " + id);
		try {
			ITax instance = (ITax) getHibernateTemplate().get(
					"sicce.api.info.Tax", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ITax instance) {
		log.debug("finding Tax instance by example");
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
		log.debug("finding Tax instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tax as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTaxValue(Object taxValue) {
		return findByProperty(TAX_VALUE, taxValue);
	}
        
        public List findByDates(Date startDate, Date endDate){
            try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String startDateParam = formatter.format(startDate);
                        String endDateParam = formatter.format(endDate);
			String queryString = "from Tax as model where model.startDate"
					+ " <= '" + startDateParam + "' and model.endDate >= '" + endDateParam + "'";
                        
                        return getHibernateTemplate().find(queryString);
                        
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
        }


	public List findAll() {
		log.debug("finding all Tax instances");
		try {
			String queryString = "from Tax";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ITax merge(ITax detachedInstance) {
		log.debug("merging Tax instance");
		try {
			ITax result = (ITax) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ITax instance) {
		log.debug("attaching dirty Tax instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ITax instance) {
		log.debug("attaching clean Tax instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TaxDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TaxDAO) ctx.getBean("TaxDAO");
	}
}