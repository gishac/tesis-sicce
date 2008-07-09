package sicce.ui.manager.reports;

import sicce.api.info.Field;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.ColumnsGroupVariableOperation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
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
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.ResourceMap;
import sicce.api.info.interfaces.IFilter;
import sicce.api.util.GetResourceDir;

public class ReportTemplate extends GenerateDjReport {

    public static final String KEY_SELECTED = "selectedFields";
    public static final String KEY_GROUP = "groupFields";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_NAME = "name";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    public static final String KEY_ORT_VERTICAL = "ortVertical";
    public static final String KEY_ORT_HORIZONTAL = "ortHorizontal";

    public DynamicReport buildReport(Map wizardData) throws Exception {

        Map param = new HashMap();
        List listSelected = (List) wizardData.get(KEY_SELECTED);
        List listGroup = (List) wizardData.get(KEY_GROUP);
        String title = (String) wizardData.get(KEY_NAME);
        Boolean pvertical = (Boolean) wizardData.get(KEY_ORT_VERTICAL);
        Boolean phorizontal = (Boolean) wizardData.get(KEY_ORT_HORIZONTAL);


        /*Instanciamos los estilos para cada sección*/
        Style detailStyle = new Style();
        Style headerStyle = new Style();
        Style titleStyle = new Style();

        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        headerStyle.setFont(Font.VERDANA_SMALL_BOLD);
        headerStyle.setBorderBottom(Border.PEN_1_POINT);
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.BLUE);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);

        /* Definir el formato para el título del reporte - titleStyle -*/
        titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));

        Style importeStyle = new Style();
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        Style oddRowStyle = new Style();
        oddRowStyle.setBorder(Border.NO_BORDER);
        oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
        oddRowStyle.setTransparency(Transparency.OPAQUE);
        detailStyle.setFont(Font.VERDANA_SMALL);


        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15).setDefaultStyles(titleStyle, null, headerStyle, detailStyle).setColumnsPerPage(1);
        //String imagePath = resourceMap.getResourcesDir() + "/" + resourceMap.getString("small_ucsg");
        //drb.addImageBanner(imagePath, new Integer(197), new Integer(60), ImageBanner.ALIGN_RIGHT);
        CreateColumns(listSelected, drb);
        
        drb.setUseFullPageWidth(true);
        if(phorizontal)
                drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
        if (pvertical)
                drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());    
        String stFieldSelected = createQueryFields(listSelected);
        String stFieldGroup = createQueryGroupby(listGroup);
        String stFiltertmp = createWhereClause(wizardData, drb);
        String stFilterWhere = (stFiltertmp != null ? " WHERE " + stFiltertmp : "");


      // createGroup(listGroup, drb);
//        String joins = "FROM location, power_meter, location_type, location_zone, zone, measure " +
//                "WHERE measure.DATE_MEASURE = $P{begin} or measure.DATE_MEASURE  IS NULL  " +
//               "AND measure.DATE_MEASURE = $P{finish} or measure.DATE_MEASURE  IS NULL"
        //               location.POWER_METER_ID_POWER_METER  = power_meter.ID_POWER_METER " +
//                "AND location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE " +
//                "AND location.ID_LOCATION = location_zone.ID_LOCATION " +
//                "AND location_zone.ID_ZONE = zone.ID_ZONE " +
//                "AND location.ID_LOCATION = measure.ID_LOCATION " +
//                "AND power_meter.ID_POWER_METER = measure.ID_POWER_METER ";
//        "FROM location " +
//                "INNER JOIN location_type ON location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE" +
//                " LEFT JOIN location_zone ON location.ID_LOCATION = location_zone.ID_LOCATION " +
//                " LEFT JOIN zone ON location_zone.ID_ZONE = zone.ID_ZONE " +
//                " LEFT JOIN power_meter ON location.POWER_METER_ID_POWER_METER  = power_meter.ID_POWER_METER";
        String joins = "FROM measure " +
                "INNER JOIN location ON measure.ID_LOCATION = location.ID_LOCATION " +
                "INNER JOIN power_meter ON measure.ID_POWER_METER = power_meter.ID_POWER_METER " +
                "INNER JOIN location_type ON location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE " +
                "LEFT JOIN location_zone ON location.ID_LOCATION = location_zone.ID_LOCATION " +
                "LEFT JOIN zone ON location_zone.ID_ZONE = zone.ID_ZONE ";

        String query = "SELECT" + " " + stFieldSelected + " " + joins + " " + stFilterWhere + " GROUP BY " + stFieldGroup;
        //String query = "SELECT" + " " + stFieldSelected + " " + joins + " " + stFilterWhere;
        //+ " GROUP BY " + stFieldGroup;
        System.out.println("QUERY :" + query);
        drb.setQuery(query, DJConstants.QUERY_LANGUAGE_SQL);
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT);
        drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT,AutoText.PATTERN_DATE_DATE_TIME);
        

        DynamicReport dr = drb.build();

        return dr;
    }

    /**
     * 
     * @param listSelected
     * @param drb
     * @throws ar.com.fdvs.dj.domain.builders.ColumnBuilderException
     */
    public void CreateColumns(List<Field> listSelected, DynamicReportBuilder drb) {

        for (Field fieldSelected : (List<Field>) listSelected) {
            try {

                AbstractColumn column = ColumnBuilder.getInstance().setColumnProperty(fieldSelected.getAliasField(), fieldSelected.getDataType()).setTitle(fieldSelected.getTitle()).setWidth(85).build();
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
        if (listGroup.size() < 0) {
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
    public void createGroup(List<Field> listGroup, DynamicReportBuilder drb) {

        for (Field fieldGroup : (List<Field>) listGroup) {
            try {
                GroupBuilder gb1 = new GroupBuilder();
                AbstractColumn column = ColumnBuilder.getInstance().setColumnProperty(fieldGroup.getAliasField(), fieldGroup.getDataType()).setTitle(fieldGroup.getTitle()).setWidth(85).build();
                ColumnsGroup g1 = gb1.setCriteriaColumn((PropertyColumn) column).addFooterVariable(column, ColumnsGroupVariableOperation.COUNT).setGroupLayout(GroupLayout.DEFAULT).build();


                drb.addGroup(g1);
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public String createWhereClause(Map wizardData, DynamicReportBuilder drb) {
        List listFilters = (List) wizardData.get(KEY_WHERE);
        Date beginDate = (Date) wizardData.get(KEY_BEGIN_DATE);
        Date finishDate = (Date) wizardData.get(KEY_FINISH_DATE);
        
        String whereClause = new String();
        if (listFilters.size() < 0) {
            return null;
        }

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
            
            if (beginDate!=null && finishDate!=null){
                String whereDate = "measure.date_measure" + " >= " + beginDate +  "measure.date_measure" + " <= " + finishDate + " AND ";
                whereClause = whereClause.concat(whereDate);
            }


        }
        String total = (whereClause != null ? (whereClause.substring(0, whereClause.length() - 4)) : whereClause);
        System.out.println("total :" + total);
        return total;

    }
    
    
    public String createClause(IFilter pfilter, String operator, String condition){
            String whereClause = pfilter.getField().getTableName() + "." + pfilter.getField().getDescriptionField() + operator + "'" + pfilter.getValues() + "'" + condition;
    return whereClause;
    }
}
