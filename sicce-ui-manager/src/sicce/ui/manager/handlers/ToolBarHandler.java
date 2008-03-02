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
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;

/**
 * Clase que administra todos los eventos generados por el toolbar y notifica los estados
 * generados por las acciones del toolbar
 * @author gish@c
 */
public class ToolBarHandler implements ListSelectionListener{
    
    JToolBar toolBar;
    
    /**
     * Lista de objetos que monitorea los eventos del toolbar
     */
    private Vector<IToolBarStateListener> toolBarStateListeners;
    public Vector<IToolBarStateListener> getToolBarStateListeners() {
        if(toolBarStateListeners == null)
        {
            toolBarStateListeners = new Vector<IToolBarStateListener>();
        }
        return toolBarStateListeners;
    }
    
    /**
     * Constructor
     */
    public ToolBarHandler()
    {}
    
    /**
     * Constructor
     * @param toolBar
     */
    public ToolBarHandler(JToolBar toolBar)    
    {
        this.toolBar = toolBar;
    }
    
    /**
     * Agrega un listener para monitorear los eventos del toolbar
     * @param listener
     */
    public void AddToolBarStateListener(IToolBarStateListener listener)
    {
        listener.setListSelectionListener(this);
        listener.RegisterSelectionListener();
        getToolBarStateListeners().add(listener);
    }
    
    /**
     * Remueve un listener
     * @param listener
     */
    public void RemoveToolBarStateListener(IToolBarStateListener listener)
    {
        getToolBarStateListeners().remove(listener);
    }
    
    /**
     * Notifica a todos los listeners que ha cambiado el estado del toolbar
     * @param eventArgument
     */
    public void ToolBarStateChanged(ToolBarEventObject eventArgument) throws Exception
    {
        
        List<IToolBarStateListener> listeners;
        synchronized(this)
        {
            listeners = (Vector<IToolBarStateListener>) getToolBarStateListeners().clone();
        }
        for(IToolBarStateListener listener : listeners)
        {
            if(((ITabbedWindow) listener).IsActive())
            {
                listener.ToolBarStateChanged(eventArgument);            
            }
        }
        HandleToolBarStates(eventArgument);
    }
    
    /**
     * Activa o desactiva los los botones del toolbar segun el estado actual
     * @param eventArgument
     */
    private void HandleToolBarStates(ToolBarEventObject eventArgument)
    {
        if(eventArgument.getCancelEvent())
            return;
        switch(eventArgument.getToolBarState())
        {
            case None:
                break;
            case New:
                SetToolBarItemsState(true, true, false, false, false);
                    break;
            case Save:
                SetDefaultState();
                break;
            case Edit:
                SetToolBarItemsState(true, true, false, true, true);
                break;
            case Delete:
                SetDefaultState();
                break;
            case Search:
                if(eventArgument.getSearchDialogResult() == DialogResult.Ok)
                    SetToolBarItemsState(true, false, true, true, true);
                break;
            case RegistryLoaded:
                SetToolBarItemsState(true, false, true, true, true);
                break;
            case Back:
                SetDefaultState();
                break;                
        }
    }
    
    /**
     * Coloca al toolbar en el estado por default : true false false false true
     */
    public void SetDefaultState()
    {
        SetToolBarItemsState(true, false, false, false, true,true);
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
            boolean editButtonState, boolean deleteButtonState, boolean searchButtonState)
    {
        toolBar.getComponent(0).setEnabled(newButtonState);
        toolBar.getComponent(1).setEnabled(saveButtonState);
        toolBar.getComponent(2).setEnabled(editButtonState);
        toolBar.getComponent(3).setEnabled(deleteButtonState);
        toolBar.getComponent(4).setEnabled(searchButtonState);
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
            boolean editButtonState, boolean deleteButtonState, boolean searchButtonState, boolean backButtonState)
    {
        SetToolBarItemsState(newButtonState,saveButtonState,editButtonState,deleteButtonState,searchButtonState);
        toolBar.getComponent(5).setEnabled(searchButtonState);
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
            if(event.getSelectedIndex() >= 0)
                ToolBarStateChanged(event);
        } catch (Exception ex) {
            Logger.getLogger(ToolBarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
}
