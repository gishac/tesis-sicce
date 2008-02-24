package sicce.api.dataaccess;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.OptionSicce;
import sicce.api.info.interfaces.IOptionSicce;

/**
 * Data access object (DAO) for domain model class OptionSicce.
 * 
 * @see sicce.api.info.OptionSicce
 * @author MyEclipse Persistence Tools
 */
public class OptionSicceDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(OptionSicceDAO.class);
    // property constants
    public static final String DESCRIPTION = "description";
    public static final String ICON = "icon";

    protected void initDao() {
    // do nothing
    }

    public void save(OptionSicce transientInstance) {
        log.debug("saving OptionSicce instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(OptionSicce persistentInstance) {
        log.debug("deleting OptionSicce instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IOptionSicce findById(java.lang.Integer id) {
        log.debug("getting OptionSicce instance with id: " + id);
        try {
            IOptionSicce instance = (IOptionSicce) getHibernateTemplate().get(
                    "sicce.api.info.OptionSicce", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(OptionSicce instance) {
        log.debug("finding OptionSicce instance by example");
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
        log.debug("finding OptionSicce instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from OptionSicce as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findByIcon(Object icon) {
        return findByProperty(ICON, icon);
    }

    public List findAll() {
        log.debug("finding all OptionSicce instances");
        try {
            String queryString = "from OptionSicce";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public OptionSicce merge(OptionSicce detachedInstance) {
        log.debug("merging OptionSicce instance");
        try {
            OptionSicce result = (OptionSicce) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(OptionSicce instance) {
        log.debug("attaching dirty OptionSicce instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(OptionSicce instance) {
        log.debug("attaching clean OptionSicce instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static OptionSicceDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (OptionSicceDAO) ctx.getBean("OptionSicceDAO");
    }
}
