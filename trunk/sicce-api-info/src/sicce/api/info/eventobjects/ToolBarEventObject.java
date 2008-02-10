/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.eventobjects;

import java.util.EventObject;
import sicce.api.info.ConstantsProvider.ToolBarAction;

/**
 *
 * @author gish@c
 */
public class ToolBarEventObject extends EventObject {

    private ToolBarAction toolBarState;
    private boolean cancelEvent;
    private int selectedIndex;

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public boolean getCancelEvent() {
        return cancelEvent;
    }

    public void setCancelEvent(boolean cancelEvent) {
        this.cancelEvent = cancelEvent;
    }

    public ToolBarAction getToolBarState() {
        return toolBarState;
    }
    
    public ToolBarEventObject(Object source, ToolBarAction state)    
    {
        super(source);
        this.toolBarState = state;
    }
}
