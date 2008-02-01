/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.handlers;

import java.util.List;
import java.util.Vector;
import javax.swing.JToolBar;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;

/**
 *
 * @author gish@c
 */
public class ToolBarHandler{
    
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
        HandleToolBarStates(eventArgument);
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
    }
    
    /**
     * Activa o desactiva los los botones del toolbar segun el estado actual
     * @param eventArgument
     */
    private void HandleToolBarStates(ToolBarEventObject eventArgument)
    {
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
        SetToolBarItemsState(true, false, false, false, true);
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
       
}
