package sicce.api.dataaccess;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.Parameter;

/**
 * Data access object (DAO) for domain model class Parameter.
 * 
 * @see sicce.api.info.Parameter
 * @author MyEclipse Persistence Tools
 */
public class ParameterDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ParameterDAO.class);
    // property constants
    public static final String DESCRIPTION = "description";
    public static final String VALUE = "value";

    protected void initDao() {
    // do nothing
    }

    public void save(Parameter transientInstance) {
        log.debug("saving Parameter instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(Parameter parameter){
        log.debug("updating Parameter instance");
        try {
            getHibernateTemplate().update(parameter);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("updated failed", re);
            throw re;
        }
    }

    public void delete(Parameter persistentInstance) {
        log.debug("deleting Parameter instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Parameter findById(java.lang.Integer id) {
        log.debug("getting Parameter instance with id: " + id);
        try {
            Parameter instance = (Parameter) getHibernateTemplate().get(
                    "sicce.api.info.Parameter", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Parameter instance) {
        log.debug("finding Parameter instance by example");
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
        log.debug("finding Parameter instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Parameter as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findByValue(Object value) {
        return findByProperty(VALUE, value);
    }

    public List findAll() {
        log.debug("finding all Parameter instances");
        try {
            String queryString = "from Parameter";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Parameter merge(Parameter detachedInstance) {
        log.debug("merging Parameter instance");
        try {
            Parameter result = (Parameter) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Parameter instance) {
        log.debug("attaching dirty Parameter instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Parameter instance) {
        log.debug("attaching clean Parameter instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ParameterDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ParameterDAO) ctx.getBean("ParameterDAO");
    }
}
