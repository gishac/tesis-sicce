/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.dataaccess;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import sicce.api.info.Alarm;
import sicce.api.info.interfaces.IAlarm;

/**
 *
 * @author gish@c
 */
/**
 * Data access object (DAO) for domain model class Alarm.
 * 
 * @see sicce.api.info.Alarm
 * @author MyEclipse Persistence Tools
 */
public class AlarmDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(AlarmDAO.class);
    // property constants
    public static final String SCHEDULE_TYPE = "scheduleType";
    public static final String ALARM_TYPE = "alarmType";
    public static final String DESCRIPTION = "description";

    @Override
    protected void initDao() {
    // do nothing
    }

    public void save(IAlarm transientInstance) {
        log.debug("saving Alarm instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(IAlarm alarm) {
        log.debug("updating Alarm instance");
        try {
            getHibernateTemplate().update(alarm);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void delete(IAlarm persistentInstance) {
        log.debug("deleting Alarm instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IAlarm findById(java.lang.Integer id) {
        log.debug("getting Alarm instance with id: " + id);
        try {
            IAlarm instance = (IAlarm) getHibernateTemplate().get(
                    "sicce.api.info.Alarm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Alarm instance) {
        log.debug("finding Alarm instance by example");
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
        log.debug("finding Alarm instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Alarm as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByScheduleType(Object scheduleType) {
        return findByProperty(SCHEDULE_TYPE, scheduleType);
    }

    public List findByAlarmType(Object alarmType) {
        return findByProperty(ALARM_TYPE, alarmType);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all Alarm instances");
        try {
            String queryString = "from Alarm";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Alarm merge(Alarm detachedInstance) {
        log.debug("merging Alarm instance");
        try {
            Alarm result = (Alarm) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Alarm instance) {
        log.debug("attaching dirty Alarm instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Alarm instance) {
        log.debug("attaching clean Alarm instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static AlarmDAO getFromApplicationContext(ApplicationContext ctx) {
        return (AlarmDAO) ctx.getBean("AlarmDAO");
    }
}
