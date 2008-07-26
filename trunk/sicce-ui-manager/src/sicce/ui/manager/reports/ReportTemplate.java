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
    public List<AbstractColumn> lstColumns = new ArrayList();

    public DynamicReport buildReport(Map wizardData) throws Exception {

        ReportFeatures report = new ReportFeatures();
        DynamicQuery pQuery = new DynamicQuery();
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
        Style footerVariables = new Style();
        Style importeStyle = new Style();
        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        headerStyle.setFont(Font.VERDANA_SMALL_BOLD);
        headerStyle.setBorder(Border.NO_BORDER);
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.gray);
        headerStyle.setTextColor(Color.BLACK);
        headerStyle.setTransparency(Transparency.OPAQUE);
        headerStyle.getStreching();

        /* Definir el formato para la cabecera del reporte - headerStyle -*/
        footerVariables.setFont(Font.VERDANA_SMALL);
        footerVariables.setBorder(Border.NO_BORDER);
        footerVariables.setTextColor(Color.RED);
        footerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
        footerVariables.getStreching();


        /* Definir el formato para el título del reporte - titleStyle -*/
        titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));


        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
        importeStyle.setBlankWhenNull(true);
        importeStyle.setBorder(Border.NO_BORDER);
        importeStyle.setFont(Font.VERDANA_SMALL);
        importeStyle.getStreching();


        detailStyle.setHorizontalAlign(HorizontalAlign.LEFT);
        detailStyle.setBlankWhenNull(true);
        detailStyle.setBorder(Border.NO_BORDER);
        detailStyle.setFont(Font.VERDANA_SMALL);
        //detailStyle.getStreching();
        
        Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER);
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);

        
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle(title).setDetailHeight(15).setMargins(30, 20, 30, 15)
                .setDefaultStyles(headerStyle, titleStyle, detailStyle, importeStyle)
                .setTitleStyle(titleStyle)
                .setGrandTotalLegend("Totales :")
                .setOddRowBackgroundStyle(oddRowStyle)
                .setWhenResourceMissing(DJConstants.WHEN_RESOURCE_MISSING_TYPE_EMPTY)
                .setWhenNoDataNoPages();
                
        
                
                
        //String imagePath = resourceMap.getResourcesDir() + "/" + resourceMap.getString("small_ucsg");
        //drb.addImageBanner(imagePath, new Integer(197), new Integer(60), ImageBanner.ALIGN_RIGHT);
        report.CreateColumns(listSelected, drb, detailStyle, importeStyle, headerStyle);

        drb.setUseFullPageWidth(true);
        if (phorizontal) {
            drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
        }
        if (pvertical) {
            drb.setPageSizeAndOrientation(Page.Page_A4_Portrait());
        }
        String stFieldSelected = pQuery.createQueryFields(listSelected);
        String stFiltertmp = pQuery.createWhereClause(wizardData, drb);
        String stFilterWhere = (stFiltertmp != null ? " WHERE " + stFiltertmp : "");
        String stFieldGroup = null;

        if (listGroup != null) {
            stFieldGroup = pQuery.createQueryGroupby(listGroup);
            report.addGlobalVariables(listMeasure, drb, footerVariables, importeStyle, headerStyle);
            report.createGroup(wizardData, drb, detailStyle, importeStyle, headerStyle);
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
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT);
        drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, AutoText.PATTERN_DATE_DATE_TIME);


        DynamicReport dr = drb.build();

        return dr;
    }
}
