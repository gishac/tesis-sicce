package sicce.api.dataaccess;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.Zone;

/**
 * Data access object (DAO) for domain model class Zone.
 * 
 * @see sicce.api.info.Zone
 * @author MyEclipse Persistence Tools
 */
public class ZoneDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ZoneDAO.class);
    // property constants
    public static final String CODE = "code";
    public static final String DESCRIPTION = "description";

    protected void initDao() {
    // do nothing
    }

    public void save(Zone transientInstance) {
        log.debug("saving Zone instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Zone persistentInstance) {
        log.debug("deleting Zone instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Zone findById(java.lang.Integer id) {
        log.debug("getting Zone instance with id: " + id);
        try {
            Zone instance = (Zone) getHibernateTemplate().get(
                    "sicce.api.info.Zone", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Zone instance) {
        log.debug("finding Zone instance by example");
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
        log.debug("finding Zone instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Zone as model where model." + propertyName + "= ?";
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
        log.debug("finding all Zone instances");
        try {
            String queryString = "from Zone";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Zone merge(Zone detachedInstance) {
        log.debug("merging Zone instance");
        try {
            Zone result = (Zone) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Zone instance) {
        log.debug("attaching dirty Zone instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Zone instance) {
        log.debug("attaching clean Zone instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ZoneDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ZoneDAO) ctx.getBean("ZoneDAO");
    }
}
