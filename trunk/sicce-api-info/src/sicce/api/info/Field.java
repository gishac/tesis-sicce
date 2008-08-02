/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

import java.io.Serializable;

/**
 *
 * @author Karu
 */
public class Field implements Serializable {

    private Integer idField;
    private int order;
    private Integer idTable;
    private String tableName;
    private String descriptionField;
    private String aliasField;
    private String title;
    private String dataType;
    private Integer size;

    public Field(Integer idField, int order, Integer idTable, String tableName,String descriptionField, String aliasField, String title, String dataType, Integer size) {
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

   public Field(){}

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
      public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

 
    
    public String getAliasField() {
        return aliasField;
    }

    public void setAliasField(String aliasField) {
        this.aliasField = aliasField;
    }

    public String getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(String descriptionField) {
        this.descriptionField = descriptionField;
    }

    public Integer getIdField() {
        return idField;
    }

    public void setIdField(Integer idField) {
        this.idField = idField;
    }

    public Integer getIdTable() {
        return idTable;
    }

    public void setIdTable(Integer idTable) {
        this.idTable = idTable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
        public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
   
}
