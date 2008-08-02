/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.wizard.reports.models;


import sicce.wizard.reports.models.FieldHandler;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import sicce.api.info.Field;
import sicce.api.info.interfaces.IFilter;

/**
 *
 * @author gish@c
 */
public class ReportModel implements Serializable {

    private FieldHandler field;
    private List<IFilter> filters;
    private String reportName;
    private String description;
    private Date startDate;
    private Date endDate;
    private Boolean pchart;
    private Boolean ortHorizontal;
    private Boolean ortVertical;
    private Field charSelected;
    private Boolean isLoaded;

   

    public Boolean getPchart() {
        return pchart;
    }

    public void setPchart(Boolean pchart) {
        this.pchart = pchart;
    }

    public ReportModel() {
    }

     public Boolean getOrtHorizontal() {
        return ortHorizontal;
    }

    public void setOrtHorizontal(Boolean ortHorizontal) {
        this.ortHorizontal = ortHorizontal;
    }

    public Boolean getOrtVertical() {
        return ortVertical;
    }

    public void setOrtVertical(Boolean ortVertical) {
        this.ortVertical = ortVertical;
    }

    
    
    public List<IFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<IFilter> filters) {
        this.filters = filters;
    }
    public Field getCharSelected() {
        return charSelected;
    }

    public void setCharSelected(Field charSelected) {
        this.charSelected = charSelected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getChart() {
        return pchart;
    }

    public void setChart(Boolean pchart) {
        this.pchart = pchart;
    }

    public FieldHandler getField() {
        return field;
    }

    public void setField(FieldHandler pfield) {
        this.field = pfield;
    }

   
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
     public Boolean getIsLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(Boolean isLoaded) {
        this.isLoaded = isLoaded;
    }
    
}
