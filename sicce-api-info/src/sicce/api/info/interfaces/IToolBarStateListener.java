/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.EventListener;
import javax.swing.event.ListSelectionListener;
import sicce.api.info.eventobjects.ToolBarEventObject;

/**
 * Define los metodos a ser implementados por las clases que representen a los objetos que monitorean
 * los eventos del toolbar
 * @author gish@c 
 */
public interface IToolBarStateListener  extends EventListener  {
    
    /**
     * Evento ejecutado cuando cambia el estado del toolbar
     * @param event Parametros del cambio de estado
     * @throws java.lang.Exception
     * @see ToolBarEventObject
     */
    public void ToolBarStateChanged(ToolBarEventObject event) throws Exception;
    
    /**
     * Establece el toolbar que sera monitoreado
     * @param selectionListener Toolbar que sera monitoreado
     */
    void setListSelectionListener(ListSelectionListener selectionListener);
    
    /**
     * Registra el objeto para monitorear la seleccion de items en la tabla del tab
     */
    void RegisterSelectionListener();
}
