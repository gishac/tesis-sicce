/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.auto;

import java.io.Serializable;
import sicce.api.info.interfaces.IOptionSicce;
import sicce.api.info.interfaces.IReport;

/**
 *
 * @author gish@c
 */
public class AbstractReport implements Serializable, IReport {

    // Fields    

     protected Integer idReport;
     protected IOptionSicce optionSicce;
     protected String reportName;
     protected String reportDescription;
     protected String reportJrxml;


    // Constructors

    /** default constructor */
    public AbstractReport() {
    }

	/** minimal constructor */
    public AbstractReport(Integer idReport, IOptionSicce optionSicce, String reportName) {
        this.idReport = idReport;
        this.optionSicce = optionSicce;
        this.reportName = reportName;
    }
    
    /** full constructor */
    public AbstractReport(Integer idReport, IOptionSicce optionSicce, String reportName, String reportDescription, String reportJrxml) {
        this.idReport = idReport;
        this.optionSicce = optionSicce;
        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.reportJrxml = reportJrxml;
    }

   
    // Property accessors

    public Integer getIdReport() {
        return this.idReport;
    }
    
    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public IOptionSicce getOptionSicce() {
        return this.optionSicce;
    }
    
    public void setOptionSicce(IOptionSicce optionSicce) {
        this.optionSicce = optionSicce;
    }

    public String getReportName() {
        return this.reportName;
    }
    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return this.reportDescription;
    }
    
    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportJrxml() {
        return this.reportJrxml;
    }
    
    public void setReportJrxml(String reportJrxml) {
        this.reportJrxml = reportJrxml;
    }
    
}
