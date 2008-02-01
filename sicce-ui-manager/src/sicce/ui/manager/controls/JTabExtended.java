/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;


import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import sicce.api.info.ConstantsProvider.ToolBarAction;
import sicce.api.info.eventobjects.ToolBarEventObject;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.api.info.interfaces.IToolBarStateListener;
import sicce.api.util.ComponentUtil;

/**
 *
 * @author gish@c
 */
public class JTabExtended extends JPanel implements ITabbedWindow, IToolBarStateListener{

    private String title;
    private FocusListener focusEventHandler;
    private JTabbedPaneExtended parentPane;
    private boolean objectLoaded;
    private List<Component> controlsToClear;

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
    
    public boolean IsObjectLoaded()
    {
        return objectLoaded;
    }
    
    public List<Component> getControlsToClear()
    {
        if(controlsToClear == null)
        {
            controlsToClear = new ArrayList<Component>();
        }
        return controlsToClear;
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
       objectLoaded = false;
       ComponentUtil.Clear(getControlsToClear());
    }
    
    public void Save() throws Exception {
       
    }

    public void Search() {
       
    }

    public void Delete() throws Exception {
        
    }

    public void Edit() {
        objectLoaded = true;
    }
    
    public void Back() {
        
    }
    
    public void Update() throws Exception{
    
    }
    
    /**
     * Indica si el tab es el que se encuentra activo
     * @return
     */
    public boolean IsActive()
    {
        if(getParentPane() == null)
            return false;
        return getParentPane().getCurrentTab().equals(this);
    }

    /**
     * Evento generado cuando cambia el estado del toolbar
     * @param event
     */
    public void ToolBarStateChanged(ToolBarEventObject event) throws Exception {
        ToolBarAction toolbarAction = event.getToolBarState();
        switch(toolbarAction)
        {
            case New:
                New();
                break;
            case Edit:
                Edit();
                break;
            case Save:
                Save();
                break;
            case Delete:
                Delete();
                break;
            case Search:
                Search();
        }
    }
    
    
}
