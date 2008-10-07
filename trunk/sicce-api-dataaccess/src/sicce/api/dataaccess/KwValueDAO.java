package sicce.api.dataaccess;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import sicce.api.info.interfaces.IKwValue;

/**
 * Data access object (DAO) for domain model class KwValue.
 * 
 * @see sicce.api.info.KwValue
 * @author MyEclipse Persistence Tools
 */

public class KwValueDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(KwValueDAO.class);
	// property constants
	public static final String KW_VALUE1 = "kwValue1";
	public static final String KW_VALUE2 = "kwValue2";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";

	protected void initDao() {
		// do nothing
	}

	public void save(IKwValue transientInstance) {
		log.debug("saving KwValue instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
        
        public void update(IKwValue instance) {
		log.debug("updating KwValue instance");
		try {
			getHibernateTemplate().update(instance);
			log.debug("updating successful");
		} catch (RuntimeException re) {
			log.error("updating failed", re);
			throw re;
		}
	}

	public void delete(IKwValue persistentInstance) {
		log.debug("deleting KwValue instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IKwValue findById(java.lang.Integer id) {
		log.debug("getting KwValue instance with id: " + id);
		try {
			IKwValue instance = (IKwValue) getHibernateTemplate().get(
					"sicce.api.info.KwValue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IKwValue instance) {
		log.debug("finding KwValue instance by example");
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
		log.debug("finding KwValue instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from KwValue as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKwValue1(Object kwValue1) {
		return findByProperty(KW_VALUE1, kwValue1);
	}

	public List findByKwValue2(Object kwValue2) {
		return findByProperty(KW_VALUE2, kwValue2);
	}
        
        public List findByDates(Date startDate, Date endDate){
            try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String startDateParam = formatter.format(startDate);
                        String endDateParam = formatter.format(endDate);
			String queryString = "from KwValue as model where model.startDate"
					+ " <= '" + startDateParam + "' and model.endDate >= '" + endDateParam + "'";
                        
                        return getHibernateTemplate().find(queryString);
                        
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
        }

	public List findAll() {
		log.debug("finding all KwValue instances");
		try {
			String queryString = "from KwValue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static KwValueDAO getFromApplicationContext(ApplicationContext ctx) {
		return (KwValueDAO) ctx.getBean("KwValueDAO");
	}
}