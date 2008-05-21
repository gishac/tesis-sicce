package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.util.Date;



public class MeasureTemplate extends BaseDjReportTest {

    public DynamicReport buildReport(String title) throws Exception {

        Style detailStyle = new Style();
        Style headerStyle = new Style();

        Style titleStyle = new Style();
        Style subtitleStyle = new Style();
        Style amountStyle = new Style();
        amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

        /**
         * Creates the DynamicReportBuilder and sets the basic options for
         * the report
         */
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15).setDefaultStyles(titleStyle, subtitleStyle, headerStyle, detailStyle).setColumnsPerPage(1);

        /**
         * Column definitions. We use a new ColumnBuilder instance for each
         * column, the ColumnBuilder.getInstance() method returns a new instance
         * of the builder
         */
        AbstractColumn columnValue = ColumnBuilder.getInstance() 
                .setColumnProperty("value", String.class.getName())
                .setTitle("Valor") 
                .setWidth(85) 
                .build();													

        //Create more columns
        AbstractColumn columnDateMeasure = ColumnBuilder.getInstance()
                .setColumnProperty("dateMeasure", Date.class.getName())
                .setTitle("Fecha Medición")
                .setWidth(new Integer(120)) 
                .build();

        // location
        AbstractColumn columnDescription = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionLocation", String.class.getName())
                .setTitle("Ubicación") 
                .setWidth(85) 
                .build();													

        AbstractColumn columnDescriptionLtype = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionLtype", String.class.getName())
                .setTitle("Tipo Dependencia") 
                .setWidth(85) 
                .build();
     
         AbstractColumn columnDescriptionZone = ColumnBuilder.getInstance() 
                .setColumnProperty("descriptionZone", String.class.getName())
                .setTitle("Zona Asignada") 
                .setWidth(85) 
                .build();
     
        
        //PowerMeter
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
       
        
   
        /**
         * We add the columns to the report (through the builder) in the order
         * we want them to appear
         */
        drb.addColumn(columnValue);
        drb.addColumn(columnDateMeasure);
       
        
        /**
         * add some more options to the report (through the builder)
         */
        drb.setUseFullPageWidth(true);	//we tell the report to use the full width of the page. this rezises
        //the columns width proportionally to meat the page width.

        drb.setQuery("select m.value, m.date_measure, l.description descriptionLocation," +
                " lt.description descriptionLType, z.description descriptionZone, p.ip_address, p.serial, " +
                "p.description  descriptionPmeter from measure m , location l, location_type lt," +
                " location_zone lz, zone z, power_meter p " +
                "where m.id_location = l.id_location  " +
                "and m.id_power_meter = p.id_power_meter " +
                "and l.id_location = lt.id_location_type " +
                "and l.id_location = lz.id_location " +
                "and z.id_zone = lz.id_zone ", DJConstants.QUERY_LANGUAGE_SQL);
        DynamicReport dr = drb.build();	//Finally build the report!

        return dr;
    }

   
}
