package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;



public class LocationTemplate extends BaseDjReportTest {

    public DynamicReport buildReport(String title) throws Exception {

        Style detailStyle = new Style();
        Style headerStyle = new Style();

        Style titleStyle = new Style();
        Style subtitleStyle = new Style();
        Style amountStyle = new Style();
        amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15).setDefaultStyles(titleStyle, subtitleStyle, headerStyle, detailStyle).setColumnsPerPage(1);

       
        AbstractColumn columnDescription = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionLocation", String.class.getName())
                .setTitle("Descripcion") 
                .setWidth(85) 
                .build();													

        AbstractColumn columnDescriptionLtype = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionLtype", String.class.getName())
                .setTitle("Descripcion") 
                .setWidth(85) 
                .build();
     
        AbstractColumn columnIpAddress = ColumnBuilder.getInstance()
                .setColumnProperty("ipAddress", String.class.getName())
                .setTitle("Dirección IP")
                .setWidth(new Integer(85)) 
                .build();

        AbstractColumn columnSerial = ColumnBuilder.getInstance()
                .setColumnProperty("serial", String.class.getName())
                .setTitle("Serial")
                .setWidth(new Integer(100)) 
                .build();

        AbstractColumn columnDescriptionPM = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionPmeter", String.class.getName())
                .setTitle("Descripcion") 
                .setWidth(85) 
                .build();	
       
        AbstractColumn columnDescriptionZone = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionZone", String.class.getName())
                .setTitle("Zona Asignada") 
                .build();
        
        drb.addColumn(columnDescription);
        drb.addColumn(columnIpAddress);
        drb.addColumn(columnSerial);
        drb.addColumn(columnDescriptionPM);
        drb.addColumn(columnDescriptionLtype);
        drb.setUseFullPageWidth(true);

        drb.setQuery("select " +
                "l.description descriptionLocation, " +
                "lt.description descriptionLtype, " +
                "p.description descriptionPmeter, " +
                "z.description descriptionZone, " + 
                "p.ip_address ipAddress, " +
                "p.serial serial " +
                "from location l , power_meter p , location_type lt, location_zone lz, zone z " +
                "where l.POWER_METER_ID_POWER_METER  = p.ID_POWER_METER " +
                "and l.ID_LOCATION_TYPE = lt.ID_LOCATION_TYPE" +
                "and l.ID_LOCATION = lz.ID_LOCATION" +
                "and z.ID_ZONE =  lz.ID_ZONE", DJConstants.QUERY_LANGUAGE_SQL);
      
        //compilar el reporte.
        DynamicReport dr = drb.build();	

        return dr;
    }
    
 
            
           

   
}
