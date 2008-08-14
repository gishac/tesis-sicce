package sicce.api.dataaccess;
// default package
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import sicce.api.info.interfaces.IExceptionSicce;

/**
 * Data access object (DAO) for domain model class ExceptionSicce.
 * @see .ExceptionSicce
 * @author MyEclipse Persistence Tools 
 */
public class ExceptionSicceDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ExceptionSicceDAO.class);
    //property constants
    public static final String ID_POWER_METER = "idPowerMeter";
    public static final String STACK_TRACE = "stackTrace";
    public static final String MESSAGE = "message";

    @Override
    protected void initDao() {
        
    }

    public void save(IExceptionSicce transientInstance) {
        log.debug("saving ExceptionSicce instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(IExceptionSicce persistentInstance) {
        log.debug("deleting ExceptionSicce instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IExceptionSicce findById(java.lang.Integer id) {
        log.debug("getting ExceptionSicce instance with id: " + id);
        try {
            IExceptionSicce instance = (IExceptionSicce) getHibernateTemplate().get("ExceptionSicce", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(IExceptionSicce instance) {
        log.debug("finding ExceptionSicce instance by example");
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
        log.debug("finding ExceptionSicce instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from ExceptionSicce as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByIdPowerMeter(Object idPowerMeter) {
        return findByProperty(ID_POWER_METER, idPowerMeter);
    }

    public List findByStackTrace(Object stackTrace) {
        return findByProperty(STACK_TRACE, stackTrace);
    }

    public List findByMessage(Object message) {
        return findByProperty(MESSAGE, message);
    }

    public List findAll() {
        log.debug("finding all ExceptionSicce instances");
        try {
            String queryString = "from ExceptionSicce";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public IExceptionSicce merge(IExceptionSicce detachedInstance) {
        log.debug("merging ExceptionSicce instance");
        try {
            IExceptionSicce result = (IExceptionSicce) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IExceptionSicce instance) {
        log.debug("attaching dirty ExceptionSicce instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(IExceptionSicce instance) {
        log.debug("attaching clean ExceptionSicce instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ExceptionSicceDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ExceptionSicceDAO) ctx.getBean("ExceptionSicceDAO");
    }
}
