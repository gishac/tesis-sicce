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
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;
import sicce.api.util.ComponentUtil;

/**
 *
 * @author gish@c
 */
public class JTabExtended extends JPanel implements ITabbedWindow, IToolBarStateListener {

    private String title;
    private FocusListener focusEventHandler;
    protected ListSelectionListener selectionListener;
    private JTabbedPaneExtended parentPane;
    private boolean objectLoaded;
    private List<Component> controlsToClear;
    private List<Component> controlsToEnable;
    protected boolean cancelAction;

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
        return true;
    }

    public DialogResult Search() {
        return DialogResult.Cancel;
    }

    public boolean Delete() throws Exception {
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(false, getControlsToEnable());
        return false;
    }

    public void Edit() {
        objectLoaded = true;
        ComponentUtil.SetState(true, getControlsToEnable());
    }

    public void Back() {
        ComponentUtil.Clear(getControlsToClear());
        ComponentUtil.SetState(false, getControlsToEnable());
    }

    public boolean Update() throws Exception {
        return true;
    }

    public void ItemSelected(int selectedIndex) {
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
        switch (toolbarAction) {
            case New:
                New();
                break;
            case Edit:
                Edit();
                break;
            case Save:
                event.setCancelEvent(Save());
                break;
            case Delete:
                event.setCancelEvent(Delete());
                break;
            case Search:
                event.setSearchDialogResult(Search());
                break;
            case Back:
                Back();
                break;
            case RegistryLoaded:
                ItemSelected(event.getSelectedIndex());
                break;
        }
    }

    
}
