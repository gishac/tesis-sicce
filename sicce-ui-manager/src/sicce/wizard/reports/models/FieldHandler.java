/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.wizard.reports.models;

import java.io.Serializable;
import sicce.api.info.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Karu
 */
public class FieldHandler implements Serializable {

    private List<Field> listAvailableFields = new ArrayList();
    private List<Field> listSelectedFields = new ArrayList();
    private List<Field> listGroupFields = new ArrayList();
    private List<Field> lstMeasureFields = new ArrayList();

    public FieldHandler() {
        listAvailableFields = new ArrayList();
        listSelectedFields = new ArrayList();
        listGroupFields = new ArrayList();
        lstMeasureFields = new ArrayList();
    }

    public void setListAvailableFields(List<Field> listAvailableFields) {
        this.listAvailableFields = listAvailableFields;
    }

    public void setListSelectedFields(List<Field> listSelectedFields) {
        this.listSelectedFields = listSelectedFields;
    }

    public List<Field> getSelectedFields() {
        if (listSelectedFields == null) {
            listSelectedFields = new ArrayList();
        }

        return listSelectedFields;
    }

    public List<Field> getAvailableFields() {
        return listAvailableFields;
    }

    public List<Field> getListGroupFields() {
        return listGroupFields;
    }

    public void setListGroupFields(List<Field> listGroupFields) {
        this.listGroupFields = listGroupFields;
    }

    public List<Field> getLstMeasureFields() {
        return lstMeasureFields;
    }

    public void setLstMeasureFields(List<Field> lstMeasureFields) {
        this.lstMeasureFields = lstMeasureFields;
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

        lstfilterFields.add(new Field(1, 11, 3, "power_meter", "description", "descriptionPowerMeter", "Descripción/Medidor", String.class.getName(), 70));
        lstfilterFields.add(new Field(2, 12, 3, "power_meter", "ip_Address", "ipAddress", "Dirección IP", String.class.getName(), 30));
        lstfilterFields.add(new Field(3, 13, 3, "power_meter", "serial", "serial", "No. Serial", String.class.getName(), 70));
        lstfilterFields.add(new Field(4, 14, 2, "location", "description", "descriptionLocation", "Descripción/Ubicación", String.class.getName(), 70));
        lstfilterFields.add(new Field(5, 15, 2, "location_type", "description", "descriptionLocationType", "Descripción/Tipo Ubicación", String.class.getName(), 70));
        lstfilterFields.add(new Field(6, 16, 1, "zone", "description", "descriptionZone", "Descripción/Zona", String.class.getName(), 70));

        return lstfilterFields;


    }

    public List<Field> fillMeasureFields() {

        List<Field> lstMeasureFields = new ArrayList<Field>();

        lstMeasureFields.add(new Field(1, 17, 4, "measure", "instantaneous_current_phase_1", "ins_current_phase_1", "Intensidad Instantánea fase 1", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(2, 18, 4, "measure", "instantaneous_current_phase_2", "ins_current_phase_2", "Intensidad Instantánea fase 2", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(3, 19, 4, "measure", "instantaneous_current_phase_3", "ins_current_phase_3", "Intensidad Instantánea fase 3", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(4, 20, 4, "measure", "neutral_current", "neutral_current", "Intensidad del Neutro", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(5, 21, 4, "measure", "phase_to_phase_voltage_phase_1_to_2", "ptp_phase_1to2", "Tensión fase a fase/fase 1 a 2", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(6, 22, 4, "measure", "phase_to_phase_voltage_phase_2_to_3", "ptp_phase_2to3", "Tensión fase a fase/fase 2 a 3", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(7, 23, 4, "measure", "phase_to_phase_voltage_phase_3_to_1", "ptp_phase_3to1", "Tensión fase a fase/fase 3 a 1", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(8, 24, 4, "measure", "phase_to_neutral_voltage_phase_1", "ptn_phase_1", "Tensión fase a neutro, fase 1", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(9, 25, 4, "measure", "phase_to_neutral_voltage_phase_2", "ptn_phase_2", "Tensión fase a neutro, fase 2", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(10, 26, 4, "measure", "phase_to_neutral_voltage_phase_3", "ptn_phase_3", "Tensión fase a neutro, fase 3", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(11, 27, 4, "measure", "frequency", "frequency", "Frecuencia", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(12, 28, 4, "measure", "total_active_power", "total_active_power", "Potencia Activa Total", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(13, 29, 4, "measure", "total_reactive_power", "total_reactive_power", "Potencia Reactiva Total", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(14, 30, 4, "measure", "total_apparent_power", "total_apparent_power", "Potencia Aparente Total", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(15, 31, 4, "measure", "active_power_phase_1", "active_power_phase_1", "Potencia activa/fase 1", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(16, 32, 4, "measure", "active_power_phase_2", "active_power_phase_2", "Potencia activa/fase 2", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(17, 33, 4, "measure", "active_power_phase_3", "active_power_phase_3", "Potencia activa/fase 3", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(18, 34, 4, "measure", "reactive_power_phase_1", "reactive_power_phase_1", "Potencia Reactiva/fase 1", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(19, 35, 4, "measure", "reactive_power_phase_2", "reactive_power_phase_2", "Potencia Reactiva/fase 2", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(20, 36, 4, "measure", "reactive_power_phase_3", "reactive_power_phase_3", "Potencia Reactiva/fase 3", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(21, 37, 4, "measure", "apparent_power_phase_1", "apparent_power_phase_1", "Potencia aparente/fase 1", Double.class.getName(), 50));
        lstMeasureFields.add(new Field(22, 38, 4, "measure", "apparent_power_phase_2", "apparent_power_phase_2", "Potencia aparente/fase 2", Double.class.getName(), 50));

        return lstMeasureFields;
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

    public void addSelectedField(Field tmp) {
        getAvailableFields().remove(tmp);
        getSelectedFields().add(tmp);
    }

    public void removeSelectedField(Field tmp) {
        if (getAvailableFields().isEmpty()) {
             getSelectedFields().remove(tmp);
             return;
        }
        Field ftmp = (Field) getAvailableFields().get(0);
        if (ftmp.getIdTable() == tmp.getIdTable()) {
            getSelectedFields().remove(tmp);
            getAvailableFields().add(tmp);

        } else {
            JOptionPane.showMessageDialog(null, "El campo no pertenece al grupo selecionado");
        }

    }

    public void addGroupField(Field tmp) {

        getListGroupFields().add(tmp);
    }

    public void removeGroupField(Field tmp) {
        getListGroupFields().remove(tmp);
    }

    public boolean CompareList(Field fieldSelected, List<Field> groupList) {

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

    public List<Field> getMeasureSelected(List<Field> selectedList) {
        List<Field> resultList = new ArrayList<Field>();

        if (selectedList.isEmpty()) {
            return null;
        }
        resultList.addAll(selectedList);
        for (Field fieldSelected : (List<Field>) selectedList) {
            if (fieldSelected.getIdTable() != 4) {
                resultList.remove(fieldSelected);
            }

        }
        return resultList;

    }
}


