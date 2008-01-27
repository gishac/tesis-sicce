/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.handlers;

import java.util.List;
import java.util.Vector;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import sicce.api.info.ConstantsProvider.ToolBarAction;
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
    public void ToolBarStateChanged(ToolBarEventObject eventArgument)
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
    
    private void HandleToolBarStates(ToolBarEventObject eventArgument)
    {
        switch(eventArgument.getToolBarState())
        {
            case None:
                break;
            case New:
                    toolBar.getComponent(0).setEnabled(true);
                    toolBar.getComponent(1).setEnabled(true);
                    toolBar.getComponent(2).setEnabled(false);
                    toolBar.getComponent(3).setEnabled(false);
                    toolBar.getComponent(4).setEnabled(false);
                    break;
            case Save:
                    SetDefaultState();
                    break;
            case Edit:
                    toolBar.getComponent(0).setEnabled(true);
                    toolBar.getComponent(1).setEnabled(true);
                    toolBar.getComponent(2).setEnabled(false);
                    toolBar.getComponent(3).setEnabled(true);
                    toolBar.getComponent(4).setEnabled(true);
                    break;
            case Delete:
                    SetDefaultState();
                    break;
            case Search:
                break;
            case RegistryLoaded:
                break;
            case Back:
                    SetDefaultState();
                    break;                
        }
    }
    
    /**
     * Coloca al toolbar en el estado por default
     */
    private void SetDefaultState()
    {
        toolBar.getComponent(0).setEnabled(true);
        toolBar.getComponent(1).setEnabled(false);
        toolBar.getComponent(2).setEnabled(false);
        toolBar.getComponent(3).setEnabled(false);
        toolBar.getComponent(4).setEnabled(true);
    }
       
}
