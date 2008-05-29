package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.util.List;



public class PowerMeterTemplate extends BaseDjReportTest {

    public DynamicReport buildReport(String title, List<Field> listSelected) throws Exception {

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
        AbstractColumn columnDescription = ColumnBuilder.getInstance() 
                .setColumnProperty("description", String.class.getName())
                .setTitle("Descripcion") 
                .setWidth(85) 
                .build();													

        //Create more columns
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

        /**
         * We add the columns to the report (through the builder) in the order
         * we want them to appear
         */
        drb.addColumn(columnDescription);
        drb.addColumn(columnIpAddress);
        drb.addColumn(columnSerial);

        
        /**
         * add some more options to the report (through the builder)
         */
        drb.setUseFullPageWidth(true);	//we tell the report to use the full width of the page. this rezises
        //the columns width proportionally to meat the page width.

        drb.setQuery("select * from power_meter", DJConstants.QUERY_LANGUAGE_SQL);
        DynamicReport dr = drb.build();	//Finally build the report!

        return dr;
    }

   
}
