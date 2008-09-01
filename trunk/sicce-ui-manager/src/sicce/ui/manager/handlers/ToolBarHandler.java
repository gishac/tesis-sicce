/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.ui.manager.handlers;

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.ToolBarStateInfo;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;
import sicce.ui.manager.controls.JTabbedPaneExtended;

/**
 * Administra todos los eventos generados por el toolbar y notifica los estados
 * generados por las acciones del toolbar
 * @author gish@c
 */
public class ToolBarHandler implements ListSelectionListener {

    /**
     * Toolbar a ser administrado
     */
    private JToolBar toolBar;
    
    /**
     * Objeto administrador de tabs
     * @see JTabbedPaneExtended
     */
    private JTabbedPaneExtended tabManager;
    
    /**
     * Objeto con informacion de los estados del toolbar
     */
    private ToolBarStateInfo toolBarStateInfo;
    
    /**
     * Indica si se ejecuto un proceso interno
     */
    private boolean internalEvent;

    /**
     * Devuelve el objeto con informacion de los estados del toolbar
     * @return Objeto con informacion de los estados del toolbar
     */
    public ToolBarStateInfo getToolBarStateInfo() {
        return toolBarStateInfo;
    }

    /**
     * Establece el objeto con informacion de los estados del toolbar
     * @param toolBarStateInfo Objeto con informacion de los estados del toolbar
     */
    public void setToolBarStateInfo(ToolBarStateInfo toolBarStateInfo) {
        this.toolBarStateInfo = toolBarStateInfo;
    }
    
    /**
     * Objetos que monitorean los eventos del toolbar
     */
    private Vector<IToolBarStateListener> toolBarStateListeners;

    /**
     * Devuelve los objetos que monitorean los eventos del toolbar
     * @return
     */
    public Vector<IToolBarStateListener> getToolBarStateListeners() {
        if (toolBarStateListeners == null) {
            toolBarStateListeners = new Vector<IToolBarStateListener>();
        }
        return toolBarStateListeners;
    }

    /**
     * Constructor
     */
    public ToolBarHandler() {
    }

    /**
     * Constructor
     * @param toolBar Toolbar a ser administrado
     * @param tabManager Objeto administrador de tabs
     * @see JTabbedPaneExtended
     */
    public ToolBarHandler(JToolBar toolBar, JTabbedPaneExtended tabManager) {
        this.toolBar = toolBar;
        this.tabManager = tabManager;

    }

    /**
     * Agrega un listener para monitorear los eventos del toolbar
     * @param listener
     */
    public void AddToolBarStateListener(IToolBarStateListener listener) {
        listener.setListSelectionListener(this);
        listener.RegisterSelectionListener();
        getToolBarStateListeners().add(listener);
    }

    /**
     * Remueve un listener
     * @param listener Listener a remover
     */
    public void RemoveToolBarStateListener(IToolBarStateListener listener) {
        getToolBarStateListeners().remove(listener);
    }

    /**
     * Notifica a todos los listeners que ha cambiado el estado del toolbar
     * @param eventArgument Objeto con argumentos de la accion
     */
    public void ToolBarStateChanged(ToolBarEventObject eventArgument) throws Exception {

        List<IToolBarStateListener> listeners;
        synchronized (this) {
            listeners = (Vector<IToolBarStateListener>) getToolBarStateListeners().clone();
        }
        for (IToolBarStateListener listener : listeners) {
            if (((ITabbedWindow) listener).IsActive()) {
                listener.ToolBarStateChanged(eventArgument);
                break;
            }
        }
        if (eventArgument.getHandleToolBarStates()) {
            HandleToolBarStates(eventArgument);
        }
    }

    /**
     * Activa o desactiva los los botones del toolbar segun el estado actual
     * @param curentAction Accion del toolbar
     * @param eventArgument Objeto con argumentos de la accion
     */
    private void HandleToolBarStates(ToolBarAction curentAction, ToolBarEventObject eventArgument) {
        switch (curentAction) {
            case None:
                SetDefaultState();
                break;
            case New:
                SetToolBarItemsState(true, true, false, false, false);
                break;
            case Save:
                if (!eventArgument.getCancelEvent()) {
                    SetToolBarItemsState(true, false, true, true, true, true);
                }
                break;
            case Edit:
                SetToolBarItemsState(true, true, false, true, true, true);
                break;
            case Delete:
                SetDefaultState();
                break;
            case Search:
                if (eventArgument.getSearchDialogResult() == DialogResult.Ok) {
                    SetToolBarItemsState(true, false, true, true, true, true);
                }
                break;
            case RegistryLoaded:
                SetToolBarItemsState(true, false, true, true, true, true);
                break;
            case Back:
                SetDefaultState();
                break;
        }
    }

