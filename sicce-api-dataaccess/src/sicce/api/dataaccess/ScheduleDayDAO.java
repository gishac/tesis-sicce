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
import sicce.api.info.interfaces.IScheduleDay;

/**
 *
 * @author gish@c
 */
public class ScheduleDayDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ScheduleDayDAO.class);
    // property constants
    public static final String DAY_SCHEDULED = "dayScheduled";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";

    @Override
    protected void initDao() {
    // do nothing
    }

    public void save(IScheduleDay transientInstance) {
        log.debug("saving ScheduleDay instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void updated(IScheduleDay scheduleDay) {
        log.debug("updating ScheduleDay instance");
        try {
            getHibernateTemplate().update(scheduleDay);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void delete(IScheduleDay persistentInstance) {
        log.debug("deleting ScheduleDay instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IScheduleDay findById(java.lang.Integer id) {
        log.debug("getting ScheduleDay instance with id: " + id);
        try {
            IScheduleDay instance = (IScheduleDay) getHibernateTemplate().get(
                    "sicce.api.info.ScheduleDay", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(IScheduleDay instance) {
        log.debug("finding ScheduleDay instance by example");
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
        log.debug("finding ScheduleDay instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from ScheduleDay as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByDayScheduled(Object dayScheduled) {
        return findByProperty(DAY_SCHEDULED, dayScheduled);
    }

    public List findByStartTime(Object startTime) {
        return findByProperty(START_TIME, startTime);
    }

    public List findByEndTime(Object endTime) {
        return findByProperty(END_TIME, endTime);
    }

    public List findAll() {
        log.debug("finding all ScheduleDay instances");
        try {
            String queryString = "from ScheduleDay";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public IScheduleDay merge(IScheduleDay detachedInstance) {
        log.debug("merging ScheduleDay instance");
        try {
            IScheduleDay result = (IScheduleDay) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IScheduleDay instance) {
        log.debug("attaching dirty ScheduleDay instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(IScheduleDay instance) {
        log.debug("attaching clean ScheduleDay instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ScheduleDayDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ScheduleDayDAO) ctx.getBean("ScheduleDayDAO");
    }
}
