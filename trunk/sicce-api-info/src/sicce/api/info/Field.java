/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info;

import java.io.Serializable;

/**
 *
 * @author Karu
 * Representacion de un campo de una tabla
 */
public class Field implements Serializable {

    /**
     * Identificador del campo
     */
    private Integer idField;
    /**
     * Orden del campo en el reporte
     */
    private int order;
    /**
     * Identificador de la tabla a la que pertenece el campo
     */
    private Integer idTable;
    /**
     * Nombre de la tabla a la que pertenece el campo
     */
    private String tableName;
    /**
     * Nombre del campo en la tabla
     */
    private String descriptionField;
    /**
     * Alias del campo
     */
    private String aliasField;
    /**
     * Titulo a ser mostrado en la cabecera del reporte
     */
    private String title;
    /**
     * Tipo de dato del campo en la tabla
     */
    private String dataType;
    /**
     * Tamaño de la cabecera del campo en el reporte
     */
    private Integer size;

    /**
     * Constructor
     * @param idField Identificador del campo
     * @param order Orden del campo en el reporte
     * @param idTable Identificador de la tabla a la que pertenece el campo
     * @param tableName Nombre de la tabla a la que pertenece el campo
     * @param descriptionField Nombre del campo en la tabla
     * @param aliasField Alias del campo
     * @param title Titulo a ser mostrado en la cabecera del reporte
     * @param dataType Tipo de dato del campo en la tabla
     * @param size Tamaño de la cabecera del campo en el reporte
     */
    public Field(Integer idField, int order, Integer idTable, String tableName, String descriptionField, String aliasField, String title, String dataType, Integer size) {
        this.idField = idField;
        this.order = order;
        this.idTable = idTable;
        this.tableName = tableName;
        this.descriptionField = descriptionField;
        this.aliasField = aliasField;
        this.title = title;
        this.dataType = dataType;
        this.size = size;

    }

    /**
     * Constructor
     */
    public Field() {
    }

    /**
     * Devuelve el nombre de la tabla a la que pertenece el campo
     * @return Nombre de la tabla a la que pertenece el campo
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Establece el nombre de la tabla a la que pertenece el campo
     * @param tableName Nombre de la tabla a la que pertenece el campo
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Devuelve el tipo de dato del campo en la tabla
     * @return Tipo de dato del campo en la tabla
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Establece el tipo de dato del campo en la tabla
     * @param dataType Tipo de dato del campo en la tabla
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Devuelve el tamaño de la cabecera del campo en el reporte
     * @return Tamaño de la cabecera del campo en el reporte
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la cabecera del campo en el reporte
     * @param size Tamaño de la cabecera del campo en el reporte 
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Devuelve el alias del campo en la tabla
     * @return Alias del campo en la tabla
     */
    public String getAliasField() {
        return aliasField;
    }

    /**
     * Establece el alias del campo en la tabla
     * @param aliasField Alias del campo en la tabla
     */
    public void setAliasField(String aliasField) {
        this.aliasField = aliasField;
    }

    /**
     * Devuelve la descripcion del campo en la tabla
     * @return Descripcion del campo en la tabla
     */
    public String getDescriptionField() {
        return descriptionField;
    }

    /**
     * Establece la descripcion del campo en la tabla
     * @param descriptionField Descripcion del campo en la tabla
     */
    public void setDescriptionField(String descriptionField) {
        this.descriptionField = descriptionField;
    }

    /**
     * Devuelve el identificador del campo
     * @return Identificador del campo
     */
    public Integer getIdField() {
        return idField;
    }

    /**
     * Establece el identificador del campo
     * @param idField Identificador del campo
     */
    public void setIdField(Integer idField) {
        this.idField = idField;
    }

    /**
     * Devuelve el identificador de la tabla a la que pertenece el campo
     * @return Identificador de la tabla a la que pertenece el campo
     */
    public Integer getIdTable() {
        return idTable;
    }

    /**
     * Establece el identificador de la tabla a la que pertenece el campo
     * @param idTable identificador de la tabla a la que pertenece el campo
     */
    public void setIdTable(Integer idTable) {
        this.idTable = idTable;
    }

    /**
     * Devuelve el titulo del campo en la cabecera del reporte
     * @return Titulo del campo en la cabecera del reporte
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el titulo del campo en el reporte
     * @param title Titulo del campo en el reporte
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Devuelve el orden del campo en el reporte
     * @return
     */
    public int getOrder() {
        return order;
    }

    /**
     * Establece el orden del campo en el reporte
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }
}
