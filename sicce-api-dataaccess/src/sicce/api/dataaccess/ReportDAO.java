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
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author gish@c
 */
public class ReportDAO extends HibernateDaoSupport {

    private static final Log log = LogFactory.getLog(ReportDAO.class);
    // property constants
    public static final String REPORT_NAME = "reportName";
    public static final String REPORT_DESCRIPTION = "reportDescription";
    public static final String REPORT_JRXML = "reportJrxml";

    protected void initDao() {
    // do nothing
    }

    public void save(IReport transientInstance) {
        log.debug("saving Report instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(IReport report) {
        log.debug("udpating Report instance");
        try {
            getHibernateTemplate().update(report);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void delete(IReport persistentInstance) {
        log.debug("deleting Report instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public IReport findById(java.lang.Integer id) {
        log.debug("getting Report instance with id: " + id);
        try {
            IReport instance = (IReport) getHibernateTemplate().get(
                    "sicce.api.info.Report", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(IReport instance) {
        log.debug("finding Report instance by example");
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
        log.debug("finding Report instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Report as model where model." + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByReportName(Object reportName) {
        return findByProperty(REPORT_NAME, reportName);
    }

    public List findByReportDescription(Object reportDescription) {
        return findByProperty(REPORT_DESCRIPTION, reportDescription);
    }

    public List findByReportJrxml(Object reportJrxml) {
        return findByProperty(REPORT_JRXML, reportJrxml);
    }

    public List findAll() {
        log.debug("finding all Report instances");
        try {
            String queryString = "from Report";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public IReport merge(IReport detachedInstance) {
        log.debug("merging Report instance");
        try {
            IReport result = (IReport) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IReport instance) {
        log.debug("attaching dirty Report instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(IReport instance) {
        log.debug("attaching clean Report instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ReportDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ReportDAO) ctx.getBean("ReportDAO");
    }
}
