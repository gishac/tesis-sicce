/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;

/**
 *
 * @author gish@c
 */
public class JTabExtended extends JPanel implements ITabbedWindow, IToolBarStateListener{

    private String title;
    private FocusListener focusEventHandler;
    private JTabbedPaneExtended parentPane;

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
    
    /**
     * Constructor
     */
    public JTabExtended()
    {
        super();
        this.addFocusListener(focusEventHandler = new FocusListener() {

            public void focusGained(FocusEvent e) {
                parentPane.setCurrentTab((ITabbedWindow)e.getComponent());
            }

            public void focusLost(FocusEvent e) {
                
            }

        });
    }
    
    
    public JTabExtended(String title)
    {
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
       
    }
    
    public void Save() {
       
    }

    public void Search() {
       
    }

    public void Delete() {
        
    }

    public void Edit() {
        
    }
    
    public void Back() {
        
    }
    
    public boolean IsActive()
    {
        return getParentPane().getCurrentTab().equals(this);
    }

    /**
     * Evento generado cuando cambia el estado del toolbar
     * @param event
     */
    public void ToolBarStateChanged(ToolBarEventObject event) {
        ToolBarAction toolbarAction = event.getToolBarState();       
    }
    
    
}
