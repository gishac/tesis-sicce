/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 *
 * @author gish@c
 */
public interface IReport {

    public Integer getIdReport();
    
    public void setIdReport(Integer idReport);

    public IOptionSicce getOptionSicce();
    
    public void setOptionSicce(IOptionSicce optionSicce);

    public String getReportName();
    
    public void setReportName(String reportName);

    public String getReportDescription();
    
    public void setReportDescription(String reportDescription);

    public String getReportJrxml();
    
    public void setReportJrxml(String reportJrxml);
    
}
