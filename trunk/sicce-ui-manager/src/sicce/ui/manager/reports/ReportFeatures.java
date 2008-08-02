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
    public void CreateColumns(List<Field> listSelected, DynamicReportBuilder drb, Style detailStyle, Style importeStyle, Style headerStyle) {



        for (Field fieldSelected : (List<Field>) listSelected) {
            try {

                AbstractColumn column = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldSelected.getAliasField(), fieldSelected.getDataType())
                        .setTitle(fieldSelected.getTitle())
                        .setWidth(fieldSelected.getSize())
                        .setStyle(setStyleType(fieldSelected, importeStyle, detailStyle))
                        .setHeaderStyle(headerStyle)
                        .build();

                drb.addColumn(column);
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 
     * @param listGroup
     * @param drb
     */
    public void createGroup(Map wizardData, DynamicReportBuilder drb, Style detailStyle, Style importeStyle, Style headerStyle) {

        FieldHandler pFieldHandler = (FieldHandler) wizardData.get(KEY_FIELD);
        List listGroup = pFieldHandler.getListGroupFields();
        List listVariableMeasure = pFieldHandler.getLstMeasureFields();
        Boolean chart = (Boolean) wizardData.get(KEY_BL_CHART);
        Field chartSelected = (Field) wizardData.get(KEY_FIELD_CHART);

        if (listGroup.size() < 0) {
            return;
        }

        for (Field fieldGroup : (List<Field>) listGroup) {
            try {
                GroupBuilder groupBuilder = addFooterVariables(listVariableMeasure, importeStyle, headerStyle);
                AbstractColumn column = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldGroup.getAliasField(), fieldGroup.getDataType())
                        .setTitle(fieldGroup.getTitle())
                        .setStyle(setStyleType(fieldGroup, importeStyle, detailStyle))
                        .setHeaderStyle(headerStyle)
                        .setWidth(fieldGroup.getSize())
                        .build();
                ColumnsGroup columnsGroup = groupBuilder.setCriteriaColumn((PropertyColumn) column)
                        .setGroupLayout(GroupLayout.DEFAULT)
                        .build();

                drb.addGroup(columnsGroup);
                if (chart) {
                    if (fieldGroup.getIdField() == chartSelected.getIdField()) {
                        addChart(columnsGroup, drb);
                    }
                }
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void addGlobalVariables(List<Field> listVariableMeasure, DynamicReportBuilder drb, Style footerVariables, Style importeStyle, Style headerStyle) {

        if (listVariableMeasure.size() < 0) {
            return;
        }

        for (Field fieldMeasure : (List<Field>) listVariableMeasure) {
            try {
                AbstractColumn columnMeasure = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldMeasure.getAliasField(), fieldMeasure.getDataType())
                        .setTitle(fieldMeasure.getTitle())
                        .setStyle(importeStyle)
                        .setHeaderStyle(headerStyle)
                        .setWidth(fieldMeasure.getSize())
                        .setPattern("0.00")
                        .build();
                drb.addGlobalFooterVariable(columnMeasure, ColumnsGroupVariableOperation.SUM, footerVariables);
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public GroupBuilder addFooterVariables(List<Field> listVariableMeasure, Style importeStyle, Style headerStyle) {

        if (listVariableMeasure.size() < 0) {
            return null;
        }

        GroupBuilder groupBuilder = new GroupBuilder();
        for (Field fieldMeasure : (List<Field>) listVariableMeasure) {
            try {
                AbstractColumn columnMeasure = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldMeasure.getAliasField(), fieldMeasure.getDataType())
                        .setTitle(fieldMeasure.getTitle())
                        .setStyle(importeStyle)
                        .setHeaderStyle(headerStyle)
                        .setWidth(fieldMeasure.getSize())
                        .build();
                groupBuilder.addFooterVariable(columnMeasure, ColumnsGroupVariableOperation.SUM);
                lstColumns.add(columnMeasure);

            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return groupBuilder;
    }

    public void addChart(ColumnsGroup groupChart, DynamicReportBuilder drb) {
        try {
            DJChartBuilder cb = new DJChartBuilder();
            
            DJChartBuilder builder2 = cb.addType(DJChart.BAR_CHART)
                    .addOperation(DJChart.CALCULATION_SUM)
                    .addColumnsGroup(groupChart)
                    .setPosition(DJChartOptions.POSITION_FOOTER)
                    .setShowLabels(true).setShowLegend(true);
            
            for (AbstractColumn column : (List<AbstractColumn>) lstColumns) {
                builder2 = builder2.addColumn(column);
            }
            DJChart chart = builder2.build();



            drb.addChart(chart); //add chart
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
}
