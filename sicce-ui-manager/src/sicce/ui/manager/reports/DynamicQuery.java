/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.reports;

import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import sicce.api.info.Field;
import sicce.api.info.interfaces.IFilter;

/**
 *
 * @author gish@c
 */
public class DynamicQuery {

    public static final String KEY_WHERE = "whereFields";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_FINISH_DATE = "finishDate";

    public DynamicQuery() {
    }

    /**
     * 
     * @param listSelected
     * @return
     */
    public String createQueryFields(List<Field> listSelected) {

        String totalFields = new String();
        for (Field fieldSelected : (List<Field>) listSelected) {
            if (fieldSelected.getDataType().equals(String.class.getName())) {
                String field = "COALESCE(" + fieldSelected.getTableName() + "." + fieldSelected.getDescriptionField() + " , 'No Asignado') " + fieldSelected.getAliasField() + ",";
                totalFields = totalFields.concat(field);
            } else {
                String field = "COALESCE(" + fieldSelected.getTableName() + "." + fieldSelected.getDescriptionField() + " , '0') " + fieldSelected.getAliasField() + ",";
                totalFields = totalFields.concat(field);
            }
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
        if (listGroup.isEmpty()) {
            return null;
        }

        for (Field fieldGroup : (List<Field>) listGroup) {
            String field = fieldGroup.getAliasField() + ",";
            totalFieldsGroup = totalFieldsGroup.concat(field);
        }
        String total = totalFieldsGroup.substring(0, totalFieldsGroup.length() - 1);

        return total;

    }

    public String getJoins() {
        String joins = "FROM measure " +
                "INNER JOIN location ON measure.ID_LOCATION = location.ID_LOCATION " +
                "INNER JOIN power_meter ON measure.ID_POWER_METER = power_meter.ID_POWER_METER " +
                "INNER JOIN location_type ON location.ID_LOCATION_TYPE = location_type.ID_LOCATION_TYPE " +
                "LEFT JOIN location_zone ON location.ID_LOCATION = location_zone.ID_LOCATION " +
                "LEFT JOIN zone ON location_zone.ID_ZONE = zone.ID_ZONE ";
        return joins;
    }

    public String createWhereClause(Map wizardData, DynamicReportBuilder drb) {
        List listFilters = (List) wizardData.get(KEY_WHERE);
        Date begin = (Date) wizardData.get(KEY_BEGIN_DATE);
        Date finish = (Date) wizardData.get(KEY_FINISH_DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate = format.format(begin);
        String finishDate = format.format(finish);
        String whereClause = new String();



        if (listFilters != null) {
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

            }
        }
        if (beginDate != null && finishDate != null) {
            String whereDate = " measure.date_measure" + " between  '" + beginDate + "' and  '" + finishDate + "' AND ";
            whereClause = whereClause.concat(whereDate);
        }

        String total = (whereClause != null ? (whereClause.substring(0, whereClause.length() - 4)) : whereClause);
        System.out.println("total :" + total);


        return total;

    }

    public String createClause(IFilter pfilter, String operator, String condition) {
        String whereClause = pfilter.getField().getTableName() + "." + pfilter.getField().getDescriptionField() + operator + "'" + pfilter.getValues() + "'" + condition;
        return whereClause;
    }
}
