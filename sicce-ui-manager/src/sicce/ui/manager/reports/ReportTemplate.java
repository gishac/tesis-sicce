package sicce.ui.manager.reports;

import ar.com.fdvs.dj.domain.builders.ChartBuilderException;
import sicce.api.info.Field;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.ColumnsGroupVariableOperation;
import ar.com.fdvs.dj.domain.DJChart;
import ar.com.fdvs.dj.domain.DJChartOptions;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DJChartBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.ColumnsGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.info.interfaces.IFilter;

public class ReportTemplate extends GenerateDjReport {

    public static final String KEY_SELECTED = "selectedFields";
    public static final String KEY_GROUP = "groupFields";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_MEASURE_FIELDS = "measureFields";
    public static final String KEY_NAME = "name";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    public static final String KEY_ORT_VERTICAL = "ortVertical";
    public static final String KEY_ORT_HORIZONTAL = "ortHorizontal";
    public static final String KEY_BL_CHART = "blChart";
    public static final String KEY_FIELD_CHART = "FieldChart";
    public  List<AbstractColumn> lstColumns = new ArrayList();
    public DynamicReport buildReport(Map wizardData) throws Exception {

        Map param = new HashMap();
       
        List listSelected = (List) wizardData.get(KEY_SELECTED);
        String title = (String) wizardData.get(KEY_NAME);
        Boolean pvertical = (Boolean) wizardData.get(KEY_ORT_VERTICAL);
        Boolean phorizontal = (Boolean) wizardData.get(KEY_ORT_HORIZONTAL);
        List listGroup = (List) wizardData.get(KEY_GROUP);
        List listMeasure = (List) wizardData.get(KEY_MEASURE_FIELDS);
      

        /*Instanciamos los estilos para cada sección*/
        Style detailStyle = new Style();
        Style headerStyle = new Style();
        Style titleStyle = new Style();
        Style headerVariables = new Style();
        Style importeStyle = new Style();
        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        headerStyle.setFont(Font.VERDANA_SMALL_BOLD);
        headerStyle.setBorder(Border.THIN);
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.gray);
        headerStyle.setTextColor(Color.BLACK);
        headerStyle.setTransparency(Transparency.OPAQUE);
        headerStyle.getStreching();

        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        headerVariables.setFont(Font.VERDANA_SMALL);
        headerVariables.setBorder(Border.THIN);
        headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerVariables.setVerticalAlign(VerticalAlign.JUSTIFIED);
        headerVariables.getStreching();


