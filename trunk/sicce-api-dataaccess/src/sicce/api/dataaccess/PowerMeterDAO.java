package sicce.api.dataaccess;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.PowerMeter;
import sicce.api.info.interfaces.IPowerMeter;

/**
 * Data access object (DAO) for domain model class PowerMeter.
 * 
 * @see sicce.api.info.PowerMeter
 * @author MyEclipse Persistence Tools
 */
public class PowerMeterDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(PowerMeterDAO.class);
    // property constants
    public static final String DESCRIPTION = "description";
    public static final String IP_ADDRESS = "ipAddress";
    public static final String LOG_STATUS = "logStatus";
    public static final String SERIAL = "serial";
    public static final String DEVICE_ID = "deviceID";

    protected void initDao() {
    // do nothing
    }

    public void save(IPowerMeter transientInstance) {
        log.debug("saving PowerMeter instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(IPowerMeter powerMeter) {
        log.debug("saving PowerMeter instance");
        try {
            getHibernateTemplate().update(powerMeter);
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(IPowerMeter persistentInstance) {
        log.debug("deleting PowerMeter instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PowerMeter findById(java.lang.Integer id) {
        log.debug("getting PowerMeter instance with id: " + id);
        try {
            PowerMeter instance = (PowerMeter) getHibernateTemplate().get(
                    "sicce.api.info.PowerMeter", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(PowerMeter instance) {
        log.debug("finding PowerMeter instance by example");
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
        log.debug("finding PowerMeter instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from PowerMeter as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findByIpAddress(Object ipAddress) {
        return findByProperty(IP_ADDRESS, ipAddress);
    }

    public List findByLogStatus(Object logStatus) {
        return findByProperty(LOG_STATUS, logStatus);
    }
    
    public List findByDeviceID(Object deviceID) {
        return findByProperty(DEVICE_ID, deviceID);
    }

    public List findBySerial(Object serial) {
        return findByProperty(SERIAL, serial);
    }


    public List findAll() {
        log.debug("finding all PowerMeter instances");
        try {
            String queryString = "from PowerMeter";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PowerMeter merge(PowerMeter detachedInstance) {
        log.debug("merging PowerMeter instance");
        try {
            PowerMeter result = (PowerMeter) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PowerMeter instance) {
        log.debug("attaching dirty PowerMeter instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PowerMeter instance) {
        log.debug("attaching clean PowerMeter instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static PowerMeterDAO getFromApplicationContext(ApplicationContext ctx) {
        return (PowerMeterDAO) ctx.getBean("PowerMeterDAO");
    }
}
