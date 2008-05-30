package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.util.List;



public class UserTemplate extends BaseDjReportTest {

    public DynamicReport buildReport(String title, List<Field> listSelected,  List<Field> listGroup) throws Exception {

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
        AbstractColumn columnName = ColumnBuilder.getInstance() 
                .setColumnProperty("name", String.class.getName())
                .setTitle("Nombre") 
                .setWidth(85) 
                .build();													

        //Create more columns
        AbstractColumn columnLastName = ColumnBuilder.getInstance()
                .setColumnProperty("lastname", String.class.getName())
                .setTitle("Apellidos")
                .setWidth(new Integer(120)) 
                .build();

        AbstractColumn columnCodeUcsg = ColumnBuilder.getInstance()
                .setColumnProperty("codeUcsg", Long.class.getName())
                .setTitle("Código")
                .setWidth(new Integer(100)) 
                .build();

        AbstractColumn columnUsernameSicce = ColumnBuilder.getInstance()
                .setColumnProperty("usernameSicce", Long.class.getName())
                .setTitle("Usuario")
                .setWidth(new Integer(100)) 
                .build();

     AbstractColumn columnDescriptionRole = ColumnBuilder.getInstance()
                .setColumnProperty("descriptionRole", Long.class.getName())
                .setTitle("Role Asignado")
                .setWidth(new Integer(100)) 
                .build();

        
        /**
         * We add the columns to the report (through the builder) in the order
         * we want them to appear
         */
        drb.addColumn(columnName);
        drb.addColumn(columnLastName);
        drb.addColumn(columnCodeUcsg);
        drb.addColumn(columnUsernameSicce);
       
        
        /**
         * add some more options to the report (through the builder)
         */
        drb.setUseFullPageWidth(true);	//we tell the report to use the full width of the page. this rezises
        //the columns width proportionally to meat the page width.

        drb.setQuery("select u.name, u.lastname, u.code_ucsg," +
                " u.username_sicce, r.description descriptionRole " +
                "from user_sicce u, role r " +
                "where u.id_role = r.id_role", DJConstants.QUERY_LANGUAGE_SQL);
        DynamicReport dr = drb.build();	//Finally build the report!

        return dr;
    }

   
}
