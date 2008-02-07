/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.ui.manager.controls;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sicce.api.info.interfaces.ITabbedWindow;
import sicce.ui.manager.listeners.CloseTabListener;

/**
 *
 * @author gish@c
 */
public class JTabbedPaneExtended extends JTabbedPane{
    
    /**
     * Mantiene la instancia del tab actual
     */
    private ITabbedWindow currentTab;
    
    /** 
     *  Constructor
     */
    public JTabbedPaneExtended()
    {
        super();
        this.addMouseListener(new CloseTabListener(this));
        this.getModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
               DefaultSingleSelectionModel selectionModel = (DefaultSingleSelectionModel)e.getSource();
               int tabIndex = selectionModel.getSelectedIndex();
               setCurrentTab(tabIndex);
            }
        });
        
    }
    
    /**
     * Contiene la lista de tabs que aloja el control
     */
    private List<ITabbedWindow> tabs;
    public List<ITabbedWindow> getTabs() {
        if(tabs == null)
        {
            tabs = new ArrayList<ITabbedWindow>();
        }
        return tabs;
    }

   
    /**
     * Agrega un tab al panel
     * @param tab
     */
    public void AddTab(JTabExtended tab)
    {
        tab.setParentPane(this);
        getTabs().add(tab);
        this.addTab(tab.getTitle(), tab);
        
    }
    
    /**
     * Remueve un tab del panel
     * @param index
     */
    public void RemoveTab(int index)
    {
        //JTabExtended tab = (JTabExtended)this.getTabComponentAt(index);        
        getTabs().remove(index);
        this.remove(index);
    }
    
    /**
     * Devuelve el tab actualmente visible
     * @return
     */
    public ITabbedWindow getCurrentTab()
    {
        return currentTab;
    }
    
    /**
     * Setea el tab actual
     * @param currentTab
     */
    public void setCurrentTab(ITabbedWindow currentTab)
    {
        this.currentTab = currentTab;
        for(int i=0; i<=this.getComponentCount()-1;i++)
        {
            if(this.getComponent(i).equals(this.currentTab))
            {
                this.setSelectedIndex(i);
            }
        }      
    }
    
    /**
     * Setea el tab actual
     * @param index
     */
    public void setCurrentTab(int index)
    {
        if(getTabs().size() > index && index >= 0)
        {
            this.setSelectedIndex(index);
            this.currentTab = getTabs().get(index);
        }
    }
    
    
}





