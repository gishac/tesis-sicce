package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
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
import sicce.api.util.GetResourceDir;

public class ReportTemplate extends GenerateDjReport {

  
    public DynamicReport buildReport(String title, List<Field> listSelected, List<Field> listGroup, ResourceMap resourceMap) throws Exception {

        Map param = new HashMap();
        /*Instanciamos los estilos para cada sección*/
        Style detailStyle = new Style();
        Style headerStyle = new Style();
        Style titleStyle = new Style();
        
        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        headerStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
        headerStyle.setBorderBottom(Border.PEN_1_POINT);
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.BLUE);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);

        /* Definir el formato para el título del reporte - titleStyle -*/
        titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
        
        Style importeStyle = new Style();
        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        Style oddRowStyle = new Style();
        oddRowStyle.setBorder(Border.NO_BORDER);
        oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
        oddRowStyle.setTransparency(Transparency.OPAQUE);
        detailStyle.setFont(Font.VERDANA_SMALL);
        
        
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15).setDefaultStyles(titleStyle, null, headerStyle, detailStyle).setColumnsPerPage(1);
        String imagePath = resourceMap.getResourcesDir() + "/" + resourceMap.getString("small_ucsg");
        drb.addImageBanner(imagePath, new Integer(197), new Integer(60), ImageBanner.ALIGN_RIGHT);
        CreateColumns(listSelected, drb);

        drb.setUseFullPageWidth(true);
        String stFieldSelected = createQueryFields(listSelected);
        String stFieldGroup = createQueryGroupby(listGroup);
        
        
        
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
        String joins = "FROM location " +
                "INNER JOIN location_type ON location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE" +
                " LEFT JOIN location_zone ON location.ID_LOCATION = location_zone.ID_LOCATION " +
                " LEFT JOIN zone ON location_zone.ID_ZONE = zone.ID_ZONE " +
                " LEFT JOIN power_meter ON location.POWER_METER_ID_POWER_METER  = power_meter.ID_POWER_METER";
        String query = "SELECT" + " " + stFieldSelected + " " + joins + " " + "GROUP BY " + stFieldGroup;
        System.out.println("QUERY :" + query);
        drb.setQuery(query, DJConstants.QUERY_LANGUAGE_SQL);
 
        DynamicReport dr = drb.build();

        return dr;
    }

    public void CreateColumns(List<Field> listSelected, DynamicReportBuilder drb) throws ColumnBuilderException {

        for (Field fieldSelected : (List<Field>) listSelected) {

            AbstractColumn column = ColumnBuilder.getInstance().setColumnProperty(fieldSelected.getAliasField(), fieldSelected.getDataType()).setTitle(fieldSelected.getTitle()).setWidth(85).build();
            drb.addColumn(column);

        }
          

    }


    public String createQueryFields(List<Field> listSelected) {

        String totalFields = new String();
        for (Field fieldSelected : (List<Field>) listSelected) {
            String field = fieldSelected.getTableName() + "." + fieldSelected.getDescriptionField() + " " + fieldSelected.getAliasField() + ",";
            totalFields = totalFields.concat(field);
        }

        String total = totalFields.substring(0, totalFields.length() - 1);

        return total;
    }

    public String createQueryGroupby(List<Field> listGroup) {

        String totalFieldsGroup = new String();
        for (Field fieldGroup : (List<Field>) listGroup) {
            String field = fieldGroup.getAliasField() + ",";
            totalFieldsGroup = totalFieldsGroup.concat(field);
        }
        String total = totalFieldsGroup.substring(0, totalFieldsGroup.length() - 1);

        return total;
    }
    
    public void createGroup (List<Field> listGroup, DynamicReportBuilder drb){
     
     for (Field fieldGroup : (List<Field>) listGroup) {
            try {
                GroupBuilder gb1 = new GroupBuilder();
                AbstractColumn column = ColumnBuilder.getInstance().setColumnProperty(fieldGroup.getAliasField(), fieldGroup.getDataType()).setTitle(fieldGroup.getTitle()).setWidth(85).build();
                ColumnsGroup g1 = gb1.setCriteriaColumn((PropertyColumn) column)
                        .addFooterVariable(column,ColumnsGroupVariableOperation.COUNT) 
                        .setGroupLayout(GroupLayout.DEFAULT)
                        .build();
                        
               
                drb.addGroup(g1);
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(ReportTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
