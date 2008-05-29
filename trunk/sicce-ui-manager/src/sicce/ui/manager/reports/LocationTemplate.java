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


public class LocationTemplate extends BaseDjReportTest {

    public DynamicReport buildReport(String title, List<Field> listSelected) throws Exception {

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
        String query = createQueryFields(listSelected);
        System.out.println("QUERY" + query);
        drb.setQuery("select " , DJConstants.QUERY_LANGUAGE_SQL);
      
        //compilar el reporte.
        DynamicReport dr = drb.build();	

        return dr;
    }
    
 
            
       public void CreateColumns (List<Field> listSelected,DynamicReportBuilder drb) throws ColumnBuilderException{    
       
       for (Field fieldSelected : (List<Field>) listSelected) {
           
           AbstractColumn column  = ColumnBuilder.getInstance()
                .setColumnProperty(fieldSelected.getAliasField(),fieldSelected.getDataType())
                .setTitle(fieldSelected.getTitle()) 
                .setWidth(85) 
                .build();	
           drb.addColumn(column);
           
       }

       }					

       
      public  String CreateQuery (){
       String joins = "from location, power_meter, location_type, location_zone, zone, measure " +
                "where location.POWER_METER_ID_POWER_METER  = power_meter.ID_POWER_METER " +
                "and location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE" +
                "and location.ID_LOCATION = location_zone.ID_LOCATION" +
                "and location_zone.ID_ZONE = zone.ID_ZONE" +
                "and location.ID_LOCATION = measure.ID_LOCATION" +
                "and power_meter.ID_POWER_METER = measure.ID_POWER_METER";
       return null;
      }
      
      
      
      public String createQueryFields (List<Field> listSelected){
      
          String totalFields = new String();
          for (Field fieldSelected : (List<Field>) listSelected) {
           String field = fieldSelected.getTableName() + "." + fieldSelected.getDescriptionField() + " " + fieldSelected.getAliasField() + ",";
           totalFields = totalFields.concat(field);
       }
          
       
       return totalFields;
       }
       
   
}
