package sicce.ui.manager.reports;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import sicce.api.info.interfaces.IUserSicce;
import sicce.wizard.reports.models.FieldHandler;

public class ReportTemplate extends GenerateDynamicReport {

    public static final String KEY_FIELD = "KeyField";
    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_NAME = "name";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";
    public static final String KEY_ORT_VERTICAL = "ortVertical";
    public static final String KEY_ORT_HORIZONTAL = "ortHorizontal";
    public List<AbstractColumn> lstColumns = new ArrayList();

    public DynamicReport buildReport(Map wizardData, IUserSicce userSicce) throws Exception {

        ReportFeatures report = new ReportFeatures();
        DynamicQuery pQuery = new DynamicQuery();
        Map param = new HashMap();
        FieldHandler pFieldHandler = (FieldHandler) wizardData.get(KEY_FIELD);
        List listSelected = pFieldHandler.getSelectedFields();
        List listGroup = pFieldHandler.getListGroupFields();
        List listMeasure = pFieldHandler.getLstMeasureFields();
        String title = (String) wizardData.get(KEY_NAME);
        Boolean pvertical = (Boolean) wizardData.get(KEY_ORT_VERTICAL);
        Boolean phorizontal = (Boolean) wizardData.get(KEY_ORT_HORIZONTAL);
        
        /*Instanciamos los estilos para cada sección*/
        Style detStyle = new Style();
        Style headerStyle = new Style();
        Style titleStyle = new Style();
        Style footerVariables = new Style();
        Style oddRowStyle = new Style();
        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        headerStyle.setFont(Font.VERDANA_SMALL_BOLD);
        headerStyle.setBorder(Border.NO_BORDER);
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
        headerStyle.setTextColor(Color.BLACK);
        headerStyle.setTransparency(Transparency.OPAQUE);
       // headerStyle.getStreching();

        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        footerVariables.setFont(Font.VERDANA_SMALL);
        footerVariables.setBorder(Border.NO_BORDER);
        footerVariables.setTextColor(Color.BLUE);
        footerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
       // footerVariables.getStreching();


        /* Definir el formato para el título del reporte - titleStyle -*/
        titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));

        detStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        detStyle.setBlankWhenNull(true);
        detStyle.setBorder(Border.NO_BORDER);
        detStyle.setFont(Font.VERDANA_SMALL);
        
        oddRowStyle.setBorder(Border.NO_BORDER);
	oddRowStyle.setBackgroundColor(Color.lightGray);
	oddRowStyle.setTransparency(Transparency.TRANSPARENT);
      
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setMargins(30, 20, 30, 15)
                .setDefaultStyles(headerStyle, titleStyle, detStyle, footerVariables)
                .setTitleStyle(titleStyle)
                .setGrandTotalLegend("Totales :")
                .setPrintBackgroundOnOddRows(true)
                .setOddRowBackgroundStyle(oddRowStyle)
                .setWhenResourceMissing(DJConstants.WHEN_RESOURCE_MISSING_TYPE_EMPTY)
                .setWhenNoDataNoPages();

        //.setOddRowBackgroundStyle(oddRowStyle)
                
        //String imagePath = resourceMap.getResourcesDir() + "/" + resourceMap.getString("small_ucsg");
        //drb.addImageBanner(imagePath, new Integer(197), new Integer(60), ImageBanner.ALIGN_RIGHT);
        List<AbstractColumn> columns = report.CreateColumns(listSelected, drb, detStyle, headerStyle);

        drb.setUseFullPageWidth(true);
        if (phorizontal) {
            drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
        }
        if (pvertical) {
            drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());
        }
        String stFieldSelected = pQuery.createQueryFields(listSelected);
        String stFiltertmp = pQuery.createWhereClause(wizardData, drb);
        String stFilterWhere = (stFiltertmp != null ? " WHERE user_power_meter.ID_USER_SICCE = " + userSicce.getIdUserSicce() + " AND "+ stFiltertmp : "");
        String stFieldGroup = null;

        if (listGroup != null && !listGroup.isEmpty()) {
            drb.setPrintColumnNames(false);
            stFieldGroup = pQuery.createQueryGroupby(listGroup); 
            report.createGroup(columns,wizardData, drb);
        }
        else{
            report.addGlobalVariables(columns, drb, footerVariables, detStyle, headerStyle);
        }

        String joins = pQuery.getJoins();

        String query = null;
        if (stFilterWhere != null) {
            query = "SELECT" + " " + stFieldSelected + " " + joins + " " + stFilterWhere;
        } else {
            query = "SELECT" + " " + stFieldSelected + " " + joins;
        }
        if (!listGroup.isEmpty()) {
            query = query + " GROUP BY " + stFieldGroup;
        }


        System.out.println("QUERY :" + query);
        drb.setQuery(query, DJConstants.QUERY_LANGUAGE_SQL);
        drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, AutoText.PATTERN_DATE_DATE_TIME);
        drb.setReportLocale(new Locale("es","AR"));


        DynamicReport dr = drb.build();

        return dr;
    }
}
