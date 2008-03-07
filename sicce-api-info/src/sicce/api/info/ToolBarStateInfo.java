/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info;

/**
 * Clase para configurar los botones que pueden ser habilitados por el toolbar durante los 
 * diferentes estados
 * @author gish@c
 */
public class ToolBarStateInfo {

    private boolean enableNew;
    private boolean enableSave;
    private boolean enableEdit;
    private boolean enableDelete;
    private boolean enableSearch;
    private boolean enableBack;
    private boolean alwaysEnabledNew;
    private boolean alwaysEnabledEdit;
    private boolean alwaysEnabledSave;
    private boolean alwaysEnabledDelete;
    private boolean alwaysEnabledSearch;
    private boolean alwaysEnabledBack;

    public boolean isAlwaysEnabledBack() {
        return alwaysEnabledBack;
    }

    public void setAlwaysEnabledBack(boolean alwaysEnabledBack) {
        this.alwaysEnabledBack = alwaysEnabledBack;
    }

    public boolean isAlwaysEnabledDelete() {
        return alwaysEnabledDelete;
    }

    public void setAlwaysEnabledDelete(boolean alwaysEnabledDelete) {
        this.alwaysEnabledDelete = alwaysEnabledDelete;
    }

    public boolean isAlwaysEnabledEdit() {
        return alwaysEnabledEdit;
    }

    public void setAlwaysEnabledEdit(boolean alwaysEnabledEdit) {
        this.alwaysEnabledEdit = alwaysEnabledEdit;
    }

    public boolean isAlwaysEnabledNew() {
        return alwaysEnabledNew;
    }

    public void setAlwaysEnabledNew(boolean alwaysEnabledNew) {
        this.alwaysEnabledNew = alwaysEnabledNew;
    }

    public boolean isAlwaysEnabledSave() {
        return alwaysEnabledSave;
    }

    public void setAlwaysEnabledSave(boolean alwaysEnabledSave) {
        this.alwaysEnabledSave = alwaysEnabledSave;
    }

    public boolean isAlwaysEnabledSearch() {
        return alwaysEnabledSearch;
    }

    public void setAlwaysEnabledSearch(boolean alwaysEnabledSearch) {
        this.alwaysEnabledSearch = alwaysEnabledSearch;
    }

    public boolean getEnableBack() {
        return enableBack;
    }

    public void setEnableBack(boolean enableBack) {
        this.enableBack = enableBack;
    }

    public boolean getEnableDelete() {
        return enableDelete;
    }

    public void setEnableDelete(boolean enableDelete) {
        this.enableDelete = enableDelete;
    }

    public boolean getEnableEdit() {
        return enableEdit;
    }

    public void setEnableEdit(boolean enableEdit) {
        this.enableEdit = enableEdit;
    }

    public boolean getEnableNew() {
        return enableNew;
    }

    public void setEnableNew(boolean enableNew) {
        this.enableNew = enableNew;
    }

    public boolean getEnableSave() {
        return enableSave;
    }

    public void setEnableSave(boolean enableSave) {
        this.enableSave = enableSave;
    }

    public boolean getEnableSearch() {
        return enableSearch;
    }

    public void setEnableSearch(boolean enableSearch) {
        this.enableSearch = enableSearch;
    }

    public ToolBarStateInfo(boolean enableNew, boolean enableSave, boolean enableEdit, boolean enableDelete, boolean enableSearch, boolean enableBack) {
        this.enableNew = enableNew;
        this.enableSave = enableSave;
        this.enableEdit = enableEdit;
        this.enableDelete = enableDelete;
        this.enableSearch = enableSearch;
        this.enableBack = enableBack;
    }

    public ToolBarStateInfo() {
        this(true,true,true,true,true,true);
        this.alwaysEnabledBack = false;
        this.alwaysEnabledDelete = false;
        this.alwaysEnabledEdit = false;
        this.alwaysEnabledNew = false;
        this.alwaysEnabledSave = false;
        this.alwaysEnabledSearch = false;
        
    }
    
    
    
}
