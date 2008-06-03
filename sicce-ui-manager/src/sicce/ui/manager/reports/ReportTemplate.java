package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.util.List;
import sicce.ui.manager.reports.Field;

public class ReportTemplate extends BaseDjReportTest {

    public DynamicReport buildReport(String title, List<Field> listSelected, List<Field> listGroup) throws Exception {

        Style detailStyle = new Style();
        Style headerStyle = new Style();

        Style titleStyle = new Style();
        Style subtitleStyle = new Style();
        Style amountStyle = new Style();
        amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15).setDefaultStyles(titleStyle, subtitleStyle, headerStyle, detailStyle).setColumnsPerPage(1);

        CreateColumns(listSelected, drb);

        drb.setUseFullPageWidth(true);
        String stFieldSelected = createQueryFields(listSelected);
        String stFieldGroup = createQueryGroupby(listGroup);
//        String joins = "FROM location, power_meter, location_type, location_zone, zone, measure " +
//                "WHERE location.POWER_METER_ID_POWER_METER  = power_meter.ID_POWER_METER " +
//                "AND location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE " +
//                "AND location.ID_LOCATION = location_zone.ID_LOCATION " +
//                "AND location_zone.ID_ZONE = zone.ID_ZONE " +
//                "AND location.ID_LOCATION = measure.ID_LOCATION " +
//                "AND power_meter.ID_POWER_METER = measure.ID_POWER_METER ";
        String joins = "FROM location, power_meter, location_type, location_zone, zone " +
                "WHERE location.POWER_METER_ID_POWER_METER  = power_meter.ID_POWER_METER " +
                "AND location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE " +
                "AND location.ID_LOCATION = location_zone.ID_LOCATION " +
                "AND location_zone.ID_ZONE = zone.ID_ZONE " ;
        String query = "SELECT" + " " + stFieldSelected + " " +  joins + " " + "GROUP BY " +  stFieldGroup;
        System.out.println("QUERY :" + query);

        drb.setQuery(query, DJConstants.QUERY_LANGUAGE_SQL);

        //compilar el reporte.
        DynamicReport dr = drb.build();

        return dr;
    }

    public void CreateColumns(List<Field> listSelected, DynamicReportBuilder drb) throws ColumnBuilderException {

        for (Field fieldSelected : (List<Field>) listSelected) {

            AbstractColumn column = ColumnBuilder.getInstance().setColumnProperty(fieldSelected.getAliasField(), fieldSelected.getDataType()).setTitle(fieldSelected.getTitle()).setWidth(85).build();
            drb.addColumn(column);

        }

    }

    public String CreateQuery() {

        return null;
    }

    public String createQueryFields(List<Field> listSelected) {

        String totalFields = new String();
        for (Field fieldSelected : (List<Field>) listSelected) {
            String field =  fieldSelected.getTableName() + "." + fieldSelected.getDescriptionField() + " " + fieldSelected.getAliasField() + ",";
            totalFields = totalFields.concat(field);
        }
        
       String total =  totalFields.substring(0,totalFields.length() - 1);

        return total;
    }

    public String createQueryGroupby(List<Field> listGroup) {

        String totalFieldsGroup = new String();
        for (Field fieldGroup : (List<Field>) listGroup) {
            String field =  fieldGroup.getAliasField() + ",";
            totalFieldsGroup = totalFieldsGroup.concat(field);
        }
          String total =   totalFieldsGroup.substring(0, totalFieldsGroup.length() - 1);

        return total;
    }
}
