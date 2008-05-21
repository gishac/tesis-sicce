/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

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

        lstZone.add(new Field(1,1, 1, "idZone", "idZone", "Id. Zona", "Integer", 30));
        lstZone.add(new Field(2,2, 1, "description", "descriptionZone", "Descripción/Zona", "String", 70));

        return lstZone;


    }

    public List<Field> fillLocation() {

        List<Field> lstLocation = new ArrayList<Field>();

        lstLocation.add(new Field(1,3, 2, "idLocation", "idLocation", "Id. Ubicación", "Integer", 30));
        lstLocation.add(new Field(2,4, 2, "description", "descriptionLocation", "Descripción/Ubicación", "String", 70));
        lstLocation.add(new Field(3,5,2, "idLocationType", "idLocationType", "Id. Tipo Ubicación", "Integer", 30));
        lstLocation.add(new Field(4,6, 2, "description", "descriptionLocationType", "Descripción/Tipo Ubicación", "String", 70));

        return lstLocation;

    }

    public List<Field> fillPowerMeter() {

        List<Field> lstPowerMeter = new ArrayList<Field>();

        lstPowerMeter.add(new Field(1,7, 3, "idPowerMeter", "idPowerMeter", "Id. Medidor", "Integer", 30));
        lstPowerMeter.add(new Field(2,8,3, "description", "descriptionPowerMeter", "Descripción/Medidor", "String", 70));
        lstPowerMeter.add(new Field(3,9, 3, "ipAddress", "ipAddress", "Dirección IP", "Integer", 30));
        lstPowerMeter.add(new Field(4,10, 3, "serial", "serial", "No. Serial", "String", 70));

        return lstPowerMeter;


    }

    public Map fillModulesMap() {

        Map modules = new HashMap();

        modules.put(1, fillZone());
        modules.put(2, fillLocation());
        modules.put(3, fillPowerMeter());


        return modules;
    }

    public static List<Field> CompareLists(List<Field> availableList, List<Field> selectedList) {
        List<Field> resultList = new  ArrayList<Field>();

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
        getSelectedFields().remove(tmp);
    }
    
    public static void addGroupField(Field tmp) {
        
        getListGroupFields().add(tmp);
    }

    public static void removeGroupField(Field tmp) {
        getListGroupFields().remove(tmp);
    }
}

