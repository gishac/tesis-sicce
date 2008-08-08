package sicce.api.dataaccess;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import sicce.api.info.interfaces.IUserPowerMeter;

/**
 * Data access object (DAO) for domain model class UserPowerMeter.
 * 
 * @see sicce.api.info.UserPowerMeter
 * @author MyEclipse Persistence Tools
 */
public class UserPowerMeterDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(UserPowerMeterDAO.class);
    // property constants
    public static final String ASSIGNED = "assigned";
    public static final String MONITOR = "monitor";

    @Override
    protected void initDao() {
    // do nothing
    }

    public void save(IUserPowerMeter transientInstance) {
        log.debug("saving UserPowerMeter instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(IUserPowerMeter persistentInstance) {
        log.debug("deleting UserPowerMeter instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IUserPowerMeter findById(java.lang.Integer id) {
        log.debug("getting UserPowerMeter instance with id: " + id);
        try {
            IUserPowerMeter instance = (IUserPowerMeter) getHibernateTemplate().get("sicce.api.info.UserPowerMeter", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(IUserPowerMeter instance) {
        log.debug("finding UserPowerMeter instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding UserPowerMeter instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from UserPowerMeter as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByAssigned(Object assigned) {
        return findByProperty(ASSIGNED, assigned);
    }

    public List findByMonitor(Object monitor) {
        return findByProperty(MONITOR, monitor);
    }

    public List findAll() {
        log.debug("finding all UserPowerMeter instances");
        try {
            String queryString = "from UserPowerMeter";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public IUserPowerMeter merge(IUserPowerMeter detachedInstance) {
        log.debug("merging UserPowerMeter instance");
        try {
            IUserPowerMeter result = (IUserPowerMeter) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IUserPowerMeter instance) {
        log.debug("attaching dirty UserPowerMeter instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(IUserPowerMeter instance) {
        log.debug("attaching clean UserPowerMeter instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static UserPowerMeterDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (UserPowerMeterDAO) ctx.getBean("UserPowerMeterDAO");
    }
}
