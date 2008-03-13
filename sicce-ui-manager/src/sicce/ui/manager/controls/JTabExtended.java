/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.controls;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.ToolBarStateInfo;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;
import sicce.api.util.ComponentUtil;
import sicce.ui.manager.handlers.ToolBarHandler;

/**
 *
 * @author gish@c
 */
public class JTabExtended<T> extends JPanel implements ITabbedWindow, IToolBarStateListener {

    private String title;
    private FocusListener focusEventHandler;
    protected ListSelectionListener selectionListener;
    private JTabbedPaneExtended parentPane;
    private boolean objectLoaded;
    private List<Component> controlsToClear;
    private List<Component> controlsToEnable;
    protected boolean cancelAction;
    private boolean handleToolBarStates;
    private ToolBarStateInfo toolBarStateInfo;
    private ToolBarAction tabState;
    protected T currentObject;

    public T getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(T currentObject) {
        this.currentObject = currentObject;
    }

    
    public ToolBarStateInfo getToolBarStateInfo() {
        if(toolBarStateInfo == null)
            toolBarStateInfo = new ToolBarStateInfo();
        return toolBarStateInfo;
    }

    public void setToolBarStateInfo(ToolBarStateInfo toolBarStateInfo) {
        this.toolBarStateInfo = toolBarStateInfo;
    }
   

    /**
     * Devuelve el panel que contiene al tab
     * @return
     */
    public JTabbedPaneExtended getParentPane() {
        return parentPane;
    }

    public void setParentPane(JTabbedPaneExtended parentPane) {
        this.parentPane = parentPane;
    }

    public void setListSelectionListener(ListSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
        
    }
    
    public void RegisterToolBarStateInfo(){
        if(this.selectionListener != null){
            ((ToolBarHandler) selectionListener).setToolBarStateInfo(this.getToolBarStateInfo());
        }
    }
    
     public boolean getHandleToolBarStates() {
        return handleToolBarStates;
    }

    public void setHandleToolBarStates(boolean handleToolBarStates) {
        this.handleToolBarStates = handleToolBarStates;
    }
    

    public boolean IsObjectLoaded() {
        return objectLoaded;
    }

    /**
     * Lista de controles que van a ser reseteados cuando el formulario ingresa a modo nuevo
     * @return
     */
    public List<Component> getControlsToClear() {
        if (controlsToClear == null) {
            controlsToClear = new ArrayList<Component>();
        }
        return controlsToClear;
    }

    /**
     * Lista de controles que van a ser habilitados o deshabilitados cuando el formulario ingresa a modo de edicion
     * @return
     */
    public List<Component> getControlsToEnable() {
        if (controlsToEnable == null) {
            controlsToEnable = new ArrayList<Component>();
        }
        return controlsToEnable;
    }

    /**
     * Constructor
     */
    public JTabExtended() {
        super();
        this.handleToolBarStates = true;
        this.tabState = ToolBarAction.None;
        this.addFocusListener(focusEventHandler = new FocusListener() {

            public void focusGained(FocusEvent e) {
                parentPane.setCurrentTab((ITabbedWindow) e.getComponent());
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }

    public JTabExtended(String title) {
        this();
        setTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void New() {
        objectLoaded = false;
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(true, getControlsToEnable());
    }

    public boolean Save() throws Exception {
        tabState = ToolBarAction.Save;
        return true;
    }
    
    public void AfterSave(){
        tabState = ToolBarAction.RegistryLoaded;
        ComponentUtil.SetState(false, this.getControlsToEnable());
    }

    public DialogResult Search() {
        return DialogResult.Cancel;
    }

    public boolean Delete() throws Exception {
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(false, getControlsToEnable());
        objectLoaded = false;
        currentObject = null;
        return false;
    }

    public void Edit() {
        objectLoaded = true;
        ComponentUtil.SetState(true, getControlsToEnable());
    }

    public void Back() {
        Clear();
    }

    public boolean Update() throws Exception {
        return true;
    }

    public void ItemSelected(int selectedIndex) {
        objectLoaded = true;
        ComponentUtil.SetState(false, getControlsToEnable());
    }
    
    public void RegisterSelectionListener() {
    }

    public void SetUIElements(){
        
    }
    
    public void FillGrid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Indica si el tab es el que se encuentra activo
     * @return
     */
    public boolean IsActive() {
        if (getParentPane() == null) {
            return false;
        }
        return getParentPane().getCurrentTab().equals(this);
    }

    /**
     * Evento generado cuando cambia el estado del toolbar
     * @param event
     */
    public void ToolBarStateChanged(ToolBarEventObject event) throws Exception {
        ToolBarAction toolbarAction = event.getToolBarState();
        event.setHandleToolBarStates(getHandleToolBarStates());
        switch (toolbarAction) {
            case New:
                New();
                tabState = ToolBarAction.New;
                break;
            case Edit:
                Edit();
                tabState = ToolBarAction.Edit;
                break;
            case Save:                
                event.setCancelEvent(Save());
                if(!event.getCancelEvent()){
                    AfterSave();                    
                }                
                break;
            case Delete:
                event.setCancelEvent(Delete());
                tabState = ToolBarAction.None;
                break;
            case Search:
                event.setSearchDialogResult(Search());
                if(event.getSearchDialogResult() == DialogResult.Ok){
                    ComponentUtil.SetState(false, getControlsToEnable());
                    tabState = ToolBarAction.RegistryLoaded;
                }
                break;
            case Back:
                Back();
                tabState = ToolBarAction.None;
                break;
            case RegistryLoaded:
                if(tabState != ToolBarAction.New)
                    ItemSelected(event.getSelectedIndex());
                tabState = ToolBarAction.RegistryLoaded;
                break;
        }
    }

    public ToolBarAction getTabState(){
        return this.tabState;
    }
    
    /**
     * 
     */
    public void SetDefaultState(){
        tabState = ToolBarAction.None;
        ComponentUtil.SetState(false, getControlsToEnable());
        ((ToolBarHandler) selectionListener).SetDefaultState();
    }
    
    public void RefreshToolBarState(){
        //Reemplaza los estado de edicion por estados neutrales
        //ya que los estados de edicion no se mantienen
        if(tabState == ToolBarAction.Edit)
            tabState = ToolBarAction.RegistryLoaded;
        if(tabState == ToolBarAction.New)
            tabState = ToolBarAction.None;
        SetUIElements();
        ComponentUtil.SetState(false, getControlsToEnable());
        ((ToolBarHandler) selectionListener).RefreshState(tabState);
    }
    
    public void Clear(){
        tabState = ToolBarAction.None;
        this.currentObject = null;
        objectLoaded = false;
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(false, getControlsToEnable());
    }
    
    public void Close(){
        Clear();        
        FillGrid();
        RefreshToolBarState();
    }
    
    public void CancelSave(){
        
    }
    
    public boolean CheckFields()
    {
     return true;
    }

    public void ItemSelectionCanceled(int previousIndexSelected){
        
    }

    
    
}
