/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.handlers;

import sicce.api.info.Field;
import java.util.ArrayList;
import java.util.List;
import sicce.ui.manager.controls.JOptionPaneExtended;

/**
 *
 * @author Karu
 */
public class FieldHandler {

    private static List<Field> listAvailableFields = new ArrayList();
    private static List<Field> listSelectedFields = new ArrayList();
    private static List<Field> listGroupFields = new ArrayList();

    public FieldHandler() {
        listAvailableFields = new ArrayList();
        listSelectedFields = new ArrayList();
        listGroupFields = new ArrayList();
    }

    public static void setListAvailableFields(List<Field> listAvailableFields) {
        FieldHandler.listAvailableFields = listAvailableFields;
    }

    public static void setListSelectedFields(List<Field> listSelectedFields) {
        FieldHandler.listSelectedFields = listSelectedFields;
    }

    public static List<Field> getSelectedFields() {
        if (listSelectedFields == null) {
            listSelectedFields = new ArrayList();
        }

        return listSelectedFields;
    }

    public static List<Field> getAvailableFields() {
        return listAvailableFields;
    }

    public static List<Field> getListGroupFields() {
        return listGroupFields;
    }

    public static void setListGroupFields(List<Field> listGroupFields) {
        FieldHandler.listGroupFields = listGroupFields;
    }

    public List<Field> fillZone() {

        List<Field> lstZone = new ArrayList<Field>();

        lstZone.add(new Field(1, 1, 1, "zone", "id_Zone", "idZone", "Id. Zona", Integer.class.getName(), 30));
        lstZone.add(new Field(2, 2, 1, "zone", "description", "descriptionZone", "Descripción/Zona", String.class.getName(), 70));

        return lstZone;


    }

    public List<Field> fillLocation() {

        List<Field> lstLocation = new ArrayList<Field>();

        lstLocation.add(new Field(1, 3, 2, "location", "id_Location", "idLocation", "Id. Ubicación", Integer.class.getName(), 30));
        lstLocation.add(new Field(2, 4, 2, "location", "description", "descriptionLocation", "Descripción/Ubicación", String.class.getName(), 70));
        lstLocation.add(new Field(3, 5, 2, "location_type", "id_Location_Type", "idLocationType", "Id. Tipo Ubicación", Integer.class.getName(), 30));
        lstLocation.add(new Field(4, 6, 2, "location_type", "description", "descriptionLocationType", "Descripción/Tipo Ubicación", String.class.getName(), 70));

        return lstLocation;

    }

    public List<Field> fillPowerMeter() {

        List<Field> lstPowerMeter = new ArrayList<Field>();

        lstPowerMeter.add(new Field(1, 7, 3, "power_meter", "id_Power_Meter", "idPowerMeter", "Id. Medidor", Integer.class.getName(), 30));
        lstPowerMeter.add(new Field(2, 8, 3, "power_meter", "description", "descriptionPowerMeter", "Descripción/Medidor", String.class.getName(), 70));
        lstPowerMeter.add(new Field(3, 9, 3, "power_meter", "ip_Address", "ipAddress", "Dirección IP", String.class.getName(), 30));
        lstPowerMeter.add(new Field(4, 10, 3, "power_meter", "serial", "serial", "No. Serial", String.class.getName(), 70));

        return lstPowerMeter;


    }

     public List<Field> fillfilterFields() {

        List<Field> lstfilterFields = new ArrayList<Field>();

        lstfilterFields.add(new Field(1,11, 3, "power_meter", "description", "descriptionPowerMeter", "Descripción/Medidor", String.class.getName(), 70));
        lstfilterFields.add(new Field(2,12, 3, "power_meter", "ip_Address", "ipAddress", "Dirección IP", String.class.getName(), 30));
        lstfilterFields.add(new Field(3,13, 3, "power_meter", "serial", "serial", "No. Serial", String.class.getName(), 70));
        lstfilterFields.add(new Field(4,14, 2, "location", "description", "descriptionLocation", "Descripción/Ubicación", String.class.getName(), 70));
        lstfilterFields.add(new Field(5,15, 2, "location_type", "description", "descriptionLocationType", "Descripción/Tipo Ubicación", String.class.getName(), 70));
        lstfilterFields.add(new Field(6,16, 1, "zone", "description", "descriptionZone", "Descripción/Zona", String.class.getName(), 70));

        return lstfilterFields;


    }

    
    
    public static List<Field> CompareLists(List<Field> availableList, List<Field> selectedList) {
        List<Field> resultList = new ArrayList<Field>();

        if (selectedList.isEmpty()) {
            return availableList;
        }
        resultList.addAll(availableList);
        for (Field fieldSelected : (List<Field>) selectedList) {
            for (Field fieldAvailable : (List<Field>) availableList) {
                if (fieldAvailable.getIdField() == fieldSelected.getIdField() && fieldAvailable.getIdTable() == fieldSelected.getIdTable()) {
                    resultList.remove(fieldAvailable);
                }
            }
        }
        return resultList;

    }

    public static void addSelectedField(Field tmp) {
        getAvailableFields().remove(tmp);
        getSelectedFields().add(tmp);
    }

    public static void removeSelectedField(Field tmp) {
            Field ftmp = (Field) getAvailableFields().get(0);
            if (ftmp.getIdTable() == tmp.getIdTable()) {
                getSelectedFields().remove(tmp);
                
            } else {
                JOptionPaneExtended.showMessageDialog(null, "El campo no pertenece al grupo selecionado");
            }

    }

    public static void addGroupField(Field tmp) {

        getListGroupFields().add(tmp);
    }

    public static void removeGroupField(Field tmp) {
        getListGroupFields().remove(tmp);
    }

    public static boolean CompareList(Field fieldSelected, List<Field> groupList) {

        boolean result = false;

        if (groupList == null) {
            return result;
        }

        for (Field fieldGroup : (List<Field>) groupList) {
            if (fieldSelected.getIdField() == fieldGroup.getIdField() && fieldSelected.getIdTable() == fieldGroup.getIdTable()) {
                result = true;
                return result;
            }
        }

        return result;

    }
}


