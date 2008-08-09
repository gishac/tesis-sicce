/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.reports;

import ar.com.fdvs.dj.domain.ColumnsGroupVariableOperation;
import ar.com.fdvs.dj.domain.DJChart;
import ar.com.fdvs.dj.domain.DJChartOptions;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ChartBuilderException;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DJChartBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.entities.ColumnsGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.info.Field;
import sicce.wizard.reports.models.FieldHandler;

/**
 *
 * @author gish@c
 */
public class ReportFeatures {

    public static final String KEY_FIELD = "KeyField";
    public static final String KEY_BL_CHART = "blChart";
    public static final String KEY_FIELD_CHART = "FieldChart";
    public List<AbstractColumn> lstColumns = new ArrayList();

    public ReportFeatures() {

    }

    /**
     * 
     * @param listSelected
     * @param drb
     * @throws ar.com.fdvs.dj.domain.builders.ColumnBuilderException
     */
    public List<AbstractColumn> CreateColumns(List<Field> listSelected, DynamicReportBuilder drb, Style detailStyle, Style headerStyle) {

        List<AbstractColumn> listColumn = new ArrayList();
        for (Field fieldSelected : (List<Field>) listSelected) {
            try {

                AbstractColumn column = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldSelected.getAliasField(), fieldSelected.getDataType())
                        .setTitle(fieldSelected.getTitle()).setWidth(fieldSelected.getSize()).setStyle(detailStyle)
                        .setHeaderStyle(headerStyle).build();
                listColumn.add(column);
                drb.addColumn(column);
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listColumn;
    }
//setStyleType(fieldSelected, importeStyle, detailStyle)
    /**
     * 
     * @param listGroup
     * @param drb
     */
    public void createGroup(List<AbstractColumn> listColumn, Map wizardData, DynamicReportBuilder drb) {

        Boolean chart = (Boolean) wizardData.get(KEY_BL_CHART);
        Field chartSelected = (Field) wizardData.get(KEY_FIELD_CHART);
        FieldHandler field = (FieldHandler) wizardData.get(KEY_FIELD);
        List listGroup = field.getListGroupFields();
        ColumnsGroup columnsGroup = new ColumnsGroup();
        if (listColumn.size() < 0) {
            return;
        }
        List<AbstractColumn> columnMeasure = getMeasuresColumns(listColumn);
        List<AbstractColumn> columnGroup = getColumnsGroup(listColumn, listGroup);
        String columnGraph = null;
        for (AbstractColumn column : (List<AbstractColumn>) columnGroup) {
            GroupBuilder groupBuilder = addFooterVariables(columnMeasure);
            columnsGroup = groupBuilder.setCriteriaColumn((PropertyColumn) column)
                    .setStartInNewPage(chart)
                    .setGroupLayout(GroupLayout.DEFAULT_WITH_HEADER).build();
            
            drb.addGroup(columnsGroup);
            columnGraph = column.getTitle();
        }
         if (chart && chartSelected!=null) {
                if (chartSelected.getTitle().equals(columnGraph)) {
                    addChart(columnsGroup, drb);
                }
            }
    }

    public void addGlobalVariables(List<AbstractColumn> listColumns, DynamicReportBuilder drb, Style footerVariables, Style importeStyle, Style headerStyle) {

        List<AbstractColumn> listMeasure = getMeasuresColumns(listColumns);
        
        if (listMeasure.size() < 0) {
            return;
        }
        for (AbstractColumn columnMeasure : (List<AbstractColumn>) listMeasure) {
            drb.addGlobalFooterVariable(columnMeasure, ColumnsGroupVariableOperation.SUM, footerVariables);
        }

    }

    public GroupBuilder addFooterVariables(List<AbstractColumn> listMeasure) {

        if (listMeasure.size() < 0) {
            return null;
        }

        GroupBuilder groupBuilder = new GroupBuilder();
        for (AbstractColumn columnMeasure : (List<AbstractColumn>) listMeasure) {
            groupBuilder.addFooterVariable(columnMeasure, ColumnsGroupVariableOperation.SUM);
            lstColumns.add(columnMeasure);

        }

        return groupBuilder;
    }

    public void addChart(ColumnsGroup groupChart, DynamicReportBuilder drb) {
        try {
            DJChartBuilder cb = new DJChartBuilder();
            int chartHeight = 150;
            DJChartBuilder builder2 = cb.addType(DJChart.BAR_CHART)
                    .addOperation(DJChart.CALCULATION_SUM)
                    .addColumnsGroup(groupChart)
                    .setPosition(DJChartOptions.POSITION_HEADER)
                    .setCentered(true)
                    .setHeight(chartHeight)
                    .setShowLabels(true).setShowLegend(true);

            for (AbstractColumn column : (List<AbstractColumn>) lstColumns) {
                builder2 = builder2.addColumn(column);
            }
            DJChart chart = builder2.build();
            drb.addChart(chart);
        } catch (ChartBuilderException ex) {
            Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Style setStyleType(Field field, Style importeStyle, Style detailStyle) {
        Style tmp = new Style();

        if (field.getDataType().equals(String.class.getName())) {
            tmp = detailStyle;
        } else {
            tmp = importeStyle;
        }
        return tmp;
    }

    public List<AbstractColumn> getMeasuresColumns(List<AbstractColumn> listColumn) {
        List<AbstractColumn> listMeasure = new ArrayList();
        for (AbstractColumn columnMeasure : (List<AbstractColumn>) listColumn) {
            if (columnMeasure.getValueClassNameForExpression().equals(Double.class.getName())) {
                listMeasure.add(columnMeasure);
            }
        }

        return listMeasure;
    }

    public List<AbstractColumn> getColumnsGroup(List<AbstractColumn> listColumn, List<Field> listfield) {
        List<AbstractColumn> listGroup = new ArrayList();
        for (AbstractColumn column : (List<AbstractColumn>) listColumn) {
            for (Field fieldgroup : (List<Field>) listfield) {
                if (column.getTitle().equals(fieldgroup.getTitle())) {
                    listGroup.add(column);
                }
            }
        }

        return listGroup;
    }
}
