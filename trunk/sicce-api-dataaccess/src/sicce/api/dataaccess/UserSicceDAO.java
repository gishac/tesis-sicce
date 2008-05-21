package sicce.api.dataaccess;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import sicce.api.info.UserSicce;
import sicce.api.info.interfaces.IUserSicce;

/**
 * Data access object (DAO) for domain model class UserSicce.
 * 
 * @see sicce.api.info.UserSicce
 * @author MyEclipse Persistence Tools
 */
public class UserSicceDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(UserSicceDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String CODE_UCSG = "codeUcsg";
    public static final String USERNAME_SICCE = "usernameSicce";
    public static final String PASSWORD_SICCE = "passwordSicce";

    protected void initDao() {
    // do nothing
    }

    public void save(IUserSicce transientInstance) {
        log.debug("saving UserSicce instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(IUserSicce user) {
        log.debug("updating UserSicce instance");
        try {
            getHibernateTemplate().update(user);
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void delete(IUserSicce persistentInstance) {
        log.debug("deleting UserSicce instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public UserSicce findById(java.lang.Integer id) {
        log.debug("getting UserSicce instance with id: " + id);
        try {
            UserSicce instance = (UserSicce) getHibernateTemplate().get(
                    "sicce.api.info.UserSicce", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(UserSicce instance) {
        log.debug("finding UserSicce instance by example");
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
        log.debug("finding UserSicce instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from UserSicce as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List findByLastname(Object lastname) {
        return findByProperty(LASTNAME, lastname);
    }

    public List findByCodeUcsg(Object codeUcsg) {
        return findByProperty(CODE_UCSG, codeUcsg);
    }

    public List findByUsernameSicce(Object usernameSicce) {
        return findByProperty(USERNAME_SICCE, usernameSicce);
    }

    public List findByPasswordSicce(Object passwordSicce) {
        return findByProperty(PASSWORD_SICCE, passwordSicce);
    }

    public List findAll() {
        log.debug("finding all UserSicce instances");
        try {
            //String queryString = "from UserSicce as model where model." + propertyName + "= ?";
            String queryString = "from UserSicce as model where model.usernameSicce != ?";
            return getHibernateTemplate().find(queryString,"adminsicce");
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public UserSicce merge(UserSicce detachedInstance) {
        log.debug("merging UserSicce instance");
        try {
            UserSicce result = (UserSicce) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(UserSicce instance) {
        log.debug("attaching dirty UserSicce instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(UserSicce instance) {
        log.debug("attaching clean UserSicce instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static UserSicceDAO getFromApplicationContext(ApplicationContext ctx) {
        return (UserSicceDAO) ctx.getBean("UserSicceDAO");
    }
}