        /* Definir el formato para el título del reporte - titleStyle -*/
        titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));

        
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        importeStyle.setBlankWhenNull(true);
        importeStyle.setBorder(Border.PEN_1_POINT);
        importeStyle.setFont(Font.VERDANA_SMALL);
        importeStyle.getStreching();



        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15).setDefaultStyles(titleStyle, null, headerStyle, detailStyle).setColumnsPerPage(1);
        //String imagePath = resourceMap.getResourcesDir() + "/" + resourceMap.getString("small_ucsg");
        //drb.addImageBanner(imagePath, new Integer(197), new Integer(60), ImageBanner.ALIGN_RIGHT);
        CreateColumns(listSelected, drb, importeStyle, headerStyle);

        drb.setUseFullPageWidth(true);
        if (phorizontal) {
            drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
        }
        if (pvertical) {
            drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());
        }
        String stFieldSelected = createQueryFields(listSelected);      
        String stFiltertmp = createWhereClause(wizardData, drb);
        String stFilterWhere = (stFiltertmp != null ? " WHERE " + stFiltertmp : "");
        String stFieldGroup = null;
        if (listGroup!=null){
        stFieldGroup = createQueryGroupby(listGroup);
        addGlobalVariables(listMeasure, drb, headerVariables, importeStyle, headerStyle);
        createGroup(wizardData,drb,importeStyle,headerStyle);
        }
        
        String joins = "FROM measure " +
                "INNER JOIN location ON measure.ID_LOCATION = location.ID_LOCATION " +
                "INNER JOIN power_meter ON measure.ID_POWER_METER = power_meter.ID_POWER_METER " +
                "INNER JOIN location_type ON location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE " +
                "LEFT JOIN location_zone ON location.ID_LOCATION = location_zone.ID_LOCATION " +
                "LEFT JOIN zone ON location_zone.ID_ZONE = zone.ID_ZONE ";
        String query = null;
        if (stFilterWhere != null) {
            query = "SELECT" + " " + stFieldSelected + " " + joins + " " + stFilterWhere;
        } else {
            query = "SELECT" + " " + stFieldSelected + " " + joins;
        }
        if(!listGroup.isEmpty())
           query = query +  " GROUP BY " + stFieldGroup;


        System.out.println("QUERY :" + query);
        drb.setQuery(query, DJConstants.QUERY_LANGUAGE_SQL);
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT);
        drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, AutoText.PATTERN_DATE_DATE_TIME);


        DynamicReport dr = drb.build();

        return dr;
    }

    /**
     * 
     * @param listSelected
     * @param drb
     * @throws ar.com.fdvs.dj.domain.builders.ColumnBuilderException
     */
    public void CreateColumns(List<Field> listSelected, DynamicReportBuilder drb, Style importeStyle, Style headerStyle) {

        for (Field fieldSelected : (List<Field>) listSelected) {
            try {

                AbstractColumn column = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldSelected.getAliasField(), fieldSelected.getDataType())
                        .setTitle(fieldSelected.getTitle())
                        .setWidth(85)
                        .setStyle(importeStyle)
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
     * @param listSelected
     * @return
     */
    public String createQueryFields(List<Field> listSelected) {

        String totalFields = new String();
        for (Field fieldSelected : (List<Field>) listSelected) {
            String field = fieldSelected.getTableName() + "." + fieldSelected.getDescriptionField() + " " + fieldSelected.getAliasField() + ",";
            totalFields = totalFields.concat(field);
        }

        String total = totalFields.substring(0, totalFields.length() - 1);

        return total;
    }

    /**
     * 
     * @param listGroup
     * @return
     */
    public String createQueryGroupby(List<Field> listGroup) {

        String totalFieldsGroup = new String();
        if (listGroup.isEmpty()) {
            return null;
        }

        for (Field fieldGroup : (List<Field>) listGroup) {
            String field = fieldGroup.getAliasField() + ",";
            totalFieldsGroup = totalFieldsGroup.concat(field);
        }
        String total = totalFieldsGroup.substring(0, totalFieldsGroup.length() - 1);

        return total;

    }

    /**
     * 
     * @param listGroup
     * @param drb
     */
    public void createGroup(Map wizardData, DynamicReportBuilder drb, Style importeStyle, Style headerStyle) {

        List listGroup = (List) wizardData.get(KEY_GROUP);
        List listVariableMeasure = (List) wizardData.get(KEY_MEASURE_FIELDS);
        Boolean chart = (Boolean) wizardData.get(KEY_BL_CHART);
        Field chartSelected = (Field)wizardData.get(KEY_FIELD_CHART);        

          if (listGroup.size() < 0) {
            return;
        }

        for (Field fieldGroup : (List<Field>) listGroup) {
            try {
                GroupBuilder groupBuilder = addFooterVariables(listVariableMeasure,importeStyle,headerStyle);
                AbstractColumn column = ColumnBuilder.getInstance()
                        .setColumnProperty(fieldGroup.getAliasField(), fieldGroup.getDataType())
                        .setTitle(fieldGroup.getTitle())
                        .setStyle(importeStyle)
		        .setHeaderStyle(headerStyle)
                        .setWidth(85)
                        .build();
                ColumnsGroup columnsGroup = groupBuilder.setCriteriaColumn((PropertyColumn) column)
                        .setGroupLayout(GroupLayout.DEFAULT)
                        .build();

                drb.addGroup(columnsGroup);
                if (chart){
                    if (fieldGroup.getIdField() == chartSelected.getIdField())
                        addChart(columnsGroup, drb);
                }
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void addGlobalVariables(List<Field> listVariableMeasure, DynamicReportBuilder drb, Style headerVariables, Style importeStyle, Style headerStyle) {
        
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
                        .setWidth(85)
                        .build();
                drb.addGlobalHeaderVariable(columnMeasure, ColumnsGroupVariableOperation.SUM, headerVariables);
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
                        .setWidth(85)
                        .build();
                groupBuilder.addFooterVariable(columnMeasure, ColumnsGroupVariableOperation.SUM);
                lstColumns.add(columnMeasure);
                       
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return groupBuilder;
    }
    
    public void addChart(ColumnsGroup groupChart, DynamicReportBuilder drb)
    {
        try {
            DJChartBuilder cb = new DJChartBuilder();
            
            
             DJChartBuilder builder2 = cb.addType(DJChart.PIE_CHART)
                    .addOperation(DJChart.CALCULATION_SUM)
                    .addColumnsGroup(groupChart)
                    .setPosition(DJChartOptions.POSITION_FOOTER)
                    .setShowLabels(true);
            for (AbstractColumn column : (List<AbstractColumn>) lstColumns) {
                 builder2 = builder2.addColumn(column);
             }   
            DJChart chart =    builder2.build();
                    
             

            drb.addChart(chart); //add chart
        } catch (ChartBuilderException ex) {
            Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public String createWhereClause(Map wizardData, DynamicReportBuilder drb) {
        List listFilters = (List) wizardData.get(KEY_WHERE);
        Date begin = (Date) wizardData.get(KEY_BEGIN_DATE);
        Date finish = (Date) wizardData.get(KEY_FINISH_DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate = format.format(begin);
        String finishDate = format.format(finish);
        String whereClause = new String();



        if (listFilters != null) {
            for (IFilter pfilter : (List<IFilter>) listFilters) {

                if (pfilter.getOperator().equals("igual")) {
                    String whereEquals = createClause(pfilter, " = ", " AND ");
                    whereClause = whereClause.concat(whereEquals);
                }

                if (pfilter.getOperator().equals("mayor")) {
                    String whereMajor = createClause(pfilter, " > ", " AND ");
                    whereClause = whereClause.concat(whereMajor);
                }

                if (pfilter.getOperator().equals("menor")) {
                    String whereMinor = createClause(pfilter, " < ", " AND ");
                    whereClause = whereClause.concat(whereMinor);
                }

                if (pfilter.getOperator().equals("diferente")) {
                    String whereDiferent = createClause(pfilter, " <> ", " AND ");
                    whereClause = whereClause.concat(whereDiferent);
                }

                if (pfilter.getOperator().equals("entre")) {
                    String whereIn = pfilter.getField().getTableName() + "." + pfilter.getField().getDescriptionField() + " IN " + "(" + pfilter.getValues() + ")" + " AND ";
                    whereClause = whereClause.concat(whereIn);
                }

                if (pfilter.getOperator().equals("contiene")) {
                    String whereLike = pfilter.getField().getTableName() + "." + pfilter.getField().getDescriptionField() + " LIKE " + "'%" + pfilter.getValues() + "%'" + " AND ";
                    whereClause = whereClause.concat(whereLike);
                }

            }
        }
        if (beginDate != null && finishDate != null) {
            String whereDate = " measure.date_measure" + " between  '" + beginDate + "' and  '" + finishDate + "' AND ";
            whereClause = whereClause.concat(whereDate);
        }

        String total = (whereClause != null ? (whereClause.substring(0, whereClause.length() - 4)) : whereClause);
        System.out.println("total :" + total);


        return total;

    }

    public String createClause(IFilter pfilter, String operator, String condition) {
        String whereClause = pfilter.getField().getTableName() + "." + pfilter.getField().getDescriptionField() + operator + "'" + pfilter.getValues() + "'" + condition;
        return whereClause;
    }
}
