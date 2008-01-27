/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

import java.util.EventListener;
import sicce.api.info.eventobjects.ToolBarEventObject;

/**
 * 
 * @author gish@c
 * 
 * Interfaz para poder implementar el monitoreo de eventos del toolbar
 */
public interface IToolBarStateListener  extends EventListener  {
    public void ToolBarStateChanged(ToolBarEventObject event);
}
