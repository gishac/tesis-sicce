/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.eventobjects;

import java.util.EventObject;
import sicce.api.info.ConstantsProvider.DialogResult;
import sicce.api.info.ConstantsProvider.ToolBarAction;

/**
 * Representacion de parametro de eventos del toolbar
 * @author gish@c
 */
public class ToolBarEventObject extends EventObject {

    /**
     * Constructor
     * @param source Toolbar que origina el evento
     * @param state Estado actual del toolbar
     */
    public ToolBarEventObject(Object source, ToolBarAction state)    
    {
        super(source);
        this.handleToolBarStates = true;
        this.toolBarState = state;
    }
    
    /**
     * Accion realizada por el toolbar
     */
    private ToolBarAction toolBarState;
    
    /**
     * Indica si el evento del toolbar es cancelado
     */
    private boolean cancelEvent;
    
    /**
     * Ultima seleccion en la tabla mostrada en el tab
     */
    private int selectedIndex;
    
    /**
     * Resultado del dialogo de busqueda
     * @see DialogResult
     */
    private DialogResult searchDialogResult;
    
    /**
     * Indica si se debe manejar los estados del toolbar
     */
    private boolean handleToolBarStates;

    /**
     * Devuelve si se debe o no manejar los estados del toolbar
     * @return <strong>True</strong>, si se deben manejar los estados del toolbar, <strong>False</strong>, si no se deben manejar los estados del toolbar
     */
    public boolean getHandleToolBarStates() {
        return handleToolBarStates;
    }

    /**
     * Establece si se deben manejar o no los estados del toolbar
     * @param handleToolBarStates <strong>True</strong>, si se deben manejar los estados del toolbar, <strong>False</strong>, si no se deben manejar los estados del toolbar
     */
    public void setHandleToolBarStates(boolean handleToolBarStates) {
        this.handleToolBarStates = handleToolBarStates;
    }

    /**
     * Devuelve la ultima seleccion en la tabla mostrada en el tab
     * @return
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Establece la ultima seleccion en la tabla mostrada en el tab
     * @param selectedIndex
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    /**
     * Devuelve si el evento del toolbar fue cancelado
     * @return <strong> True </strong>, si fue cancelado el evento; <strong> False </strong>, si no fue cancelado el evento del toolbar
     */
    public boolean getCancelEvent() {
        return cancelEvent;
    }

    /**
     * Establece si el evento del toolbar debe ser cancelado o no
     * @param cancelEvent <strong> True </strong>, si fue cancelado el evento; <strong> False </strong>, si no fue cancelado el evento del toolbar
     */
    public void setCancelEvent(boolean cancelEvent) {
        this.cancelEvent = cancelEvent;
    }

    /**
     * Devuelve el estado del toolbar
     * @return Estado del toolbar
     * @see ToolBarAction
     */
    public ToolBarAction getToolBarState() {
        return toolBarState;
    }
    
    /**
     * Devuelve el resultado del dialogo de busqueda
     * @return Resultado del dialogo de busqueda
     * @see DialogResult
     */
    public DialogResult getSearchDialogResult() {
        return searchDialogResult;
    }

    /**
     * Establece el resultado del dialogo de busqueda
     * @param searchDialogResult Resultado del dialogo de busqueda
     */
    public void setSearchDialogResult(DialogResult searchDialogResult) {
        this.searchDialogResult = searchDialogResult;
    }
}