    /**
     * Activa o desactiva los los botones del toolbar segun el estado actual
     * @param eventArgument Objeto con argumentos de la accion
     */
    private void HandleToolBarStates(ToolBarEventObject eventArgument) {
        if (eventArgument.getCancelEvent()) {
            return;
        }
        HandleToolBarStates(eventArgument.getToolBarState(), eventArgument);
    }

    /**
     * Coloca al toolbar en el estado por default : true false false false true
     */
    public void SetDefaultState() {
        SetToolBarItemsState(true, false, false, false, true, true);
    }

    /**
     * Coloca el estado de activacion de los botones del toolbar
     * @param newButtonState - Estado del boton nuevo
     * @param saveButtonState - Estado del boton grabar
     * @param editButtonState - Estado del boton editar
     * @param deleteButtonState - Estado del boton eliminar
     * @param searchButtonState - Estado del boton buscar
     */
    private void SetToolBarItemsState(boolean newButtonState, boolean saveButtonState,
            boolean editButtonState, boolean deleteButtonState, boolean searchButtonState) {
        toolBar.getComponent(0).setEnabled((this.getToolBarStateInfo().isAlwaysEnabledNew()) ? true : (this.getToolBarStateInfo().getEnableNew()) ? newButtonState : false);
        toolBar.getComponent(1).setEnabled((this.getToolBarStateInfo().isAlwaysEnabledSave()) ? true : (this.getToolBarStateInfo().getEnableSave()) ? saveButtonState : false);
        toolBar.getComponent(2).setEnabled((this.getToolBarStateInfo().isAlwaysEnabledEdit()) ? true : (this.getToolBarStateInfo().getEnableEdit()) ? editButtonState : false);
        toolBar.getComponent(3).setEnabled((this.getToolBarStateInfo().isAlwaysEnabledDelete()) ? true : (this.getToolBarStateInfo().getEnableDelete()) ? deleteButtonState : false);
        toolBar.getComponent(4).setEnabled((this.getToolBarStateInfo().isAlwaysEnabledSearch()) ? true : (this.getToolBarStateInfo().getEnableSearch()) ? searchButtonState : false);
    }

    /**
     * Coloca el estado de activacion de los botones del toolbar
     * @param newButtonState - Estado del boton nuevo
     * @param saveButtonState - Estado del boton grabar
     * @param editButtonState - Estado del boton editar
     * @param deleteButtonState - Estado del boton eliminar
     * @param searchButtonState - Estado del boton buscar
     * @param backButtonState - Estado del boton regresar
     */
    private void SetToolBarItemsState(boolean newButtonState, boolean saveButtonState,
            boolean editButtonState, boolean deleteButtonState, boolean searchButtonState, boolean backButtonState) {
        SetToolBarItemsState(newButtonState, saveButtonState, editButtonState, deleteButtonState, searchButtonState);
        toolBar.getComponent(5).setEnabled((this.getToolBarStateInfo().isAlwaysEnabledBack()) ? true : (this.getToolBarStateInfo().getEnableBack()) ? backButtonState : false);
    }

    /**
     * Metodo implementado de la interfaz ListSelectionListener
     * @param e Objeto con argumentos de la accion
     */
    public void valueChanged(ListSelectionEvent e) {
        try {
            if (internalEvent) {
                return;
            }
            if(tabManager.getCurrentTab().getTabState() == ToolBarAction.Save)
                return;            
            ListSelectionModel selectionModel = (ListSelectionModel) e.getSource();
            int selectedIndex = selectionModel.getMinSelectionIndex();
            boolean cancelSelection = tabManager.HandleTabChanging();
            if (!cancelSelection) {
                ToolBarEventObject event = new ToolBarEventObject(e, ToolBarAction.RegistryLoaded);
                event.setSelectedIndex(selectedIndex);
                if (event.getSelectedIndex() >= 0) {
                    ToolBarStateChanged(event);
                    internalEvent = true;
                    selectionModel.setSelectionInterval(selectedIndex, selectedIndex);
                    internalEvent = false;
                }
            } else {
                internalEvent = true;
                if (e.getLastIndex() == e.getFirstIndex()) {
                    selectionModel.clearSelection();
                } 
                else {
                    int rowIndex = 0;
                    if (selectionModel.getMinSelectionIndex() == e.getLastIndex()) {
                        rowIndex = e.getFirstIndex();
                    } else {
                        rowIndex = e.getLastIndex();
                    }
                    selectionModel.setSelectionInterval(rowIndex, rowIndex);
                }
                internalEvent = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(ToolBarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Actualiza el estado del toolbar
     * @param currentAction Accion con la que se va a colocar el estado del toolbar
     */
    public void RefreshState(ToolBarAction currentAction) {
        HandleToolBarStates(currentAction, new ToolBarEventObject(toolBar, currentAction));
    }
}
