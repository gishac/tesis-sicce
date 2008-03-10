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
import org.jdesktop.application.ResourceMap;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.ToolBarStateInfo;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;

/**
 * Clase que administra todos los eventos generados por el toolbar y notifica los estados
 * generados por las acciones del toolbar
 * @author gish@c
 */
public class ToolBarHandler implements ListSelectionListener {

    JToolBar toolBar;
    private ToolBarStateInfo toolBarStateInfo;

    public ToolBarStateInfo getToolBarStateInfo() {
        return toolBarStateInfo;
    }

    public void setToolBarStateInfo(ToolBarStateInfo toolBarStateInfo) {
        this.toolBarStateInfo = toolBarStateInfo;
    }
    /**
     * Lista de objetos que monitorea los eventos del toolbar
     */
    private Vector<IToolBarStateListener> toolBarStateListeners;

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
     * @param toolBar
     */
    public ToolBarHandler(JToolBar toolBar) {
        this.toolBar = toolBar;
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
     * @param listener
     */
    public void RemoveToolBarStateListener(IToolBarStateListener listener) {
        getToolBarStateListeners().remove(listener);
    }

    /**
     * Notifica a todos los listeners que ha cambiado el estado del toolbar
     * @param eventArgument
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
     * @param eventArgument
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
                if(!eventArgument.getCancelEvent())
                    SetToolBarItemsState(true, false, true, true, true,true);
                break;
            case Edit:
                SetToolBarItemsState(true, true, false, true, true,true);
                break;
            case Delete:
                SetDefaultState();
                break;
            case Search:
                if (eventArgument.getSearchDialogResult() == DialogResult.Ok) {
                    SetToolBarItemsState(true, false, true, true, true,true);
                }
                break;
            case RegistryLoaded:
                SetToolBarItemsState(true, false, true, true, true,true);
                break;
            case Back:
                SetDefaultState();
                break;
        }
    }

    /**
     * Activa o desactiva los los botones del toolbar segun el estado actual
     * @param eventArgument
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
     * @param e
     */
    public void valueChanged(ListSelectionEvent e) {
        try {
            ListSelectionModel selectionModel = (ListSelectionModel) e.getSource();
            ToolBarEventObject event = new ToolBarEventObject(e, ToolBarAction.RegistryLoaded);
            event.setSelectedIndex(selectionModel.getMinSelectionIndex());
            if (event.getSelectedIndex() >= 0) {
                ToolBarStateChanged(event);
            }
        } catch (Exception ex) {
            Logger.getLogger(ToolBarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param currentAction
     */
    public void RefreshState(ToolBarAction currentAction) {
        HandleToolBarStates(currentAction, new ToolBarEventObject(toolBar, currentAction));
    }
}
